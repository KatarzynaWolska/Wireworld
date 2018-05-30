package pl.edu.pw.ee.jimp2.gr11.wireworld.main.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class GameControllerHelper {
    private String style = "../view/wireWorldStyle.css";

    public GameControllerHelper() {
    }

    public void makeAlertForWrongConfigFile() {
        makeAlert("Wire World", "Plik konfiguracyjny", "Wczytywanie pliku konfiguracyjnego" +
                " nie powiodło. Zły format pliku. Plik powinien mieć 20 wierszy i 24 kolumny.\n\n" +
                "Powinien składać się z cyfr 0 (pusta), 1 (przewód), 2 (ogon), 3 (głowa) oddzielonych spacjami" +
                " i podzielonych na wiersze oraz kolumny.\n\n");
    }

    public TextInputDialog setTextInputDialogForGenNumber() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Ustaw liczbę generacji");
        dialog.setHeaderText("Wpisz ilość generacji");

        DialogPane dialogPane = dialog.getDialogPane();
        setStyleForPane(dialogPane);

        return dialog;
    }

    public void setPreviewForConfig(MenuItem diode) {

        Image image = new Image("files/configfiles/preview/" + diode.getId() + ".JPG");

        Rectangle rect = new Rectangle();
        rect.setHeight(95);
        rect.setWidth(100);
        rect.setFill(new ImagePattern(image));

        diode.setGraphic(rect);
    }


    public void makeAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.setResizable(true);


        DialogPane dialogPane = alert.getDialogPane();
        setStyleForPane(dialogPane);

        alert.showAndWait();
    }

    private void setStyleForPane(DialogPane d) {
        d.getStylesheets().add(getClass().getResource(style).toExternalForm());
        d.getStyleClass().add("myDialog");
    }

    public void helpWindow() {
        String content = "Gra jest symulatorem automatu komórkowego 'WireWorld' autorstwa Briana Silvermana.\n" +
                "Aby zobaczyć zasady gry kliknij w przycisk \"Zasady gry\". \n Aby wstawić własną konfigurację kliknij " +
                "Wstaw->Plik konfiguracyjny, a następnie wybierz plik w formacie tekstowym (*.txt). Istnieje mozliwość" +
                "dodania gotowych bramke logicznych i struktur. W tym celu nalezy kliknąć Wstaw i wybrać odpowiednią " +
                "opcję. Każdy schemat ma jest przedstawiony na miniaturze z boku.\n" +
                "\nAby rozpocząć grę ustaw komórki na planszy za pomocą kliknięć. Plansza domyslnie ma wszystkie komórki puste." +
                " Jedno kliknięcie na wybraną komórkę ustawia ją jako przewodnik, dwa - ogon, trzy - głowę, a cztery - " +
                "ponownie jako pustą.\n\n Istnieje możliwość ustawienia własnych kolorów dla wybranych typów komórek.\n" +
                "Po ustawieniu komórek można ustawić preferowaną liczbę generacji (Ustawienia->Ustaw liczbę generacji)," +
                " a następnie należy kliknąć przycisk \"START\" - wtedy gra się rozpocznie. " +
                "Aby zapisać animację z dotychczasowego przebiegu gry nalezy nacisnąć przycisk \"Zapisz animację\"." +
                "Istnieje także mozliwość zapisu obrazu w formacie PNG (za pomocą przycisku \"Zapisz obraz" +
                "\") oraz pliku konfiguracyjnego w formacie TXT (opcja \"Zapisz plik konfiguracyjny\"). Uprzednio nalezy" +
                "zatrzymać grę przyciskiem \"STOP\". \n" +
                "\nŻeby wznowić grę należ wcisnąć \"START\". \n\n Aby rozpocząć nową grę kliknij Ustawienia->" +
                " Zresetuj aktualną grę.\n\n" + "(UWAGA: Plansza wytwarza generację wolniej niż animacja je przedstawia, aby użytkownik miał szansę " +
                "zatrzymać i zapisać wybraną przez siebie planszę bądź plik konfiguracyjny.)";

        makeAlert("Wire World", "Pomoc", content);
    }


    public void rulesWindow() {
        String content = "Gra jest symulatorem automatu komórkowego 'WireWorld' autorstwa Briana Silvermana.\n" +
                " Każda komórka na planszy może znajdować się w jednym z czterech następujących stanów, które domyślnie oznacza " +
                "się następującymi kolorami: \n\n*Pusta - kolor czarny, \n*Głowa elektronu - kolor niebieski, \n*Ogon elektronu " +
                "- kolor czerwony, \n*Przewodnik - kolor żółty .\nW automacie stosuje się sąsiedztwo Moore'a, które za" +
                " sąsiada traktuje każdą komórkę położoną na: północ, północny-wschód, wschód, południowy-wschód, " +
                "południe, południowy-zachód, zachód i północny-zachód od danej komórki. W 'WireWorld' stosuje się " +
                "następujące zasady: \n\n1) Komórka pozostaje pusta jeśli była pusta. \n2) Komórka staje się ogonem elektronu," +
                " jeśli była głową elektronu. \n3) Komórka staje się przewodnikiem, jeśli była ogonem elektronu. \n4) " +
                "Komórka staje się głową elektronu tylko wtedy, gdy dokładnie 1 lub 2 sąsiadujące komórki są głowami " +
                "elektronu. \n5) Komórka staje się przewodnikiem w każdym innym wypadku.";

        makeAlert("WireWorld", "Zasady Gry", content);

    }
}

