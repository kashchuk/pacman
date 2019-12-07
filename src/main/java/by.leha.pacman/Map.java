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
    Referee referee;

    public Map(int height, int width, int scale, Referee referee)  {
        this.height = height;
        this.width = width;
        this.scale = scale;
        this.referee = referee;
        cells = new Cell[height][width];
        setObstacles();
        putFood();
        settlePacman();
        settleGhosts();
    }

    private void setObstacles() {
        Random random = new Random();
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

    public void putFood() {
        putPellets();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (cells[i][j].isFree()) {
                    cells[i][j].putTheFoodOnTheCell();
                }
            }
        }
    }

    public void putPellets() {
        cells[1][10].putThePelletOnTheCell();
    }

    public void settlePacman() {
        int landingCellNumber = 5;
        cells[landingCellNumber][landingCellNumber].settleOnTheCell(
                new Pacman(this, new Coordinates(landingCellNumber, landingCellNumber), referee));
        cells[landingCellNumber][landingCellNumber].removeObstacleFromTheCell();
    }

    public void settleGhosts() {
        cells[8][8].settleOnTheCell(new Ghost(this, new Coordinates(8, 8)));
        cells[9][9].settleOnTheCell(new Ghost(this, new Coordinates(9, 9)));
    }


    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
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
        return cells[x][y].isThePacmanOnTheCell();
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

    public Cell[][] getCells() {
        return cells;
    }
}
