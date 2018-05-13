package pl.edu.pw.ee.jimp2.gr11.wireworld.main.view.rules;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RulesController extends Application {

    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Rules.fxml"));
        root.getStyleClass().add("root");
        Scene scene = new Scene(root, 696, 661);
        primaryStage.setTitle("WireWorld - Zasady");
        primaryStage.setScene(scene);
        primaryStage.show();


        // scene.getStylesheets().add("pl.edu.pw.ee.jimp2.gr11.wireworld.main.view.wireWorldStyle");

    }

    public RulesController() {
        try {
            this.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
