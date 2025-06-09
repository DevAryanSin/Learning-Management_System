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
import javafx.scene.layout.VBox;

public class TeacherMarksController implements Initializable {
    @FXML private ComboBox<String> courseCombo;
    @FXML private ComboBox<String> semCombo;
    @FXML private TableView<StudentMark> marksTable;
    @FXML private TableColumn<StudentMark, String> rollColumn;
    @FXML private TableColumn<StudentMark, String> nameColumn;
    @FXML private TableColumn<StudentMark, Integer> marksColumn;
    @FXML private Button saveButton;

    private ObservableList<StudentMark> students = FXCollections.observableArrayList(
        new StudentMark("101", "John Doe", 85),
        new StudentMark("102", "Jane Smith", 92),
        new StudentMark("103", "Bob Wilson", 78)
    );

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
        // Setup ComboBoxes
        courseCombo.getItems().addAll("Computer Science", "Mathematics", "Physics");
        semCombo.getItems().addAll("Semester 1", "Semester 2", "Semester 3");

        // Setup TableView
        rollColumn.setCellValueFactory(new PropertyValueFactory<>("roll"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        marksColumn.setCellValueFactory(new PropertyValueFactory<>("marks"));
        
        marksTable.setItems(students);

        // Setup Save Button
        saveButton.setOnAction(e -> saveMarks());
    }

    private void saveMarks() {
        // Add save functionality here
        System.out.println("Saving marks...");
    }

    @FXML
    private void goBack() {
        try {
            Parent view = FXMLLoader.load(getClass().getResource("/hellofx/fxml/Teachers.fxml"));
            marksTable.getScene().setRoot(view);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}