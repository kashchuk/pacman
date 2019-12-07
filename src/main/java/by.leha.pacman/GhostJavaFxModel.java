package by.leha.pacman;

import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class GhostJavaFxModel extends CreatureJavaFxModel {

    public GhostJavaFxModel(Creature creature, Group board, int scale, Duration duration, Color color) {
        super(creature, board, scale, duration, color);
    }

    @Override
    protected Timeline createAnimation() {
        return null;
    }

    @Override
    protected void createImage() {
        int discrete = scale / 8;
        pane = new Pane();
        Ellipse ellipse = new Ellipse(4 * discrete, 3 * discrete, 4 * discrete, 3 * discrete);
        ellipse.setFill(color);
        Rectangle rectangle = new Rectangle(scale, scale / 2, color);
        rectangle.setX(0);
        rectangle.setY(3 * discrete);
        Circle leftEye = new Circle(0.3* scale, 0.3* scale, 0.15* scale, Color.WHITE);
        Circle rightEye = new Circle(0.7* scale, 0.3* scale, 0.15* scale, Color.WHITE);
        Circle leftIris = new Circle(0.3* scale, 0.3* scale, 0.05* scale, Color.BLACK);
        Circle rightIris = new Circle(0.7* scale, 0.3* scale, 0.05* scale, Color.BLACK);

        chooseEyesPosition();
        pane.getChildren().addAll(ellipse);
        pane.getChildren().addAll(rectangle);
        pane.getChildren().addAll(leftEye);
        pane.getChildren().addAll(rightEye);
        pane.getChildren().addAll(leftIris);
        pane.getChildren().addAll(rightIris);


        board.getChildren().add(pane);
        pane.setLayoutX(creature.getX() * scale);
        pane.setLayoutY(creature.getY() * scale);
    }

    private Coordinates chooseEyesPosition() {
        Coordinates coordinates = new Coordinates(0, 0);
        coordinates.setCoordinates(10, 10);
        return coordinates;

    }
}
