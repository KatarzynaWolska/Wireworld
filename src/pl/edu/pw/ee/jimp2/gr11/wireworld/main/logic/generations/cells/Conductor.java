package pl.edu.pw.ee.jimp2.gr11.wireworld.main.logic.generations.cells;

public class Conductor extends Cell implements ConfigurationOfState {

    //private String nextState;
    private int numberOfHeadNeighbours = 0;

    public Conductor(int x, int y) {
        super(x, y, 1);
    }

    public void setNumberOfHeadNeighbours(int numberOfHeads) {
        this.numberOfHeadNeighbours = numberOfHeads;
    }

    @Override
    public Cell checkStateOfNextGeneration() {
        if ((numberOfHeadNeighbours == 1) || (numberOfHeadNeighbours == 2)) {
            return new Head(getX(), getY());
        } else {
            return new Conductor(getX(), getY());
        }

    }

    /*public String getNextState() {
        return nextState;
    }

    public boolean countNeibours(){

        return true;
    }

    public Cell checkStateOfNextGeneration() {
        //tu bedzie uzupełnianie nextState
    }*/
}
