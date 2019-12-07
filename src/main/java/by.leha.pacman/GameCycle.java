package by.leha.pacman;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class GameCycle extends Transition {

    List<CreatureJavaFxModel> figures = new ArrayList<>();
    int scale;

    public GameCycle(List<CreatureJavaFxModel> figures, int scale) {
        this.figures = figures;
        this.scale = scale;
        setCycleDuration(Duration.millis(200));
        setInterpolator(Interpolator.LINEAR);
        setCycleCount(1);
        setAutoReverse(false);
    }

    @Override
    protected void interpolate(double fraction) {
        for (int i = 0; i < figures.size(); i++) {
            double startX = figures.get(i).getPreviousX() * scale;
            double startY = figures.get(i).getPreviousY() * scale;
            double endX = figures.get(i).getX() * scale; //Refactor
            double endY = figures.get(i).getY() * scale; //Refactor
            figures.get(i).getPane().setLayoutX(getInterpolator().interpolate(startX, endX, fraction));
            figures.get(i).getPane().setLayoutY(getInterpolator().interpolate(startY, endY, fraction));
        }
    }
}
