package pl.edu.pw.ee.jimp2.gr11.wireworld.main.view.mainmenu;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import pl.edu.pw.ee.jimp2.gr11.wireworld.main.logic.Game;
import pl.edu.pw.ee.jimp2.gr11.wireworld.main.view.mainmenu.MainMenuController;

import java.net.URL;
import java.util.ResourceBundle;

import static javafx.application.Application.launch;


public class App extends Application {
    @FXML
    public Parent root;
    @FXML
    public Scene scene;


    @Override
    public void start(Stage primaryStage) throws Exception {
        root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        primaryStage.setResizable(false);

        scene = new Scene(root, 800, 700);

        primaryStage.setTitle("Wire World");
        primaryStage.setScene(scene);
        //scene.getStylesheets().add(MainMenuController.class.getResource("wireWorldStyle.css").toExternalForm());

        scene.getStylesheets().add("pl/edu/pw/ee/jimp2/gr11/wireworld/main/view/mainmenu/wireWorldStyle.css");
        //  root.getStylesheets().add("pl/edu/pw/ee/jimp2/gr11/wireworld/main/view/wireWorldStyle.css");
        root.getStyleClass().add("root");
        //System.out.println( root.isResizable());
        //MainMenuController m = new MainMenuController();
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
