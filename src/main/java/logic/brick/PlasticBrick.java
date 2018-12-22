package logic.brick;

import visitor.Visitor;

/**
 * Plastic brick: destroy all the bricks on level when destroyed
 *
 * @author Jose Miguel Cordero
 */
public class PlasticBrick extends AbstractBrick {

    public PlasticBrick() {
        super(15, 0);
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
        return false;
    }

    /**
     * Returns a boolean depending of the type
     *
     * @return true if is Plastic Brick
     */
    @Override
    public boolean isPlasticBrick() {
        return true;
    }

    /**
     * Performs the operation defined by the visitor
     *
     * @param visitor operation
     */
    @Override
    public void accept(Visitor visitor) {
        visitor.visitPlasticBrick(this);
    }
}
