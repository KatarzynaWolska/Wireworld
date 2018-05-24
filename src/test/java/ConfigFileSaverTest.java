package test.java;


import org.junit.BeforeClass;
import org.junit.Test;
import pl.edu.pw.ee.jimp2.gr11.wireworld.main.logic.generations.Generation;
import pl.edu.pw.ee.jimp2.gr11.wireworld.main.logic.generations.cells.*;
import pl.edu.pw.ee.jimp2.gr11.wireworld.main.utils.ConfigFileSaver;

import java.util.List;

public class ConfigFileSaverTest {
    private String path1 = "src/pl/edu/pw/ee/jimp2/gr11/wireworld/test/testfiles/file.txt";
    private String nullPath = "";
    Generation testGen;
    private List<Cell> cells;

    @BeforeClass
    public void setUp() {
        testGen = new Generation();
        testGen.setHeight(1);
        testGen.setWidth(4);
        testGen.setCells(cells);

        cells.add(new Tail(0, 0));
        cells.add(new Head(0, 1));
        cells.add(new Blank(0, 2));
        cells.add(new Conductor(0, 3));
    }


    @Test
    public void shouldSaveIntoFile() {
        ConfigFileSaver c = new ConfigFileSaver(path1, testGen);

        try {
            ConfigFileSaver cfs = new ConfigFileSaver(nullPath, testGen);
            //fail();
        } catch (IllegalArgumentException e) {

        }


    }

}