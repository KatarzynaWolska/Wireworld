package pl.edu.pw.ee.jimp2.gr11.wireworld.main.view.game;

public class GameController extends JPanel{

    private List<JButton> matrix;
    private Generation g;
    private JPanel grid;

    public GameController() {
        super(new GridLayout());

        this.g = g;

        this.matrix = new ArrayList<JButton>(g.getCells().size());

        grid = new JPanel(new GridLayout(g.getHeight(), g.getWidth()));

        for (Cell c : g.getCells()) {
            JButton j = new JButton();
            j.setBackground(setColor(c));
            j.addMouseListener(new changeColor());
            matrix.add(j);
            grid.add(j);
        }

        add(grid);
        setVisible(true);
    }

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
}
//tu jest ta klasa, której nie mogłam dodać jako osobnej
/*
public class AllGui extends JFrame {

    GameController gg;
    private JButton start;
    private int height = 500;
    private int width = 500;
    private JPanel all;
    private Game g;

    public AllGui(Game g) {

        super("WireWorld");
        setBounds(height, width, height, width);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setDefaultLookAndFeelDecorated(true);
        setResizable(false);

        all = new JPanel(new GridLayout(2, 1));
        add(all);

        gg = new GameController(g.getActualGeneration());
        all.add(gg);
        start = new JButton("Start");
        start.addActionListener(new StartGame());

        all.add(start);

        this.g = g;

        g.setGameController(gg);


        setVisible(true);
    }

    class StartGame implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
             g.start();

        }
    }

}

 */
