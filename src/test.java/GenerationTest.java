package test.java;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.edu.pw.ee.jimp2.gr11.wireworld.main.logic.generations.Generation;
import pl.edu.pw.ee.jimp2.gr11.wireworld.main.logic.generations.cells.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GenerationTest {
    List<Cell> cells;
    Generation g;
    Conductor c;
    Blank b;
    Tail t;
    Head h;

    @Before
    public void init (){
        cells = new ArrayList<>();
        b = new Blank(0, 0);
        cells.add(b);
        c = new Conductor(0, 1);
        cells.add(c);
        t = new Tail(0, 2);
        cells.add(t);
        h = new Head(0, 3);
        cells.add(h);
        cells.add(new Blank(1,0));
        cells.add(new Blank(1,1));
        cells.add(new Blank(1,2));
        cells.add(new Blank(1,3));

        g = new Generation(2,4,cells);
    }

    @Test
    public void getCell() {
        Assert.assertEquals(b, g.getCell(0,0));
        Assert.assertEquals(c, g.getCell(0,1));
        Assert.assertEquals(t, g.getCell(0,2));
        Assert.assertEquals(h, g.getCell(0,3));
    }

    @Test
    public void getCellFromList() {
        Assert.assertEquals(b, g.getCellFromList(0,0));
        Assert.assertEquals(c, g.getCellFromList(0,1));
        Assert.assertEquals(t, g.getCellFromList(0,2));
        Assert.assertEquals(h, g.getCellFromList(0,3));
    }

    @Test
    public void setCell() {
        Assert.assertEquals(b.getClass(), g.setCell(0,0,0).getClass());
        Assert.assertEquals(c.getClass(), g.setCell(0,0,1).getClass());
        Assert.assertEquals(t.getClass(), g.setCell(0,0,2).getClass());
        Assert.assertEquals(h.getClass(), g.setCell(0,0,3).getClass());
    }

    @Test
    public void countNeighbours() {
        Assert.assertEquals(0,g.countNeighbours(0,0));
        Assert.assertEquals(0,g.countNeighbours(0,1));
        Assert.assertEquals(1,g.countNeighbours(0,2));
        Assert.assertEquals(0,g.countNeighbours(0,3));
    }

    @Test
    public void createNextGeneration() {
        List<Cell> nextCells = new ArrayList<>();
        nextCells.add(new Blank(0,0));
        nextCells.add(new Conductor(0,1));
        nextCells.add(new Conductor(0,2));
        nextCells.add(new Tail(0,3));

        Generation next = g.createNextGeneration(g);

        for(int i = 0; i < nextCells.size(); i++) {
            Assert.assertTrue(nextCells.get(i).getClass() == next.getCells().get(i).getClass());
            Assert.assertTrue(nextCells.get(i).getX() == next.getCells().get(i).getX());
            Assert.assertTrue(nextCells.get(i).getY() == next.getCells().get(i).getY());
        }
    }
}