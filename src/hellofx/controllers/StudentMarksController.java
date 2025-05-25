package hellofx.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.collections.*;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class StudentMarksController implements Initializable {
    @FXML private TableView<Mark> marksTable;
    @FXML private TableColumn<Mark, String> courseColumn;
    @FXML private TableColumn<Mark, String> semColumn;
    @FXML private TableColumn<Mark, Integer> marksColumn;

    private ObservableList<Mark> marks = FXCollections.observableArrayList();

    public static class Mark {
        private final String course, sem;
        private final int marks;
        public Mark(String course, String sem, int marks) {
            this.course = course; this.sem = sem; this.marks = marks;
        }
        public String getCourse() { return course; }
        public String getSem() { return sem; }
        public int getMarks() { return marks; }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        courseColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getCourse()));
        semColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getSem()));
        marksColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getMarks()).asObject());
        marksTable.setItems(marks);
        loadMarks();
    }

    private void loadMarks() {
        String rollnum = "YOUR_ROLL_NUMBER";
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:lms.db")) {
            PreparedStatement ps = conn.prepareStatement(
                "SELECT courseCode, semTitle, marksObtained FROM Marks WHERE rollnum = ?");
            ps.setString(1, rollnum);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                marks.add(new Mark(rs.getString("courseCode"), rs.getString("semTitle"), rs.getInt("marksObtained")));
            }
        } catch (Exception e) { e.printStackTrace(); }
    }
}