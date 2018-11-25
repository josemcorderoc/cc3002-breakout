package controller;

import logic.level.Level;
import logic.level.NullLevel;
import logic.level.RealLevel;
import visitor.NotifyVisitor;

import java.util.Observable;
import java.util.Observer;


/**
 * Game logic controller class.
 *
 * @author Juan-Pablo Silva
 */
public class Game implements Observer {

    private int balls;
    private int currentScore;
    private Level currentLevel;

    /**
     * Game constructor
     * @param balls
     */
    public Game(int balls) {
        this.balls = balls;
        this.currentScore = 0;
        this.currentLevel = new NullLevel(false);
    }

    /**
     * balls getter
     * @return balls
     */
    public int getBalls() {
        return balls;
    }

    /**
     * balls setter
     * @param newBalls new balls set
     */
    public void setBalls(int newBalls) {
        balls = newBalls;
    }

    /**
     * currentScore getter
     * @return currentScore
     */
    public int getCurrentScore() {
        return currentScore;
    }

    /**
     * currentScore setter
     */
    public void setCurrentScore(int newCurrentScore) {
        currentScore = newCurrentScore;

    }

    /**
     * currentLevel getter
     * @return currentLevel
     */
    public Level getCurrentLevel() {
        return currentLevel;
    }

    /**
     * currentLevel setter
     * @param newLevel new level set
     */
    public void setCurrentLevel(Level newLevel) {
        currentLevel = newLevel;
    }

    /**
     * Pass to the next level of the game
     */
    public void goNextLevel() {
        currentLevel = currentLevel.getNextLevel();
    }

    /**
     * Checks if game is over (has a winner or losses all balls)
     * @return True if game is over, false if not
     */
    public boolean isOver() {
        return balls <= 0 || winner();
    }

    /**
     * This method is just an example. Change it or delete it at wish.
     * <p>
     * Checks whether the game has a winner or not
     *
     * @return true if the game has a winner, false otherwise
     */
    public boolean winner() {
        return balls > 0 && currentLevel.winned() && !currentLevel.hasNextLevel();
    }

    /**
     * Reduces the count of available balls and returns the new number.
     *
     * @return the new number of available balls
     */
    public int dropBall() {
        return (balls == 0) ? 0 : --balls;
    }

    @Override
    public void update(Observable observable, Object o) {
        NotifyVisitor visitor = (NotifyVisitor) o;

        currentScore += visitor.getScore();
        balls += visitor.getExtraBalls();

        if (currentLevel.winned()) {
            goNextLevel();
        }
    }

    /**
     * Creates a new level with the given parameters.
     *
     * @param name           the name of the level
     * @param numberOfBricks the number of bricks in the level
     * @param probOfGlass    the probability of a {@link logic.brick.GlassBrick}
     * @param probOfMetal    the probability of a {@link logic.brick.MetalBrick}
     * @param seed           the seed for the random number generator
     * @return a new level determined by the parameters
     * @see Level
     */
    public Level newLevelWithBricksFull(String name, int numberOfBricks, double probOfGlass, double probOfMetal, int seed) {
        RealLevel newLevel = new RealLevel(name, numberOfBricks, probOfGlass, probOfMetal, seed);
        newLevel.setObservableBricks();
        newLevel.addObserver(this);
        return newLevel;
    }

    /**
     * Creates a new level with the given parameters with no metal bricks.
     *
     * @param name           the name of the level
     * @param numberOfBricks the number of bricks in the level
     * @param probOfGlass    the probability of a {@link logic.brick.GlassBrick}
     * @param seed           the seed for the random number generator
     * @return a new level determined by the parameters
     * @see Level
     */
    public Level newLevelWithBricksNoMetal(String name, int numberOfBricks, double probOfGlass, int seed) {
        RealLevel newLevel = new RealLevel(name, numberOfBricks, probOfGlass, 0, seed);
        newLevel.setObservableBricks();
        newLevel.addObserver(this);
        return newLevel;
    }
}
