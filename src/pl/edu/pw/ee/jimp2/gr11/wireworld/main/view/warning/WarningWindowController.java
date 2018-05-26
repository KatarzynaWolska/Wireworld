package pl.edu.pw.ee.jimp2.gr11.wireworld.main.view.warning;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class WarningWindowController {//niepotrzeebna
    //nie moge ustawić tu otwieranai nowego okna. jakis LoadException z fxml
    @FXML
    private Label warningLabel;//= new Label("nana");
    private Parent root;

    public WarningWindowController(Parent root) {
        this.root = root;
    }

    public void showWarning(String text) {
        try {
            root = FXMLLoader.load(getClass().getResource("../mainmenu/WarningWindow.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Uwaga:");
            stage.setScene(new Scene(root, 400, 200));
            warningLabel = new Label(text);
            warningLabel.setText(text);
            //trzeba jakos zrobic zmiane teksu labela
            stage.show();
            // Hide this current window (if this is what you want)
            //((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
