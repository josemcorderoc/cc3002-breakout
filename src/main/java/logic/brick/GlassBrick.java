package logic.brick;

import visitor.Visitor;

/**
 * A glass brick model
 *
 * @author Jose Migue Cordero
 */
public class GlassBrick extends AbstractBrick {

    public GlassBrick(int i, int j) {
        super(1, 50, i, j);
    }
    public GlassBrick() {
        this(0,0);
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
}
