package pl.edu.pw.ee.jimp2.gr11.wireworld.main.utils.savers;

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
    private String pathToFile;
    private int tileSize;
    private File imageFile;
    private int headColor;
    private int tailColor;
    private int conductorColor;
    private int blankColor;

    public ImageSaver(Game game, String formatName, String path) {
        currentGen = game.getActualGeneration();
        this.formatName = formatName;
        this.pathToFile = path;
        this.tileSize = 20;
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

    public int getHeadColor() {
        return headColor;
    }

    public void setHeadColor(Color headColor) {


        this.headColor = 65536 * ((int) (headColor.getRed() * 255)) + 256 * ((int) (headColor.getGreen() * 255)) + ((int)
                (headColor.getBlue() * 255));


    }

    public int getTailColor() {
        return tailColor;
    }

    public void setTailColor(Color tailColor) {
        this.tailColor = 65536 * ((int) (tailColor.getRed() * 255)) + 256 * ((int) (tailColor.getGreen() * 255)) + ((int)
                (tailColor.getBlue() * 255));

    }

    public int getConductorColor() {
        return conductorColor;
    }

    public void setConductorColor(Color conductorColor) {
        this.conductorColor = 65536 * ((int) (conductorColor.getRed() * 255)) + 256 * ((int) (conductorColor.getGreen()
                * 255)) + ((int) (conductorColor.getBlue() * 255));

    }

    public int getBlankColor() {
        return blankColor;
    }

    public void setBlankColor(Color blankColor) {
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

    public int defineColor(Cell c) {
        if (c instanceof Tail)
            return tailColor;
        else if (c instanceof Head)
            return headColor;
        else if (c instanceof Conductor)
            return conductorColor;
        else
            return blankColor;
    }


}
