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

    public ConfigFileSaver(String pathToFile, Generation currentGeneration) {
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
                e.printStackTrace();
            }
        }


    }

    private void saveDataToFile() {
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

}

