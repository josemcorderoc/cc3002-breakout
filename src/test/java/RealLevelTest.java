package test.java;

import logic.brick.Brick;
import logic.brick.GlassBrick;
import logic.brick.MetalBrick;
import logic.brick.WoodenBrick;
import logic.level.NullLevel;
import logic.level.RealLevel;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Test the RealLevel class associated methods
 */
public class RealLevelTest {

    private RealLevel level1;
    private RealLevel level2;
    private RealLevel level3;
    private RealLevel level4;
    private RealLevel level5;

    private RealLevel levelWithNext;

    @Before
    /**
     * Initialize real levels
     */
    public void setUp() {
        level1 = new RealLevel("Level 1", 2, 1, 0,100);
        level2 = new RealLevel("Level 2", 2, 1, 1,100);
        level3 = new RealLevel("Level 3", 2, 0, 1,100);
        level4 = new RealLevel("Level 4", 2, 0, 0,100);
        level5 = new RealLevel("Level 5", 2, 0.5, 0,100);

        levelWithNext = new RealLevel("Level 1", 2, 1, 0,100);
        levelWithNext.setNextLevel(level1);
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
        assertEquals("Level 1", level1.getName());
        assertEquals("Level 2", level2.getName());
        assertEquals("Level 3", level3.getName());
        assertEquals("Level 4", level4.getName());
        assertEquals("Level 5", level5.getName());
    }

    /**
     * Test getNumberOfBricks method
     */
    @Test
    public void testGetNumberOfBricks() {
        assertEquals(2, level1.getNumberOfBricks());
        assertEquals(4, level2.getNumberOfBricks());
        assertEquals(4, level3.getNumberOfBricks());
        assertEquals(2, level4.getNumberOfBricks());
        //assertEquals(15, level5.getNumberOfBricks());
    }

    /**
     * Test getBricks method
     */
    @Test
    public void testGetBricks() {
        List<Brick> level1bricks = new ArrayList<>();
        level1bricks.add(new GlassBrick());
        level1bricks.add(new GlassBrick());
        List<Brick> level2bricks = new ArrayList<>();
        level2bricks.add(new GlassBrick());
        level2bricks.add(new GlassBrick());
        level2bricks.add(new MetalBrick());
        level2bricks.add(new MetalBrick());
        List<Brick> level3bricks = new ArrayList<>();
        level3bricks.add(new WoodenBrick());
        level3bricks.add(new WoodenBrick());
        level3bricks.add(new MetalBrick());
        level3bricks.add(new MetalBrick());
        List<Brick> level4bricks = new ArrayList<>();
        level4bricks.add(new WoodenBrick());
        level4bricks.add(new WoodenBrick());
        //List<Brick> level5bricks = new ArrayList<>();

        assertEquals(level1bricks, level1.getBricks());
        assertEquals(level2bricks, level2.getBricks());
        assertEquals(level3bricks, level3.getBricks());
        assertEquals(level4bricks, level4.getBricks());
    }

    /**
     * Test getNextLevel method
     */
    @Test
    public void testGetNextLevel() {
        assertEquals(new NullLevel(), level1.getNextLevel());
        assertEquals(new NullLevel(), level2.getNextLevel());
        //assertEquals();

    }

    /**
     * Test isPlayableLevel method
     */
    @Test
    public void testIsPlayableLevel() {
        assertTrue(level1.isPlayableLevel());
        assertTrue(level2.isPlayableLevel());
        assertTrue(level3.isPlayableLevel());
        assertTrue(level4.isPlayableLevel());
        assertTrue(level5.isPlayableLevel());
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
        assertEquals(100, level1.getPoints());
        assertEquals(100, level2.getPoints());
        assertEquals(400, level3.getPoints());
        assertEquals(400, level4.getPoints());

    }

    /**
     * test addPlayingLevel method
     */
    @Test
    public void testAddPlayingLevel() {
        level1.addPlayingLevel(level2);
        level1.addPlayingLevel(level3);
        level1.addPlayingLevel(level4);
        assertEquals(level2, level1.getNextLevel());
        assertEquals(level3, level1.getNextLevel().getNextLevel());
        assertEquals(level4, level1.getNextLevel().getNextLevel().getNextLevel());
    }

    /**
     * test setNextLevel method
     */
    @Test
    public void testSetNextLevel() {

    }
}
