package by.leha.pacman;

public class Pacman extends Creature{

    public Pacman(Map map, Coordinates coordinates) {
        super(map, coordinates);
    }

    public void setDirectionToGo(Direction directionToGo) {
        this.directionToGo = directionToGo;
    }

    public void move() {
        if (directionToGo != null && isDirectionWithoutObstacles(this.directionToGo)) {
            go();

        }
    }


}
