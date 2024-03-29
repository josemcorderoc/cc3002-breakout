package test.java;

import logic.brick.Brick;
import logic.brick.GlassBrick;
import logic.level.NullLevel;
import logic.level.RealLevel;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Observable;

import static org.junit.Assert.*;

public class NullLevelTest implements LevelTest {

    private NullLevel nulllevel1;
    private NullLevel nulllevel2;
    private NullLevel nulllevel3;


    /**
     * Initialize null levels
     */
    @Before
    public void setUp() {
        nulllevel1 = new NullLevel();
        nulllevel2 = new NullLevel(true);
        nulllevel3 = new NullLevel(false);
    }

    /**
     * Test equals method
     */
    @Test
    public void testEquals() {
        assertEquals(nulllevel1,nulllevel2);
        assertEquals(nulllevel1,nulllevel3);
        assertNotEquals(nulllevel1, new RealLevel());
        assertNotEquals(nulllevel1, new GlassBrick());
    }

    /**
     * Test getName method
     */
    @Test
    public void testGetName() {
        assertEquals("",nulllevel1.getName());
        assertEquals("",nulllevel2.getName());
        assertEquals("",nulllevel3.getName());
    }

    /**
     * Test getNumberOfBricks method
     */
    @Test
    public void testGetNumberOfBricks() {
        assertEquals(0, nulllevel1.getNumberOfBricks());
        assertEquals(0, nulllevel2.getNumberOfBricks());
        assertEquals(0, nulllevel3.getNumberOfBricks());
    }

    /**
     * Test setNumberOfBricks method
     */
    @Override
    public void testSetNumberOfBricks() {

    }

    /**
     * Test getBricks method
     */
    @Test
    public void testGetBricks() {
        assertEquals(new ArrayList<Brick>(), nulllevel1.getBricks());
        assertEquals(new ArrayList<Brick>(), nulllevel2.getBricks());
        assertEquals(new ArrayList<Brick>(), nulllevel3.getBricks());
    }

    /**
     * Test getNextLevel method
     */
    @Test
    public void testGetNextLevel() {
        assertEquals(new NullLevel(), nulllevel1.getNextLevel());
        assertEquals(new NullLevel(), nulllevel1.getNextLevel());
        assertEquals(new NullLevel(), nulllevel1.getNextLevel());

    }

    /**
     * Test isPlayableLevel method
     */
    @Test
    public void testIsPlayableLevel() {
        assertFalse(nulllevel1.isPlayableLevel());
        assertFalse(nulllevel2.isPlayableLevel());
        assertFalse(nulllevel3.isPlayableLevel());
    }

    /**
     * Test hasNextLevel method
     */
    @Test
    public void testHasNextLevel() {
        assertFalse(nulllevel1.hasNextLevel());
        assertFalse(nulllevel2.hasNextLevel());
        assertFalse(nulllevel3.hasNextLevel());
    }

    /**
     * test getPoints method
     */
    @Test
    public void testGetPoints() {
        assertEquals(0,nulllevel1.getPoints());
        assertEquals(0,nulllevel2.getPoints());
        assertEquals(0,nulllevel3.getPoints());
    }

    /**
     * test addPlayingLevel method
     */
    @Test
    public void testAddPlayingLevel() {
        RealLevel levelAdd = new RealLevel();
        nulllevel1.addPlayingLevel(levelAdd);
        nulllevel1.addPlayingLevel(levelAdd);
        assertEquals(new NullLevel(), nulllevel1);
    }

    /**
     * test setNextLevel method
     */
    @Test
    public void testSetNextLevel() {
        RealLevel level = new RealLevel();
        nulllevel1.setNextLevel(level);
        assertEquals(new NullLevel(), nulllevel1);
    }

    /**
     * test setPoints method
     */
    @Test
    public void testSetPoints() {
        nulllevel1.setPoints(767);
        nulllevel2.setPoints(123);
        nulllevel3.setPoints(456);
        assertEquals(767, nulllevel1.getPoints());
        assertEquals(123, nulllevel2.getPoints());
        assertEquals(456, nulllevel3.getPoints());
    }

    /**
     * Test update method
     */
    @Test
    public void testUpdate() {
        nulllevel1.update(new Observable(), "notify1");
        nulllevel2.update(new Observable(), "notify2");
        nulllevel3.update(new Observable(), "notify3");
        assertEquals(new NullLevel(),nulllevel1);
        assertEquals(new NullLevel(),nulllevel2);
        assertEquals(new NullLevel(),nulllevel3);
    }
}
