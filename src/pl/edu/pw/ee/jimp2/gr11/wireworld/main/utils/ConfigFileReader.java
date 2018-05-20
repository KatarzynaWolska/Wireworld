package pl.edu.pw.ee.jimp2.gr11.wireworld.main.utils;

public class ConfigFileReader {
    private int heightOfMatrix = 0;
    private int widthOfMatrix = 0;

    public int getHeightOfMatrix() {
        return this.heightOfMatrix;
    }

    public void setHeightOfMatrix(int height) {
        this.heightOfMatrix = height;
    }

    public int getWidthOfMatrix() {
        return this.widthOfMatrix;
    }

    public void setWidthOfMatrix(int width) {
        this.widthOfMatrix = width;
    }

    public ConfigFileReader() {

    }

    void incrementHeight() {
        this.heightOfMatrix++;
    }

    public boolean checkLine(String[] line) {
        for (int i = 0; i < line.length; i++) {
            if ((!"0".equals(line[i])) && (!"1".equals(line[i])) && (!"2".equals(line[i])) && (!"3".equals(line[i]))) {
                return false;
            } else if (line.length != this.widthOfMatrix) {
                return false;
            }
        }

        return true;
    }

    public ArrayList<Cell> readFile(String path) throws IOException {
        Generation g = new Generation();
        ArrayList<Cell> readCells = new ArrayList<>();
        File configFile = new File(path);
        FileReader fr = new FileReader(configFile);
        BufferedReader br = new BufferedReader(fr);
        String line = null;

        if ((line = br.readLine()) != null) { // oddzieliłam od pętli while pierwszą iterację z pierwszą linią pliku aby ustalić długość linii
            String[] data = line.split("\\s+"); // i potem sprawdzać w checkLine czy jest ona inna
            setWidthOfMatrix(data.length);
            if (checkLine(data) == false) {
                System.out.println("Błędny format pliku konfiguracyjnego.");
                br.close();
                fr.close();
                System.exit(1);
            }

            for (int i = 0; i < data.length; i++) {
                readCells.add(g.setCell(this.heightOfMatrix, i, Integer.parseInt(data[i])));
            }

            incrementHeight();
        }

        while ((line = br.readLine()) != null) {
            String[] data = line.split("\\s+");

            if (checkLine(data) == false) {
                System.out.println("Błędny format pliku kofiguracyjnego.");
                System.exit(1);
            }

            for (int i = 0; i < data.length; i++) {
                readCells.add(g.setCell(this.heightOfMatrix, i, Integer.parseInt(data[i])));
            }

            incrementHeight();
        }

        br.close();
        fr.close();

        return readCells;
    }
}
