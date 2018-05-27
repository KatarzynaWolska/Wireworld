package pl.edu.pw.ee.jimp2.gr11.wireworld.main.view.mainmenu;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import pl.edu.pw.ee.jimp2.gr11.wireworld.main.utils.ConfigFileReader;
import pl.edu.pw.ee.jimp2.gr11.wireworld.main.utils.ConfigFileSaver;
import pl.edu.pw.ee.jimp2.gr11.wireworld.main.utils.GifFileSaver;
import pl.edu.pw.ee.jimp2.gr11.wireworld.main.utils.ImageSaver;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {
    Parent root;
    private Button cellButton;
    private Game game;
    private ColorPicker colorPicker;
    private Thread thread;
    private boolean stopped;

    @FXML
    ColorPicker headColor;
    @FXML
    ColorPicker tailColor;
    @FXML
    ColorPicker conductorColor;
    @FXML
    ColorPicker blankColor;

    @FXML
    private Button animation;
    @FXML
    private Button image;
    @FXML
    private Button configuration;
    @FXML
    private GridPane grid;

    @FXML
    private List<Button> buttons;

    @FXML
    private Button startStop;

    @FXML
    private Label currentGenNumber;


    /*public MainMenuController() {
        initialize();
    }
*/


    public void handleColor(ActionEvent event) { //todo: po wybraniu koloru tutaj trzeba tez zmienić kolor wszytskich komórek na planszy!
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

        if (thread == null) {

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

        } else {

        }


    }


    public void pressSaveConfigurationFile(ActionEvent event) {
        //saver = (Button) event.getSource();
        Node node = (Node) event.getSource();


        if (!game.isGameContinued()) {
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

        if (file != null) {
            GifFileSaver gifFileSaver = new GifFileSaver("src/files/images", game, file.getAbsolutePath());
        }
    }

    public void pressSaveImage(ActionEvent event) {
        Node node = (Node) event.getSource();


        if (game.isGameContinued() == false) {
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

                ImageSaver imageSaver = new ImageSaver(game, "PNG", file.getAbsolutePath());
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
            stopped = false;
            animation.setDisable(true);
            image.setDisable(true);
            configuration.setDisable(true);

            GameStarter startGame = new GameStarter();

            thread = new Thread(startGame);

            thread.start();
        } else {
            animation.setDisable(false);
            image.setDisable(false);
            configuration.setDisable(false);

            stopped = true;
            thread.interrupt();
            thread = null;
        }

    }

    /*@FXML
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
    */

    private void makeAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.setResizable(true);

        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("wireWorldStyle.css").toExternalForm());
        dialogPane.getStyleClass().add("myDialog");

        alert.showAndWait();
    }

    public void showHelp() {
        String content = "Gra jest symulatorem automatu komórkowego 'WireWorld' autorstwa Briana Silvermana.\n" +
                "Aby zobaczyć zasady gry kliknij w przycisk \"Zasady gry\". \n Aby wstawić własną konfigurację kliknij " +
                "Wstaw->Plik konfiguracyjny, a następnie wybierz plik w formacie tekstowym (*.txt).\n" +
                "\nAby rozpocząć grę ustaw komórki na planszy za pomocą kliknięć. Plansza domyslnie ma wszytskie komórki puste." +
                " Jedno kliknięcie na wybraną komórkę ustawia ją jako głowę, dwa - ogon, trzy - przewodnik, a cztery - " +
                "ponownie jako pustą.\n\n Istnieje możliwość ustawienia własnych kolorów dla wybranych typów komórek." +
                "" +
                "\n";

        makeAlert("Wire World", "Pomoc", content);
    }


    public void showRules() { //todo zrobic to alertem albo tooltip
        //tooltipu sie nie da , bo syzbko gasnie i nie da się ogarnac tekstu - jest jedna dluga linia

        String content = "Gra jest symulatorem automatu komórkowego 'WireWorld' autorstwa Briana Silvermana.\n" +
                " Każda komórka na planszy może znajdować się w jednym z czterech następujących stanów, które domyślnie oznacza " +
                "się następującymi kolorami: \n\n*Pusta - kolor czarny, \n*Głowa elektronu - kolor czerwony, \n*Ogon elektronu " +
                "- kolor niebieski, \n*Przewodnik - kolor żółty .\nW automacie stosuje się sąsiedztwo Moore'a, które za" +
                " sąsiada traktuje każdą komórkę położoną na: północ, północny-wschód, wschód, południowy-wschód, " +
                "południe, południowy-zachód, zachód i północny-zachód od danej komórki. W 'WireWorld' stosuje się " +
                "następujące zasady: \n\n1) Komórka pozostaje pusta jeśli była pusta. \n2) Komórka staje się ogonem elektronu," +
                " jeśli była głową elektronu. \n3) Komórka staje się przewodnikiem, jeśli była ogonem elektronu. \n4) " +
                "Komórka staje się głową elektronu tylko wtedy, gdy dokładnie 1 lub 2 sąsiadujące komórki są głowami " +
                "elektronu. \n5) Komórka staje się przewodnikiem w każdym innym wypadku." +
                "\n";

        makeAlert("WireWorld", "Zasady Gry", content);

    }

    public void handleConfigFile(ActionEvent event) {
        MenuItem item = (MenuItem) event.getSource();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Wybierz plik konfiguracyjny:");

        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("tekstowy (*.txt)",
                " *.txt"));
        fileChooser.showOpenDialog(new Stage());


        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("plik tekstowy (*.txt)",
                " *.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        //extFilter = new FileChooser.ExtensionFilter("plik jpg (*.jpg)", " *.jpg");

        File file = fileChooser.showSaveDialog(new Stage());
        while (true) {
            if (file != null) {

                //tu trzeba podpiąć readera i monit jesli jest zly plik konfiguracyjny
                ArrayList<Cell> cells = null;
                ConfigFileReader configFileReader = new ConfigFileReader();

                try {
                    cells = configFileReader.readFile(file.getAbsolutePath());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (cells != null) {
                    //game.getActualGeneration().setCells(cells);
                    // ^^^ todo: Kasia, zobacz czy to ok. czy w ten sposob masz tam zapisywane komórki
                    break;
                } else {
                    makeAlert("Wire World", "Plik konfiguracyjy", "Wczytywanie pliku konfiguracyjnego" +
                            " nie powiodło. Zły format pliku. Plik powinien mieć 20 wierszy i 24 kolumny.\n\n" +
                            "Powinien składać się z cyfr 0 (pusta), 1 (przewód), 2 (głowa), 3 (ogon) oddzielonych spacjami" +
                            " i podzielonych na wiersze oraz kolumny.\n\n");
                    file = fileChooser.showSaveDialog(new Stage());
                }

            } else {
                break;
            }
        }
    }

    public synchronized void setGenerationNumberLabel(String number) {
        currentGenNumber.setText(number);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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

            headColor.setValue(new Color(1, 0, 0, 1));
            tailColor.setValue(new Color(0, 0, 1, 1));
            conductorColor.setValue(new Color(1, 1, 0, 1));
            blankColor.setValue(new Color(0, 0, 0, 1));

            game.setTail(new Color(0, 0, 1, 1));
            game.setHead(new Color(1, 0, 0, 1));
            game.setConductor(new Color(1, 1, 0, 1));
            game.setBlank(new Color(0, 0, 0, 1));
        }
    }

    /*public void setGenerationNumberLabel (ActionEvent event) {
        Label l = (Label) event.getSource();
        l.setText(Integer.toString(game.getActualGenerationNumber()));
    }*/

    class GameStarter implements Runnable {
        ImageSaver imageSaver;

        @Override
        public void run() {
            try {

                imageSaver = new ImageSaver(game, "PNG", "src/files/images" + game.getActualGenerationNumber() + ".png");
                imageSaver.setColors(game.getHead(), game.getTail(), game.getConductor(), game.getBlank());

                imageSaver.makeImage();
                //setGenerationNumberLabel("0");

                while (game.performGame()) {
                    //if(game.isGameContinued() == false)
                    //  game.setNumberOfGenerations(game.getActualGenerationNumber());

                    game.incrementActualGenerationNumber();
                    imageSaver = new ImageSaver(game, "PNG", "src/files/images" + game.getActualGenerationNumber() + ".png");
                    imageSaver.setColors(game.getHead(), game.getTail(), game.getConductor(), game.getBlank());
                    imageSaver.makeImage();
                    //setGenerationNumberLabel(Integer.toString(game.getActualGenerationNumber()));
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            setGenerationNumberLabel(Integer.toString(game.getActualGenerationNumber()));
                        }
                    });

                    for (Cell c : game.getActualGeneration().getCells()) {
                        int index = game.getActualGeneration().getCells().indexOf(c);
                        buttons.get(index).setStyle(game.setColor(c));
                    }
                    Thread.sleep(1000);
                }

                game.setNumberOfGenerations(game.getActualGenerationNumber());
                //włączenie przycisku zapisz animacje
                //animation.setDisable(false);
                //}while(game.isGameContinued());

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

        }
    }


}
