package pl.edu.pw.ee.jimp2.gr11.wireworld.main.view;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    @FXML
    public Parent root;
    @FXML
    public Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        root = FXMLLoader.load(getClass().getResource("GameWindow.fxml"));
        primaryStage.setResizable(false);

        scene = new Scene(root, 800, 700);

        scene.getStylesheets().add("pl/edu/pw/ee/jimp2/gr11/wireworld/main/view/wireWorldStyle.css");
        root.getStyleClass().add("root");

        primaryStage.setTitle("Wire World");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() {
        System.exit(0);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
