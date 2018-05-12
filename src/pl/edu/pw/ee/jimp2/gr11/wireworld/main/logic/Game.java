package pl.edu.pw.ee.jimp2.gr11.wireworld.main.logic;

import pl.edu.pw.ee.jimp2.gr11.wireworld.main.logic.generations.Generation;
import pl.edu.pw.ee.jimp2.gr11.wireworld.main.logic.generations.cells.Cell;

import java.util.List;

public class Game extends Thread {
    private int totalNumberOfGenerations;
    private boolean isEarlyEndMonitDisplayed = false;
    private Generation currentGeneration;
    private List<Cell> cellsOfFirstGeneration;//dla tego trzeba bedzie stworzyć tablice przechwyconych kolorów i
    // zamienić to na liste obiektów Color

    public Game(int totalNumberOfGenerations) {//to bedziemy wywoływać w GUI po naciśnięciu "start"
        this.totalNumberOfGenerations = totalNumberOfGenerations;
        goThroughGenerations();
    }

    private void goThroughGenerations() {
        if (currentGeneration.getNumberOfGeneration() == 1)//lub 0
            this.cellsOfFirstGeneration = currentGeneration.getAllCells();
        //tu bedzie w pętelce nieskończonej wywowływane:
        //gen = makeNextGeneration();
    }
    //metoda sprawdzająca czy nalezy przerwac generacje

    private boolean isContinued() {
        if (currentGeneration.isAnyHeadOrTailInActiveCells() == false) {
            this.totalNumberOfGenerations = currentGeneration.getNumberOfGeneration();
            if (isEarlyEndMonitDisplayed == false) {
                //tu tzreba jakoś podpiąć monit mowiacy o przedwczesnym zakonczeniu

                isEarlyEndMonitDisplayed = true;
            }
            return false;
        }

        return true;
    }


    //metoda inicjujaca piersza generacje i zapisujaca ja do cellsOfFirstGeneration


}
