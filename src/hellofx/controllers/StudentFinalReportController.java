package hellofx.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.collections.*;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class StudentFinalReportController implements Initializable {
    @FXML private TableView<Report> reportTable;
    @FXML private TableColumn<Report, String> courseColumn;
    @FXML private TableColumn<Report, String> semColumn;
    @FXML private TableColumn<Report, Integer> marksColumn;
    @FXML private TableColumn<Report, String> gradeColumn;

    private ObservableList<Report> reports = FXCollections.observableArrayList();

    public static class Report {
        private final String course, sem, grade;
        private final int marks;
        public Report(String course, String sem, int marks, String grade) {
            this.course = course; this.sem = sem; this.marks = marks; this.grade = grade;
        }
        public String getCourse() { return course; }
        public String getSem() { return sem; }
        public int getMarks() { return marks; }
        public String getGrade() { return grade; }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        courseColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getCourse()));
        semColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getSem()));
        marksColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getMarks()).asObject());
        gradeColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getGrade()));
        reportTable.setItems(reports);
        loadReports();
    }

    private void loadReports() {
        String rollnum = "YOUR_ROLL_NUMBER";
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:lms.db")) {
            PreparedStatement ps = conn.prepareStatement(
                "SELECT m.courseCode, m.semTitle, m.marksObtained, g.grade FROM Marks m LEFT JOIN Grades g ON m.rollnum = g.rollnum AND m.courseCode = g.courseCode AND m.semTitle = g.semTitle WHERE m.rollnum = ?");
            ps.setString(1, rollnum);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                reports.add(new Report(
                    rs.getString("courseCode"),
                    rs.getString("semTitle"),
                    rs.getInt("marksObtained"),
                    rs.getString("grade")
                ));
            }
        } catch (Exception e) { e.printStackTrace(); }
    }
}