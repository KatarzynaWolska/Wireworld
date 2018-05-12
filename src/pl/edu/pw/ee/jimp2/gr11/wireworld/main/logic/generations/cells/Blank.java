package pl.edu.pw.ee.jimp2.gr11.wireworld.main.logic.generations.cells;

import com.sun.prism.paint.Color;

public class Blank extends Cell implements ConfigurationOfState {
    private String nextState;

    public Blank(int x, int y, Color colorOfCell) {
        super(x, y, colorOfCell);
    }

    @Override
    public void checkStateOfNextGeneration() {
        this.nextState = "blank";
    }
    //super();

}
