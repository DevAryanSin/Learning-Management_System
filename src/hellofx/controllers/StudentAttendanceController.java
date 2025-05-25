package hellofx.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.collections.*;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class StudentAttendanceController implements Initializable {
    @FXML private TableView<Attendance> attendanceTable;
    @FXML private TableColumn<Attendance, String> courseColumn;
    @FXML private TableColumn<Attendance, String> dateColumn;
    @FXML private TableColumn<Attendance, String> statusColumn;

    private ObservableList<Attendance> records = FXCollections.observableArrayList();

    public static class Attendance {
        private final String course, date, status;
        public Attendance(String course, String date, String status) {
            this.course = course; this.date = date; this.status = status;
        }
        public String getCourse() { return course; }
        public String getDate() { return date; }
        public String getStatus() { return status; }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        courseColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getCourse()));
        dateColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getDate()));
        statusColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getStatus()));
        attendanceTable.setItems(records);
        loadAttendance();
    }

    private void loadAttendance() {
        String rollnum = "YOUR_ROLL_NUMBER";
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:lms.db")) {
            PreparedStatement ps = conn.prepareStatement(
                "SELECT courseCode, date, status FROM Attendance WHERE rollnum = ?");
            ps.setString(1, rollnum);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                records.add(new Attendance(rs.getString("courseCode"), rs.getString("date"), rs.getString("status")));
            }
        } catch (Exception e) { e.printStackTrace(); }
    }
}
