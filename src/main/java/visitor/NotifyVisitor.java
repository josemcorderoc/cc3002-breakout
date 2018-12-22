package visitor;

import controller.Game;
import logic.brick.GlassBrick;
import logic.brick.MetalBrick;
import logic.brick.PlasticBrick;
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
    private boolean destroyBricks = false;
    private boolean winner = false;
    private boolean changeLevel = false;

    public int getScore() {
        return score;
    }

    public boolean getWinner() {
        return winner;
    }

    public int getExtraBalls() {
        return extraBalls;
    }

    public boolean getChangeLevel() {
        return changeLevel;
    }

    public boolean getDestroyBricks() {
        return destroyBricks;
    }
    @Override
    public void visitRealLevel(RealLevel level) {
        changeLevel = level.winned();
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

    @Override
    public void visitPlasticBrick(PlasticBrick brick) {
        score += brick.getScore();
        destroyBricks = true;
        brick.notifyObservers(this);
    }

    @Override
    public void visitGame(Game game) {
        score = game.getCurrentScore();
        winner = game.winner();
        game.notifyObservers(this);
    }
}
