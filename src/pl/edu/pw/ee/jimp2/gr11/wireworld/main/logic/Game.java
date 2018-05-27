package pl.edu.pw.ee.jimp2.gr11.wireworld.main.logic;

import javafx.scene.paint.Color;
import pl.edu.pw.ee.jimp2.gr11.wireworld.main.logic.generations.Generation;
import pl.edu.pw.ee.jimp2.gr11.wireworld.main.logic.generations.cells.*;

import java.util.List;

public class Game {

    private Generation actualGeneration;
    private Generation nextGeneration;
    private int numberOfGenerations;
    private  int actualGenerationNumber = 0;
    private boolean isGameContinued = true;
    private boolean stopped = false;
    //newColor.toString() z pickera wywala coś takiego co masz niżej. a to w -fx nie działa :v
    private String blankColor = "rgb(0, 0, 0)";//"rgb(0, 0, 0)";
    private String conductorColor = "rgb(255, 255, 0)";// "rgb(255, 255, 0)";
    private String tailColor = "rgb(255, 0 , 0)"; //"rgb(255, 0, 0)";
    private String headColor = "rgb(0, 0, 255)";// "rgb(0, 0, 255)";

    private Color blank;//"rgb(0, 0, 0)";
    private Color conductor;
    private Color tail;
    private Color head;


    public boolean isStopped() {
        return stopped;
    }

    public void setStopped(boolean stopped) {
        this.stopped = stopped;
    }


    public int getActualGenerationNumber() {
        return this.actualGenerationNumber;
    }

    public void setActualGenerationNumber(int number) {
        this.actualGenerationNumber = number;
    }

    public void incrementActualGenerationNumber() {
        this.actualGenerationNumber++;
    }


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

    public void setGameContinued(boolean gameContinued) {
        isGameContinued = gameContinued;
    }

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

    public boolean isGameContinued() {
        return isGameContinued;
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

    public String newColorForCell(String id, Game game) {

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

        if (c instanceof Blank) {
            game.getActualGeneration().getCells().add(i, new Conductor(x, y));
            return "-fx-background-color: " + conductorColor;
        } else if (c instanceof Conductor) {
            game.getActualGeneration().getCells().add(i, new Tail(x, y));
            return "-fx-background-color: " + tailColor;
        } else if (c instanceof  Tail) {
            game.getActualGeneration().getCells().add(i, new Head(x, y));
            return "-fx-background-color: " + headColor;
        } else if (c instanceof Head) {
            game.getActualGeneration().getCells().add(i, new Blank(x, y));
            return "-fx-background-color: " + blankColor;
        } else {
            game.getActualGeneration().getCells().add(i, new Blank(x, y));
            return "-fx-background-color: " + blankColor;
        }
    }

}
