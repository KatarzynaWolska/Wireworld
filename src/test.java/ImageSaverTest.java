import javafx.scene.paint.Color;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import pl.edu.pw.ee.jimp2.gr11.wireworld.main.logic.Game;
import pl.edu.pw.ee.jimp2.gr11.wireworld.main.logic.generations.Generation;
import pl.edu.pw.ee.jimp2.gr11.wireworld.main.logic.generations.cells.*;
import pl.edu.pw.ee.jimp2.gr11.wireworld.main.utils.ImageSaver;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ImageSaverTest {
    private static Game g;
    private static Generation testGen;
    private static ImageSaver is;
    private static List<Cell> cells;
    private static File file;

    @BeforeClass
    public static void setUp() {
        g = new Game(1);
        testGen = new Generation();
        testGen.setHeight(2);
        testGen.setWidth(3);
        cells = new ArrayList<Cell>();
        testGen.setCells(cells);
        g.setActualGeneration(testGen);

        cells.add(new Tail(0, 0));
        cells.add(new Head(0, 1));
        cells.add(new Conductor(0, 2));

        cells.add(new Blank(1, 0));
        cells.add(new Conductor(1, 1));
        cells.add(new Conductor(1, 2));
        file = new File("src/files/testfiles/image.png");

        is = new ImageSaver(g, "PNG", "src/files/testfiles/image.png");

        is.setHeadColor(new Color(0, 0, 1, 1));
        is.setTailColor(new Color(1, 0, 0, 1));
        is.setConductorColor(new Color(1, 1, 0, 1));
        is.setBlankColor(new Color(0, 0, 0, 1));
    }

    @Test
    public void shoudDelete() {
        file.delete();
        Assert.assertEquals(false, file.exists());
    }

    @Test
    public void shouldSave() {

        is.makeImage();

        Assert.assertEquals(true, file.isFile());
    }

    @Test
    public void defineColorTest() {
        Assert.assertEquals(is.getTailColor(), is.defineColor(new Tail(0, 0)));
        Assert.assertEquals(is.getBlankColor(), is.defineColor(new Blank(0, 0)));
        Assert.assertEquals(is.getConductorColor(), is.defineColor(new Conductor(0, 0)));
        Assert.assertEquals(is.getHeadColor(), is.defineColor(new Head(0, 0)));


    }
}
