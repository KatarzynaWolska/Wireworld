package pl.edu.pw.ee.jimp2.gr11.wireworld.main.view.mainmenu;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pl.edu.pw.ee.jimp2.gr11.wireworld.main.view.rules.RulesController;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController extends Application implements Initializable {
/*
    private class Tile extends Button {
        private int x, y;
        private Color color;

        public Tile(int x, int y, Color color) {
            this.x = x;
            this.y = y;
            this.color = color;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }
    }*/

    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));

        root.getStyleClass().add("root");
/*
//dodawanie roznej ilosci kolumn i wierszy
        GridPane gridPane = new GridPane();
        final int numCols = 50 ;
        final int numRows = 50 ;

        for (int i = 0; i < numCols; i++) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPercentWidth(100.0 / numCols);
            gridPane.getColumnConstraints().add(colConst);
        }

        for (int i = 0; i < numRows; i++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPercentHeight(100.0 / numRows);
            gridPane.getRowConstraints().add(rowConst);
        }
*/


        Scene scene = new Scene(root, 800, 900);

        primaryStage.setTitle("Wire World");
        primaryStage.setScene(scene);
        primaryStage.show();


        // scene.getStylesheets().add("pl.edu.pw.ee.jimp2.gr11.wireworld.main.view.wireWorldStyle");

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) { //potrzebne?

    }
}
