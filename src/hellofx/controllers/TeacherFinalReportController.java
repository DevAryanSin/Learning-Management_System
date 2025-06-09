package hellofx.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.layout.VBox;

public class TeacherFinalReportController implements Initializable {
    @FXML private ComboBox<String> courseCombo;
    @FXML private ComboBox<String> semCombo;
    @FXML private TableView<Report> reportTable;
    @FXML private TableColumn<Report, String> rollColumn;
    @FXML private TableColumn<Report, String> nameColumn;
    @FXML private TableColumn<Report, Integer> marksColumn;
    @FXML private TableColumn<Report, String> gradeColumn;

    private ObservableList<Report> reports = FXCollections.observableArrayList(
        new Report("101", "John Doe", 85, "A"),
        new Report("102", "Jane Smith", 92, "A+"),
        new Report("103", "Bob Wilson", 78, "B+")
    );

    public static class Report {
        private final String roll, name, grade;
        private final int marks;
        public Report(String roll, String name, int marks, String grade) {
            this.roll = roll; this.name = name; this.marks = marks; this.grade = grade;
        }
        public String getRoll() { return roll; }
        public String getName() { return name; }
        public int getMarks() { return marks; }
        public String getGrade() { return grade; }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Setup ComboBoxes with default data
        courseCombo.getItems().addAll("Computer Science", "Mathematics", "Physics");
        courseCombo.setValue("Computer Science");
        
        semCombo.getItems().addAll("Semester 1", "Semester 2", "Semester 3");
        semCombo.setValue("Semester 1");

        // Setup TableView columns
        rollColumn.setCellValueFactory(new PropertyValueFactory<>("roll"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        marksColumn.setCellValueFactory(new PropertyValueFactory<>("marks"));
        gradeColumn.setCellValueFactory(new PropertyValueFactory<>("grade"));
        
        reportTable.setItems(reports);

        // Add change listeners
        courseCombo.setOnAction(e -> updateReport());
        semCombo.setOnAction(e -> updateReport());
    }

    private void updateReport() {
        String course = courseCombo.getValue();
        String semester = semCombo.getValue();
        // In real app, you would fetch data based on course and semester
        System.out.println("Updating report for " + course + " - " + semester);
    }

    @FXML
    private void goBack() {
        try {
            Parent view = FXMLLoader.load(getClass().getResource("/hellofx/fxml/Teachers.fxml"));
            reportTable.getScene().getRoot().setStyle("-fx-background-color: #F8F9FA;");
            ((VBox) reportTable.getScene().getRoot()).getChildren().setAll(view);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void exportReport() {
        // Add export functionality here
        System.out.println("Exporting report...");
    }
}