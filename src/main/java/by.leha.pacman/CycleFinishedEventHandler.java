package by.leha.pacman;


import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;

import java.util.List;

public class CycleFinishedEventHandler implements EventHandler<ActionEvent> {
    List<CreatureJavaFxModel> figures;
    Drawer drawer;
    Transition transition;

    public CycleFinishedEventHandler(List<CreatureJavaFxModel> figures, Transition transition, Drawer drawer) {
        this.figures = figures;
        this.transition = transition;
        this.drawer = drawer;
    }

    @Override
    public void handle(ActionEvent actionEvent) {

        for (int i = 0; i < figures.size(); i++) {
            figures.get(i).move();
        }
        drawer.drawTheMap();
        drawer.drawTheFooter();
        transition.play();
    }
}
