package pl.edu.pw.ee.jimp2.gr11.wireworld.main.logic;

import pl.edu.pw.ee.jimp2.gr11.wireworld.main.logic.generations.*;

import pl.edu.pw.ee.jimp2.gr11.wireworld.main.logic.generations.Generation;
import pl.edu.pw.ee.jimp2.gr11.wireworld.main.logic.generations.cells.Cell;

import java.util.List;

public class Game extends Thread {
    private Generation generation;
    private List<Cell> cellsOdFirstGeneration;

    public Game(){

    }

    public void goThroughGenerations(){
        //tu bedzie w pętelce wywowływane:
        //gen = makeNextGeneration();
    }
    //metoda sprawdzająca czy nalezy przerwac generacje
//metoda inicjujaca piersza generacje i zapisujaca ja do cellsOfFirstGeneration

}
