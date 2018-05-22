package pl.edu.pw.ee.jimp2.gr11.wireworld.main.logic.generations.cells;

import com.sun.prism.paint.Color;

public class Cell {
    private int x, y;
    //private Color colorOfCell;

    public Cell() {
        this.x = 0;
        this.y = 0;
        //this.colorOfCell = BLACK;

    }

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Cell(int x, int y, Color colorOfCell) {
        this.x = x;
        this.y = y;
        //this.colorOfCell = colorOfCell;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /*public Color getColorOfCell() {
        return colorOfCell;
    }*/

    /*public void setColorOfCell(Color colorOfCell) {
        this.colorOfCell = colorOfCell;
    }*/

  /*  @Override
    public Cell checkStateOfNextGeneration() { //chyba lepiej zrobić to jako metodę a nie interfejs
        return new Cell(getX(), getY());
    }*/
}
