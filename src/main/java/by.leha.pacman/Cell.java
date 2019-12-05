package by.leha.pacman;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    private Coordinates coordinates;
    public List<Creature> creaturesOnTheCell = new ArrayList<Creature>();
    private boolean isTheObstacleOnTheCell;

    public Cell(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void settleOnTheCell(Creature creature) {
        creaturesOnTheCell.add(creature);
    }

    public void leaveTheCell(Creature creature) {
        creaturesOnTheCell.remove(creature);
    }

    public boolean isThePacmanOnTheCell() {
        boolean isThePacmanOnTheCell = false;
        for (Creature creature : creaturesOnTheCell) {
            if (creature instanceof Pacman) {
                isThePacmanOnTheCell = true;
            }
        }
        return isThePacmanOnTheCell;
    }

    public Pacman getThePacman() {
        Pacman pacman = null;
        if (isThePacmanOnTheCell()) {
            for (Creature creature : creaturesOnTheCell) {
                if (creature instanceof Pacman) {
                    pacman = (Pacman) creature;
                }
            }
        }
        return pacman;
    }

    public boolean isTheGhostOnTheCell() {
        boolean isTheGhostOnTheCell = false;
        for (Creature creature : creaturesOnTheCell) {
            if (creature instanceof Ghost) {
                isTheGhostOnTheCell = true;
            }
        }
        return isTheGhostOnTheCell;
    }

    public List<Ghost> getGhosts() {
        List<Ghost> ghosts = new ArrayList<Ghost>();
        if (isTheGhostOnTheCell()) {
            for (Creature creature : creaturesOnTheCell) {
                if (creature instanceof Ghost) {
                    ghosts.add((Ghost) creature);
                }
            }
        }
        return ghosts;
    }


    public List<Creature> getCreatures() {
        return creaturesOnTheCell;
    }

    public void setObstacleOnTheCell() {
        this.isTheObstacleOnTheCell = true;
    }

    public boolean isTheCellWithoutObstacles() {
        return !isTheObstacleOnTheCell;
    }

    public boolean isTheObstacleOnTheCell() {
        return isTheObstacleOnTheCell;
    }

    public void removeObstacleFromTheCell() {
        isTheObstacleOnTheCell = false;
    }



    public boolean whatTypeOfCreatureOnTheCell(String creatureType) { //Refactro (remove)
        boolean isGhostOnCell = false;
        for (Creature creature : creaturesOnTheCell) {
            if (creature.getClass().getSimpleName().equals(creatureType)) {
                isGhostOnCell = true;
            }
        }
        return isGhostOnCell;
    }


}
