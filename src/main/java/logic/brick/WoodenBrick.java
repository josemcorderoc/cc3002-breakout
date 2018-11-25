package logic.brick;

import visitor.Visitor;

/**
 * A wooden brick model
 *
 * @author Jose Migue Cordero
 */
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
}
