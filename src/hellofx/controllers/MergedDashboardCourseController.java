package hellofx.controllers;

// import hellofx.model.StudentsModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class MergedDashboardCourseController implements Initializable {

    // Courses data
    public static class Course {
        private final String title;
        private final String info;

        public Course(String title, String info) {
            this.title = title;
            this.info = info;
        }
        public String getTitle() { return title; }
        public String getInfo() { return info; }
    }

    private final List<Course> courses = Arrays.asList(
        new Course("Mathematics", "Algebra and Calculus"),
        new Course("Physics", "Mechanics and Thermodynamics"),
        new Course("Chemistry", "Organic and Inorganic Chemistry"),
        new Course("Biology", "Genetics and Evolution"),
        new Course("Computer Science", "Programming and Data Structures")
    );

    // Subject-wise performance data model
    public static class SubjectPerformance {
        private final String subject;
        private final int marks;

        public SubjectPerformance(String subject, int marks) {
            this.subject = subject;
            this.marks = marks;
        }
        public String getSubject() { return subject; }
        public int getMarks() { return marks; }
    }

    // ObservableList for subject-wise performance
    private final ObservableList<SubjectPerformance> subjectPerformanceList = FXCollections.observableArrayList(
        new SubjectPerformance("Mathematics", 88),
        new SubjectPerformance("Physics", 76),
        new SubjectPerformance("Chemistry", 82),
        new SubjectPerformance("Biology", 91),
        new SubjectPerformance("Computer Science", 95)
    );

    // TableView and columns for subject-wise performance
    @FXML
    private TableView<SubjectPerformance> subjectPerformanceTable;
    @FXML
    private TableColumn<SubjectPerformance, String> subjectColumn;
    @FXML
    private TableColumn<SubjectPerformance, Integer> marksColumn;

    // PieChart
    @FXML
    private PieChart pieChart;

    // Course viewer
    @FXML
    private GridPane courseGrid;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadChart();
        loadSubjectPerformance();
        loadCourses();
    }

    private void loadChart() {
        PieChart.Data slice1 = new PieChart.Data("Classes", 213);
        PieChart.Data slice2 = new PieChart.Data("Attendance", 67);
        PieChart.Data slice3 = new PieChart.Data("Teachers", 36);

        pieChart.getData().clear();
        pieChart.getData().addAll(slice1, slice2, slice3);

        // Animate PieChart slices
        for (PieChart.Data data : pieChart.getData()) {
            data.getNode().setScaleX(0);
            data.getNode().setScaleY(0);
            javafx.animation.ScaleTransition st = new javafx.animation.ScaleTransition(javafx.util.Duration.millis(800), data.getNode());
            st.setToX(1);
            st.setToY(1);
            st.setCycleCount(1);
            st.play();
        }
    }

    private void loadSubjectPerformance() {
        subjectColumn.setCellValueFactory(new PropertyValueFactory<>("subject"));
        marksColumn.setCellValueFactory(new PropertyValueFactory<>("marks"));
        subjectPerformanceTable.setItems(subjectPerformanceList);

        // Ensure text is visible on dark background
        subjectColumn.setCellFactory(column -> new TableCell<SubjectPerformance, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : item);
                setStyle("-fx-text-fill: #F7FAFC; -fx-font-size: 15px; -fx-background-color: #181A20;");
            }
        });
        marksColumn.setCellFactory(column -> new TableCell<SubjectPerformance, Integer>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : String.valueOf(item));
                setStyle("-fx-text-fill: #F7FAFC; -fx-font-size: 15px; -fx-background-color: #181A20;");
            }
        });
    }

    private void loadCourses() {
        if (courseGrid == null) return; // In case the FXML doesn't include the grid
        courseGrid.getChildren().clear();
        int col = 0, row = 0;
        for (Course course : courses) {
            VBox vbox = new VBox(10);
            vbox.setStyle("-fx-border-color: #00337C; -fx-padding: 10;");
            Label titleLabel = new Label(course.getTitle());
            titleLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: #F7FAFC;");
            Button enrollBtn = new Button("Enroll");
            Button infoBtn = new Button("View Info");

            enrollBtn.setStyle("-fx-background-color: #00337C; -fx-text-fill: #F7FAFC; -fx-background-radius: 6;");
            infoBtn.setStyle("-fx-background-color: #353945; -fx-text-fill: #F7FAFC; -fx-background-radius: 6;");

            enrollBtn.setOnAction(e -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Enrolled in " + course.getTitle() + "!");
                alert.showAndWait();
            });
            infoBtn.setOnAction(e -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, course.getInfo());
                alert.setHeaderText(course.getTitle() + " Info");
                alert.showAndWait();
            });

            vbox.getChildren().addAll(titleLabel, enrollBtn, infoBtn);
            vbox.setStyle("-fx-background-color: #181A20; -fx-border-color: #00337C; -fx-border-radius: 8; -fx-background-radius: 8; -fx-padding: 16;");
            courseGrid.add(vbox, col, row);

            col++;
            if (col > 1) { col = 0; row++; }
        }
    }
}