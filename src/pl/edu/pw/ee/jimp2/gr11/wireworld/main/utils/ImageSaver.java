package pl.edu.pw.ee.jimp2.gr11.wireworld.main.utils;

import javafx.scene.paint.Color;
import pl.edu.pw.ee.jimp2.gr11.wireworld.main.logic.Game;
import pl.edu.pw.ee.jimp2.gr11.wireworld.main.logic.generations.Generation;
import pl.edu.pw.ee.jimp2.gr11.wireworld.main.logic.generations.cells.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImageSaver {
    private Generation currentGen;
    private String formatName;
    private String pathToFile;// = "src/pl/edu/pw/ee/jimp2/gr11/wireworld/test/java.files.testfiles/image.bmp";
    private int tileSize;
    private File imageFile;
    private int headColor = 000011;
    private int tailColor = 110000;
    private int conductorColor = 001111;
    private int blankColor = 000000;

    public ImageSaver(Game game, String formatName, String path) {
        currentGen = game.getActualGeneration();
        this.formatName = formatName;
        this.pathToFile = path;
        this.tileSize = 20;
    }

    public void deletePreviousFiles() {
        for (File file : new java.io.File("src/files/images").listFiles())
            if (!file.isDirectory())
                file.delete();
    }

    public void setColors(Color headColor, Color tailColor, Color conductorColor, Color blankColor) {
        this.headColor = 65536 * ((int) (headColor.getRed() * 255)) + 256 * ((int) (headColor.getGreen() * 255)) + ((int)
                (headColor.getBlue() * 255));
        this.tailColor = 65536 * ((int) (tailColor.getRed() * 255)) + 256 * ((int) (tailColor.getGreen() * 255)) + ((int)
                (tailColor.getBlue() * 255));
        this.conductorColor = 65536 * ((int) (conductorColor.getRed() * 255)) + 256 * ((int) (conductorColor.getGreen()
                * 255)) + ((int) (conductorColor.getBlue() * 255));
        this.blankColor = 65536 * ((int) (blankColor.getRed() * 255)) + 256 * ((int) (blankColor.getGreen() * 255)) + ((int)
                (blankColor.getBlue() * 255));
    }

    public void setHeadColor(Color headColor) {
        /*java.awt.Color transformation = new java.awt.Color((float) headColor.getRed(), (float) headColor.getGreen(),
                (float) headColor.getBlue(), (float) headColor.getOpacity());
        this.headColor = transformation.getRGB();*/

        this.headColor = 65536 * ((int) (headColor.getRed() * 255)) + 256 * ((int) (headColor.getGreen() * 255)) + ((int)
                (headColor.getBlue() * 255));


    }

    public void setTailColor(Color tailColor) {
        this.tailColor = 65536 * ((int) (tailColor.getRed() * 255)) + 256 * ((int) (tailColor.getGreen() * 255)) + ((int)
                (tailColor.getBlue() * 255));
/*
        java.awt.Color transformation = new java.awt.Color((float) tailColor.getRed(), (float) tailColor.getGreen(),
                (float) tailColor.getBlue(), (float) tailColor.getOpacity());
        this.tailColor = transformation.getRGB();
    */
    }

    public void setConductorColor(Color conductorColor) {
        /*java.awt.Color transformation = new java.awt.Color((float) conductorColor.getRed(), (float) conductorColor.getGreen(),
                (float) conductorColor.getBlue(), (float) conductorColor.getOpacity());
        this.conductorColor = transformation.getRGB();*/
        this.conductorColor = 65536 * ((int) (conductorColor.getRed() * 255)) + 256 * ((int) (conductorColor.getGreen()
                * 255)) + ((int) (conductorColor.getBlue() * 255));

    }

    public void setBlankColor(Color blankColor) {
        /*java.awt.Color transformation = new java.awt.Color((float) blankColor.getRed(), (float) blankColor.getGreen(),
                (float) blankColor.getBlue(), (float) blankColor.getOpacity());
        this.blankColor = transformation.getRGB();*/
        this.blankColor = 65536 * ((int) (blankColor.getRed() * 255)) + 256 * ((int) (blankColor.getGreen() * 255)) + ((int)
                (blankColor.getBlue() * 255));
    }


    public void makeImage() {
        BufferedImage dataOfImage = new BufferedImage(currentGen.getWidth() * tileSize, currentGen.getHeight() * tileSize,
                BufferedImage.TYPE_INT_RGB);
        fillImage(dataOfImage);

        try {
            imageFile = new File(pathToFile);

            if (imageFile.isFile() == false)
                imageFile.createNewFile();

            ImageIO.write(dataOfImage, formatName, imageFile);
        } catch (IOException e) {
            System.err.println("Błąd zapisu pliku " + formatName + ".");
            e.printStackTrace();
        }

    }

    private void fillImage(BufferedImage image) {
        int ri, rj;
        for (int i = 0; i < currentGen.getWidth() * tileSize; i++) {
            for (int j = 0; j < currentGen.getHeight() * tileSize; j++) {
                ri = i % tileSize;
                rj = j % tileSize;
                image.setRGB(i, j, defineColor(currentGen.getCellFromList((j - rj) / tileSize, (i - ri) / tileSize)));//((i - ri) / tileSize, (j - rj) / tileSize)));

            }
        }
    }

    private int defineColor(Cell c) {
        if (c instanceof Tail)
            return tailColor;//70130180;//niebieski
        else if (c instanceof Head)
            return headColor;//(new Color(244, 11, 11, 1)).getIntArgbPre();
        else if (c instanceof Conductor)
            return conductorColor;//(new Color(255, 235, 7, 255)).getIntArgbPre(); //zółty
        else
            return blankColor;//(new Color(0, 0, 0, 255)).getIntArgbPre();//czarny
    }

    public static void main(String[] args) {//przerzucić do testów
        Game g = new Game(1);
        Generation testGen = new Generation();//tego maina trzeba przerzucić do testów
        testGen.setHeight(2);
        testGen.setWidth(3);
        List<Cell> cells = new ArrayList<Cell>();
        testGen.setCells(cells);
        g.setActualGeneration(testGen);


        cells.add(new Tail(0, 0));
        cells.add(new Head(0, 1));
        cells.add(new Conductor(0, 2));

        cells.add(new Blank(1, 0));
        cells.add(new Conductor(1, 1));
        cells.add(new Conductor(1, 2));

        ImageSaver is = new ImageSaver(g, "PNG", "src/files/testfiles/image.png");
        is.setHeadColor(new Color(0, 0, 1, 1));
        is.setTailColor(new Color(1, 0, 0, 1));
        is.setConductorColor(new Color(1, 1, 0, 1));
        is.setBlankColor(new Color(0, 0, 0, 1));

        is.makeImage();
    }
}
