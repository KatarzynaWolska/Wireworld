package pl.edu.pw.ee.jimp2.gr11.wireworld.main.view.mainmenu;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pl.edu.pw.ee.jimp2.gr11.wireworld.main.logic.Game;
import pl.edu.pw.ee.jimp2.gr11.wireworld.main.logic.generations.Generation;
import pl.edu.pw.ee.jimp2.gr11.wireworld.main.logic.generations.cells.*;
import pl.edu.pw.ee.jimp2.gr11.wireworld.main.logic.generations.cells.Cell;
import pl.edu.pw.ee.jimp2.gr11.wireworld.main.utils.ConfigFileSaver;
import pl.edu.pw.ee.jimp2.gr11.wireworld.main.utils.GifFileSaver;
import pl.edu.pw.ee.jimp2.gr11.wireworld.main.utils.ImageSaver;
import pl.edu.pw.ee.jimp2.gr11.wireworld.main.view.warning.WarningWindowController;


import javax.xml.stream.events.Namespace;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainMenuController {
    Parent root;
    private Button cellButton;
    private Game game;
    private ColorPicker colorPicker;
    private Thread thread;

    @FXML
    private GridPane grid;

    @FXML
    private List<Button> buttons;

    @FXML
    private Button startStop;

    @FXML
    private Label currentGenNumber;

    public void handleColor(ActionEvent event) {
        colorPicker = (ColorPicker) event.getSource();
        Color newColor = colorPicker.getValue();
        // System.out.println((new java.awt.Color((float)newColor.getRed(), (float)newColor.getGreen(),
        //       (float)newColor.getBlue(), (float)newColor.getOpacity())));
        //StringBuilder b = new StringBuilder("rgb(");
        //b.append(newColor.getRed()).append(", ").append(newColor.getGreen()).append(", ").append(newColor.getBlue());
        //b.append(")");
        //System.out.println(b.toString());
        //System.out.println(newColor.getBlue());

        if (colorPicker.getId().equals("tailColor")) {
            game.setTailColor(newColor.toString());
            game.setTail(newColor);

        } else if (colorPicker.getId().equals("headColor")) {
            game.setHeadColor(newColor.toString());
            game.setHead(newColor);

        } else if (colorPicker.getId().equals("conductorColor")) {
            game.setConductor(newColor);
            game.setConductorColor(newColor.toString());
        } else {
            game.setBlank(newColor);
            game.setBlankColor(newColor.toString());
        }
    }


    public void pressTile(ActionEvent event) {

        if(thread == null) {

            cellButton = (Button) event.getSource();

            Color color = (Color) cellButton.getBackground().getFills().get(0).getFill();
            String id = cellButton.getId();

            //newColorForCell by zwracało newColor np  Color(12,12,12,11))
            //a tak by się ustawiało vvv
            //cellButton.setBackground(new Background(new BackgroundFill(newColor, CornerRadii.EMPTY, Insets.EMPTY)));


            cellButton.setStyle(game.newColorForCell(color.toString(), id, game));

            //cellButton.setStyle(newColorForCell(color.toString(), id));

            //System.out.println(color.toString());

            //cellButton.getStyleClass().add("headCell");//tak tez sie da ale tu trudniej bedzie zmienić paletę.

            //wywołanie metody która zwraca kolor do ustawienia, a pobiera aktualny + fxid
            //tam w sordku sobie ustawia komorke ktora trzeba na stan ktory powinna i zwraca kolor stanu
            // czyli zwraca String np  "-fx-background-color: rgb(0, 0, 0)" do zmiennej lokalnej jakiejs
            //i wywoluje się dalej: cellButton.setStyle(newColorForCell);
            // cos w tym stylu :
            // cellButton.setStyle("-fx-background-color: rgb(0, 0, 0)");

        }
        else {

        }


    }


    public void pressSaveConfigurationFile(ActionEvent event) {
        //saver = (Button) event.getSource();
        Node node = (Node) event.getSource();


        if (game.isStopped() == false) {
            makeAlert("Wire World", "Nie można teraz zapisać pliku konfiguracyjnego.",
                    "Aby to zrobić musisz najpierw zatrzymać grę przyciskiem \"STOP\" na wybranej generacji.");

        } else {
            Generation genToSaveIntoConfigFile = game.getActualGeneration();

            FileChooser dir = new FileChooser();
            dir.setTitle("Zapisz plik konfiguracyjny:");

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
            ConfigFileSaver configFileSaver = new ConfigFileSaver(file.getAbsolutePath(), testGen);//tu zmienic potem generacje
        }


    }

    public void pressSaveAnimation(ActionEvent event) {
        Node node = (Node) event.getSource();

        FileChooser dir = new FileChooser();
        dir.setTitle("Zapisz animację:");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("plik GIF (*.gif)",
                " *.gif");
        dir.getExtensionFilters().add(extFilter);


        File file = dir.showSaveDialog(new Stage());
        GifFileSaver gifFileSaver = new GifFileSaver("src/files/images", file.getAbsolutePath());

    }

    public void pressSaveImage(ActionEvent event) {
        Node node = (Node) event.getSource();


        if (game.isStopped() == false) {
            makeAlert("Wire World", "Nie można teraz zapisać obrazu.",
                    "Aby to zrobić musisz najpierw zatrzymać grę przyciskiem \"STOP\" na wybranej generacji.");

        } else {
            FileChooser dir = new FileChooser();
            dir.setTitle("Zapisz obraz:");

            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("plik PNG (*.png)",
                    " *.png");
            dir.getExtensionFilters().add(extFilter);
            //extFilter = new FileChooser.ExtensionFilter("plik jpg (*.jpg)", " *.jpg");

            File file = dir.showSaveDialog(new Stage());
            if (file != null) {
                ImageSaver imageSaver = new ImageSaver(game.getActualGeneration(), "PNG", file.getAbsolutePath());
                //imageSaver.setHeadColor(game.getHeadColor());
                imageSaver.setBlankColor(game.getBlank());
                imageSaver.setHeadColor(game.getBlank());
                imageSaver.setTailColor(game.getConductor());
                imageSaver.setConductorColor(game.getConductor());
                imageSaver.makeImage();
            }
        }
    }

    public void pressStart(ActionEvent event) {
        if (thread == null) {
            GameStarter startGame = new GameStarter();

            thread = new Thread(startGame);

            thread.start();
        } else {
            thread.interrupt();
            thread = null;
        }

    }

    @FXML
    private void initialize() {

        this.game = new Game();
        buttons = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 24; j++) {
                Button b = new Button();
                b.setMaxHeight(1.7976931348623157E308);
                b.setMaxWidth(1.7976931348623157E308);
                b.setMnemonicParsing(false);
                b.setOnAction(this::pressTile);
                b.setPrefHeight(0.0);
                b.setPrefWidth(5.0);
                if (i < 10) {
                    if (j < 10) {
                        b.setId("tile0" + i + "0" + j);
                    } else {
                        b.setId("tile0" + i + j);
                    }
                } else {
                    if (j < 10) {
                        b.setId("tile" + i + "0" + j);
                    } else {
                        b.setId("tile" + i + j);
                    }
                }
                buttons.add(b);
                grid.add(b, j, i);
            }

            startStop = new Button();
            startStop.setOnAction(this::pressStart);

            currentGenNumber.setText(Integer.toString(game.getActualGenerationNumber()));
        }

    }

    private void makeAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);

        alert.showAndWait();
    }


    public void showRules(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("RulesWindow.fxml"));
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle("Zasady gry WireWorld");
            Scene scene = new Scene(root, 500, 800);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void handleConfigFile(ActionEvent event) {
        MenuItem item = (MenuItem) event.getSource();
        //System.out.println("doks");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Wybierz plik konfiguracyjny:");

        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("tekstowy (*.txt)",
                " *.txt"));
        fileChooser.showOpenDialog(new Stage());

        if (fileChooser != null) {
            //tu trzeba podpiąć readera i monit jesli jest zly plik konfiguracyjny

        }

    }

    public synchronized void setGenerationNumber (String number) {
        currentGenNumber.setText(number);
    }

    /*public void setGenerationNumber (ActionEvent event) {
        Label l = (Label) event.getSource();
        l.setText(Integer.toString(game.getActualGenerationNumber()));
    }*/

    class GameStarter implements Runnable {
        @Override
        public void run() {
            try {
                while (game.performGame()) {

                    game.incrementActualGenerationNumber();
                    //setGenerationNumber(Integer.toString(game.getActualGenerationNumber()));
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            setGenerationNumber(Integer.toString(game.getActualGenerationNumber()));
                        }
                    });

                    for (Cell c : game.getActualGeneration().getCells()) {
                        int index = game.getActualGeneration().getCells().indexOf(c);
                        buttons.get(index).setStyle(game.setColor(c));
                    }

                    Thread.sleep(1000);

                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

        }
    }


}
