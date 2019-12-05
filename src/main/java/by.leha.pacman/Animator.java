package by.leha.pacman;

import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;

public class Animator {
    Map map;
    int scale;


    Animator(Map map, int scale) {
        this.map = map;
        this.scale = scale;
    }
}