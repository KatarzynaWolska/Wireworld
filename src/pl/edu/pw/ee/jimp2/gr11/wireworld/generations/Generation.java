package pl.edu.pw.ee.jimp2.gr11.wireworld.generations;


import com.sun.prism.paint.Color;
import pl.edu.pw.ee.jimp2.gr11.wireworld.generations.cells.Cell;
import pl.edu.pw.ee.jimp2.gr11.wireworld.generations.cells.Head;

import java.util.List;
import java.util.Set;
//wzorzec fasada!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

public class Generation {
    private int numberOfGeneration;
    private List<Cell> allCells; // Set<Cell> allCells;//=new HashSet();
    private List<Cell> activeCells;

    public void setStateOfCell(int x, int y, Color state){
        //sprawdzać czy Cell to instanceof Head, Tail, ...
    }
    public Color checkState(Color state){
        Color result = new Color(12, 23, 34, 12);//przyklad
        return result;
    }
    public Cell setState(Color colorOfState){
        Cell result = new Head();

        return result;
    }
//lub zamiast Cell dać Colour

}
