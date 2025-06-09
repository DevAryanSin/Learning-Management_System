package hellofx.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.sql.*;

public class ForgotPasswordController {
    @FXML private TextField emailField;
    @FXML private PasswordField newPasswordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private Button nextButton;
    @FXML private Label headerLabel;
    
    private boolean isEmailVerified = false;
    private String userType;
    private String sourceView; // Add this field to track source

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:lms.db");
    }

    public void setSourceView(String source) {
        this.sourceView = source;
    }

    @FXML
    public void initialize() {
        // Remove the automatic source detection
        if (sourceView == null) {
            sourceView = "student"; // fallback default
        }
    }

    @FXML
    private void onNext() {
        if (emailField.getText().trim().isEmpty()) {
            showError("Input Error", "Please enter your email address");
            return;
        }

        if (!isEmailVerified) {
            verifyEmail();
        } else {
            updatePassword();
        }
    }

    private void verifyEmail() {
        String email = emailField.getText().trim();
        
        try (Connection conn = getConnection()) {
            // Check both tables
            boolean isStudent = checkUser(conn, "Student", email);
            boolean isTeacher = checkUser(conn, "Teacher", email);
            
            if (isStudent) {
                userType = "student";
                showPasswordFields();
            } else if (isTeacher) {
                userType = "teacher";
                showPasswordFields();
            } else {
                showError("Account Not Found", "No account exists with this email address");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showError("Database Error", "Could not verify email address: " + e.getMessage());
        }
    }

    private boolean checkUser(Connection conn, String table, String email) throws SQLException {
        String query = "SELECT * FROM " + table + " WHERE email = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, email);
            return stmt.executeQuery().next();
        }
    }

    private void showPasswordFields() {
        headerLabel.setText("Create new password");
        emailField.setDisable(true);
        newPasswordField.setVisible(true);
        newPasswordField.setManaged(true);
        confirmPasswordField.setVisible(true);
        confirmPasswordField.setManaged(true);
        nextButton.setText("Reset Password");
        isEmailVerified = true;
    }

    private void updatePassword() {
        if (!validatePasswords()) return;
        
        String email = emailField.getText().trim();
        String newPassword = newPasswordField.getText();
        
        try (Connection conn = getConnection()) {
            String table = userType.equals("student") ? "Student" : "Teacher";
            String updateQuery = "UPDATE " + table + " SET password = ? WHERE email = ?";
                
            try (PreparedStatement stmt = conn.prepareStatement(updateQuery)) {
                stmt.setString(1, newPassword);
                stmt.setString(2, email);
                
                int updated = stmt.executeUpdate();
                
                if (updated > 0) {
                    showSuccess("Success", "Your password has been reset successfully!");
                    goBack();
                } else {
                    showError("Update Failed", "Could not update password. Please try again.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showError("Database Error", "Could not update password: " + e.getMessage());
        }
    }

    private boolean validatePasswords() {
        String newPass = newPasswordField.getText();
        String confirmPass = confirmPasswordField.getText();
        
        if (newPass.isEmpty() || confirmPass.isEmpty()) {
            showError("Validation Error", "Please fill in both password fields");
            return false;
        }
        
        if (!newPass.equals(confirmPass)) {
            showError("Validation Error", "Passwords do not match");
            return false;
        }
        
        if (newPass.length() < 6) {
            showError("Validation Error", "Password must be at least 6 characters long");
            return false;
        }
        
        return true;
    }

    @FXML
    private void onBack() {
        goBack();
    }

    private void goBack() {
        try {
            String fxmlPath = String.format("/hellofx/fxml/%s_login.fxml", sourceView);
            System.out.println("Navigating to: " + fxmlPath); // Debug line
            Parent view = FXMLLoader.load(getClass().getResource(fxmlPath));
            Scene scene = new Scene(view, 1024, 640);
            scene.getRoot().setStyle("-fx-background-color: #FFFFFF;");
            Stage stage = (Stage) emailField.getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
            showError("Navigation Error", "Could not return to login screen");
        }
    }

    private void showError(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void showSuccess(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}