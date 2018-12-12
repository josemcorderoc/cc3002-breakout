package logic.brick;

import visitor.Visitor;

public class WoodenBrick extends AbstractBrick {

    public WoodenBrick(int i, int j) {
        super(3, 200, i, j);
    }

    public WoodenBrick() {
        this(0,0);
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
}
