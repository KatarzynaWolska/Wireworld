package pl.edu.pw.ee.jimp2.gr11.wireworld.main.logic.generations;


import com.sun.prism.paint.Color;
import pl.edu.pw.ee.jimp2.gr11.wireworld.main.logic.generations.cells.*;
import pl.edu.pw.ee.jimp2.gr11.wireworld.main.logic.generations.cells.Cell;

import java.util.List;
//wzorzec fasada!

public class Generation {
    private int height = 20;
    private int width = 20;
    private int numberOfGeneration;
    private List<Cell> allCells; // Set<Cell> allCells;//=new HashSet();
    //chyba lepiej zeby to była lista. do pol mozna sie przeciez odnosić za pomoca indeksu obiektu z listy
    // odpowiednio mnożąc x i y komórki
    private List<Cell> activeCells;

    public int getNumberOfGeneration() {
        return numberOfGeneration;
    }

    public Generation(int numberOfGeneration) {
        this.numberOfGeneration = numberOfGeneration;
    }

    public void setStateOfCell(Color state) {
        //sprawdzać czy Cell to instanceof Head, Tail, ...

        //a potem ustawiac kolor

        //ale chyba można za pomocą checkNext... z interfejsu

        //bez senu w sumie: robi to przecież konstruktor(w sensie ustawia x, y i kolor)
        //wiec usuwam int x i int  y ^

        //wgl ta metoda chyba nie jest  potrzebna
    }
    public Color checkState(Color state){
        Color result = new Color(12, 23, 34, 12);//przyklad
        return result;// jest w interfejsie
    }

    public void setStateOfCellsForGeneration(List<Color> colors) {
        int x = 0;
        int y = 0;
        for (Color c : colors) {
            if (c.equals("blank")) {//tymczasowo - powinno  byc np. (1, 2, 3,3)
                allCells.add(new Blank(x, y, new Color(1, 2, 3, 3)));
                x++;
                y++;
            } else if (c.equals("red")) {
                Head h = new Head(x, y, new Color(1, 2, 2, 3));
                allCells.add(h);
                activeCells.add(h);//tak samo dla Tail i Conductor
                x++;
                y++;
            }
            //(...)
        }

    }

    public List<Cell> getAllCells() {
        return allCells;
    }

    public boolean isAnyHeadOrTailInActiveCells() {

        //liczenie czy sa jakies głowy lub ogony
        for (Cell c : this.activeCells) {
            c = c instanceof Tail ? ((Tail) c) : null;
            if (c != null) {
                return true;
            } //zrobic tak by uwzględniało jeszcze head
        }
        return false;
    }


}
