package by.leha.pacman;

import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GhostJavaFxModel extends CreatureJavaFxModel {

    Color color;

    public GhostJavaFxModel(Creature creature, Group root, int scale, Color color) {
        super(creature, root, scale);
        this.color = color;
    }

    @Override
    protected Timeline createAnimation() {
        return null;
    }

    @Override
    protected void createImage() {
        pane = new Pane();
        Rectangle ghostRectangle = new Rectangle(scale, scale, Color.RED);
        pane.getChildren().addAll(ghostRectangle);
        root.getChildren().add(pane);
        pane.setLayoutX(creature.getX()*scale);
        pane.setLayoutY(creature.getY()*scale);
    }
}
