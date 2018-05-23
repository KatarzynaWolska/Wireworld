package pl.edu.pw.ee.jimp2.gr11.wireworld.main.utils;

import pl.edu.pw.ee.jimp2.gr11.wireworld.main.logic.generations.Generation;
import pl.edu.pw.ee.jimp2.gr11.wireworld.main.logic.generations.cells.Cell;

import java.io.*;
import java.util.List;

public class ConfigFileSaver {
    private String pathToSaveFile;
    private PrintWriter configFile;
    private File file;
    private Generation genToSave;
    private List<Cell> cells;

    public ConfigFileSaver(String pathToFile, Generation currentGeneration) {//macierz cell
        pathToSaveFile = pathToFile;
        genToSave = currentGeneration;
        this.cells = genToSave.getCells();
        initFile();
    }

    private void initFile() {

        if (pathToSaveFile != null) {
            try {
                file = new File(pathToSaveFile);
                configFile = new PrintWriter(file);
                saveConfigInfoToFile();
                configFile.close();
            } catch (IOException e) {
                //configFile.close();
                System.out.println("Nie udało się zapisać pliku.");
            } catch (NullPointerException e) {

            }
        }
    }

    private void saveConfigInfoToFile() {//macierz cell
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

    public static void main(String[] args) {

    }

}

