package pl.edu.pw.ee.jimp2.gr11.wireworld.main.utils;

public class Generator {

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 24; j++)
                System.out.println(" <Button maxHeight=\"1.7976931348623157E308\" maxWidth=\"1.7976931348623157E308\" mnemonicParsing=\"false\"\n" +
                        "                        prefHeight=\"0.0\" prefWidth=\"5.0\" GridPane.columnIndex=\"" + j + "\" GridPane.rowIndex=\"" + i + "\" >\n" +
                        "                    <font>\n" +
                        "                        <Font size=\"10.0\"/>\n" +
                        "                    </font>\n" +
                        "                </Button>");
        }
    }

}