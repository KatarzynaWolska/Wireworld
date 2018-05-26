package pl.edu.pw.ee.jimp2.gr11.wireworld.main.view.mainmenu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
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
    private WarningWindowController warningWindow;//= new WarningWindowController(root);
    private Thread thread;


    @FXML
    private GridPane grid;

    @FXML
    private List<Button> buttons;

    @FXML
    private Button startStop;

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

        } else if (colorPicker.getId().equals("headColor")) {
            game.setHeadColor(newColor.toString());
        } else if (colorPicker.getId().equals("conductorColor")) {
            game.setConductorColor(newColor.toString());
        } else {
            game.setBlankColor(newColor.toString());
        }
    }




    public String newColorForCell(String color, String id) { //na razie nie działa jak powinno, jutro poprawię
        String[] coords = id.split(("(?!^)"));
        int x = 10 * Integer.parseInt(coords[4]) + Integer.parseInt(coords[5]);
        int y = 10 * Integer.parseInt(coords[6]) + Integer.parseInt(coords[7]);
//TODO: zamien te stringi w nawiasach na zmienne. BTW lepiej by było przenisc te metode do Game. zmienne kolorów też.
// np. "private String headColor = "-fx-background-color: rgb(0, 0, 255)". dzieki temu będzie można
// kolory w łatwy sposob przechwycić do pickera


        Cell c = game.getActualGeneration().getCell(x, y);
        int i = game.getActualGeneration().getCells().indexOf(c);
        game.getActualGeneration().getCells().remove(i);

        if (color.equals("0x000000ff")) {
            game.getActualGeneration().getCells().add(i, new Conductor(x, y));
            return "-fx-background-color: rgb(255, 255, 0)";
        } else if (color.equals("0xffff00ff")) {
            game.getActualGeneration().getCells().add(i, new Tail(x, y));
            return "-fx-background-color: rgb(255, 0, 0)";
        } else if (color.equals("0xff0000ff")) {
            game.getActualGeneration().getCells().add(i, new Head(x, y));
            return "-fx-background-color: rgb(0, 0, 255)";
        } else if (color.equals("0xff0000ff")) {
            game.getActualGeneration().getCells().add(i, new Blank(x, y));
            return "-fx-background-color: rgb(0, 0, 0)";
        } else {
            game.getActualGeneration().getCells().add(i, new Blank(x, y));
            return "-fx-background-color: rgb(0, 0, 0)";
        }
    }

    public void pressTile(ActionEvent event) {

        cellButton = (Button) event.getSource();

        Color color = (Color) cellButton.getBackground().getFills().get(0).getFill();
        String id = cellButton.getId();

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
            ImageSaver imageSaver = new ImageSaver(game.getActualGeneration(), "PNG", file.getAbsolutePath());
            //imageSaver.setHeadColor(game.getHeadColor());
            //...
            imageSaver.makeImage();
        }
    }

    public void pressStart(ActionEvent event) {
        if(thread == null) {
            GameStarter startGame = new GameStarter();

            thread = new Thread(startGame);

            thread.start();
        }
        else {
            thread.interrupt();
            thread = null;
        }

    }

    @FXML
    private void initialize() {

        this.game = new Game();//NOTE: game powinno byc tu inicjowane, ale liczba generacji powinna
        //być -1 albo cos( aby zaznaczyc ze maja sie wykonywac dopoki sie nie skoncza komorki). bo liczbe genracji
        //bedziemy przecież przechwytywac przy starcie(najlepeij wyskakujace okno do tego, żeby nie mieszać poleceń
        // z labelem do wyswietlania aktualnej.) i jak ktoś ustali to wtedy dopiero zmienić.
        //currentGenNumber.setText("00");

        //warningLabel.setText("kdkdkkdkd");*/
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
        //FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("tekstowy (*.txt)",
        //     " *.txt");
        //fileChooser.getExtensionFilters().add(extFilter);
        //fileChooser.setInitialDirectory(new File(System.getProperty("src/files/configfiles")));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("tekstowy (*.txt)",
                " *.txt"));
        fileChooser.showOpenDialog(new Stage());

        //tu trzeba podpiąć readera i monit jesli jest zly plik konfiguracyjny

    }

    class GameStarter implements Runnable {
        @Override
        public void run() {
            try{
                while (game.performGame()) {

                    for (Cell c : game.getActualGeneration().getCells()) {
                        int index = game.getActualGeneration().getCells().indexOf(c);
                        buttons.get(index).setStyle(game.setColor(c));
                    }

                    Thread.sleep(1000);

                }
            } catch (InterruptedException e) {
                //System.exit(0);
                Thread.currentThread().interrupt();
                //e.printStackTrace();
            }

        }
    }


}
