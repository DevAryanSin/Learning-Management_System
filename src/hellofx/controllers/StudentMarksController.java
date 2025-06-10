package hellofx.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.collections.*;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class StudentMarksController implements Initializable {
    @FXML private TableView<Mark> marksTable;
    @FXML private TableColumn<Mark, String> courseColumn;
    @FXML private TableColumn<Mark, String> semColumn;
    @FXML private TableColumn<Mark, Integer> marksColumn;
    
    // Add new FXML controls for performance metrics
    @FXML private Label averageMarksLabel;
    @FXML private Label highestMarkLabel;
    @FXML private Label lowestMarkLabel;
    @FXML private ProgressBar performanceBar;
    @FXML private Label gpaLabel;

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
        // Set up column factories
        courseColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getCourse()));
        semColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getSem()));
        marksColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getMarks()).asObject());
        
        // Style the table
        marksTable.setStyle("-fx-font-size: 14px;");
        String columnStyle = "-fx-alignment: CENTER;";
        courseColumn.setStyle(columnStyle);
        semColumn.setStyle(columnStyle);
        marksColumn.setStyle(columnStyle);

        marksTable.setItems(marks);
        loadMarks();
        updatePerformanceMetrics();
    }

    private void updatePerformanceMetrics() {
        double average = marks.stream()
                .mapToInt(Mark::getMarks)
                .average()
                .orElse(0.0);
                
        int highest = marks.stream()
                .mapToInt(Mark::getMarks)
                .max()
                .orElse(0);
                
        int lowest = marks.stream()
                .mapToInt(Mark::getMarks)
                .min()
                .orElse(0);
                
        double gpa = calculateGPA(average);

        averageMarksLabel.setText(String.format("Average: %.1f%%", average));
        highestMarkLabel.setText("Highest: " + highest + "%");
        lowestMarkLabel.setText("Lowest: " + lowest + "%");
        gpaLabel.setText(String.format("GPA: %.2f", gpa));
        
        performanceBar.setProgress(average / 100.0);
        String barColor = average >= 75 ? "#2ecc71" : "#e74c3c";
        performanceBar.setStyle("-fx-accent: " + barColor + ";");
    }

    private double calculateGPA(double average) {
        if (average >= 90) return 4.0;
        else if (average >= 80) return 3.5;
        else if (average >= 70) return 3.0;
        else if (average >= 60) return 2.5;
        else if (average >= 50) return 2.0;
        else return 0.0;
    }

    private void loadMarks() {
        // Add sample marks data
        marks.addAll(
            new Mark("CS101", "Fall 2024", 85),
            new Mark("MATH201", "Fall 2024", 92),
            new Mark("PHY102", "Fall 2024", 78),
            new Mark("CS201", "Spring 2025", 88),
            new Mark("ENG101", "Spring 2025", 95),
            new Mark("STAT301", "Spring 2025", 82)
        );
    }
}