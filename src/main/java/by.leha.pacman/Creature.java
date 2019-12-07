package by.leha.pacman;

import java.util.ArrayList;
import java.util.List;

public abstract class Creature {
    Map map;
    List<Direction> possibleDirections = new ArrayList<>(); //Refactor (do not remember why + maybe remove to Ghost)
    Coordinates coordinates;
    Coordinates previousCoordinates;
    Direction directionToGo;

    public Creature(Map map, Coordinates coordinates) {
        this.map = map;
        this.coordinates = coordinates;
        this.directionToGo = null;
    }

    public void setDirectionToGo(Direction directionToGo) {
        this.directionToGo = directionToGo;
    }

    public int getX() {
        return coordinates.getX();
    }

    public int getY() {
        return coordinates.getY();
    }

    public void move() {
    }

    public boolean isDirectionWithoutObstacles(Direction direction) { //Refactor !!! (Two responsibilities)
        boolean isDirectionWithoutObstacles = false;
        Coordinates directionVector = direction.getDirectionVector(); //Refactor
        int x = coordinates.getX() + directionVector.getX(); //Refactor
        int y = coordinates.getY() + directionVector.getY(); //Refactor
        if (map.isTheCellWithoutObstacles(x, y)) {
            possibleDirections.add(direction); //Refactor !!! (Look header)
            isDirectionWithoutObstacles = true;
        }
        return isDirectionWithoutObstacles;
    }


    public void go() {
        map.unregister(this);
        Coordinates step = directionToGo.getDirectionVector();
        coordinates.setCoordinates(coordinates.getX() + step.getX(), coordinates.getY() + step.getY()); //Refactor (add vector operations)
        map.register(this);
    }


}
