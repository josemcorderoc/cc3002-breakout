package logic.level;

import logic.brick.Brick;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class NullLevel implements Level {

    private Level nextLevel;
    private boolean isEnd;

    public NullLevel(boolean cond) {
        nextLevel = null;
        isEnd = cond;
    }

    public NullLevel() {
        this(true);
    }

    /**
     * Gets the level's name. Each level must have a name.
     *
     * @return the table's name
     */
    @Override
    public String getName() {
        return "";
    }

    /**
     * Gets the number of {@link Brick} in the level.
     *
     * @return the number of Bricks in the level
     */
    @Override
    public int getNumberOfBricks() {
        return 0;
    }

    /**
     * Gets the {@link List} of {@link Brick}s in the level.
     *
     * @return the bricks in the level
     */
    @Override
    public List<Brick> getBricks() {
        return new ArrayList<Brick>();
    }

    /**
     * Gets the next level of a level object. Each level have a reference to the next level to play.
     *
     * @return the next level
     */
    @Override
    public Level getNextLevel() {
        return this;
    }

    /**
     * Check if 2 null levels are the same
     * @param o level to compare
     * @return true if equals, false else
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof NullLevel) {
            return true;
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
        return false;
    }

    /**
     * Whether the level's next level is playable or not.
     *
     * @return true if the level's next level is playable, false otherwise
     */
    @Override
    public boolean hasNextLevel() {
        return false;
    }

    /**
     * Gets the total number of points obtainable in level.
     *
     * @return the number of points in the current level
     */
    @Override
    public int getPoints() {
        return 0;
    }

    /**
     * Adds a level to the list of levels. This adds the level in the last position of the list.
     *
     * @param level the level to be added
     */
    @Override
    public Level addPlayingLevel(Level level) {
        return level;
    }

    /**
     * Sets the reference for the next level of a level object.
     *
     * @param level the next level of a level object
     */
    @Override
    public void setNextLevel(Level level) {}

    /**
     * Gets whether the level is winned (max score is achieved) or not.
     *
     * @return True if level is winned, false if is not
     */
    @Override
    public boolean winned() {
        return isEnd;
    }

    @Override
    public void update(Observable observable, Object o) {

    }
}
