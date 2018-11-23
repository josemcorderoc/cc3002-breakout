package logic.level;

import logic.brick.*;

import java.util.*;

/**
 * Models behavior and properties of {@link Level}s
 *
 * @author Jose Miguel Cordero
 */
public abstract class AbstractLevel extends Observable implements Level {

    private List<Brick> bricks;
    private int numberOfBricks;
    private String name;
    private int points;

    /**
     * Default non-parameters AbstractLevel constructor
     */
    protected AbstractLevel() {
        this.bricks = new ArrayList<>();
        this.numberOfBricks = 0;
        this.name = "";
        this.points = 0;
    }

    /**
     * AbstractLevel constructor
     * @param name           the name of the level
     * @param numberOfBricks the number of bricks in the level
     * @param probOfGlass    the probability of a {@link logic.brick.GlassBrick}
     * @param probOfMetal    the probability of a {@link logic.brick.MetalBrick}
     * @param seed           the seed for the random number generator
     */
    protected AbstractLevel(String name, int numberOfBricks, double probOfGlass, double probOfMetal, int seed) {
        this.name = name;
        this.bricks = new ArrayList<>();
        this.points = 0;

        // creation of brick list
        Random random = new Random(seed);
        for(int i = 0; i < numberOfBricks; i++) {
            Brick newBrick = (random.nextDouble() < probOfGlass) ? new GlassBrick() : new WoodenBrick();
            bricks.add(newBrick);
            points += newBrick.getScore();
        }

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
     * numberOfBricks setter
     * @param newNumberOfBricks new number of bricks
     */
    public void setNumberOfBricks(int newNumberOfBricks) {
        numberOfBricks = newNumberOfBricks;
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
     * points setter
     */
    public void setPoints(int newPoints) {
        points = newPoints;
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
     * Subscribes the bricks (observable) to the level (observer)
     */
    public void setObservableBricks() {
        for (Brick brick : bricks) {
            ((AbstractBrick) brick).addObserver(this);
        }
    }

    /**
     * Check if 2 levels are the same
     * @param o level to compare
     * @return true if equals, false else
     */
    @Override
    public boolean equals(Object o) {
        if (this.getClass() == o.getClass()) {
            AbstractLevel comp = (AbstractLevel)o;
            return getBricks().equals(comp.getBricks()) &&
                    getName().equals(comp.getName()) &&
                    getNextLevel().equals(comp.getNextLevel()) &&
                    getPoints() == comp.getPoints();
        }
        return false;
    }
}
