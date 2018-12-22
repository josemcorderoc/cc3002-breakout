package test.java;

import logic.brick.MetalBrick;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test the MetalBrick class associated methods
 */
public class MetalBrickTest implements BrickTest {

    private MetalBrick mb1;
    private MetalBrick mb2;
    private MetalBrick mb3;

    /**
     * Initialize MetalBricks
     */
    @Before
    public void setUp() {
        mb1 = new MetalBrick();
        mb2 = new MetalBrick();
        mb3 = new MetalBrick();
    }

    /**
     * Test getTotalHP method
     */
    @Override
    @Test
    public void testGetTotalHP() {
        assertEquals(10, mb1.getTotalHP());
        assertEquals(10, mb2.getTotalHP());
        assertEquals(10, mb3.getTotalHP());
    }

    /**
     * Test setTotalHP method
     */
    @Override
    @Test
    public void testSetTotalHP() {
        mb1.setTotalHP(15);
        mb2.setTotalHP(25);
        mb3.setTotalHP(44);
        assertEquals(15, mb1.getTotalHP());
        assertEquals(25, mb2.getTotalHP());
        assertEquals(44, mb3.getTotalHP());

    }

    /**
     * Test getCurrentHP method
     */
    @Override
    @Test
    public void testGetCurrentHP() {
        assertEquals(10, mb1.getCurrentHP());
        assertEquals(10, mb2.getCurrentHP());
        assertEquals(10, mb3.getCurrentHP());
    }

    /**
     * Test setCurrentHP method
     */
    @Override
    @Test
    public void testSetCurrentHP() {
        mb1.setCurrentHP(89);
        mb2.setCurrentHP(241);
        mb3.setCurrentHP(66);
        assertEquals(89, mb1.getCurrentHP());
        assertEquals(241, mb2.getCurrentHP());
        assertEquals(66, mb3.getCurrentHP());
    }

    /**
     * Test getScore method
     */
    @Override
    @Test
    public void testGetScore() {
        assertEquals(0, mb1.getScore());
        assertEquals(0, mb2.getScore());
        assertEquals(0, mb3.getScore());
    }

    /**
     * Test setScore method
     */
    @Override
    @Test
    public void testSetScore() {
        mb1.setScore(432);
        mb2.setScore(345);
        mb3.setScore(875);
        assertEquals(432, mb1.getScore());
        assertEquals(345, mb2.getScore());
        assertEquals(875, mb3.getScore());
    }

    /**
     * Test equals method
     */
    @Override
    @Test
    public void testEquals() {
        assertEquals(new MetalBrick(), new MetalBrick());
        assertEquals(mb1, new MetalBrick());
        assertEquals(mb1, mb2);
    }

    /**
     * Test hit method
     */
    @Override
    @Test
    public void testHit() {
        assertEquals(10, mb1.getCurrentHP());
        mb1.hit();
        assertEquals(9, mb1.getCurrentHP());
        mb1.hit();
        mb1.hit();
        mb1.hit();
        mb1.hit();
        mb1.hit();
        mb1.hit();
        mb1.hit();
        mb1.hit();
        mb1.hit();
        mb1.hit();
        assertEquals(0, mb1.getCurrentHP());
    }

    /**
     * Test isDestroyed method
     */
    @Override
    @Test
    public void testIsDestroyed() {
        assertFalse(mb1.isDestroyed());
        assertFalse(mb2.isDestroyed());
        mb2.setCurrentHP(-2);
        assertTrue(mb2.isDestroyed());
    }

    /**
     * Test remainingHits method
     */
    @Override
    @Test
    public void testRemainingHits() {
        assertEquals(10,mb1.remainingHits());
        assertEquals(10,mb2.remainingHits());
        assertEquals(10,mb3.remainingHits());
        mb1.setCurrentHP(0);
        assertEquals(0,mb1.remainingHits());
    }
}
