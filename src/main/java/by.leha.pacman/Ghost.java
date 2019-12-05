package by.leha.pacman;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ghost extends Creature {

    boolean isPacmanDetected;
    //Direction directionToGo;

    public Ghost(Map map, Coordinates coordinates) {
        super(map, coordinates);
        isPacmanDetected = false;
    }

    @Override
    public void move() {
        lookAround();
        chooseDirection();
        go();
        isPacmanDetected = false;
        possibleDirections.clear();

    }

    private void lookAround() {
        look(Direction.UP);
        look(Direction.DOWN);
        look(Direction.LEFT);
        look(Direction.RIGHT);
    }

    private void look(Direction direction) {
        isDirectionWithoutObstacles(direction);
        Coordinates directionVector = direction.getDirectionVector();
        int x = coordinates.getX() + directionVector.getX(); //Refactor
        int y = coordinates.getY() + directionVector.getY(); //Refactor
            while (map.isTheCellWithoutObstacles(x, y) && !isPacmanDetected) {
                if (map.isPacManOnTheCell(x, y)) {
                    isPacmanDetected = true;
                    directionToGo = direction;
                }
                x += directionVector.getX();
                y += directionVector.getY();
            }
    }

    private void chooseDirection() {
        if (isPacmanDetected || possibleDirections.contains(directionToGo)) {
//            return;
        } else {
            Random random = new Random();
            directionToGo = possibleDirections.get(random.nextInt(possibleDirections.size()));
        }
    }

//    public void go() {
//        Coordinates step = directionToGo.getDirectionVector();
//        coordinates.setCoordinates(coordinates.getX() + step.getX(), coordinates.getY() + step.getY()); //Refactor (add vector operations)
//    }

}
