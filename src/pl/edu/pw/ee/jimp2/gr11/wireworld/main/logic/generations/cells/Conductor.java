package pl.edu.pw.ee.jimp2.gr11.wireworld.main.logic.generations.cells;

public class Conductor extends Cell implements ConfigurationOfState {

    private String nextState;

    public String getNextState() {
        return nextState;
    }

    public boolean countNeibours(){

        return true;
    }


    public void checkStateOfNextGeneration() {
        //tu bedzie uzupełnianie nextState
    }
}
