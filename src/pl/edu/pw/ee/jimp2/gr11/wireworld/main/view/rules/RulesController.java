package pl.edu.pw.ee.jimp2.gr11.wireworld.main.view.rules;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import pl.edu.pw.ee.jimp2.gr11.wireworld.main.view.mainmenu.MainMenuController;

public class RulesController {
    private MainMenuController mainMenuWindow;

    public RulesController(MainMenuController m) {
        this.mainMenuWindow = m;
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("Rules.fxml"));
        root.getStyleClass().add("root");
        Scene scene = new Scene(root, 696, 661);
        primaryStage.setTitle("WireWorld - Zasady");
        primaryStage.setScene(scene);
        primaryStage.show();


        // scene.getStylesheets().add("pl.edu.pw.ee.jimp2.gr11.wireworld.main.view.wireWorldStyle");

    }

}
