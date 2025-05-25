package hellofx.controllers;

import hellofx.model.StudentsModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentsController implements Initializable {

    @FXML
    private TableView<StudentsModel> tbData;
    @FXML
    public TableColumn<StudentsModel, Integer> studentId;

    @FXML
    public TableColumn<StudentsModel, String> firstName;

    @FXML
    public TableColumn<StudentsModel, String> lastName;

    @FXML
    private AnchorPane mainPane;

    private ObservableList<StudentsModel> studentsModels = FXCollections.observableArrayList(
            new StudentsModel(1,"Amos", "Chepchieng"),
            new StudentsModel(2,"Amos", "Mors"),
            new StudentsModel(3,"Amos", "Chepchieng"),
            new StudentsModel(4,"Amos", "Mors")
    );

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        studentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tbData.setItems(studentsModels);
        try {
            Parent courseViewer = FXMLLoader.load(getClass().getResource("/hellofx/fxml/CourseViewer.fxml"));
            mainPane.getChildren().setAll(courseViewer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
