package pl.edu.pw.ee.jimp2.gr11.wireworld.main.utils;

import pl.edu.pw.ee.jimp2.gr11.wireworld.main.logic.Game;

import javax.imageio.*;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GifFileSaver {//każdy obraz z generacji musi zostać zapisany jako png do jednego folderu w naszym projekcie
    private String pathToImagesDir;
    private Game currentGame;
    private String pathToGifFile;
    private File gifFile;

    public GifFileSaver(String pathToImagesDir, Game currentGame, String pathToGifFile) {
        this.pathToImagesDir = pathToImagesDir;
        this.currentGame = currentGame;
        this.pathToGifFile = pathToGifFile;
        saveImagesIntoGif();
    }

    public GifFileSaver(String pathToImagesDir, String pathToGifFile) {
        this.pathToImagesDir = pathToImagesDir;
        this.pathToGifFile = pathToGifFile;
        saveImagesIntoGif();

    }

    public void saveImagesIntoGif() {
        try {
            gifFile = new File(pathToGifFile);

            if (gifFile.isFile() == false) {
                gifFile.createNewFile();
            } else {
                System.out.println("Plik już istniał, zatem został on nadpisany.");
            }

            ImageOutputStream output = new FileImageOutputStream(gifFile);

            BufferedImage image = ImageIO.read(new File(pathToImagesDir + "/image0.png"));

            GifSequenceWriter writer = new GifSequenceWriter(output, image.getType(), 10, true);
            writer.writeToSequence(image);


            for (int i = 1; i < 6/*currentGame.getNumberOfGenerations()*/; i++) {
                image = ImageIO.read(new File(pathToImagesDir + "/image" + i + ".png"));
                writer.writeToSequence(image);
            }

            writer.close();
            output.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Błąd podczas tworzenia pliku gif.");
        }
    }

    public static void main(String[] args) { //przeniesc do testów!

        GifFileSaver gfs = new GifFileSaver("src/pl/edu/pw/ee/jimp2/gr11/wireworld/test/images",
                "src/pl/edu/pw/ee/jimp2/gr11/wireworld/test/testfiles/ani.gif");

    }
}
