package hellofx.controllers;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXML;

import java.net.URL;
import java.util.ResourceBundle;

public class TeachersController implements Initializable {

    @FXML
    private AnchorPane mainPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Parent courseViewer = FXMLLoader.load(getClass().getResource("/hellofx/fxml/CourseViewer.fxml"));
            mainPane.getChildren().setAll(courseViewer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}