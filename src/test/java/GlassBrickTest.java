package test.java;

import logic.brick.GlassBrick;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test the GlassBrick class associated methods
 */
public class GlassBrickTest implements BrickTest {

    private GlassBrick gb1;
    private GlassBrick gb2;
    private GlassBrick gb3;

    /**
     * Initialize GlassBricks
     */
    @Before
    public void setUp() {
        gb1 = new GlassBrick();
        gb2 = new GlassBrick();
        gb3 = new GlassBrick(2,3);
    }
    /**
     * Test getI method
     */
    @Override
    @Test
    public void testGetI() {
        assertEquals(0, gb1.getI());
        assertEquals(0, gb2.getI());
        assertEquals(2, gb3.getI());
    }

    /**
     * Test getJ method
     */
    @Override
    @Test
    public void testGetJ() {
        assertEquals(0, gb1.getJ());
        assertEquals(0, gb2.getJ());
        assertEquals(3, gb3.getJ());
    }

    /**
     * Test setPosition method
     */
    @Override
    @Test
    public void testSetPosition() {
        gb1.setPosition(7,9);
        gb3.setPosition(0,0);
        assertEquals(7, gb1.getI());
        assertEquals(9, gb1.getJ());
        assertEquals(0, gb3.getI());
        assertEquals(0, gb3.getJ());

    }

    /**
     * Test getTotalHP method
     */
    @Override
    @Test
    public void testGetTotalHP() {
        assertEquals(1, gb1.getTotalHP());
        assertEquals(1, gb2.getTotalHP());
        assertEquals(1, gb3.getTotalHP());
    }

    /**
     * Test setTotalHP method
     */
    @Override
    @Test
    public void testSetTotalHP() {
        gb1.setTotalHP(10);
        gb2.setTotalHP(25);
        gb3.setTotalHP(44);
        assertEquals(10, gb1.getTotalHP());
        assertEquals(25, gb2.getTotalHP());
        assertEquals(44, gb3.getTotalHP());

    }

    /**
     * Test getCurrentHP method
     */
    @Override
    @Test
    public void testGetCurrentHP() {
        assertEquals(1, gb1.getCurrentHP());
        assertEquals(1, gb2.getCurrentHP());
        assertEquals(1, gb3.getCurrentHP());
    }

    /**
     * Test setCurrentHP method
     */
    @Override
    @Test
    public void testSetCurrentHP() {
        gb1.setCurrentHP(89);
        gb2.setCurrentHP(24);
        gb3.setCurrentHP(66);
        assertEquals(89, gb1.getCurrentHP());
        assertEquals(24, gb2.getCurrentHP());
        assertEquals(66, gb3.getCurrentHP());
    }

    /**
     * Test getScore method
     */
    @Override
    @Test
    public void testGetScore() {
        assertEquals(50, gb1.getScore());
        assertEquals(50, gb2.getScore());
        assertEquals(50, gb3.getScore());
    }

    /**
     * Test setScore method
     */
    @Override
    @Test
    public void testSetScore() {
        gb1.setScore(43);
        gb2.setScore(345);
        gb3.setScore(875);
        assertEquals(43, gb1.getScore());
        assertEquals(345, gb2.getScore());
        assertEquals(875, gb3.getScore());
    }

    /**
     * Test equals method
     */
    @Override
    @Test
    public void testEquals() {
        assertEquals(new GlassBrick(), new GlassBrick());
        assertEquals(gb1, new GlassBrick());
        assertEquals(gb1, gb2);
        assertNotEquals(gb1,gb3);
    }

    /**
     * Test hit method
     */
    @Override
    @Test
    public void testHit() {
        assertEquals(1, gb1.getCurrentHP());
        gb1.hit();
        assertEquals(0, gb1.getCurrentHP());
        gb1.hit();
    }

    /**
     * Test isDestroyed method
     */
    @Override
    @Test
    public void testIsDestroyed() {
        assertFalse(gb1.isDestroyed());
        assertFalse(gb2.isDestroyed());
        gb1.hit();
        gb2.setCurrentHP(-2);
        assertTrue(gb1.isDestroyed());
        assertTrue(gb2.isDestroyed());
    }

    /**
     * Test remainingHits method
     */
    @Override
    @Test
    public void testRemainingHits() {
        assertEquals(1,gb1.remainingHits());
        assertEquals(1,gb2.remainingHits());
        assertEquals(1,gb3.remainingHits());
        gb1.setCurrentHP(0);
        assertEquals(0,gb1.remainingHits());
    }
}
