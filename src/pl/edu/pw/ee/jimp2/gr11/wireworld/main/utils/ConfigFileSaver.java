package pl.edu.pw.ee.jimp2.gr11.wireworld.main.utils;

import pl.edu.pw.ee.jimp2.gr11.wireworld.main.logic.generations.Generation;
import pl.edu.pw.ee.jimp2.gr11.wireworld.main.logic.generations.cells.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ConfigFileSaver {
    private String pathToSaveFile;
    private PrintWriter configFile;
    private File file;
    private Generation genToSave;
    private List<Cell> cells;

    public File getFile() {
        return file;
    }

    public ConfigFileSaver(String pathToFile, Generation currentGeneration) {//macierz cell
        pathToSaveFile = pathToFile;
        genToSave = currentGeneration;
        this.cells = genToSave.getCells();
        makeConfigFile();
    }

    private void makeConfigFile() {
        file = new File(pathToSaveFile);

        if (file.isFile() == false) {
            try {
                file.createNewFile();
                configFile = new PrintWriter(file);
                saveDataToFile();
                configFile.close();
            } catch (IOException e) {
                System.out.println("Nie udało się zapisać pliku.");
            }
        } else {
            System.out.println("Plik już istniał, zatem został on nadpisany.");
        }

    }

    private void saveDataToFile() {//macierz cell
        int i = 0;
        StringBuilder line = new StringBuilder("");
        for (Cell c : cells) {
            line.append(c.getColor()).append(" ");
            if (c.getY() == genToSave.getWidth() - 1) {
                configFile.println(line.toString());
                line = new StringBuilder("");
            }
        }

    }

    public static void main(String[] args) {//tego maina trzeba przerzucić do testów

        Generation testGen = new Generation();
        testGen.setHeight(1);
        testGen.setWidth(2);
        List<Cell> cells = new ArrayList<Cell>();
        testGen.setCells(cells);

        cells.add(new Tail(0, 0));
        cells.add(new Head(0, 1));
        cells.add(new Blank(1, 0));
        cells.add(new Conductor(1, 1));

        ConfigFileSaver c = new ConfigFileSaver("src/pl/edu/pw/ee/jimp2/gr11/wireworld/test/java.files.testfiles/file.txt", testGen);
    }

}

