package logic.brick;

import visitor.Visitor;

/**
 * Wooden brick
 *
 * @author Jose Miguel Cordero
 */
public class WoodenBrick extends AbstractBrick {

    public WoodenBrick() {
        super(3, 200);
    }

    /**
     * Performs the operation defined by the visitor
     *
     * @param visitor operation
     */
    @Override
    public void accept(Visitor visitor) {
        visitor.visitWoodenBrick(this);
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
        return true;
    }

    /**
     * Returns a boolean depending of the type
     *
     * @return true if is Metal Brick
     */
    @Override
    public boolean isMetalBrick() {
        return false;
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
