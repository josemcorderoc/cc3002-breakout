package test.java;

import logic.brick.GlassBrick;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class GlassBrickTest {

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
        gb3 = new GlassBrick();
    }

    /**
     * Test equals method
     */
    @Test
    public void testEquals() {
        assertEquals(new GlassBrick(), new GlassBrick());
        assertEquals(gb1, new GlassBrick());
        assertEquals(gb1, gb2);
    }

    /**
     * Test hit method
     */
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
    @Test
    public void testIsDestroyed() {
        assertFalse(gb1.isDestroyed());
        assertFalse(gb2.isDestroyed());
        gb1.hit();
        gb2.setCurrentHP(-2);
        assertTrue(gb1.isDestroyed());
        assertTrue(gb2.isDestroyed());
    }
}
