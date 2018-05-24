package pl.edu.pw.ee.jimp2.gr11.wireworld.main.logic.generations;


import pl.edu.pw.ee.jimp2.gr11.wireworld.main.logic.generations.cells.*;
import pl.edu.pw.ee.jimp2.gr11.wireworld.main.logic.generations.cells.Cell;

import java.util.ArrayList;
import java.util.List;
//wzorzec fasada!

public class Generation {
    private int height = 20;
    private int width = 24;//liczb kolumn
    private int numberOfGeneration;

    /* private List<Cell> allCells; // Set<Cell> allCells;//=new HashSet();
     //chyba lepiej zeby to była lista. do pol mozna sie przeciez odnosić za pomoca indeksu obiektu z listy
     // odpowiednio mnożąc x i y komórki
     private List<Cell> activeCells;
 */
    public int getNumberOfGeneration() {
        return numberOfGeneration;
    }
/*
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
    }*/

    //private int height;
    //private int width;
    private List<Cell> cells;
    private List<Cell> activeCells;
    //jeszcze trzeba dodać numberOfGeneration

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Generation() {
        this.height = 0;
        this.width = 0;
    }

    public Generation(int height, int width, List<Cell> cells, List<Cell> activeCells) {
        this.height = height;
        this.width = width;
        this.cells = cells;
        this.activeCells = activeCells;
    }

    public void setCells(List<Cell> cells) {
        this.cells = cells;
    }

    public void setActiveCells(List<Cell> activeCells) {
        this.activeCells = activeCells;
    }

    public List<Cell> getCells() {
        return cells;
    }

    public List<Cell> getActiveCells() {
        return activeCells;
    }

    public Cell getCell(int x, int y) {
        return cells.get(x * width + y);
    }

    public Cell setCell(int x, int y, int state) {
        if (state == 0) {
            return new Blank(x, y);
        } else if (state == 1) {
            return new Conductor(x, y);
        } else if (state == 2) {
            return new Tail(x, y);
        } else if (state == 3) {
            return new Head(x, y);
        } else {
            return new Cell(x, y);
        }
    }

    public List<Cell> setActiveCells() {
        List<Cell> aliveCells = new ArrayList<>();
        for (Cell c : cells) {
            if ((c instanceof Blank) == false) {
                aliveCells.add(c);
            }
        }

        return aliveCells;
    }

    public Generation(int height, int width, List<Cell> cells) {
        this.height = height;
        this.width = width;
        this.cells = cells;
        this.activeCells = setActiveCells();
    }

    public int countNeighbours(int x, int y) {
        int counter = 0;
        if ((x - 1) < 0) {
            if ((y - 1) < 0) {
                if (getCell(x, y) instanceof Head) {
                    counter++;
                }
                if (getCell(x + 1, y + 1) instanceof Head) {
                    counter++;
                }
                if (getCell(x + 1, y) instanceof Head) {
                    counter++;
                }
            } else if ((y + 1) > (width - 1)) {
                if (getCell(x + 1, y) instanceof Head) {
                    counter++;
                }
                if (getCell(x + 1, y - 1) instanceof Head) {
                    counter++;
                }
                if (getCell(x, y - 1) instanceof Head) {
                    counter++;
                }
            } else {
                if (getCell(x, y + 1) instanceof Head) {
                    counter++;
                }
                if (getCell(x + 1, y + 1) instanceof Head) {
                    counter++;
                }
                if (getCell(x + 1, y) instanceof Head) {
                    counter++;
                }
                if (getCell(x + 1, y - 1) instanceof Head) {
                    counter++;
                }
                if (getCell(x, y - 1) instanceof Head) {
                    counter++;
                }
            }
        } else if ((x + 1) > (height - 1)) {
            if ((y - 1) < 0) {
                if (getCell(x - 1, y) instanceof Head) {
                    counter++;
                }
                if (getCell(x - 1, y + 1) instanceof Head) {
                    counter++;
                }
                if (getCell(x, y + 1) instanceof Head) {
                    counter++;
                }
            } else if ((y + 1) > (width - 1)) {
                if (getCell(x, y - 1) instanceof Head) {
                    counter++;
                }
                if (getCell(x - 1, y - 1) instanceof Head) {
                    counter++;
                }
                if (getCell(x - 1, y) instanceof Head) {
                    counter++;
                }
            } else {
                if (getCell(x, y - 1) instanceof Head) {
                    counter++;
                }
                if (getCell(x - 1, y - 1) instanceof Head) {
                    counter++;
                }
                if (getCell(x - 1, y) instanceof Head) {
                    counter++;
                }
                if (getCell(x - 1, y + 1) instanceof Head) {
                    counter++;
                }
                if (getCell(x, y + 1) instanceof Head) {
                    counter++;
                }
            }
        } else {
            if ((y - 1) < 0) {
                if (getCell(x - 1, y) instanceof Head) {
                    counter++;
                }
                if (getCell(x - 1, y + 1) instanceof Head) {
                    counter++;
                }
                if (getCell(x, y + 1) instanceof Head) {
                    counter++;
                }
                if (getCell(x + 1, y + 1) instanceof Head) {
                    counter++;
                }
                if (getCell(x + 1, y) instanceof Head) {
                    counter++;
                }
            } else if ((y + 1) > (width - 1)) {
                if (getCell(x - 1, y) instanceof Head) {
                    counter++;
                }
                if (getCell(x - 1, y - 1) instanceof Head) {
                    counter++;
                }
                if (getCell(x, y - 1) instanceof Head) {
                    counter++;
                }
                if (getCell(x + 1, y - 1) instanceof Head) {
                    counter++;
                }
                if (getCell(x + 1, y) instanceof Head) {
                    counter++;
                }
            } else {
                if (getCell(x - 1, y - 1) instanceof Head) {
                    counter++;
                }
                if (getCell(x - 1, y) instanceof Head) {
                    counter++;
                }
                if (getCell(x - 1, y + 1) instanceof Head) {
                    counter++;
                }
                if (getCell(x, y + 1) instanceof Head) {
                    counter++;
                }
                if (getCell(x + 1, y + 1) instanceof Head) {
                    counter++;
                }
                if (getCell(x + 1, y) instanceof Head) {
                    counter++;
                }
                if (getCell(x + 1, y - 1) instanceof Head) {
                    counter++;
                }
                if (getCell(x, y - 1) instanceof Head) {
                    counter++;
                }
            }
        }

        return counter;
    }

    /*
        public Generation createNextGeneration(Generation last) {
            Generation next = new Generation (last.getHeight(), last.getWidth(), new ArrayList<Cell>(last.getCells()));

            List<Cell> nextActiveCells = new ArrayList<>();

            for (Cell c : new ArrayList<Cell>(last.getCells())) {
                if (c instanceof Conductor) {
                    ((Conductor) c).setNumberOfHeadNeighbours(countNeighbours(c.getX(), c.getY()));
                }
                Cell newCell = c.checkStateOfNextGeneration();
                nextActiveCells.add(newCell);
                next.getCells().remove(c.getX() * getWidth() + c.getY());
                next.getCells().add(c.getX() * getWidth() + c.getY(), newCell);

            }

            next.setActiveCells(nextActiveCells);


            return next;

        }
    */
    //tylko żeby zobaczyć czy działa
    public void print(Generation g) {
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                if (g.getCell(j, i) instanceof Blank) {
                    System.out.print("0");
                } else if (g.getCell(j, i) instanceof Conductor) {
                    System.out.print("1");
                } else if (g.getCell(j, i) instanceof Tail) {
                    System.out.print("2");
                } else if (g.getCell(j, i) instanceof Head) {
                    System.out.print("3");
                }
            }
            System.out.println(" ");
        }
    }


}
