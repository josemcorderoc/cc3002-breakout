package test.java;

import logic.brick.GlassBrick;
import logic.brick.MetalBrick;
import logic.brick.WoodenBrick;
import logic.level.RealLevel;
import org.junit.Before;
import org.junit.Test;
import visitor.NotifyVisitor;
import static org.junit.Assert.*;

public class NotifyVisitorTest {

    private RealLevel level1;
    private RealLevel level2;
    private MetalBrick mb1;
    private MetalBrick mb2;
    private GlassBrick gb1;
    private GlassBrick gb2;
    private WoodenBrick wb1;
    private WoodenBrick wb2;

    private NotifyVisitor visitor1;
    private NotifyVisitor visitor2;
    private NotifyVisitor visitor3;

    /**
     * initizalize
     */
    @Before
    public void setUp() {
        level1 = new RealLevel();
        mb1 = new MetalBrick();
        gb1 = new GlassBrick();
        wb1 = new WoodenBrick();

        level2 = new RealLevel();
        mb2 = new MetalBrick();
        gb2 = new GlassBrick();
        wb2 = new WoodenBrick();

        visitor1 = new NotifyVisitor();
        visitor2 = new NotifyVisitor();
        visitor3 = new NotifyVisitor();
    }

    /**
     * test getScore method
     */
    @Test
    public void testGetScore() {
        assertEquals(0, visitor1.getScore());
        assertEquals(0, visitor2.getScore());
        assertEquals(0, visitor3.getScore());
    }

    /**
     * test getExtraBalls method
     */
    @Test
    public void testGetExtraBalls() {
        assertEquals(0, visitor1.getExtraBalls());
        assertEquals(0, visitor2.getExtraBalls());
        assertEquals(0, visitor3.getExtraBalls());
    }

    /**
     * test visitRealLevel method
     */
    @Test
    public void testVisitRealLevel() {

    }

    /**
     * test visitMetalBrick method
     */
    @Test
    public void testVisitMetalBrick() {

    }

    /**
     * test visitGlassBrick method
     */
    @Test
    public void testVisitGlassBrick() {

    }

    /**
     * test visitWoodenBrick method
     */
    @Test
    public void testVisitWoodenBrick() {

    }

}
