package pl.edu.pw.ee.jimp2.gr11.wireworld.main.utils;

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
    private String pathToFile = "src/pl/edu/pw/ee/jimp2/gr11/wireworld/test/testfiles/image.bmp";
    private int tileSize;
    private File imageFile;

    public ImageSaver(Generation gen, String formatName, String path) {
        currentGen = gen;
        this.formatName = formatName;
        this.pathToFile = path;
        this.tileSize = 20;
        makeImage();
    }

    private void makeImage() {
        BufferedImage dataOfImage = new BufferedImage(currentGen.getWidth() * tileSize, currentGen.getHeight() * tileSize,
                BufferedImage.TYPE_INT_RGB);
        fillImage(dataOfImage);

        try {
            imageFile = new File(pathToFile);

            if (imageFile.isFile() == false) {
                imageFile.createNewFile();
            } else {
                System.out.println("Plik już istniał, zatem został on nadpisany.");
            }
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
                image.setRGB(i, j, defineColor(currentGen.getCell((i - ri) / tileSize, (j - rj) / tileSize)));
            }
        }
    }


    private int defineColor(Cell c) { //potrzebne?
        //int color = c.getColor();
        //System.out.println(color);
        if (c instanceof Tail)
            return 1330687;//(new Color(29, 101, 254, 255)).getIntArgbPre();   //70130180;//niebieski
        else if (c instanceof Head)
            return 15734551;//(new Color(244, 11, 11, 1)).getIntArgbPre();
        else if (c instanceof Conductor)
            return 16772887;//(new Color(255, 235, 7, 255)).getIntArgbPre(); //zółty
        else
            return 000000;//(new Color(0, 0, 0, 255)).getIntArgbPre();//czarny
    }

    public static void main(String[] args) {//przerzucić do testów
        Generation testGen = new Generation();//tego maina trzeba przerzucić do testów
        testGen.setHeight(2);
        testGen.setWidth(2);
        List<Cell> cells = new ArrayList<Cell>();
        testGen.setCells(cells);

        cells.add(new Tail(0, 0));
        cells.add(new Head(0, 1));
        cells.add(new Blank(1, 0));
        cells.add(new Conductor(1, 1));

        ImageSaver is = new ImageSaver(testGen, "PNG", "src/pl/edu/pw/ee/jimp2/gr11/wireworld/test/testfiles/image.png");
    }
}
