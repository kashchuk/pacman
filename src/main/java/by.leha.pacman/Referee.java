package by.leha.pacman;

public class Referee {

    private int lives = 3;
    private int score = 0;

    public void reduceTheNumberOfLives() {
        if (lives > 0) {
            lives--;
        }
    }

    public void pacmanFoundTheFood() {
        score += 10;
    }

    public void pacmanFoundThePellet() {
        score += 100;
    }

    public int getLives() {
        return lives;
    }

    public int getScore() {
        return score;
    }
}
