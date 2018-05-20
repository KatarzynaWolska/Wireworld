package pl.edu.pw.ee.jimp2.gr11.wireworld.main.logic.generations.cells;

import com.sun.prism.paint.Color;

public class Head extends Cell implements ConfigurationOfState {
    //private String nextState;

    public Head() {
        super();
    }

    public Head(int x, int y) {
        super(x, y);
    }

    @Override
    public Cell checkStateOfNextGeneration() {
        return new Tail(getX(), getY());
    }

    /*public String getNextState() {
        return nextState;
    }

    public Head(int x, int y, Color colorOfCell) {
        super(x, y, colorOfCell);
    }

    public Cell checkStateOfNextGeneration() {
        return new Tail (this.x, this.y, RED);
    }

    */
}
