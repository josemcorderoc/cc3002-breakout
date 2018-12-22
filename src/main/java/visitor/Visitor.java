package visitor;

import controller.Game;
import logic.brick.GlassBrick;
import logic.brick.MetalBrick;
import logic.brick.PlasticBrick;
import logic.brick.WoodenBrick;
import logic.level.RealLevel;

/**
 * Defines a visitor (according to Visitor Pattern)
 *
 * @author Jose Miguel Cordero
 */
public interface Visitor {

    /**
     * Implements operation defined by the visitor requested by {@link RealLevel}
     * @param level real level
     */
    void visitRealLevel(RealLevel level);

    /**
     * Implements operation defined by the visitor requested by {@link MetalBrick}
     * @param brick metal brick
     */
    void visitMetalBrick(MetalBrick brick);

    /**
     * Implements operation defined by the visitor requested by {@link GlassBrick}
     * @param brick glass brick
     */
    void visitGlassBrick(GlassBrick brick);

    /**
     * Implements operation defined by the visitor requested by {@link WoodenBrick}
     * @param brick wooden brick
     */
    void visitWoodenBrick(WoodenBrick brick);

    /**
     * Implements operation defined by the visitor requested by {@link PlasticBrick}
     * @param brick plastic brick
     */
    void visitPlasticBrick(PlasticBrick brick);

    /**
     * Implements operation defined by the visitor requested by {@link Game}
     * @param game game
     */
    void visitGame(Game game);


}
