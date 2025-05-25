package hellofx.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.*;

public class CourseEditorController implements Initializable {

    @FXML
    private GridPane courseGrid;

    public static class Course {
        private final String title;
        private String info;
        private final List<String> enrolledStudents;

        public Course(String title, String info, List<String> students) {
            this.title = title;
            this.info = info;
            this.enrolledStudents = students;
        }
        public String getTitle() { return title; }
        public String getInfo() { return info; }
        public void setInfo(String info) { this.info = info; }
        public List<String> getEnrolledStudents() { return enrolledStudents; }
    }

    private final List<Course> courses = Arrays.asList(
        new Course("Mathematics", "Algebra and Calculus", Arrays.asList("Alice", "Bob")),
        new Course("Physics", "Mechanics and Thermodynamics", Arrays.asList("Charlie")),
        new Course("Chemistry", "Organic and Inorganic Chemistry", Arrays.asList("Alice")),
        new Course("Biology", "Genetics and Evolution", Arrays.asList("Bob", "Charlie")),
        new Course("Computer Science", "Programming and Data Structures", Arrays.asList("Alice", "Bob", "Charlie"))
    );

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        courseGrid.getChildren().clear();
        int col = 0, row = 0;
        for (Course course : courses) {
            VBox vbox = new VBox(10);
            vbox.setStyle("-fx-border-color: #E87D2D; -fx-padding: 10;");
            Label titleLabel = new Label(course.getTitle());
            titleLabel.setStyle("-fx-font-size: 18px;");
            Button editBtn = new Button("Edit Info");
            Button studentsBtn = new Button("See Enrolled Students");

            editBtn.setOnAction(e -> {
                // For demo: just show an alert, you can implement editing logic
                course.setInfo(course.getInfo() + " (edited)");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Course info updated for " + course.getTitle());
                alert.showAndWait();
            });
            studentsBtn.setOnAction(e -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, String.join(", ", course.getEnrolledStudents()));
                alert.setHeaderText("Enrolled Students in " + course.getTitle());
                alert.showAndWait();
            });

            vbox.getChildren().addAll(titleLabel, editBtn, studentsBtn);
            courseGrid.add(vbox, col, row);

            col++;
            if (col > 1) { col = 0; row++; }
        }
    }
}