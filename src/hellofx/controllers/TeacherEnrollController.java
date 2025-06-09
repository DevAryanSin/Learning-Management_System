package hellofx.controllers;

import javafx.stage.Stage;
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
import javafx.stage.Stage;
import javafx.scene.Scene;

public class TeacherEnrollController implements Initializable {
    @FXML private TextField nameField;
    @FXML private TextField emailField;
    @FXML private DatePicker dobPicker;
    @FXML private ComboBox<String> courseCombo;
    @FXML private TableView<Student> studentTable;
    @FXML private TableColumn<Student, String> nameColumn;
    @FXML private TableColumn<Student, String> emailColumn;
    @FXML private TableColumn<Student, String> courseColumn;

    private ObservableList<Student> students = FXCollections.observableArrayList();

    public static class Student {
        private final String name, email, course;
        
        public Student(String name, String email, String course) {
            this.name = name;
            this.email = email;
            this.course = course;
        }

        public String getName() { return name; }
        public String getEmail() { return email; }
        public String getCourse() { return course; }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Setup ComboBox
        courseCombo.getItems().addAll("Computer Science", "Mathematics", "Physics");
        
        // Setup TableView
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        courseColumn.setCellValueFactory(new PropertyValueFactory<>("course"));
        studentTable.setItems(students);

        // Add sample data
        students.addAll(
            new Student("Aarav Patel", "aarav.patel@example.com", "Computer Science"),
            new Student("Aditi Sharma", "aditi.sharma@example.com", "Mathematics"),
            new Student("Arjun Singh", "arjun.singh@example.com", "Physics"),
            new Student("Ananya Gupta", "ananya.gupta@example.com", "Computer Science"),
            new Student("Dev Kumar", "dev.kumar@example.com", "Mathematics"),
            new Student("Diya Verma", "diya.verma@example.com", "Physics")
        );
    }

    @FXML
    private void enrollStudent() {
        if (isValidInput()) {
            students.add(new Student(
                nameField.getText(),
                emailField.getText(),
                courseCombo.getValue()
            ));
            clearForm();
        }
    }

    @FXML
    private void clearForm() {
        nameField.clear();
        emailField.clear();
        dobPicker.setValue(null);
        courseCombo.setValue(null);
    }

    private boolean isValidInput() {
        boolean isValid = true;
        StringBuilder errorMessage = new StringBuilder();

        if (nameField.getText().trim().isEmpty()) {
            errorMessage.append("Name is required\n");
            isValid = false;
        }
        if (emailField.getText().trim().isEmpty()) {
            errorMessage.append("Email is required\n");
            isValid = false;
        }
        if (dobPicker.getValue() == null) {
            errorMessage.append("Date of Birth is required\n");
            isValid = false;
        }
        if (courseCombo.getValue() == null) {
            errorMessage.append("Course selection is required\n");
            isValid = false;
        }

        if (!isValid) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Validation Error");
            alert.setContentText(errorMessage.toString());
            alert.showAndWait();
        }

        return isValid;
    }

    @FXML
    private void goBack() {
        try {
            Parent view = FXMLLoader.load(getClass().getResource("/hellofx/fxml/Teachers.fxml"));
            // Get the current stage
            Stage stage = (Stage) studentTable.getScene().getWindow();
            // Create new scene with the Teachers view
            Scene scene = new Scene(view, 1024, 640);
            scene.getRoot().setStyle("-fx-background-color: #F8F9FA;");
            // Set the scene on the stage
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Navigation Error");
            alert.setContentText("Could not return to Teachers view: " + e.getMessage());
            alert.showAndWait();
        }
    }
}