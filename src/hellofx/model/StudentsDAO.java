package hellofx.model;

import hellofx.controllers.DBUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class StudentsDAO {
    public static ObservableList<StudentsModel> getAllStudents() {
        ObservableList<StudentsModel> students = FXCollections.observableArrayList();
        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id, firstName, lastName FROM Student")) {
            while (rs.next()) {
                students.add(new StudentsModel(
                        rs.getInt("id"),
                        rs.getString("firstName"),
                        rs.getString("lastName")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }

    public static void addStudent(String firstName, String lastName) {
        String sql = "INSERT INTO Student (firstName, lastName) VALUES (?, ?)";
        try (Connection conn = DBUtil.getConnection();
             java.sql.PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}