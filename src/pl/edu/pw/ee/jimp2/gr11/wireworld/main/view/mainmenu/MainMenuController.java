package pl.edu.pw.ee.jimp2.gr11.wireworld.main.view.mainmenu;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pl.edu.pw.ee.jimp2.gr11.wireworld.main.logic.Game;
import pl.edu.pw.ee.jimp2.gr11.wireworld.main.logic.generations.Generation;
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
import java.util.Optional;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {
    private Button cellButton;
    private Game game;
    private ColorPicker colorPicker;
    private Thread thread;
    private boolean stopped;
    private boolean isDirectoryClean;
    ImageSaver imageSaver;


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


    public void handleColor(ActionEvent event) {
        colorPicker = (ColorPicker) event.getSource();
        Color newColor = colorPicker.getValue();
        double redColor = newColor.getRed() * 255;
        double greenColor = newColor.getGreen() * 255;
        double blueColor = newColor.getBlue() * 255;
        StringBuilder b = new StringBuilder("rgb( ");
        b.append(redColor).append(", ").append(greenColor).append(", ").append(blueColor);
        b.append(", 1)");

        if (colorPicker.getId().equals("tailColor")) {
            game.setTailColor(b.toString());
            game.setTail(newColor);
            setStylesForCells();

        } else if (colorPicker.getId().equals("headColor")) {
            game.setHeadColor(b.toString());
            game.setHead(newColor);
            setStylesForCells();

        } else if (colorPicker.getId().equals("conductorColor")) {
            game.setConductor(newColor);
            game.setConductorColor(b.toString());
            setStylesForCells();
        } else {
            game.setBlank(newColor);
            game.setBlankColor(b.toString());
            setStylesForCells();
        }
    }


    public void pressTile(ActionEvent event) {

        if (thread == null) {

            cellButton = (Button) event.getSource();
            String id = cellButton.getId();
            cellButton.setStyle(game.newColorForCell(id, game));

        } else {

        }


    }


    public void pressSaveConfigurationFile(ActionEvent event) {


        FileChooser dir = new FileChooser();
        dir.setTitle("Zapisz plik konfiguracyjny:");

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("tekstowy (*.txt)",
                " *.txt");
        dir.getExtensionFilters().add(extFilter);


        File file = dir.showSaveDialog(new Stage());
        ConfigFileSaver configFileSaver = new ConfigFileSaver(file.getAbsolutePath(), game.getActualGeneration());


    }

    public void pressSaveAnimation(ActionEvent event) {

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

        FileChooser dir = new FileChooser();
        dir.setTitle("Zapisz obraz:");

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("plik PNG (*.png)",
                " *.png");
        dir.getExtensionFilters().add(extFilter);

        File file = dir.showSaveDialog(new Stage());
        if (file != null) {

            ImageSaver is = new ImageSaver(game, "PNG", file.getAbsolutePath());
            is.setBlankColor(game.getBlank());
            is.setHeadColor(game.getHead());
            is.setTailColor(game.getTail());
            is.setConductorColor(game.getConductor());
            is.makeImage();
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
                " Jedno kliknięcie na wybraną komórkę ustawia ją jako przewodnik, dwa - ogon, trzy - głowę, a cztery - " +
                "ponownie jako pustą.\n\n Istnieje możliwość ustawienia własnych kolorów dla wybranych typów komórek." +
                "" +
                "\n";

        makeAlert("Wire World", "Pomoc", content);
    }


    public void showRules() { 
        String content = "Gra jest symulatorem automatu komórkowego 'WireWorld' autorstwa Briana Silvermana.\n" +
                " Każda komórka na planszy może znajdować się w jednym z czterech następujących stanów, które domyślnie oznacza " +
                "się następującymi kolorami: \n\n*Pusta - kolor czarny, \n*Głowa elektronu - kolor niebieski, \n*Ogon elektronu " +
                "- kolor czerwony, \n*Przewodnik - kolor żółty .\nW automacie stosuje się sąsiedztwo Moore'a, które za" +
                " sąsiada traktuje każdą komórkę położoną na: północ, północny-wschód, wschód, południowy-wschód, " +
                "południe, południowy-zachód, zachód i północny-zachód od danej komórki. W 'WireWorld' stosuje się " +
                "następujące zasady: \n\n1) Komórka pozostaje pusta jeśli była pusta. \n2) Komórka staje się ogonem elektronu," +
                " jeśli była głową elektronu. \n3) Komórka staje się przewodnikiem, jeśli była ogonem elektronu. \n4) " +
                "Komórka staje się głową elektronu tylko wtedy, gdy dokładnie 1 lub 2 sąsiadujące komórki są głowami " +
                "elektronu. \n5) Komórka staje się przewodnikiem w każdym innym wypadku." +
                "\n";

        makeAlert("WireWorld", "Zasady Gry", content);

    }

    public void resetGame(ActionEvent event) {
        currentGenNumber.setText("0");
        game.setActualGeneration(new Generation());
        game.setIsNumberOfGenerationSet(false);
        game.setNumberOfGenerations(0);
        game.setActualGenerationNumber(0);


        setStylesForCells();

    }

    public void setTotalGenerationsNumber(ActionEvent event) {
        TextInputDialog genNumberDialog = new TextInputDialog();
        genNumberDialog.setTitle("Ustaw liczbę generacji");
        genNumberDialog.setHeaderText("Wpisz ilość generacji");

        Optional<String> result = genNumberDialog.showAndWait();

        result.ifPresent(name -> {
            game.setNumberOfGenerations(Integer.parseInt(name));
            game.setIsNumberOfGenerationSet(true);
        });


    }

    public void insertDiode(ActionEvent event) {
        ConfigFileReader reader = new ConfigFileReader();
        try {
            game.getActualGeneration().setCells(reader.readFile("src/files/configfiles/diode.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setStylesForCells();
    }

    public void insertReversedDiode(ActionEvent event) {
        ConfigFileReader reader = new ConfigFileReader();
        try {
            game.getActualGeneration().setCells(reader.readFile("src/files/configfiles/diode-reverse.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setStylesForCells();
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

        File file = fileChooser.showSaveDialog(new Stage());
        while (true) {
            if (file != null) {

                ArrayList<Cell> cells = null;
                ConfigFileReader configFileReader = new ConfigFileReader();

                try {
                    cells = configFileReader.readFile(file.getAbsolutePath());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (cells != null) {
                    game.getActualGeneration().setCells(cells);
                    setStylesForCells();
                    break;
                } else {
                    makeAlert("Wire World", "Plik konfiguracyjny", "Wczytywanie pliku konfiguracyjnego" +
                            " nie powiodło. Zły format pliku. Plik powinien mieć 20 wierszy i 24 kolumny.\n\n" +
                            "Powinien składać się z cyfr 0 (pusta), 1 (przewód), 2 (ogon), 3 (głowa) oddzielonych spacjami" +
                            " i podzielonych na wiersze oraz kolumny.\n\n");
                    file = fileChooser.showSaveDialog(new Stage());
                }

            } else {
                break;
            }
        }
    }

    public void setStylesForCells() {
        for (Cell c : game.getActualGeneration().getCells()) {
            int index = game.getActualGeneration().getCells().indexOf(c);
            buttons.get(index).setStyle(game.setColor(c));
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

            grid.setGridLinesVisible(true);

            startStop = new Button();
            startStop.setOnAction(this::pressStart);

            currentGenNumber.setText(Integer.toString(game.getActualGenerationNumber()));

            headColor.setValue(new Color(0, 0, 1, 1));
            tailColor.setValue(new Color(1, 0, 0, 1));
            conductorColor.setValue(new Color(1, 1, 0, 1));
            blankColor.setValue(new Color(0, 0, 0, 1));

            game.setTail(new Color(1, 0, 0, 1));
            game.setHead(new Color(0, 0, 1, 1));
            game.setConductor(new Color(1, 1, 0, 1));
            game.setBlank(new Color(0, 0, 0, 1));

            isDirectoryClean = false;
        }

    }


    class GameStarter implements Runnable {

        @Override
        public void run() {

            if (!game.getIsNumberOfGenerationSet()) {
                try {
                        imageSaver = new ImageSaver(game, "PNG", "src/files/images/image" + game.getActualGenerationNumber() + ".png");
                        if (isDirectoryClean == false)
                            imageSaver.deletePreviousFiles();

                    imageSaver.setColors(game.getHead(), game.getTail(), game.getConductor(), game.getBlank());
                        imageSaver.makeImage();

                        while (game.performGame()) {

                            game.incrementActualGenerationNumber();
                            imageSaver = new ImageSaver(game, "PNG", "src/files/images/image" + game.getActualGenerationNumber() + ".png");
                            imageSaver.setColors(game.getHead(), game.getTail(), game.getConductor(), game.getBlank());
                            imageSaver.makeImage();
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    setGenerationNumberLabel(Integer.toString(game.getActualGenerationNumber()));
                                }
                            });

                            setStylesForCells();

                            Thread.sleep(1000);
                        }

                        game.setNumberOfGenerations(game.getActualGenerationNumber());


                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }

                }

            else{
                    try {
                        for (int i = 0; i < game.getNumberOfGenerations(); i++) {
                            game.performGame();
                            game.incrementActualGenerationNumber();
                            imageSaver = new ImageSaver(game, "PNG", "src/files/images/image" + game.getActualGenerationNumber() + ".png");
                            if (isDirectoryClean == false)
                                imageSaver.deletePreviousFiles();
                            imageSaver.setColors(game.getHead(), game.getTail(), game.getConductor(), game.getBlank());
                            imageSaver.makeImage();
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    setGenerationNumberLabel(Integer.toString(game.getActualGenerationNumber()));
                                }
                            });

                            setStylesForCells();

                            Thread.sleep(1000);
                        }

                        game.setNumberOfGenerations(game.getActualGenerationNumber());


                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }


        }


    }



