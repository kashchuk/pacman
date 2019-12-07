package by.leha.pacman;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyEventHandler implements EventHandler<KeyEvent> {
    private PacmanJavaFxModel pacmanJavaFxModel;

    KeyEventHandler(CreatureJavaFxModel creatureJavaFxModel) {
        this.pacmanJavaFxModel = (PacmanJavaFxModel) creatureJavaFxModel;
    }
    @Override
    public void handle(KeyEvent keyEvent) {
        KeyCode keyCode = keyEvent.getCode();
        switch (keyCode) {
            case UP:
                pacmanJavaFxModel.setDirectionToGo(Direction.UP);
                pacmanJavaFxModel.getPane().setRotate(270);
                break;
            case DOWN:
                pacmanJavaFxModel.setDirectionToGo(Direction.DOWN);
                pacmanJavaFxModel.getPane().setRotate(90);
                break;
            case LEFT:
                pacmanJavaFxModel.setDirectionToGo(Direction.LEFT);
                pacmanJavaFxModel.getPane().setRotate(180);
                break;
            case RIGHT:
                pacmanJavaFxModel.setDirectionToGo(Direction.RIGHT);
                pacmanJavaFxModel.getPane().setRotate(0);
            default:
                //map.getPacman().setDirectionToGo(null);
        }
    }
}
