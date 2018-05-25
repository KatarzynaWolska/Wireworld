package pl.edu.pw.ee.jimp2.gr11.wireworld.main.view.game;

import pl.edu.pw.ee.jimp2.gr11.wireworld.main.logic.generations.Generation;
import pl.edu.pw.ee.jimp2.gr11.wireworld.main.logic.generations.cells.Cell;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameController {
/*
    private Generation g;

    public void setMatrixColors(Generation ge) {
        for (Cell c : ge.getCells()) {
            matrix.get(ge.getCells().indexOf(c)).setBackground(setColor(c));
        }
    }

    public Color setColor(Cell c) {
        if (c instanceof Blank) {
            return Color.BLACK;
        } else if (c instanceof Conductor) {
            return Color.YELLOW;
        } else if (c instanceof Tail) {
            return Color.RED;
        } else if (c instanceof Head) {
            return Color.BLUE;
        } else {
            return Color.BLACK;
        }
    }

    class changeColor extends MouseAdapter { //poźniej trzeba jakoś "zrefaktoryzować"

        public void mouseClicked(MouseEvent e) {

            for (JButton x : matrix) {
                if (x.equals(e.getSource())) {

                    if (e.getClickCount() == 1) {
                        x.setBackground(Color.BLACK);
                        Cell c = g.getCells().get(matrix.indexOf(x));
                        g.getCells().remove(c);
                        g.getCells().add(c.getX() * g.getWidth() + c.getY(), new Blank(c.getX(), c.getY()));
                        if (g.getActiveCells().contains(c)) {
                            int v;
                            v = g.getActiveCells().indexOf(c);
                            g.getActiveCells().remove(c);
                        }
                    } else if (e.getClickCount() == 2) {
                        x.setBackground(Color.YELLOW);
                        Cell c = g.getCells().get(matrix.indexOf(x));
                        g.getCells().remove(c);
                        g.getCells().add(c.getX() * g.getWidth() + c.getY(), new Conductor(c.getX(), c.getY()));
                        if (g.getActiveCells().contains(c)) {
                            int v;
                            v = g.getActiveCells().indexOf(c);
                            g.getActiveCells().remove(c);
                            g.getActiveCells().add(v, new Conductor(c.getX(), c.getY()));
                        } else {
                            g.getActiveCells().add(new Conductor(c.getX(), c.getY()));
                        }

                    } else if (e.getClickCount() == 3) {
                        x.setBackground(Color.RED);
                        Cell c = g.getCells().get(matrix.indexOf(x));
                        g.getCells().remove(c);
                        g.getCells().add(c.getX() * g.getWidth() + c.getY(), new Tail(c.getX(), c.getY()));
                        if (g.getActiveCells().contains(c)) {
                            int v;
                            v = g.getActiveCells().indexOf(c);
                            g.getActiveCells().remove(c);
                            g.getActiveCells().add(v, new Conductor(c.getX(), c.getY()));
                        } else {
                            g.getActiveCells().add(new Conductor(c.getX(), c.getY()));
                        }
                    } else if (e.getClickCount() == 4) {
                        x.setBackground(Color.BLUE);
                        Cell c = g.getCells().get(matrix.indexOf(x));
                        g.getCells().remove(c);
                        g.getCells().add(c.getX() * g.getWidth() + c.getY(), new Head(c.getX(), c.getY()));
                        if (g.getActiveCells().contains(c)) {
                            int v;
                            v = g.getActiveCells().indexOf(c);
                            g.getActiveCells().remove(c);
                            g.getActiveCells().add(v, new Conductor(c.getX(), c.getY()));
                        } else {
                            g.getActiveCells().add(new Conductor(c.getX(), c.getY()));
                        }
                    }

                }

            }
        }
    }
}*/

}



