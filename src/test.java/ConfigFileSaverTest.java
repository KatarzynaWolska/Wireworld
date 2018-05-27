
//import org.junit.BeforeClass;
//import org.junit.Test;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import pl.edu.pw.ee.jimp2.gr11.wireworld.main.logic.generations.Generation;
import pl.edu.pw.ee.jimp2.gr11.wireworld.main.logic.generations.cells.*;
import pl.edu.pw.ee.jimp2.gr11.wireworld.main.utils.ConfigFileSaver;

import java.util.ArrayList;
import java.util.List;

public class ConfigFileSaverTest {
    private String path1 = "src/files/testfiles/file.txt";
    private String nullPath = "";
    private static Generation testGen;
    private static List<Cell> cells;

    @BeforeClass
    public static void setUp() {
        testGen = new Generation();
        testGen.setHeight(1);
        testGen.setWidth(4);
        testGen.setCells(cells);

        cells = new ArrayList<Cell>();

        cells.add(new Tail(0, 0));
        cells.add(new Head(0, 1));
        cells.add(new Blank(0, 2));
        cells.add(new Conductor(0, 3));
    }


    @Test
    public void shouldSaveIntoFile() {
        ConfigFileSaver c = new ConfigFileSaver(path1, testGen);

    }

/*
    @Test(expected = java.io.IOException.class)
    public void shouldNotSaveIntoFile() {
        try {
            ConfigFileSaver cfs = new ConfigFileSaver(nullPath, testGen);

        } catch (IllegalArgumentException e) {

        }
    }
    */
}