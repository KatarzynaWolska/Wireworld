package pl.edu.pw.ee.jimp2.gr11.wireworld.main.view.mainmenu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainMenuGUI extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        //tu (^) nie moze być całej ściezki typu pl/edu/pw/ee/jimp2/gr11/wireworld/main/view/mainmenu/MainMenu.fxml
        // bo nie zadziała ;v

            root.getStyleClass().add("root");
            Scene scene = new Scene(root, 696, 661);
            primaryStage.setTitle("Wire World");
            primaryStage.setScene(scene);
            primaryStage.show();


       // scene.getStylesheets().add("wireWorldStyle.css");

    }

    public static void main(String[] args) {

        launch(args);
    }


}
