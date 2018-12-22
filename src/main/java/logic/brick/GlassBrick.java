package logic.brick;

import visitor.Visitor;

/**
 * Glass brick
 *
 * @author Jose Miguel Cordero
 */
public class GlassBrick extends AbstractBrick {

    public GlassBrick() {
        super(1, 50);
    }

    /**
     * Performs the operation defined by the visitor
     *
     * @param visitor operation
     */
    @Override
    public void accept(Visitor visitor) {
        visitor.visitGlassBrick(this);
    }

    /**
     * Returns a boolean depending of the type
     *
     * @return true if is Glass Brick
     */
    @Override
    public boolean isGlassBrick() {
        return true;
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
