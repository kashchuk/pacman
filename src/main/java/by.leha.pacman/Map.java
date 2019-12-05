package by.leha.pacman;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Map {
    private int height;
    private int width;
    private int scale;
    public Cell[][] cells;

    public Map(int height, int width, int scale) {
        this.height = height;
        this.width = width;
        this.scale = scale;
        Random random = new Random();
        cells = new Cell[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                cells[i][j] = new Cell(new Coordinates(i, j));
                if (random.nextBoolean() && random.nextBoolean()) {   //Refactor !!!
                    cells[i][j].setObstacleOnTheCell();
                }
                if (i == 0 || j == 0 || i == (height - 1) || j == (width - 1)) {
                    cells[i][j].setObstacleOnTheCell();
                }
            }
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void settleGhosts() {
        cells[8][8].settleOnTheCell(new Ghost(this, new Coordinates(8, 8)));
    }

    public void settlePacman() {
        int landingCellNumber = 5;
        cells[landingCellNumber][landingCellNumber].settleOnTheCell(
                new Pacman(this, new Coordinates(landingCellNumber, landingCellNumber)));
        cells[landingCellNumber][landingCellNumber].removeObstacleFromTheCell();
    }

    public List<Creature> getCreatures() {
        List<Creature> creatures = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (cells[i][j].getCreatures() != null) {
                    creatures.addAll(cells[i][j].getCreatures());
                }

            }
        }
        return creatures;
    }

    public void register(Creature creature) {
        cells[creature.coordinates.getX()][creature.coordinates.getY()].settleOnTheCell(creature);
    }

    public void unregister(Creature creature) {
        cells[creature.coordinates.getX()][creature.coordinates.getY()].leaveTheCell(creature);
    }



    public boolean isTheCellWithoutObstacles(int x, int y) {
        return cells[x][y].isTheCellWithoutObstacles();
    }

    public boolean isPacManOnTheCell(int x, int y) {
        List<Creature> creatures = cells[x][y].getCreatures();
        boolean answer = false;
        for (Creature creature : creatures) {
            if (creature.getClass().getSimpleName().equals("Pacman")) { // Refactor (instanceof)
                answer = true;
            }
            ;
        }
        return answer;
    }


    protected void drawMap(GraphicsContext graphicsContext) throws InterruptedException {
        //drawField(graphicsContext);
        drawObstacles(graphicsContext);
//        drawGhosts(graphicsContext);

//        graphicsContext.setFill(Color.GREEN);
//        graphicsContext.setStroke(Color.BLUE);


//        graphicsContext.setLineWidth(5);
//        graphicsContext.strokeLine(200, 400, 400, 0);
//        graphicsContext.fillOval(100, 100, 100, 100);
//        graphicsContext.fillArc(250, 100, 100, 100, 45, 270, ArcType.ROUND);
//        graphicsContext.fillPolygon(new double[]{10, 40, 10, 40},
//                new double[]{210, 210, 240, 240}, 4);
//        graphicsContext.strokePolygon(new double[]{60, 90, 60, 90},
//                new double[]{210, 210, 240, 240}, 4);
//        graphicsContext.strokePolyline(new double[]{110, 140, 110, 140},
//                new double[]{210, 210, 240, 240}, 4);
    }

    private void drawField(GraphicsContext graphicsContext) {
        //Refactor
    }

    private void drawObstacles(GraphicsContext graphicsContext) {
        graphicsContext.setFill(Color.LIGHTGRAY);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (cells[i][j].isTheObstacleOnTheCell()) {
                    graphicsContext.fillRect(i * scale, j * scale, scale, scale);
                }
            }
        }
    }

    private void drawGhosts(GraphicsContext graphicsContext) {
        graphicsContext.setFill(Color.RED);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (cells[i][j].whatTypeOfCreatureOnTheCell("Ghost")) {
                    graphicsContext.fillOval(i * scale, j * scale, 0.5 * scale, 0.8 * scale);


                    graphicsContext.fillRect((i + 1) * scale, j * scale, scale, scale);
                    graphicsContext.fillRect(i * scale, (j + 1) * scale, scale, scale);

                }
            }
        }
    }

    public List<Ghost> getGhosts() { // Refactor !!!
        List<Ghost> ghosts = new ArrayList<Ghost>();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (cells[i][j].isTheGhostOnTheCell()) {
                    ghosts.addAll(cells[i][j].getGhosts());
                }
            }
        }
        return ghosts;
    }

    public Pacman getThePacman() {
        Pacman pacman = null;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (cells[i][j].isThePacmanOnTheCell()) {
                    pacman = cells[i][j].getThePacman();
                }
            }
        }
        return pacman;
    }


}
