package logic.level;

import logic.brick.*;
import java.util.*;

/**
 * Real level class: models a playable level
 */
public class RealLevel extends Observable implements Level  {

    private List<Brick> bricks;
    private int numberOfBricks;
    private String name;
    private Level nextLevel;
    private int points;
    private int currentPoints;

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
    public RealLevel(String name, int numberOfBricks, double probOfGlass, double probOfMetal, int seed) {
        this.name = name;
        this.bricks = new ArrayList<>();
        this.nextLevel = new NullLevel();
        this.points = 0;
        this.currentPoints = 0;

        // creation of brick list
        Random random = new Random(seed);
        for(int i = 0; i < numberOfBricks; i++) {
            Brick newBrick = (random.nextDouble() < probOfGlass) ? new GlassBrick() : new WoodenBrick();
            bricks.add(newBrick);
            points += newBrick.getScore();
        }

        // HAY QUE VER SI CREAR OTRO ARRAYLIST PARA LOS METALBRICKS
        for(int i = 0; i < numberOfBricks; i++) {
            if (random.nextDouble() < probOfMetal) {
                bricks.add(new MetalBrick());
            }
        }

        this.numberOfBricks = bricks.size();
    }

    /**
     * Gets the level's name. Each level must have a name.
     *
     * @return the table's name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Gets the number of {@link Brick} in the level.
     *
     * @return the number of Bricks in the level
     */
    @Override
    public int getNumberOfBricks() {
        return numberOfBricks;
    }

    /**
     * Gets the {@link List} of {@link Brick}s in the level.
     *
     * @return the bricks in the level
     */
    @Override
    public List<Brick> getBricks() {
        return bricks;
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
     * Check if 2 null levels are the same
     * @param o level to compare
     * @return true if equals, false else
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof RealLevel) {
            return this.bricks.equals(((RealLevel)o).bricks) &&
                this.name.equals(((RealLevel)o).name) &&
                this.nextLevel.equals(((RealLevel)o).nextLevel) &&
                this.points == ((RealLevel)o).points &&
                this.currentPoints == ((RealLevel)o).currentPoints;
        }
        return false;
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
     * Gets the total number of points obtainable in level.
     *
     * @return the number of points in the current level
     */
    @Override
    public int getPoints() {
        return points;
    }

    /**
     * points setter
     */
    public void setPoints(int newPoints) {
        points = newPoints;
    }

    public int getCurrentPoints() {
        return currentPoints;
    }

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
        return currentPoints >= points;
    }

    /**
     * Subscribes the bricks (observable) to the level (observer)
     */
    public void setObservableBricks() {
        for (Brick brick : bricks) {
            ((AbstractBrick) brick).addObserver(this);
        }
    }

    @Override
    public void update(Observable observable, Object o) {
        if (observable instanceof Brick) {
            Brick brick = (Brick) observable;
            currentPoints += brick.getScore();
            setChanged();
            notifyObservers(brick.getScore());

            if (brick instanceof MetalBrick) {
                setChanged();
                notifyObservers("extraBall");
            }

            numberOfBricks--;
            //bricks.remove(brick);

            if (winned()) {
                setChanged();
                notifyObservers(true);
            }
        }
    }
}
