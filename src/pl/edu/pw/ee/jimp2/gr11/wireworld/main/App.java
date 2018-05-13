package pl.edu.pw.ee.jimp2.gr11.wireworld.main;

import javafx.stage.Stage;
import pl.edu.pw.ee.jimp2.gr11.wireworld.main.view.mainmenu.MainMenuController;

import static javafx.application.Application.launch;


public class App {

    public static void main(String[] args) {
        MainMenuController menu = new MainMenuController();
        try {
            menu.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }


        launch(args);
    }
}
