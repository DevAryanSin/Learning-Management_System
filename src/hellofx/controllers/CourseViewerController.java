package hellofx.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.List;
import java.util.Arrays;

public class CourseViewerController implements Initializable {

    @FXML
    private GridPane courseGrid;

    public static class Course {
        private final String title;
        private final String info;

        public Course(String title, String info) {
            this.title = title;
            this.info = info;
        }
        public String getTitle() { return title; }
        public String getInfo() { return info; }
    }

    private final List<Course> courses = Arrays.asList(
        new Course("Mathematics", "Algebra and Calculus"),
        new Course("Physics", "Mechanics and Thermodynamics"),
        new Course("Chemistry", "Organic and Inorganic Chemistry"),
        new Course("Biology", "Genetics and Evolution"),
        new Course("Computer Science", "Programming and Data Structures")
    );

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        courseGrid.getChildren().clear();
        int col = 0, row = 0;
        for (Course course : courses) {
            VBox vbox = new VBox(10);
            vbox.setStyle("-fx-border-color: #2D75E8; -fx-padding: 10;");
            Label titleLabel = new Label(course.getTitle());
            titleLabel.setStyle("-fx-font-size: 18px;");
            Button enrollBtn = new Button("Enroll");
            Button infoBtn = new Button("View Info");

            enrollBtn.setOnAction(e -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Enrolled in " + course.getTitle() + "!");
                alert.showAndWait();
            });
            infoBtn.setOnAction(e -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, course.getInfo());
                alert.setHeaderText(course.getTitle() + " Info");
                alert.showAndWait();
            });

            vbox.getChildren().addAll(titleLabel, enrollBtn, infoBtn);
            courseGrid.add(vbox, col, row);

            col++;
            if (col > 1) { col = 0; row++; }
        }
    }
}