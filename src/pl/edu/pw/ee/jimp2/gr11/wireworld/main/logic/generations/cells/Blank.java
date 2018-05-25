package pl.edu.pw.ee.jimp2.gr11.wireworld.main.logic.generations.cells;



public class Blank extends Cell implements ConfigurationOfState {


    public Blank () {
        super();
    }

    public Blank(int x, int y) {
        super(x, y, 0);
    }

    @Override
    public Cell checkStateOfNextGeneration() {
        return this;
    }
}
