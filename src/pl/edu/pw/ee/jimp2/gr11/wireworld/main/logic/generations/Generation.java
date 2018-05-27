package pl.edu.pw.ee.jimp2.gr11.wireworld.main.logic.generations;


import pl.edu.pw.ee.jimp2.gr11.wireworld.main.logic.generations.cells.*;
import pl.edu.pw.ee.jimp2.gr11.wireworld.main.logic.generations.cells.Cell;

import java.util.ArrayList;
import java.util.List;
//wzorzec fasada!

public class Generation {
    private int height = 20; //20
    private int width = 24; // liczba kolumn 24
    private int numberOfGeneration;
    private List<Cell> cells;
    //private List<Cell> activeCells;

    public int getNumberOfGeneration() {
        return numberOfGeneration;
    }

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


    /*public Generation(int height, int width, List<Cell> cells, List<Cell> activeCells) {
        this.height = height;
        this.width = width;
        this.cells = cells;
        this.activeCells = activeCells;
    }*/

    public Generation() {
        this.cells = new ArrayList<>();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                cells.add(new Blank(i, j));
            }
        }
    }

    public void setCells(List<Cell> cells) {
        this.cells = cells;
    }

    /*public void setActiveCells(List<Cell> activeCells) {
        this.activeCells = activeCells;
    }*/

    public List<Cell> getCells() {
        return cells;
    }

    /*public List<Cell> getActiveCells() {
        return activeCells;
    }*/

    public Cell getCell(int x, int y) {
        return cells.get(x * width + y);//jesli x to numer wiersza to chyba powinno być x*height? moze mi sie cos pomylilo, nw
    }


    public Cell getCellFromList(int x, int y) {

        for (Cell c : cells) {
            if (c.getX() == x && c.getY() == y) {
                return c;
            }
        }
        return null;
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

    /*public List<Cell> setActiveCells() {
        List<Cell> aliveCells = new ArrayList<>();
        for (Cell c : cells) {
            if ((c instanceof Blank) == false) {
                aliveCells.add(c);
            }
        }

        return aliveCells;
    }*/

    public Generation (List<Cell> cells) {
        this.cells = cells;
    }

    public Generation(int height, int width, List<Cell> cells) {
        this.height = height;
        this.width = width;
        this.cells = cells;
        //this.activeCells = setActiveCells();
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

    public Generation createNextGeneration(Generation last) {
        Generation next = new Generation(last.getHeight(), last.getWidth(), new ArrayList<Cell>(last.getCells()));

        //List<Cell> nextActiveCells = new ArrayList<>();

        for (Cell c : new ArrayList<Cell>(last.getCells())) {
            if (c instanceof Conductor) {
                ((Conductor) c).setNumberOfHeadNeighbours(countNeighbours(c.getX(), c.getY()));
            }
            Cell newCell = c.checkStateOfNextGeneration();
            //nextActiveCells.add(newCell);
            next.getCells().remove(c.getX() * getWidth() + c.getY());
            next.getCells().add(c.getX() * getWidth() + c.getY(), newCell);

        }

        //next.setActiveCells(nextActiveCells);


        return next;

    }


}
