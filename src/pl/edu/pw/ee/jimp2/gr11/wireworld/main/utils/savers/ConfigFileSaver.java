package pl.edu.pw.ee.jimp2.gr11.wireworld.main.utils.savers;

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
        file = new File(pathToSaveFile);

        makeConfigFile();
    }

    public File getFile() {
        return file;
    }

    private void makeConfigFile() {

        if (file.exists() == false) {
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

