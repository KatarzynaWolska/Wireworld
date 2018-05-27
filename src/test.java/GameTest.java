package test.java;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.edu.pw.ee.jimp2.gr11.wireworld.main.logic.Game;
import pl.edu.pw.ee.jimp2.gr11.wireworld.main.logic.generations.Generation;
import pl.edu.pw.ee.jimp2.gr11.wireworld.main.logic.generations.cells.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GameTest {
    List<Cell> cells;
    Generation g;
    Game game;

    @Before
    public void init() {
        cells = new ArrayList<>();
        cells.add(new Blank(0,0));
        cells.add(new Conductor(0,1));
        cells.add(new Tail(0, 2));
        cells.add(new Head(0, 3));
        cells.add(new Blank(1,0));
        cells.add(new Blank(1,1));
        cells.add(new Blank(1,2));
        cells.add(new Blank(1,3));

        g = new Generation(2,4,cells);
        game = new Game();
        game.setActualGeneration(g);
    }

    @Test
    public void incrementActualGenerationNumber() {
        game.incrementActualGenerationNumber();
        Assert.assertEquals(1, game.getActualGenerationNumber());
    }

    @Test
    public void checkEqualityOfGenerations() {
        Generation genForTest = new Generation(2,4, cells);

        Assert.assertEquals(true, game.checkEqualityOfGenerations(genForTest.getCells(), game.getActualGeneration().getCells()));
    }

    @Test
    public void performGame() {
        Assert.assertEquals(true, game.performGame());
    }

    @Test
    public void setColor() {
        Assert.assertEquals("-fx-background-color: rgb(0, 0, 0)", game.setColor(new Blank(0,0)));
        Assert.assertEquals("-fx-background-color: rgb(255, 255, 0)", game.setColor(new Conductor(0,0)));
        Assert.assertEquals("-fx-background-color: rgb(255, 0 , 0)", game.setColor(new Tail(0,0)));
        Assert.assertEquals("-fx-background-color: rgb(0, 0, 255)", game.setColor(new Head(0,0)));

    }

   /* @Test
    public void newColorForCell() {
        Assert.assertEquals("-fx-background-color: rgb(0, 0, 0)", game.newColorForCell("0xff0000ff", "tile0000", game));
        Assert.assertEquals("-fx-background-color: rgb(255, 255, 0)", game.newColorForCell("0x000000ff", "tile0000", game));
        Assert.assertEquals("-fx-background-color: rgb(255, 0 , 0)", game.newColorForCell("0xffff00ff", "tile0000", game));
        Assert.assertEquals("-fx-background-color: rgb(0, 0, 255)", game.newColorForCell("0xff0000ff", "tile0000", game));

    }*/
}