package by.leha.pacman;

import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public abstract class CreatureJavaFxModel {

    Pane pane;
    Group board;
    Creature creature;
    Duration duration;
    Color color;
    int scale;
    int previousX;
    int previousY;

    public CreatureJavaFxModel(Creature creature, Group board, int scale, Duration duration, Color color) {
        this.creature = creature;
        this.board = board;
        this.scale = scale;
        this.duration = duration;
        this.color = color;
        createImage();
        previousX = creature.getX();
        previousY = creature.getY();
    }

    public Pane getPane() {
        return pane;
    }

    public int getX() {
        return creature.getX();
    }

    public int getY() {
        return creature.getY();
    }

    public int getPreviousX() {
        return previousX;
    }

    public int getPreviousY() {
        return previousY;
    }

    public void move() {
        previousX = creature.getX();
        previousY = creature.getY();
        creature.move();
    }

    public void settleOnTheScene() {
        board.getChildren().add(pane); //Refactor
    }

    public void animate() {
        Timeline timeline = createAnimation();
        if (timeline != null) {
            timeline.play();
        }
    }

    protected abstract Timeline createAnimation();


    protected abstract void createImage();


    public void setDirectionToGo(Direction direction) {
        creature.setDirectionToGo(direction);
    }
}
