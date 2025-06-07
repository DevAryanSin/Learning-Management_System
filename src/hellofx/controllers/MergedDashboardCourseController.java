package hellofx.controllers;
import javafx.scene.Node;
// import hellofx.utils.DatabaseConnection;
import java.util.Arrays;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

import java.sql.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.List;
import java.util.Arrays;

public class MergedDashboardCourseController implements Initializable {
    @FXML private VBox sidebar;
    @FXML private VBox mainContent;
    @FXML private VBox dashboardView;
    @FXML private VBox attendanceView;
    @FXML private VBox coursesView;
    @FXML private VBox performanceView;
    @FXML private Label studentFullNameLabel;
    @FXML private Label studentEmailLabel;
    @FXML private Label studentDobLabel;
    @FXML private Label studentNameLabel;

    // Add this class for subject performance data
    public static class SubjectPerformance {
        private final String subject;
        private final int marks;

        public SubjectPerformance(String subject, int marks) {
            this.subject = subject;
            this.marks = marks;
        }

        public String getSubject() { return subject; }
        public int getMarks() { return marks; }
    }

    private boolean isSidebarCollapsed = false;
    private static final double EXPANDED_WIDTH = 250;
    private static final double COLLAPSED_WIDTH = 70;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupSidebar();
        showDashboard();
        loadStudentDetails();
    }

    private void setupSidebar() {
        sidebar.getChildren().clear();
        
        // Logo section
        VBox logoBox = new VBox(4);
        logoBox.setPadding(new Insets(16));
        Label appName = new Label("Learning MS");
        Label subtitle = new Label("Student Portal");
        appName.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-text-fill: #1a73e8;");
        subtitle.setStyle("-fx-font-size: 13px; -fx-text-fill: #5f6368;");
        logoBox.getChildren().addAll(appName, subtitle);
        
        // Toggle button
        Button toggleBtn = new Button("≡");
        toggleBtn.setOnAction(e -> toggleSidebar());
        toggleBtn.setStyle(
            "-fx-background-color: transparent;" +
            "-fx-text-fill: #5f6368;" +
            "-fx-font-size: 24px;" +
            "-fx-cursor: hand;"
        );
        
        HBox header = new HBox(12, toggleBtn, logoBox);
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(new Insets(8, 16, 8, 8));
        
        // Navigation menu
        VBox menuItems = new VBox(8);
        menuItems.setPadding(new Insets(8, 8, 8, 16));
        menuItems.getChildren().addAll(
            createMenuItem("Dashboard", "Overview and quick actions", this::showDashboard, "🏠"),
            createMenuItem("Attendance", "View and manage attendance", this::showAttendance, "📊"),
            createMenuItem("Courses", "Browse enrolled courses", this::showCourses, "📚"),
            createMenuItem("Performance", "Track academic progress", this::showProgress, "📈")
        );
        
        Separator separator = new Separator();
        separator.setPadding(new Insets(8, 0, 8, 0));
        
        sidebar.getChildren().addAll(header, separator, menuItems);
        sidebar.setStyle(
            "-fx-background-color: white;" +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.08), 8, 0, 0, 0);"
        );
    }

    private Button createMenuItem(String title, String description, Runnable action, String icon) {
        VBox content = new VBox(4);
        content.setAlignment(Pos.CENTER_LEFT);
        
        Label titleLabel = new Label(title);
        titleLabel.setGraphic(new Label(icon));
        titleLabel.setGraphicTextGap(12);
        titleLabel.setStyle(
            "-fx-font-size: 14px;" +
            "-fx-font-weight: 500;" +
            "-fx-text-fill: #202124;"
        );
        
        Label descLabel = new Label(description);
        descLabel.setStyle(
            "-fx-font-size: 12px;" +
            "-fx-text-fill: #5f6368;"
        );
        
        content.getChildren().addAll(titleLabel, descLabel);
        
        Button btn = new Button();
        btn.setUserData(title); // Store the title for collapse/expand
        btn.setGraphic(content);
        btn.setMaxWidth(Double.MAX_VALUE);
        btn.setAlignment(Pos.CENTER_LEFT);
        btn.setOnAction(e -> action.run());
        btn.setPadding(new Insets(12, 16, 12, 16));
        btn.setStyle(
            "-fx-background-color: transparent;" +
            "-fx-background-radius: 8;" +
            "-fx-cursor: hand;"
        );
        
        // Hover and active states
        btn.setOnMouseEntered(e -> {
            btn.setStyle(
                "-fx-background-color: #F8F9FA;" +
                "-fx-background-radius: 8;"
            );
        });
        
        btn.setOnMouseExited(e -> {
            btn.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-background-radius: 8;"
            );
        });
        
        return btn;
    }

    private void loadStudentDetails() {
        // Default student data
        String fullName = "John Doe";
        String email = "john.doe@example.com";
        String dob = "2000-01-01";
        
        studentNameLabel.setText("Welcome, " + fullName);
        studentFullNameLabel.setText(fullName);
        studentEmailLabel.setText(email);
        studentDobLabel.setText(dob);
    }

    private void loadCourses() {
        // Default course data
        List<Course> courses = Arrays.asList(
            new Course("Computer Science 101", "Introduction to Programming", "Prof. Smith"),
            new Course("Mathematics 201", "Advanced Calculus", "Prof. Johnson"),
            new Course("Physics 101", "Basic Physics", "Prof. Brown"),
            new Course("English 101", "Academic Writing", "Prof. Davis")
        );
        
        displayCourses(courses);
    }

    private void displayCourses(List<Course> courses) {
        coursesView.getChildren().clear();
        
        GridPane grid = new GridPane();
        grid.setHgap(20);
        grid.setVgap(20);
        
        int col = 0;
        int row = 0;
        
        for (Course course : courses) {
            VBox courseCard = new VBox(10);
            courseCard.setStyle(
                "-fx-background-color: white;" +
                "-fx-padding: 15;" +
                "-fx-background-radius: 8;" +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 4, 0, 0, 1);"
            );
            
            Label titleLabel = new Label(course.getName());
            titleLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");
            
            Label descLabel = new Label(course.getDescription());
            descLabel.setStyle("-fx-text-fill: #5f6368;");
            
            Label instructorLabel = new Label(course.getInstructor());
            instructorLabel.setStyle("-fx-text-fill: #5f6368;");
            
            courseCard.getChildren().addAll(titleLabel, descLabel, instructorLabel);
            
            grid.add(courseCard, col, row);
            
            col++;
            if (col > 1) {
                col = 0;
                row++;
            }
        }
        
        coursesView.getChildren().add(grid);
    }

    // Add Course class
    private static class Course {
        private final String name;
        private final String description;
        private final String instructor;

        public Course(String name, String description, String instructor) {
            this.name = name;
            this.description = description;
            this.instructor = instructor;
        }

        public String getName() { return name; }
        public String getDescription() { return description; }
        public String getInstructor() { return instructor; }
    }

    private void loadPerformanceData() {
        // Default performance data
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
            new PieChart.Data("Mathematics", 85),
            new PieChart.Data("Physics", 78),
            new PieChart.Data("Chemistry", 92),
            new PieChart.Data("Computer Science", 95)
        );
        
        if (performancePieChart != null) {
            performancePieChart.setData(pieChartData);
            performancePieChart.setTitle("Subject Performance");
        }
    }

    @FXML
    private void toggleSidebar() {
        TranslateTransition transition = new TranslateTransition(Duration.millis(200), sidebar);
        
        if (isSidebarCollapsed) {
            // Expand
            sidebar.setPrefWidth(EXPANDED_WIDTH);
            transition.setToX(0);
            
            // Restore menu items
            sidebar.getChildren().stream()
                .filter(node -> node instanceof VBox)
                .flatMap(vbox -> ((VBox) vbox).getChildren().stream())
                .filter(node -> node instanceof Button)
                .forEach(btn -> {
                    Button button = (Button) btn;
                    VBox content = (VBox) button.getGraphic();
                    Label titleLabel = (Label) content.getChildren().get(0);
                    Label descLabel = (Label) content.getChildren().get(1);
                    
                    // Restore original text and spacing
                    String icon = titleLabel.getGraphic().toString();
                    titleLabel.setText(button.getUserData().toString()); // Get stored title
                    titleLabel.setGraphicTextGap(12);
                    descLabel.setVisible(true);
                });
        } else {
            // Collapse
            sidebar.setPrefWidth(COLLAPSED_WIDTH);
            transition.setToX(0);
            
            // Show only icons
            sidebar.getChildren().stream()
                .filter(node -> node instanceof VBox)
                .flatMap(vbox -> ((VBox) vbox).getChildren().stream())
                .filter(node -> node instanceof Button)
                .forEach(btn -> {
                    Button button = (Button) btn;
                    VBox content = (VBox) button.getGraphic();
                    Label titleLabel = (Label) content.getChildren().get(0);
                    Label descLabel = (Label) content.getChildren().get(1);
                    
                    // Store the full title for later restoration
                    button.setUserData(titleLabel.getText());
                    
                    // Show only icon
                    titleLabel.setGraphicTextGap(0);
                    titleLabel.setText("");
                    descLabel.setVisible(false);
                });
        }
        
        transition.setOnFinished(e -> {
            // Ensure sidebar stays visible
            sidebar.setVisible(true);
            sidebar.setManaged(true);
        });
        
        transition.play();
        isSidebarCollapsed = !isSidebarCollapsed;
    }

    private void hideAllViews() {
        dashboardView.setVisible(false);
        attendanceView.setVisible(false);
        coursesView.setVisible(false);
        performanceView.setVisible(false);
    }

    @FXML
    private void showDashboard() {
        hideAllViews();
        dashboardView.setVisible(true);
    }

    @FXML
    private void showAttendance() {
        hideAllViews();
        attendanceView.setVisible(true);
    }

    @FXML
    private void showCourses() {
        hideAllViews();
        coursesView.setVisible(true);
        loadCourses(); // Refresh courses when showing the view
    }

    @FXML
    private void showProgress() {
        hideAllViews();
        performanceView.setVisible(true);
    }

    // Remove database-related imports and error handling
    private void showError(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}