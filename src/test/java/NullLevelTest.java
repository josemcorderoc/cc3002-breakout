package test.java;

import logic.level.NullLevel;
import logic.level.RealLevel;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class NullLevelTest {

    private NullLevel nulllevel1;
    private NullLevel nulllevel2;

    @Before
    /**
     * Initialize null levels
     */
    public void setUp() {
        nulllevel1 = new NullLevel();
        nulllevel2 = new NullLevel();
    }

    /**
     * Test equals method
     */
    @Test
    public void testEquals() {

    }

    /**
     * Test getName method
     */
    @Test
    public void testGetName() {
        assertEquals("",nulllevel1.getName());
        assertEquals("",nulllevel2.getName());
    }

    /**
     * Test getNumberOfBricks method
     */
    @Test
    public void testGetNumberOfBricks() {
        assertEquals(0, nulllevel1.getNumberOfBricks());
        assertEquals(0, nulllevel2.getNumberOfBricks());
    }

    /**
     * Test getBricks method
     */
    @Test
    public void testGetBricks() {

    }

    /**
     * Test getNextLevel method
     */
    @Test
    public void testGetNextLevel() {

    }

    /**
     * Test isPlayableLevel method
     */
    @Test
    public void testIsPlayableLevel() {

    }

    /**
     * Test hasNextLevel method
     */
    @Test
    public void testHasNextLevel() {

    }

    /**
     * test getPoints method
     */
    @Test
    public void testGetPoints() {

    }

    /**
     * test addPlayingLevel method
     */
    @Test
    public void testAddPlayingLevel() {

    }

    /**
     * test setNextLevel method
     */
    @Test
    public void testSetNextLevel() {

    }
}
