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
        // Set up column factories
        courseColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getCourse()));
        dateColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getDate()));
        statusColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getStatus()));
        
        // Make sure table takes full width
        attendanceTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        // Make table visible and set preferred size
        attendanceTable.setVisible(true);
        attendanceTable.setPrefHeight(400);
        attendanceTable.setPrefWidth(800);
        
        // Add styling to the table and cells
        attendanceTable.setStyle("-fx-font-size: 14px; -fx-background-color: white;");
        
        // Style the columns
        String columnStyle = "-fx-alignment: CENTER;";
        courseColumn.setStyle(columnStyle);
        dateColumn.setStyle(columnStyle);
        statusColumn.setStyle(columnStyle);
        
        // Set items and load data
        attendanceTable.setItems(records);
        loadAttendance();
    }

    private void loadAttendance() {
        // Add sample attendance data
        records.addAll(
            new Attendance("CS101", "2025-06-01", "Present"),
            new Attendance("CS101", "2025-06-03", "Present"),
            new Attendance("CS101", "2025-06-05", "Absent"),
            new Attendance("MATH201", "2025-06-02", "Present"),
            new Attendance("MATH201", "2025-06-04", "Present"),
            new Attendance("PHY102", "2025-06-01", "Present"),
            new Attendance("PHY102", "2025-06-03", "Absent"),
            new Attendance("PHY102", "2025-06-05", "Present")
        );
    }
}
