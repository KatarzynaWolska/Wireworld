package pl.edu.pw.ee.jimp2.gr11.wireworld.main.utils;

import pl.edu.pw.ee.jimp2.gr11.wireworld.main.logic.generations.Generation;
import pl.edu.pw.ee.jimp2.gr11.wireworld.main.logic.generations.cells.Cell;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ConfigFileReader {

    private static final int prefferedHeight = 20;
    private static final int prefferedWidth = 24;
    private int heightOfMatrix = 0;
    private int widthOfMatrix = 0;

    public int getHeightOfMatrix() {
        return this.heightOfMatrix;
    }

    public void setHeightOfMatrix(int height) {
        this.heightOfMatrix = height;
    }

    public int getWidthOfMatrix() {
        return this.widthOfMatrix;
    }

    public void setWidthOfMatrix(int width) {
        this.widthOfMatrix = width;
    }

    public ConfigFileReader() {

    }

    void incrementHeight() {
        this.heightOfMatrix++;
    }

    public boolean checkLine(String[] line) {
        for (int i = 0; i < line.length; i++) {
            if ((!"0".equals(line[i])) && (!"1".equals(line[i])) && (!"2".equals(line[i])) && (!"3".equals(line[i]))) {
                return false;
            } else if (line.length != this.prefferedWidth) {
                return false;
            }
        }

        return true;
    }

    public ArrayList<Cell> readFile(String path) throws IOException {
        Generation g = new Generation();
        ArrayList<Cell> readCells = new ArrayList<>();
        File configFile = new File(path);
        FileReader fr = new FileReader(configFile);
        BufferedReader br = new BufferedReader(fr);
        String line = null;

        while ((line = br.readLine()) != null) {
            String[] data = line.split("\\s+");

            if (checkLine(data) == false) {
                System.out.println("Błędny format pliku kofiguracyjnego.");
                System.exit(1);
            }

            for (int i = 0; i < data.length; i++) {
                readCells.add(g.setCell(this.heightOfMatrix, i, Integer.parseInt(data[i])));
            }

            incrementHeight();
        }

        if (this.prefferedHeight != this.heightOfMatrix) {
          System.out.println("Błędny format pliku kofiguracyjnego.");
          System.exit(1);
        }

        br.close();
        fr.close();

        return readCells;
    }


}
