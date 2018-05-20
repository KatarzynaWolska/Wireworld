package pl.edu.pw.ee.jimp2.gr11.wireworld.main;

import javafx.application.Application;
import javafx.stage.Stage;
import pl.edu.pw.ee.jimp2.gr11.wireworld.main.view.mainmenu.MainMenuController;

import static javafx.application.Application.launch;


public class App extends Application {

    public static void main(String[] args) {

        MainMenuController menu = new MainMenuController();
        menu.launch(args);

        /*ConfigFileReader r = new ConfigFileReader();
        try {
            ArrayList<Cell> cells = r.readFile(args[0]);
            Generation g = new Generation(r.getHeightOfMatrix(), r.getWidthOfMatrix(), cells);
            g.print(g);
            System.out.println(" ");
            Game game = new Game(g, 5);
            game.performGame();
        } catch (IOException e) {
            System.out.println("Nie mogę znaleźć pliku");
            System.exit(1);
        }*/
        //sprawdzanie czy działa


    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }
}
