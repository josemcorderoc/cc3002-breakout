package logic.brick;

import visitor.Visitor;

/**
 * Metal brick: adds a new ball when destroyed
 *
 * @author Jose Miguel Cordero
 */
public class MetalBrick extends AbstractBrick {

    public MetalBrick() {
        super(10, 0);
    }

    /**
     * Performs the operation defined by the visitor
     *
     * @param visitor operation
     */
    @Override
    public void accept(Visitor visitor) {
        visitor.visitMetalBrick(this);
    }

    /**
     * Returns a boolean depending of the type
     *
     * @return true if is Glass Brick
     */
    @Override
    public boolean isGlassBrick() {
        return false;
    }

    /**
     * Returns a boolean depending of the type
     *
     * @return true if is Wooden Brick
     */
    @Override
    public boolean isWoodenBrick() {
        return false;
    }

    /**
     * Returns a boolean depending of the type
     *
     * @return true if is Metal Brick
     */
    @Override
    public boolean isMetalBrick() {
        return true;
    }

    /**
     * Returns a boolean depending of the type
     *
     * @return true if is Plastic Brick
     */
    @Override
    public boolean isPlasticBrick() {
        return false;
    }
}
