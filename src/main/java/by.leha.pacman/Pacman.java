package by.leha.pacman;

public class Pacman extends Creature{
    Referee referee;

    public Pacman(Map map, Coordinates coordinates, Referee referee) {
        super(map, coordinates);
        this.referee = referee;
    }

    public void move() {
        if (directionToGo != null && isDirectionWithoutObstacles(this.directionToGo)) {
            eat();
            go();
        }
    }

    private void eat() {
        Cell cell = map.getCells()[coordinates.getX()][coordinates.getY()];
        if (cell.isTheFoodOnTheCell()) {
            cell.eatTheFoodOnTheCell();
            referee.pacmanFoundTheFood();
        }
        if (cell.isThePelletOnTheCell()) {
            cell.eatThePelletOnTheCell();
            referee.pacmanFoundThePellet();
        }
    }

}
