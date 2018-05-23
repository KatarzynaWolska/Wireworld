package pl.edu.pw.ee.jimp2.gr11.wireworld.main.logic.generations.cells;


public class Tail extends Cell implements ConfigurationOfState {
    //private String nextState;

    public Tail(int x, int y) {
        super(x, y, 3);
    }


    @Override
    public Cell checkStateOfNextGeneration() {
        return new Conductor(getX(), getY());
    }

    /*public String getNextState() {
        return nextState;
    }

    public Tail(int x, int y, Color colorOfCell) {
        super(x, y, colorOfCell);
    }

    public void checkStateOfNextGeneration() {

    }*/
}
