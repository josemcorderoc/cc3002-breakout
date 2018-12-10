package logic.brick;

import visitor.Visitor;

public class MetalBrick extends AbstractBrick {

    public MetalBrick(int i, int j) {
        super(10, 0, i, j);
    }

    public MetalBrick() {
        this(0, 0);
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
}
