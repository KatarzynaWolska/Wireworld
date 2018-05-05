package pl.edu.pw.ee.jimp2.gr11.wireworld;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class WireWorldApp extends Application {

    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
        root.getStyleClass().add("root");
        Scene scene = new Scene(root, 696, 661);
        scene.getStylesheets().add("wireWorldStyle.css");
        primaryStage.setTitle("Wire World");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
