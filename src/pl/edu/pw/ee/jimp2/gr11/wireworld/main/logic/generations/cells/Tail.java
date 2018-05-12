package pl.edu.pw.ee.jimp2.gr11.wireworld.main.logic.generations.cells;

import com.sun.prism.paint.Color;

public class Tail extends Cell implements ConfigurationOfState {
    private String nextState;

    public String getNextState() {
        return nextState;
    }

    public Tail(int x, int y, Color colorOfCell) {
        super(x, y, colorOfCell);
    }

    public void checkStateOfNextGeneration() {

    }
}
