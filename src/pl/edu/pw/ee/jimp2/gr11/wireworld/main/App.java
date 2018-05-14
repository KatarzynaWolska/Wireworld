package pl.edu.pw.ee.jimp2.gr11.wireworld.main;

import javafx.application.Application;
import javafx.stage.Stage;
import pl.edu.pw.ee.jimp2.gr11.wireworld.main.view.mainmenu.MainMenuController;

import static javafx.application.Application.launch;


public class App extends Application {

    public static void main(String[] args) {

        MainMenuController menu = new MainMenuController();
        menu.launch(args);



    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }
}
