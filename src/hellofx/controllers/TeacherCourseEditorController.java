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

public class TeacherCourseEditorController implements Initializable {
    @FXML private TableView<Course> courseTable;
    @FXML private TableColumn<Course, String> codeColumn;
    @FXML private TableColumn<Course, String> titleColumn;
    @FXML private TableColumn<Course, String> descColumn;
    @FXML private Button addButton, editButton, deleteButton;

    private ObservableList<Course> courses = FXCollections.observableArrayList();

    public static class Course {
        private final String code, title, desc;
        public Course(String code, String title, String desc) { this.code = code; this.title = title; this.desc = desc; }
        public String getCode() { return code; }
        public String getTitle() { return title; }
        public String getDesc() { return desc; }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        codeColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getCode()));
        titleColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getTitle()));
        descColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getDesc()));
        courseTable.setItems(courses);
        loadCourses();
    }

    private void loadCourses() {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:lms.db")) {
            ResultSet rs = conn.createStatement().executeQuery("SELECT courseCode, title, description FROM Course");
            while (rs.next()) {
                courses.add(new Course(rs.getString("courseCode"), rs.getString("title"), rs.getString("description")));
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    // Implement add, edit, delete methods as needed
    @FXML
    private void goBack() {
        try {
            Parent view = FXMLLoader.load(getClass().getResource("/hellofx/fxml/Teachers.fxml"));
            AnchorPane root = (AnchorPane) courseTable.getScene().getRoot();
            root.getChildren().setAll(view);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}