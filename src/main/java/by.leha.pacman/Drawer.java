package by.leha.pacman;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class Drawer {
    Map map;
    Referee referee;
    Group footer;
    GraphicsContext graphicsContext;
    int scale;

    public Drawer(Map map, int scale, GraphicsContext graphicsContext, Group footer, Referee referee) {
        this.map = map;
        this.scale = scale;
        this.graphicsContext = graphicsContext;
        this.footer = (Group) footer;
        this.referee = referee;

    }

    public void drawTheFooter() {
        Text score = (Text) footer.getChildren().get(0);
        score.setText("Score: " + Integer.toString(referee.getScore()));
    }

    public void drawTheMap() {
        clearTheMap();
        drawObstacles();
        drawPellets();
        drawFood();
    }

    public void clearTheMap() {
        graphicsContext.clearRect(0, 0, map.getWidth() * scale, map.getHeight() *scale);
    }

    private void drawObstacles() {
        graphicsContext.setFill(Color.LIGHTGRAY);
        for (int i = 0; i < map.getHeight(); i++) {
            for (int j = 0; j < map.getWidth(); j++) {
                if (map.cells[i][j].isTheObstacleOnTheCell()) { //Refactor (Do not talk to strangers)
                    graphicsContext.fillRect(i * scale, j * scale, scale, scale);
                }
            }
        }
    }

    private void drawFood() {
        graphicsContext.setFill(Color.YELLOW);
        for (int i = 0; i < map.getHeight(); i++) {
            for (int j = 0; j < map.getWidth(); j++) {
                if (map.cells[i][j].isTheFoodOnTheCell()) { //Refactor (Do not talk to strangers)
                    graphicsContext.fillOval((i + 0.4375) * scale, (j + 0.4375) * scale, scale / 8, scale / 8);
                }
            }
        }
    }

    private void drawPellets() {
        graphicsContext.setFill(Color.YELLOW);
        for (int i = 0; i < map.getHeight(); i++) {
            for (int j = 0; j < map.getWidth(); j++) {
                if (map.cells[i][j].isThePelletOnTheCell()) { //Refactor (Do not talk to strangers)
                    graphicsContext.fillOval((i + 0.25) * scale, (j + 0.25) * scale, scale / 2, scale / 2);
                }
            }
        }
    }


}
