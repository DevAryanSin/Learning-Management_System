package hellofx.controllers;

// import hellofx.model.StudentsModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

import java.io.File;
import java.net.URL;
import java.sql.*;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.time.LocalDate;
import java.time.Period;

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

    // Sidebar
    @FXML
    private VBox sidebar;
    private boolean isSidebarCollapsed = false;

    // Add new FXML injections for student details
    @FXML private ImageView studentPhoto;
    @FXML private Label studentName;
    @FXML private Label studentEmail;
    @FXML private Label studentId;
    @FXML private Label studentAge;
    @FXML private Label studentPhone;
    @FXML private Label studentCourse;
    @FXML private VBox profileCard;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Show dashboard by default
        showDashboard();
        loadStudentDetails();
        loadChart();
        loadSubjectPerformance();
        loadCourses();
    }

    private void loadStudentDetails() {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM students WHERE student_id = ?")) {
            
            // TODO: Replace 1 with actual logged-in student ID
            stmt.setInt(1, 1);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    studentName.setText(rs.getString("first_name") + " " + rs.getString("last_name"));
                    studentEmail.setText(rs.getString("email"));
                    studentId.setText(rs.getString("student_id"));
                    studentAge.setText(String.valueOf(calculateAge(rs.getDate("date_of_birth"))));
                    studentPhone.setText(rs.getString("phone_no"));
                    studentCourse.setText(rs.getString("course"));
                    
                    // Load student photo if exists
                    String photoPath = rs.getString("photo_path");
                    if (photoPath != null && !photoPath.isEmpty()) {
                        try {
                            Image img = new Image(new File(photoPath).toURI().toString());
                            studentPhoto.setImage(img);
                        } catch (Exception e) {
                            // Load default image if photo loading fails
                            Image defaultImg = new Image(getClass().getResourceAsStream("/hellofx/images/default_profile.png"));
                            studentPhoto.setImage(defaultImg);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            e.printStackTrace();
            
            // Show error dialog
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Database Error");
            alert.setHeaderText("Failed to load student details");
            alert.setContentText("Please check your database connection and try again.");
            alert.showAndWait();
            
            // Load placeholder data
            loadPlaceholderData();
        }
    }

    private void loadPlaceholderData() {
        studentName.setText("Not Available");
        studentEmail.setText("N/A");
        studentId.setText("N/A");
        studentAge.setText("N/A");
        studentPhone.setText("N/A");
        studentCourse.setText("N/A");
        
        // Load default image
        try {
            Image img = new Image(getClass().getResourceAsStream("/hellofx/images/default_profile.png"));
            studentPhoto.setImage(img);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int calculateAge(Date dob) {
        if (dob == null) return 0;
        return Period.between(dob.toLocalDate(), LocalDate.now()).getYears();
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
        }
    }

    // Hide dashboard content and show courses
    subjectPerformanceTable.setVisible(false);
    pieChart.setVisible(false);
    courseGrid.setVisible(true);
    }FXML
    private void toggleSidebar() {
    @FXMLouble expandedWidth = 250;
    private void showProgress() {0;
        // Implement progress view logic here
    }   TranslateTransition transition = new TranslateTransition(Duration.millis(200), sidebar);










}    }        courseGrid.setVisible(false);        pieChart.setVisible(true);        subjectPerformanceTable.setVisible(true);        profileCard.setVisible(true);        // Show student details and performance    private void showDashboard() {    @FXML        if (isSidebarCollapsed) {
            sidebar.setPrefWidth(expandedWidth);
            transition.setToX(0);
        } else {
            sidebar.setPrefWidth(collapsedWidth);
            transition.setToX(-180); // 250 - 70 = 180 (difference between expanded and collapsed)
        }

        transition.play();
        isSidebarCollapsed = !isSidebarCollapsed;
    }

    @FXML
    private void showDashboard() {
        // Show student details and performance
        profileCard.setVisible(true);
        subjectPerformanceTable.setVisible(true);
        pieChart.setVisible(true);
        courseGrid.setVisible(false);
    }

    @FXML
    private void showCourses() {
        // Hide dashboard content and show courses
        subjectPerformanceTable.setVisible(false);
        pieChart.setVisible(false);
        courseGrid.setVisible(true);
    }

    @FXML
    private void showProgress() {
        // Implement progress view logic here
    }
}