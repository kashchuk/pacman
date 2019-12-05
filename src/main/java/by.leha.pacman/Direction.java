package by.leha.pacman;

public enum Direction {
    UP(new Coordinates(0, -1)),
    DOWN(new Coordinates(0, +1)),
    LEFT(new Coordinates(-1, 0)),
    RIGHT(new Coordinates(1, 0));
    private Coordinates direction;

    Direction(Coordinates direction) {
        this.direction = direction;
    }

    public Coordinates getDirectionVector() {
        return direction;
    }
}

