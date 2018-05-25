package pl.edu.pw.ee.jimp2.gr11.wireworld.main.view.mainmenu;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pl.edu.pw.ee.jimp2.gr11.wireworld.main.logic.Game;
import pl.edu.pw.ee.jimp2.gr11.wireworld.main.logic.generations.Generation;
import pl.edu.pw.ee.jimp2.gr11.wireworld.main.logic.generations.cells.*;
import pl.edu.pw.ee.jimp2.gr11.wireworld.main.utils.ConfigFileSaver;
import pl.edu.pw.ee.jimp2.gr11.wireworld.main.utils.GifFileSaver;
import pl.edu.pw.ee.jimp2.gr11.wireworld.main.utils.ImageSaver;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainMenuController {
    Parent root;
    private Button cellButton;
    public Button saver;
    public Scene scene;
    private Game game;
    @FXML
    private Label warningLabel;


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

    public String newColorForCell (Color color, String id) {
        String [] coords = id.split(("(?!^)"));
        int x = 10*Integer.parseInt(coords[0])+Integer.parseInt(coords[1]);
        int y = 10*Integer.parseInt(coords[2])+Integer.parseInt(coords[3]);

        Cell c = game.getActualGeneration().getCell(x,y);
        int i = game.getActualGeneration().getCells().indexOf(c);
        game.getActualGeneration().getCells().remove(i);

        if(color == Color.BLACK) {
            game.getActualGeneration().getCells().add(i, new Conductor(x,y));
            return "-fx-background-color: rgb(255, 255, 0)";
        }
        else if(color == Color.YELLOW) {
            game.getActualGeneration().getCells().add(i, new Tail(x,y));
            return "-fx-background-color: rgb(255, 0, 0)";
        }
        else if(color == Color.RED) {
            game.getActualGeneration().getCells().add(i, new Head(x,y));
            return "-fx-background-color: rgb(0, 0, 255)";
        }
        else if(color == Color.BLUE) {
            game.getActualGeneration().getCells().add(i, new Blank(x,y));
            return "-fx-background-color: rgb(0, 0, 0)";
        }
        else {
            game.getActualGeneration().getCells().add(i, new Blank(x,y));
            return "-fx-background-color: rgb(0, 0, 0)";
        }
    }

    public void pressTile(ActionEvent event) {
        cellButton = (Button) event.getSource();

        Color color = (Color)cellButton.getBackground().getFills().get(0).getFill();
        String id = cellButton.getId();

        cellButton.setStyle(newColorForCell(color, id));

        //cellButton.getStyleClass().add("headCell");//tak tez sie da ale tu trudniej bedzie zmienić paletę.

        //wywołanie metody która zwraca kolor do ustawienia, a pobiera aktualny + fxid
        //tam w sordku sobie ustawia komorke ktora trzeba na stan ktory powinna i zwraca kolor stanu
        // czyli zwraca String np  "-fx-background-color: rgb(0, 0, 0)" do zmiennej lokalnej jakiejs
        //i wywoluje się dalej: cellButton.setStyle(newColorForCell);
        // cos w tym stylu :
        // cellButton.setStyle("-fx-background-color: rgb(0, 0, 0)");


    }

    public void pressSave(ActionEvent event) {
        //saver = (Button) event.getSource();
        Node node = (Node) event.getSource();


        FileChooser dir = new FileChooser();
        dir.setTitle("Zapisz " + node.getId());


        if (node.getId().equals("konfigurację")) {
            //tak to będzie wyglądać. jesli ktoś kliknął stop to moze zapisac konfigurację. opcjonalnie mozemy
            //to usunąć i pozwolic uzytkownikowi zawsze ją zapisać
            if (false) {//game.isStopped() == false
                showWarning("Aby zapisać generację do pliku należy napierw zatrzymać grę przycieskiem.");
            } else {
                //Generation genToSaveIntoConfigFile = game.getCurrentGeneration();


                //testowa generacja
                Generation testGen = new Generation();
                testGen.setHeight(1);
                testGen.setWidth(2);
                List<Cell> cells = new ArrayList<Cell>();
                testGen.setCells(cells);

                cells.add(new Tail(0, 0));
                cells.add(new Head(0, 1));
                cells.add(new Blank(1, 0));
                cells.add(new Conductor(1, 1));
                //koniec testGen

                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("tekstowy (*.txt)",
                        " *.txt");
                dir.getExtensionFilters().add(extFilter);


                File file = dir.showSaveDialog(new Stage());
                //System.out.println(file.getAbsolutePath());
                ConfigFileSaver s = new ConfigFileSaver(file.getAbsolutePath(), testGen);//tu zmienic potem generacje
            }

        } else if (node.getId().equals("animację")) {
            //gif
        } else {
            //int numberOfGeneration = game.getCurrentGeneration().getNumberOfGeneration();
            //ImageSaver i = new ImageSaver()
            //obraz
        }


    }

    @FXML
    private void initialize() {
        //warningLabel.setText("kdkdkkdkd");
    }

    public void showWarning(String text) {
        try {
            root = FXMLLoader.load(getClass().getResource("DialogWindow.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Uwaga:");
            stage.setScene(new Scene(root, 400, 200));
            //warningLabel.setText(text);
            //trzeba jakos zrobic zmiane teksu labela
            stage.show();
            // Hide this current window (if this is what you want)
            //((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
