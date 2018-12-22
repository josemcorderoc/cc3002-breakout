package test.java;

import logic.brick.AbstractBrick;
import logic.brick.GlassBrick;
import logic.brick.MetalBrick;
import logic.brick.WoodenBrick;
import logic.level.AbstractLevel;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test the WoodenBrick class associated methods
 */
public class WoodenBrickTest implements BrickTest {

    private WoodenBrick wb1;
    private WoodenBrick wb2;
    private WoodenBrick wb3;

    /**
     * Initialize WoodenBricks
     */
    @Before
    public void setUp() {
        wb1 = new WoodenBrick();
        wb2 = new WoodenBrick();
        wb3 = new WoodenBrick();
    }

    /**
     * Test getTotalHP method
     */
    @Override
    @Test
    public void testGetTotalHP() {
        assertEquals(3, wb1.getTotalHP());
        assertEquals(3, wb2.getTotalHP());
        assertEquals(3, wb3.getTotalHP());
    }

    /**
     * Test setTotalHP method
     */
    @Override
    @Test
    public void testSetTotalHP() {
        wb1.setTotalHP(11);
        wb2.setTotalHP(25);
        wb3.setTotalHP(44);
        assertEquals(11, wb1.getTotalHP());
        assertEquals(25, wb2.getTotalHP());
        assertEquals(44, wb3.getTotalHP());

    }

    /**
     * Test getCurrentHP method
     */
    @Override
    @Test
    public void testGetCurrentHP() {
        assertEquals(3, wb1.getCurrentHP());
        assertEquals(3, wb2.getCurrentHP());
        assertEquals(3, wb3.getCurrentHP());
    }

    /**
     * Test setCurrentHP method
     */
    @Override
    @Test
    public void testSetCurrentHP() {
        wb1.setCurrentHP(88);
        wb2.setCurrentHP(24);
        wb3.setCurrentHP(66);
        assertEquals(88, wb1.getCurrentHP());
        assertEquals(24, wb2.getCurrentHP());
        assertEquals(66, wb3.getCurrentHP());
    }

    /**
     * Test getScore method
     */
    @Override
    @Test
    public void testGetScore() {
        assertEquals(200, wb1.getScore());
        assertEquals(200, wb2.getScore());
        assertEquals(200, wb3.getScore());
    }

    /**
     * Test setScore method
     */
    @Override
    @Test
    public void testSetScore() {
        wb1.setScore(43);
        wb2.setScore(344);
        wb3.setScore(875);
        assertEquals(43, wb1.getScore());
        assertEquals(344, wb2.getScore());
        assertEquals(875, wb3.getScore());
    }

    /**
     * Test equals method
     */
    @Override
    @Test
    public void testEquals() {
        assertEquals(new WoodenBrick(), new WoodenBrick());
        assertEquals(wb1, new WoodenBrick());
        assertEquals(wb1, wb2);
    }

    /**
     * Test hit method
     */
    @Override
    @Test
    public void testHit() {
        assertEquals(3, wb1.getCurrentHP());
        wb1.hit();
        assertEquals(2, wb1.getCurrentHP());
        wb1.hit();
        wb1.hit();
        wb1.hit();
        assertEquals(0, wb1.getCurrentHP());
    }

    /**
     * Test isDestroyed method
     */
    @Override
    @Test
    public void testIsDestroyed() {
        assertFalse(wb1.isDestroyed());
        assertFalse(wb2.isDestroyed());
        wb1.hit();
        wb1.hit();
        wb1.hit();
        wb2.setCurrentHP(-3);
        assertTrue(wb1.isDestroyed());
        assertTrue(wb2.isDestroyed());
    }

    /**
     * Test remainingHits method
     */
    @Override
    @Test
    public void testRemainingHits() {
        assertEquals(3,wb1.remainingHits());
        assertEquals(3,wb2.remainingHits());
        assertEquals(3,wb3.remainingHits());
        wb1.setCurrentHP(0);
        assertEquals(0,wb1.remainingHits());
    }
}
