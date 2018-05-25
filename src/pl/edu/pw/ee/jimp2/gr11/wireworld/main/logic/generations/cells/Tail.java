package pl.edu.pw.ee.jimp2.gr11.wireworld.main.logic.generations.cells;


public class Tail extends Cell implements ConfigurationOfState {


    public Tail(int x, int y) {
        super(x, y, 3);
    }


    @Override
    public Cell checkStateOfNextGeneration() {
        return new Conductor(getX(), getY());
    }


}
