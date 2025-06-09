package hellofx.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import hellofx.utils.SampleData;

public class TeacherAttendanceController implements Initializable {
    @FXML private DatePicker datePicker;
    @FXML private ComboBox<String> courseCombo;
    @FXML private TableView<StudentAttendance> attendanceTable;
    @FXML private TableColumn<StudentAttendance, String> rollColumn;
    @FXML private TableColumn<StudentAttendance, String> nameColumn;
    @FXML private TableColumn<StudentAttendance, String> presentColumn;

    private ObservableList<StudentAttendance> students = FXCollections.observableArrayList(
        new StudentAttendance("2023001", "Aarav Patel", "Present"),
        new StudentAttendance("2023002", "Aditi Sharma", "Present"),
        new StudentAttendance("2023003", "Arjun Singh", "Absent"),
        new StudentAttendance("2023004", "Ananya Gupta", "Present"),
        new StudentAttendance("2023005", "Dev Kumar", "Present"),
        new StudentAttendance("2023006", "Diya Verma", "Absent"),
        new StudentAttendance("2023007", "Ishaan Reddy", "Present"),
        new StudentAttendance("2023008", "Kavya Mehta", "Present")
    );

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Setup DatePicker
        datePicker.setValue(LocalDate.now());

        // Setup ComboBox
        courseCombo.getItems().addAll("Computer Science", "Mathematics", "Physics");

        // Setup TableView
        rollColumn.setCellValueFactory(new PropertyValueFactory<>("roll"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        presentColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        
        attendanceTable.setItems(students);
    }

    @FXML
    private void goBack() {
        try {
            Parent view = FXMLLoader.load(getClass().getResource("/hellofx/fxml/Teachers.fxml"));
            attendanceTable.getScene().setRoot(view);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
}