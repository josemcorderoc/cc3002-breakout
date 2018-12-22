package logic.level;

import logic.brick.*;
import visitor.NotifyVisitor;
import visitor.Visitable;
import visitor.Visitor;

import java.util.*;

/**
 * Real level class: models a playable level
 *
 * @author Jose Miguel Cordero
 */
public class RealLevel extends AbstractLevel implements Visitable {

    private Level nextLevel;
    private int currentPoints;
    private List<Observer> observers;
    private boolean stateChange;

    /**
     * Default no-parameters RealLevel constructor
     */
    public RealLevel() {
        this("auto level", 10, 1, 0, 100);
    }

    /**
     * RealLevel constructor
     * @param name           the name of the level
     * @param numberOfBricks the number of bricks in the level
     * @param probOfGlass    the probability of a {@link logic.brick.GlassBrick}
     * @param probOfMetal    the probability of a {@link logic.brick.MetalBrick}
     * @param seed           the seed for the random number generator
     */
    public RealLevel(String name, int numberOfBricks, double probOfGlass, double probOfMetal, long seed) {
        super(name, numberOfBricks, probOfGlass, probOfMetal, seed);

        this.nextLevel = new NullLevel();
        this.currentPoints = 0;
    }

    /**
     * RealLevel constructor with plastic
     * @param name
     * @param numberOfBricks
     * @param probOfGlass
     * @param probOfMetal
     * @param probOfPlastic
     * @param seed
     */
    public RealLevel(String name, int numberOfBricks, double probOfGlass, double probOfMetal, double probOfPlastic, long seed) {
        super(name, numberOfBricks, probOfGlass, probOfMetal, probOfPlastic, seed);

        this.nextLevel = new NullLevel();
        this.currentPoints = 0;
    }

    /**
     * Gets the next level of a level object. Each level have a reference to the next level to play.
     *
     * @return the next level
     */
    @Override
    public Level getNextLevel() {
        return nextLevel;
    }

    /**
     * Gets whether the level is playable or not.
     *
     * @return true if the level is playable, false otherwise
     */
    @Override
    public boolean isPlayableLevel() {
        return true;
    }

    /**
     * Whether the level's next level is playable or not.
     *
     * @return true if the level's next level is playable, false otherwise
     */
    @Override
    public boolean hasNextLevel() {
        return nextLevel.isPlayableLevel();
    }

    /**
     * currentPoints getter
     * @return currentPoints
     */
    public int getCurrentPoints() {
        return currentPoints;
    }

    /**
     * currentPoints setter
     * @param newCurrentPoints new current points set
     */
    public void setCurrentPoints(int newCurrentPoints) {
        currentPoints = newCurrentPoints;
    }
    /**
     * Adds a level to the list of levels. This adds the level in the last position of the list.
     *
     * @param level the level to be added
     */
    @Override
    public Level addPlayingLevel(Level level) {
        this.nextLevel = this.nextLevel.addPlayingLevel(level);
        return this;
    }

    /**
     * Sets the reference for the next level of a level object.
     *
     * @param level the next level of a level object
     */
    @Override
    public void setNextLevel(Level level) {
        nextLevel = level;
    }

    /**
     * Gets whether the level is winned (max score is achieved) or not.
     *
     * @return True if level is winned, false if is not
     */
    @Override
    public boolean winned() {
        return currentPoints >= getPoints();
    }

    /**
     * Destroys all bricks in the level
     */
    @Override
    public void destroyAllBricks() {
        for(Brick brick : getBricks()) {
            while(!brick.isDestroyed()) {
                brick.hit();
            }
        }
    }

    /**
     * Subscribes the bricks (observable) to the level (observer)
     */
    public void setObservableBricks() {
        for (Brick brick : getBricks()) {
            ((AbstractBrick) brick).addObserver(this);
        }
    }

    @Override
    public void update(Observable observable, Object o) {

        NotifyVisitor visitor = (NotifyVisitor) o;
        currentPoints += visitor.getScore();
        setNumberOfBricks(getNumberOfBricks()-1);
        setChanged();
        accept(visitor);
    }

    /**
     * Performs the operation defined by the visitor
     *
     * @param visitor operation
     */
    @Override
    public void accept(Visitor visitor) {
        visitor.visitRealLevel(this);
    }

}
