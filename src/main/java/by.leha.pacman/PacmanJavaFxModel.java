package by.leha.pacman;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

import java.awt.*;

import static javax.swing.UIManager.get;

public class PacmanJavaFxModel extends CreatureJavaFxModel{

    public PacmanJavaFxModel(Creature creature, Group root, int scale) {
        super(creature, root, scale);
    }

    @Override
    public Timeline createAnimation() {
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);
        Duration duration = Duration.millis(200);
        Arc imageOfThePacman = (Arc) pane.getChildren().get(0);
        KeyValue keyValue1 = new KeyValue(imageOfThePacman.startAngleProperty(), 0);
        KeyValue keyValue2 = new KeyValue(imageOfThePacman.lengthProperty(), 360);
        KeyFrame keyFrame = new KeyFrame(duration, keyValue1, keyValue2);
        timeline.getKeyFrames().add(keyFrame);
        return timeline;
   }

   @Override
   public void createImage() {
       Arc imageOfThePacman = new Arc(scale/2, scale/2, scale/2, scale/2, 45, 270);  //(
       imageOfThePacman.setType(ArcType.ROUND);
       pane = new Pane();
       pane.getChildren().add(imageOfThePacman);
       root.getChildren().add(pane); //Refactor
       pane.setLayoutX(creature.getX()*scale);
       pane.setLayoutY(creature.getY()*scale);
   }

}
