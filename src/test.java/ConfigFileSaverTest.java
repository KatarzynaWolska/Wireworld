//import org.junit.BeforeClass;
//import org.junit.Test;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import pl.edu.pw.ee.jimp2.gr11.wireworld.main.logic.generations.Generation;
import pl.edu.pw.ee.jimp2.gr11.wireworld.main.logic.generations.cells.*;
import pl.edu.pw.ee.jimp2.gr11.wireworld.main.utils.savers.ConfigFileSaver;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ConfigFileSaverTest {
    private static String path1 = "src/files/testfiles/file.txt";
    private static Generation testGen;
    private static List<Cell> cells;
    private static File file;

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
        file = new File(path1);

    }


    @Test
    public void shouldSaveIntoFile() {

        ConfigFileSaver c = new ConfigFileSaver(file.getAbsolutePath(), testGen);

        Assert.assertEquals(true, c.getFile().isFile());
    }


}