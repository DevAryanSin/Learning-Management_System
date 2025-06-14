package hellofx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
// import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("fxml/welcome.fxml"));
        primaryStage.setTitle("Learning Management System");
        primaryStage.setScene(new Scene(root, 1024, 640));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}