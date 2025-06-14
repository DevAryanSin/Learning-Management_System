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
import javafx.scene.layout.AnchorPane;

public class TeacherCourseEditorController implements Initializable {
    @FXML private TextField courseNameField;
    @FXML private TextArea courseDescField;
    @FXML private ComboBox<String> semesterCombo;
    @FXML private TableView<Course> courseTable;
    @FXML private TableColumn<Course, String> nameColumn;
    @FXML private TableColumn<Course, String> descriptionColumn;
    @FXML private TableColumn<Course, String> semesterColumn;

    private ObservableList<Course> courses = FXCollections.observableArrayList(
        new Course("CS101", "Introduction to Programming", "Fundamentals of Java and Python programming"),
        new Course("CS102", "Data Structures", "Advanced data structures and algorithms"),
        new Course("MTH201", "Advanced Calculus", "Differential equations and vector calculus"),
        new Course("PHY101", "Classical Physics", "Mechanics and thermodynamics"),
        new Course("CS203", "Database Systems", "SQL and database management"),
        new Course("MTH202", "Linear Algebra", "Matrices and vector spaces")
    );

    public static class Course {
        private final String code, title, desc;
        public Course(String code, String title, String desc) { this.code = code; this.title = title; this.desc = desc; }
        public String getCode() { return code; }
        public String getTitle() { return title; }
        public String getDesc() { return desc; }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Setup ComboBox
        semesterCombo.getItems().addAll("Semester 1", "Semester 2", "Semester 3");

        // Setup TableView
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("desc"));
        semesterColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
        
        courseTable.setItems(courses);
    }

    @FXML
    private void goBack() {
        try {
            Parent view = FXMLLoader.load(getClass().getResource("/hellofx/fxml/Teachers.fxml"));
            courseTable.getScene().setRoot(view);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}