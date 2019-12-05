package by.leha.pacman;

import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.layout.Pane;

public abstract class CreatureJavaFxModel {

    Pane pane;
    Group root;
    Creature creature;
    int scale;
    int previousX;
    int previousY;

    public CreatureJavaFxModel(Creature creature, Group root, int scale) {
        this.creature = creature;
        this.root = root;
        this.scale = scale;
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
        root.getChildren().add(pane); //Refactor
    }

    public void animate() {
        Timeline timeline = createAnimation();
        if (timeline != null) {
            timeline.play();
        }
    }

    protected abstract Timeline createAnimation();


    protected abstract void createImage();


}
