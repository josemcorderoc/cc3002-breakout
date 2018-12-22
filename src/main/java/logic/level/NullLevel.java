package logic.level;

import java.util.Observable;

/**
 * NullLevel class, models a null level object according to Null Object Pattern.
 * Field isEnd allows to use NullLevel in end or in beginning of level list
 *
 * @author Jose Miguel Cordero
 */
public class NullLevel extends AbstractLevel {

    private boolean isEnd;

    /**
     * NullLevel constructor
     * @param cond is end level or not
     */
    public NullLevel(boolean cond) {
        super();
        isEnd = cond;
    }

    /**
     * Default non-parameters NullLevel constructor
     */
    public NullLevel() {
        this(true);
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
     * Check if 2 null levels are the same (2 instances of NullLevel are always equals)
     * @param o level to compare
     * @return true if equals, false else
     */
    @Override
    public boolean equals(Object o) {
        return o instanceof NullLevel;
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
    public void setNextLevel(Level level) {

    }

    /**
     * Gets whether the level is winned (max score is achieved) or not.
     *
     * @return True if level is winned, false if is not
     */
    @Override
    public boolean winned() {
        return isEnd;
    }

    /**
     * Destroys all bricks in the level
     */
    @Override
    public void destroyAllBricks() {
    }

    @Override
    public void update(Observable observable, Object o) {
    }
}
