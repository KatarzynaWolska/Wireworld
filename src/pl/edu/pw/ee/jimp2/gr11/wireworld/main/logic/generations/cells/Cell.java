package pl.edu.pw.ee.jimp2.gr11.wireworld.main.logic.generations.cells;

public class Cell {
    private int x, y;
    private int color;

    public Cell() {
        this.x = 0;
        this.y = 0;

    }

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Cell(int x, int y, int color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getColor() {
        return color;
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
