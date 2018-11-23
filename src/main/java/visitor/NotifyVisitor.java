package visitor;

import logic.brick.GlassBrick;
import logic.brick.MetalBrick;
import logic.brick.WoodenBrick;
import logic.level.RealLevel;

/**
 * Visitor used for notify to observers of Visitable (observable) object (e.g., a brick with no HP o a winned level)
 *
 * @author Jose Miguel Cordero
 */
public class NotifyVisitor implements Visitor {

    private int score = 0;
    private int extraBalls = 0;

    public int getScore() {
        return score;
    }

    public int getExtraBalls() {
        return extraBalls;
    }

    @Override
    public void visitRealLevel(RealLevel level) {
        level.notifyObservers(this);
    }

    @Override
    public void visitMetalBrick(MetalBrick brick) {
        score += brick.getScore();
        extraBalls++;
        brick.notifyObservers(this);
    }

    @Override
    public void visitGlassBrick(GlassBrick brick) {
        score += brick.getScore();
        brick.notifyObservers(this);

    }

    @Override
    public void visitWoodenBrick(WoodenBrick brick) {
        score += brick.getScore();
        brick.notifyObservers(this);
    }
}
