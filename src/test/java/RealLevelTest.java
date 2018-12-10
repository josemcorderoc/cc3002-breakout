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
public class RealLevelTest implements LevelTest {

    private RealLevel level1;
    private RealLevel level2;
    private RealLevel level3;
    private RealLevel level4;
    private RealLevel level5;

    private RealLevel levelWithNext;
    private RealLevel level1next;
    private RealLevel level2next;
    private RealLevel level3next;
    private RealLevel level4next;
    private RealLevel level5next;


    /**
     * Initialize real levels
     */
    @Before
    public void setUp() {
        level1 = new RealLevel("Level 1", 2, 1, 0,100);
        level2 = new RealLevel("Level 2", 2, 1, 1,100);
        level3 = new RealLevel("Level 3", 2, 0, 1,100);
        level4 = new RealLevel("Level 4", 2, 0, 0,100);
        level5 = new RealLevel("Level 5", 2, 0.5, 0,100);

        level1next = new RealLevel("Level 1", 2, 1, 0,100);
        level2next = new RealLevel("Level 2", 2, 1, 1,100);
        level3next = new RealLevel("Level 3", 2, 0, 1,100);
        level4next = new RealLevel("Level 4", 2, 0, 0,100);
        level5next = new RealLevel("Level 5", 2, 0.5, 0,100);


        levelWithNext = new RealLevel("Level With Next", 2, 1, 0,100);
        levelWithNext.addPlayingLevel(level1next);
        levelWithNext.addPlayingLevel(level2next);
        levelWithNext.addPlayingLevel(level3next);
        levelWithNext.addPlayingLevel(level4next);
    }

    /**
     * Test equals method
     */
    @Test
    public void testEquals() {
        assertEquals(level1, level1);
        assertEquals(level1, new RealLevel("Level 1", 2, 1, 0,100));
        assertNotEquals(level1, level2);
        assertNotEquals(level1, levelWithNext);
        assertNotEquals(level1, new NullLevel());
        assertNotEquals(level1, new GlassBrick());
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
     * Test setNumberOfBricks method
     */
    @Test
    public void testSetNumberOfBricks() {
        level1.setNumberOfBricks(43);
        level2.setNumberOfBricks(23);
        level3.setNumberOfBricks(94);
        level4.setNumberOfBricks(77);
        assertEquals(43, level1.getNumberOfBricks());
        assertEquals(23, level2.getNumberOfBricks());
        assertEquals(94, level3.getNumberOfBricks());
        assertEquals(77, level4.getNumberOfBricks());
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
        //assertEquals(level5bricks, level5.getBricks());
        assertEquals(level1bricks, levelWithNext.getBricks());
    }

    /**
     * Test getNextLevel method
     */
    @Test
    public void testGetNextLevel() {
        assertEquals(new NullLevel(), level1.getNextLevel());
        assertEquals(new NullLevel(), level2.getNextLevel());
        assertEquals(new NullLevel(), level3.getNextLevel());
        assertEquals(new NullLevel(), level4.getNextLevel());
        assertEquals(new NullLevel(), level5.getNextLevel());
        assertNotEquals(level1, levelWithNext.getNextLevel());

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
        assertTrue(levelWithNext.isPlayableLevel());
    }

    /**
     * Test hasNextLevel method
     */
    @Test
    public void testHasNextLevel() {
        assertFalse(level1.hasNextLevel());
        assertFalse(level2.hasNextLevel());
        assertFalse(level3.hasNextLevel());
        assertFalse(level4.hasNextLevel());
        assertFalse(level5.hasNextLevel());
        assertTrue(levelWithNext.hasNextLevel());
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
        assertEquals(100, levelWithNext.getPoints());
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
        level1.setNextLevel(level3);
        levelWithNext.setNextLevel(level4);
        assertEquals(level3,level1.getNextLevel());
        assertEquals(level4,levelWithNext.getNextLevel());
    }

    /**
     * test setPoints method
     */
    @Test
    public void testSetPoints() {
        level1.setPoints(767);
        level2.setPoints(123);
        level3.setPoints(456);
        level4.setPoints(789);
        assertEquals(767, level1.getPoints());
        assertEquals(123, level2.getPoints());
        assertEquals(456, level3.getPoints());
        assertEquals(789, level4.getPoints());
    }

    /**
     * Test update method
     */
    @Override
    public void testUpdate() {

    }

    /**
     * test getCurrentPoints method
     */
    @Test
    public void testGetCurrentPoints() {
        assertEquals(0, level1.getCurrentPoints());
        assertEquals(0, level2.getCurrentPoints());
        assertEquals(0, level3.getCurrentPoints());
        assertEquals(0, level4.getCurrentPoints());
        assertEquals(0, level5.getCurrentPoints());
        assertEquals(0, levelWithNext.getCurrentPoints());
    }

    /**
     * test setCurrentPoints method
     */
    @Test
    public void testSetCurrentPoints() {
        level1.setCurrentPoints(123);
        level2.setCurrentPoints(456);
        levelWithNext.setCurrentPoints(789);
        assertEquals(123,level1.getCurrentPoints());
        assertEquals(456,level2.getCurrentPoints());
        assertEquals(789,levelWithNext.getCurrentPoints());
    }
}
