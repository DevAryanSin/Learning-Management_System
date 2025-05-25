package hellofx.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.sql.*;

public class StudentLoginController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    // For account creation
    @FXML
    private TextField nameField;
    @FXML
    private DatePicker dobPicker;
    @FXML
    private TextField createEmailField;
    @FXML
    private PasswordField createPasswordField;

    private Connection getConnection() throws SQLException {

        return DriverManager.getConnection("jdbc:sqlite:lms.db");
    }

    @FXML
    private void onLogin(ActionEvent event) {
        String email = emailField.getText();
        String password = passwordField.getText();

        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM Student WHERE email = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, password); // For real apps, use hashed passwords!
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Parent root = FXMLLoader.load(getClass().getResource("/hellofx/fxml/MergedDashboardCourseView.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root, 1024, 640));
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid email or password for student.");
                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Database error: " + e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    private void onCreateAccount(ActionEvent event) {
        String name = nameField.getText();
        String email = createEmailField.getText();
        String password = createPasswordField.getText();
        String dob = dobPicker.getValue() != null ? dobPicker.getValue().toString() : null;

        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || dob == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill all fields.");
            alert.showAndWait();
            return;
        }

        try (Connection conn = getConnection()) {
            // Check if email already exists
            String checkSql = "SELECT * FROM Student WHERE email = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setString(1, email);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next()) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Email already registered.");
                alert.showAndWait();
                return;
            }

            // Insert new student (rollNumber auto-generated for demo)
            String insertSql = "INSERT INTO Student (rollNumber, name, email, password, dob, batch, cgpa, discipline, campus, contact, gender) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement insertStmt = conn.prepareStatement(insertSql);
            String rollNumber = "R" + System.currentTimeMillis(); // Simple unique roll number
            insertStmt.setString(1, rollNumber);
            insertStmt.setString(2, name);
            insertStmt.setString(3, email);
            insertStmt.setString(4, password); // For real apps, use hashed passwords!
            insertStmt.setString(5, dob);
            insertStmt.setInt(6, 2025); // Example batch
            insertStmt.setNull(7, Types.FLOAT); // cgpa
            insertStmt.setString(8, "BSCS"); // discipline
            insertStmt.setString(9, "Lahore"); // campus
            insertStmt.setString(10, ""); // contact
            insertStmt.setString(11, "M"); // gender

            insertStmt.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Account created! You can now log in.");
            alert.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Database error: " + e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    private void onBack(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/hellofx/fxml/welcome.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 1024, 640));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void showCreateAccount(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/hellofx/fxml/StudentCreateAccount.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 1024, 640)); // <-- keep window size constant
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}