package by.leha.pacman;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyEventHandler implements EventHandler<KeyEvent> {
    public Map map;
    int fake = 0;

    KeyEventHandler(Map map) {
        this.map = map;
    }
    @Override
    public void handle(KeyEvent keyEvent) {
        KeyCode keyCode = keyEvent.getCode();
        switch (keyCode) {
            case UP:
                map.getThePacman().setDirectionToGo(Direction.UP);
                break;
            case DOWN:
                map.getThePacman().setDirectionToGo(Direction.DOWN);
                break;
            case LEFT:
                map.getThePacman().setDirectionToGo(Direction.LEFT);
                break;
            case RIGHT:
                map.getThePacman().setDirectionToGo(Direction.RIGHT);
            default:
                //map.getPacman().setDirectionToGo(null);
        }
    }
}
