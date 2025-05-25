package hellofx.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.collections.*;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.scene.layout.AnchorPane;

public class TeacherEnrolledStudentsController implements Initializable {
    @FXML private ComboBox<String> courseCombo;
    @FXML private TableView<Student> studentTable;
    @FXML private TableColumn<Student, String> rollColumn;
    @FXML private TableColumn<Student, String> nameColumn;

    private ObservableList<Student> students = FXCollections.observableArrayList();

    public static class Student {
        private final String roll, name;
        public Student(String roll, String name) { this.roll = roll; this.name = name; }
        public String getRoll() { return roll; }
        public String getName() { return name; }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rollColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getRoll()));
        nameColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getName()));
        studentTable.setItems(students);
        // Load courses and students as needed
    }

    @FXML
    private void goBack() {
        try {
            Parent view = FXMLLoader.load(getClass().getResource("/hellofx/fxml/Teachers.fxml"));
            AnchorPane root = (AnchorPane) studentTable.getScene().getRoot();
            root.getChildren().setAll(view);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}