package hellofx.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.collections.*;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class TeacherAttendanceController implements Initializable {
    @FXML private ComboBox<String> courseCombo;
    @FXML private DatePicker datePicker;
    @FXML private TableView<StudentAttendance> attendanceTable;
    @FXML private TableColumn<StudentAttendance, String> rollColumn;
    @FXML private TableColumn<StudentAttendance, String> nameColumn;
    @FXML private TableColumn<StudentAttendance, String> statusColumn;
    @FXML private Button saveButton;

    private ObservableList<StudentAttendance> students = FXCollections.observableArrayList();

    public static class StudentAttendance {
        private final String roll, name;
        private String status;
        public StudentAttendance(String roll, String name, String status) {
            this.roll = roll; this.name = name; this.status = status;
        }
        public String getRoll() { return roll; }
        public String getName() { return name; }
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rollColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getRoll()));
        nameColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getName()));
        statusColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getStatus()));
        attendanceTable.setItems(students);
        // Load courses and students as needed
    }

    // Implement methods to load students, save attendance, etc.

    @FXML
    private void goBack() {
        try {
            Parent view = FXMLLoader.load(getClass().getResource("/hellofx/fxml/Teachers.fxml"));
            AnchorPane root = (AnchorPane) attendanceTable.getScene().getRoot();
            root.getChildren().setAll(view);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}