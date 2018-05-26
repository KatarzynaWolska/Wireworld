package pl.edu.pw.ee.jimp2.gr11.wireworld.main.logic;

import javafx.scene.paint.Color;
import pl.edu.pw.ee.jimp2.gr11.wireworld.main.logic.generations.Generation;
import pl.edu.pw.ee.jimp2.gr11.wireworld.main.logic.generations.cells.*;
import pl.edu.pw.ee.jimp2.gr11.wireworld.main.view.game.GameController;

import java.util.List;

public class Game extends Thread {



    /*
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
        */
    private Generation actualGeneration;
    private Generation nextGeneration;
    private int numberOfGenerations;
    //newColor.toString() z pickera wywala coś takiego co masz niżej. a to w -fx nie działa :v
    private String blankColor = "rgb(0, 0, 0)";//"rgb(0, 0, 0)";
    private String conductorColor = "rgb(255, 255, 0)";// "rgb(255, 255, 0)";
    private String tailColor = "rgb(255, 0 , 0)"; //"rgb(255, 0, 0)";
    private String headColor = "rgb(0, 0, 255)";// "rgb(0, 0, 255)";

    private Color blank;//"rgb(0, 0, 0)";
    private Color conductor;
    private Color tail;
    private Color head;

    public Color getBlank() {
        return blank;
    }

    public void setBlank(Color blank) {
        this.blank = blank;
    }

    public Color getConductor() {
        return conductor;
    }

    public void setConductor(Color conductor) {
        this.conductor = conductor;
    }

    public Color getTail() {
        return tail;
    }

    public void setTail(Color tail) {
        this.tail = tail;
    }

    public Color getHead() {
        return head;
    }

    public void setHead(Color head) {
        this.head = head;
    }

    public void setStopped(boolean stopped) {
        this.stopped = stopped;
    }

    private boolean stopped = true;

    public String getBlankColor() {
        return blankColor;
    }

    public void setBlankColor(String blankColor) {
        this.blankColor = blankColor;
    }

    public String getConductorColor() {
        return conductorColor;
    }

    public void setConductorColor(String conductorColor) {
        this.conductorColor = conductorColor;
    }

    public String getTailColor() {
        return tailColor;
    }

    public void setTailColor(String tailColor) {
        this.tailColor = tailColor;
    }

    public String getHeadColor() {
        return headColor;
    }

    public void setHeadColor(String headColor) {
        this.headColor = headColor;
    }

    public boolean isStopped() {
        return stopped;
    }

    public Game(Generation actualGeneration, int numberOfGeneration) {
        this.actualGeneration = actualGeneration;
        this.numberOfGenerations = numberOfGeneration;
    }

    public Game(int numberOfGenerations) {
        this.actualGeneration = new Generation();
        this.nextGeneration = new Generation();
        this.numberOfGenerations = numberOfGenerations;
    }

    public Game() {
        this.actualGeneration = new Generation();
        this.nextGeneration = new Generation();
    }


    public Generation getActualGeneration() {
        return actualGeneration;
    }

    public void setActualGeneration(Generation actualGeneration) {
        this.actualGeneration = actualGeneration;
    }

    public Generation getNextGeneration() {
        return nextGeneration;
    }

    public void setNextGeneration(Generation nextGeneration) {
        this.nextGeneration = nextGeneration;
    }

    public int getNumberOfGenerations() {
        return numberOfGenerations;
    }

    public void setNumberOfGenerations(int numberOfGenerations) {
        this.numberOfGenerations = numberOfGenerations;
    }


    public boolean checkEqualityOfGenerations(List<Cell> first, List<Cell> second) {
        for (Cell c : first) {
            int index = first.indexOf(c);
            if (c.getClass().equals(second.get(index).getClass())) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }


    public boolean performGame() {
        setNextGeneration(actualGeneration.createNextGeneration(actualGeneration));
        if (checkEqualityOfGenerations(actualGeneration.getCells(), nextGeneration.getCells())) {
            return false;
        }
        setActualGeneration(this.nextGeneration);
        return true;
    }

    @Override
    public void run() {
        performGame();
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

    }

    public String setColor(Cell c) {
        if (c instanceof Blank) {
            return "-fx-background-color: " + blankColor;
        } else if (c instanceof Conductor) {
            return "-fx-background-color: " + conductorColor;
        } else if (c instanceof Tail) {
            return "-fx-background-color: " + tailColor;
        } else if (c instanceof Head) {
            return "-fx-background-color: " + headColor;
        } else {
            return "-fx-background-color: " + blankColor;
        }
    }

    public String newColorForCell(String color, String id, Game game) {

        String[] coords = id.split(("(?!^)"));

        String xFirstCoordinate = coords[4];
        String xSecondCoordinate = coords[5];

        String yFirstCoordinate = coords[6];
        String ySecondCoordinate = coords[7];

        int x = 10 * Integer.parseInt(xFirstCoordinate) + Integer.parseInt(xSecondCoordinate);
        int y = 10 * Integer.parseInt(yFirstCoordinate) + Integer.parseInt(ySecondCoordinate);

        Cell c = game.getActualGeneration().getCell(x, y);
        int i = game.getActualGeneration().getCells().indexOf(c);
        game.getActualGeneration().getCells().remove(i);

        if (color.equals("0x000000ff")) {
            game.getActualGeneration().getCells().add(i, new Conductor(x, y));
            return "-fx-background-color: " + conductorColor;
        } else if (color.equals("0xffff00ff")) {
            game.getActualGeneration().getCells().add(i, new Tail(x, y));
            return "-fx-background-color: " + tailColor;
        } else if (color.equals("0xff0000ff")) {
            game.getActualGeneration().getCells().add(i, new Head(x, y));
            return "-fx-background-color: " + headColor;
        } else if (color.equals("0xff0000ff")) {
            game.getActualGeneration().getCells().add(i, new Blank(x, y));
            return "-fx-background-color: " + blankColor;
        } else {
            game.getActualGeneration().getCells().add(i, new Blank(x, y));
            return "-fx-background-color: " + blankColor;
        }
    }

}
