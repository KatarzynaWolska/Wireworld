package pl.edu.pw.ee.jimp2.gr11.wireworld.main.logic.generations.cells;

import com.sun.prism.paint.Color;

public class Cell {
    private int x, y;
    private Color colour;

     public Cell(){
     }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Color getColour() {
        return colour;
    }

    public void setColour(Color colour) {
        this.colour = colour;
    }
}
