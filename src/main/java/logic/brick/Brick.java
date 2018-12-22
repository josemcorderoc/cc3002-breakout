package logic.brick;

/**
 * Interface that represents a brick object.
 * <p>
 * All bricks should implement this interface.
 *
 * @author Juan-Pablo Silva
 */
public interface Brick


{
    /**
     * Defines that a brick has been hit.
     * Implementations should consider the events that a hit to a brick can trigger.
     */
    void hit();

    /**
     * Gets whether the brick object is destroyed or not.
     *
     * @return true if the brick is destroyed, false otherwise
     */
    boolean isDestroyed();

    /**
     * Gets the points corresponding to the destroying of a brick object.
     *
     * @return the associated points of a brick object
     */
    int getScore();

    /**
     * Gets the remaining hits the brick has to receive before being destroyed.
     *
     * @return the remaining hits to destroy de brick
     */
    int remainingHits();

    /**
     * Returns a boolean depending of the type
     * @return true if is Glass Brick
     */
    boolean isGlassBrick();

    /**
     * Returns a boolean depending of the type
     * @return true if is Wooden Brick
     */
    boolean isWoodenBrick();

    /**
     * Returns a boolean depending of the type
     * @return true if is Metal Brick
     */
    boolean isMetalBrick();

    /**
     * Returns a boolean depending of the type
     * @return true if is Plastic Brick
     */
    boolean isPlasticBrick();


}
