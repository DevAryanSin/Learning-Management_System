package hellofx.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.collections.*;
import java.net.URL;
// import java.sql.*;
import java.util.ResourceBundle;
import javafx.scene.layout.AnchorPane;

public class TeacherFinalReportController implements Initializable {
    @FXML private ComboBox<String> courseCombo;
    @FXML private ComboBox<String> semCombo;
    @FXML private TableView<Report> reportTable;
    @FXML private TableColumn<Report, String> rollColumn;
    @FXML private TableColumn<Report, String> nameColumn;
    @FXML private TableColumn<Report, Integer> marksColumn;
    @FXML private TableColumn<Report, String> gradeColumn;

    private ObservableList<Report> reports = FXCollections.observableArrayList();

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
        rollColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getRoll()));
        nameColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getName()));
        marksColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getMarks()).asObject());
        gradeColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getGrade()));
        reportTable.setItems(reports);
        // Load courses, semesters, and reports as needed
    }

    @FXML
    private void goBack() {
        try {
            Parent view = FXMLLoader.load(getClass().getResource("/hellofx/fxml/Teachers.fxml"));
            AnchorPane root = (AnchorPane) reportTable.getScene().getRoot();
            root.getChildren().setAll(view);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}