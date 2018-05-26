package pl.edu.pw.ee.jimp2.gr11.wireworld.main.view.mainmenu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pl.edu.pw.ee.jimp2.gr11.wireworld.main.logic.Game;
import pl.edu.pw.ee.jimp2.gr11.wireworld.main.logic.generations.Generation;
import pl.edu.pw.ee.jimp2.gr11.wireworld.main.logic.generations.cells.*;
import pl.edu.pw.ee.jimp2.gr11.wireworld.main.utils.ConfigFileSaver;
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
    public Button saver;
    public Scene scene;
    private WarningWindowController warningWindow;//= new WarningWindowController(root);

    @FXML
    private Label warningLabel;//= new Label("nana");
    @FXML
    private Label currentGenNumber;

    @FXML
    private GridPane grid;

    @FXML
    private List<Button> buttons;





    public void pressTile(ActionEvent event) {

        cellButton = (Button) event.getSource();

        Color color = (Color) cellButton.getBackground().getFills().get(0).getFill();
        String id = cellButton.getId();

        cellButton.setStyle(game.newColorForCell(color.toString(), id));

    }

    public void pressSave(ActionEvent event) {
        //saver = (Button) event.getSource();
        warningWindow = new WarningWindowController(root);
        Node node = (Node) event.getSource();

        FileChooser dir = new FileChooser();
        dir.setTitle("Zapisz " + node.getId());


        if (node.getId().equals("konfigurację")) {
            //tak to będzie wyglądać. jesli ktoś kliknął stop to moze zapisac konfigurację. opcjonalnie mozemy
            //to usunąć i pozwolic uzytkownikowi zawsze ją zapisać
            if (true) {//game.isStopped() == false

                showWarning("Aby zapisać generację do pliku należy napierw zatrzymać grę przycieskiem.", event);

            } else {
                Generation genToSaveIntoConfigFile = game.getActualGeneration();


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

    public void pressStart(ActionEvent event) {

        GameStarter startGame = new GameStarter();

        startGame.start();

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
            for(int j = 0; j < 24; j++) {
                Button b = new Button();
                b.setMaxHeight(1.7976931348623157E308);
                b.setMaxWidth(1.7976931348623157E308);
                b.setMnemonicParsing(false);
                b.setOnAction(this::pressTile);
                b.setPrefHeight(0.0);
                b.setPrefWidth(5.0);
                if(i < 10) {
                    if(j < 10) {
                        b.setId("tile0"+i+"0"+j);
                    }
                    else {
                        b.setId("tile0"+i+j);
                    }
                }
                else {
                    if(j < 10) {
                        b.setId("tile"+i+"0"+j);
                    }
                    else {
                        b.setId("tile"+i+j);
                    }
                }
                buttons.add(b);
                grid.add(b,j,i);
            }
        }

    }


    public void showWarning(String text, ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("WarningWindow.fxml"));
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle("Uwaga:");
            Scene scene = new Scene(root, 400, 200);
            stage.setScene(scene);
            //warningLabel = new Label("inicjacja");
            //warningLabel.setText("dd");
            //trzeba jakos zrobic zmiane teksu labela
            stage.show();
            // Hide this current window (if this is what you want)
            //((Node) (event.getSource())).getScene().getWindow().fireEvent(event);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    class GameStarter extends Thread {
        @Override
        public void run() {
            while(game.performGame()) {

                for (Cell c : game.getActualGeneration().getCells()) {
                    int index = game.getActualGeneration().getCells().indexOf(c);
                    buttons.get(index).setStyle(game.setColor(c));
                }

                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    //System.exit(0);
                    e.printStackTrace();
                }

            }

        }
    }


}
