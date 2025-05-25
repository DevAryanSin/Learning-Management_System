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

public class TeacherMarksController implements Initializable {
    @FXML private ComboBox<String> courseCombo;
    @FXML private ComboBox<String> semCombo;
    @FXML private TableView<StudentMark> marksTable;
    @FXML private TableColumn<StudentMark, String> rollColumn;
    @FXML private TableColumn<StudentMark, String> nameColumn;
    @FXML private TableColumn<StudentMark, Integer> marksColumn;
    @FXML private Button saveButton;

    private ObservableList<StudentMark> students = FXCollections.observableArrayList();

    public static class StudentMark {
        private final String roll, name;
        private int marks;
        public StudentMark(String roll, String name, int marks) {
            this.roll = roll; this.name = name; this.marks = marks;
        }
        public String getRoll() { return roll; }
        public String getName() { return name; }
        public int getMarks() { return marks; }
        public void setMarks(int marks) { this.marks = marks; }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rollColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getRoll()));
        nameColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getName()));
        marksColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getMarks()).asObject());
        marksTable.setItems(students);
        // Load courses, semesters, and students as needed
    }
    
    @FXML
    private void goBack() {
        try {
            Parent view = FXMLLoader.load(getClass().getResource("/hellofx/fxml/Teachers.fxml"));
            AnchorPane root = (AnchorPane) marksTable.getScene().getRoot();
            root.getChildren().setAll(view);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}