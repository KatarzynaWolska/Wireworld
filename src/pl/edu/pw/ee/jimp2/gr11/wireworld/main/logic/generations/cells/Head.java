package pl.edu.pw.ee.jimp2.gr11.wireworld.main.logic.generations.cells;

public class Head extends Cell implements ConfigurationOfState {
    //private String nextState;

    public Head(int x, int y) {
        super(x, y, 2);
    }
    @Override
    public Cell checkStateOfNextGeneration() {
        return new Tail(getX(), getY());//chyba w Head to powinno być bardziej rozbudowane
    }



}
