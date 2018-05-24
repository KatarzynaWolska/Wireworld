package pl.edu.pw.ee.jimp2.gr11.wireworld.main.utils;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

public class Generator {

    public static void main(String[] args) {
        StringBuilder b;
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 24; j++) {
                b = new StringBuilder(" <Button fx:id=\"tile");
                if (i < 10)
                    b.append(0);
                b.append(i);
                if (j < 10)
                    b.append(0);
                b.append(j);

                b.append("\" maxHeight=\"1.7976931348623157E308\" maxWidth=\"1.7976931348623157E308\" mnemonicParsing=\"false\"\n");
                b.append("                        prefHeight=\"0.0\" prefWidth=\"5.0\" GridPane.columnIndex=\"").append(j).append("\" GridPane.rowIndex=\"").append(i).append("\" >\n <font>\n <Font size=\"10.0\"/>\n </font>\n </Button>");

                System.out.println(b.toString());
            }
        }
    }
}