package pl.edu.pw.ee.jimp2.gr11.wireworld.main.logic.generations;


import com.sun.prism.paint.Color;
import pl.edu.pw.ee.jimp2.gr11.wireworld.main.logic.generations.cells.Cell;
import pl.edu.pw.ee.jimp2.gr11.wireworld.main.logic.generations.cells.Head;
import pl.edu.pw.ee.jimp2.gr11.wireworld.main.logic.generations.cells.Cell;

import java.util.List;
//wzorzec fasada!

public class Generation {
    private int numberOfGeneration;
    private List<Cell> allCells; // Set<Cell> allCells;//=new HashSet();
    //chyba lepiej zeby to była lista. do pol mozna sie przeciez odnosić za pomoca indeksu obiektu z listy
    // odpowiednia mnożąc x i y komórki
    private List<Cell> activeCells;

    public Generation(int numberOfGeneration) {
        this.numberOfGeneration = numberOfGeneration;
    }

    public void setStateOfCell(int x, int y, Color state){
        //sprawdzać czy Cell to instanceof Head, Tail, ...
    }
    public Color checkState(Color state){
        Color result = new Color(12, 23, 34, 12);//przyklad
        return result;
    }
    public Cell setState(Color colorOfState){
        Cell result = null;//= new Head(); //if colorOfState.equals("cośtam")

        return result;
    }

}
