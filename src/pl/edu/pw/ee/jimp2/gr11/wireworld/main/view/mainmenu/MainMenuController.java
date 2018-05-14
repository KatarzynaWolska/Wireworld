package pl.edu.pw.ee.jimp2.gr11.wireworld.main.view.mainmenu;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.edu.pw.ee.jimp2.gr11.wireworld.main.view.rules.RulesController;

import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController extends Application implements Initializable {
    private Stage primaryStage; //test

    public void pressNewGameButton(ActionEvent event){
        System.out.println("tymczasowy napis");
    }

    public void pressRulesButton(ActionEvent event) {
        RulesController r = new RulesController(this);
    }

    public void pressExitButton(ActionEvent event){
        System.exit(0);
    }

    /*public Stage getStage() {
        return stage;
    }*/

    public MainMenuController() {

    }

    public void start(Stage primaryStage) throws Exception {
        //this.stage = primaryStage;//test
        Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        //tu (^) nie moze być całej ściezki typu pl/edu/pw/ee/jimp2/gr11/wireworld/main/view/mainmenu/MainMenu.fxml
        // bo nie zadziała ;v
        root.getStyleClass().add("root");
        // StackPane stackPane = loader.load();
        Scene scene = new Scene(root, 696, 661);
        primaryStage.setTitle("Wire World");
        primaryStage.setScene(scene);
        primaryStage.show();


        // scene.getStylesheets().add("pl.edu.pw.ee.jimp2.gr11.wireworld.main.view.wireWorldStyle");

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
