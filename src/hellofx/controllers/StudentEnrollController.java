package hellofx.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.collections.*;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class StudentEnrollController implements Initializable {
    @FXML private TableView<Course> courseTable;
    @FXML private TableColumn<Course, String> codeColumn;
    @FXML private TableColumn<Course, String> titleColumn;
    @FXML private Button enrollButton;

    private ObservableList<Course> courses = FXCollections.observableArrayList();

    public static class Course {
        private final String code, title;
        public Course(String code, String title) { this.code = code; this.title = title; }
        public String getCode() { return code; }
        public String getTitle() { return title; }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        codeColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getCode()));
        titleColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getTitle()));
        courseTable.setItems(courses);
        loadCourses();
    }

    private void loadCourses() {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:lms.db")) {
            ResultSet rs = conn.createStatement().executeQuery("SELECT courseCode, title FROM Course");
            while (rs.next()) {
                courses.add(new Course(rs.getString("courseCode"), rs.getString("title")));
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    @FXML
    private void onEnroll() {
        Course selected = courseTable.getSelectionModel().getSelectedItem();
        if (selected == null) return;
        // Replace with actual student roll number from session
        String rollnum = "YOUR_ROLL_NUMBER";
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:lms.db")) {
            PreparedStatement ps = conn.prepareStatement("INSERT OR IGNORE INTO Enrollment (rollnum, courseCode) VALUES (?, ?)");
            ps.setString(1, rollnum);
            ps.setString(2, selected.getCode());
            ps.executeUpdate();
            new Alert(Alert.AlertType.INFORMATION, "Enrolled in " + selected.getTitle()).showAndWait();
        } catch (Exception e) { e.printStackTrace(); }
    }
}