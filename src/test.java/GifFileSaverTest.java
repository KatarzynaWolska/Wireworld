import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import pl.edu.pw.ee.jimp2.gr11.wireworld.main.logic.Game;
import pl.edu.pw.ee.jimp2.gr11.wireworld.main.logic.generations.Generation;
import pl.edu.pw.ee.jimp2.gr11.wireworld.main.logic.generations.cells.*;
import pl.edu.pw.ee.jimp2.gr11.wireworld.main.utils.GifFileSaver;
import pl.edu.pw.ee.jimp2.gr11.wireworld.main.utils.ImageSaver;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GifFileSaverTest {
    private static Game g;
    private static Generation testGen;
    private static List<Cell> cells;
    private static File file;

    @BeforeClass
    public static void setUp() {
        g = new Game();
        g.setActualGenerationNumber(4);
        testGen = new Generation();
        testGen.setHeight(1);
        testGen.setWidth(4);

        GifFileSaver gfs = new GifFileSaver("src/files/testfiles/gifsaverfiles", g, "src/files/testfiles/gifsaverfiles/ani.gif");
        file = gfs.getGifFile();
    }

    @Test
    public void shouldSave() {
        Assert.assertEquals(true, file.isFile());

    }
}
