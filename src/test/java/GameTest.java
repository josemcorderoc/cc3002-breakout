package test.java;

import controller.Game;
import logic.brick.Brick;
import logic.level.Level;
import logic.level.NullLevel;
import logic.level.RealLevel;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {

    private Game game1;
    private Game game2;
    private Game game3;

    /**
     * Initialize games
     */
    @Before
    public void setUp() {
        game1 = new Game(0);
        game2 = new Game(1);
        game3 = new Game(7);
    }

    /**
     * Test getBalls method
     */
    @Test
    public void testGetBalls() {
        assertEquals(0, game1.getBalls());
        assertEquals(1, game2.getBalls());
        assertEquals(7, game3.getBalls());
    }

    /**
     * Test setBalls method
     */
    @Test
    public void testSetBalls() {
        game1.setBalls(9);
        game2.setBalls(6);
        game3.setBalls(99);
        assertEquals(9, game1.getBalls());
        assertEquals(6, game2.getBalls());
        assertEquals(99, game3.getBalls());
    }

    /**
     * Test getCurrentScore method
     */
    @Test
    public void testGetCurrentScore() {
        assertEquals(0, game1.getCurrentScore());
        assertEquals(0, game2.getCurrentScore());
        assertEquals(0, game3.getCurrentScore());
    }

    /**
     * Test setCurrentScore method
     */
    @Test
    public void testSetCurrentScore() {
        game1.setCurrentScore(500);
        game2.setCurrentScore(302);
        game3.setCurrentScore(123);
        assertEquals(500, game1.getCurrentScore());
        assertEquals(302, game2.getCurrentScore());
        assertEquals(123, game3.getCurrentScore());
    }

    /**
     * Test getCurrentLevel method
     */
    @Test
    public void testGetCurrentLevel() {
        assertEquals(game1.getCurrentLevel(),new NullLevel(false));
        assertEquals(game2.getCurrentLevel(),new NullLevel(false));
        assertEquals(game3.getCurrentLevel(),new NullLevel(false));
    }

    /**
     * Test setCurrentLevel method
     */
    @Test
    public void testSetCurrentLevel() {
        RealLevel level1 = new RealLevel("level 1", 1, 1, 0, 100);
        RealLevel level2 = new RealLevel("level 2", 1, 1, 0, 100);
        RealLevel level3 = new RealLevel("level 3", 1, 1, 0, 100);
        game1.setCurrentLevel(level1);
        game2.setCurrentLevel(level2);
        game3.setCurrentLevel(level3);

        assertEquals(new RealLevel("level 1", 1, 1, 0, 100),
                game1.getCurrentLevel());
        assertEquals(new RealLevel("level 2", 1, 1, 0, 100),
                game2.getCurrentLevel());
        assertEquals(new RealLevel("level 3", 1, 1, 0, 100),
                game3.getCurrentLevel());
    }

    /**
     * Test goNextLevel method
     */
    @Test
    public void testGoNextLevel() {
        RealLevel level1 = new RealLevel("level 1", 1, 1, 0, 100);
        RealLevel level2 = new RealLevel("level 2", 1, 1, 0, 100);
        RealLevel level3 = new RealLevel("level 3", 1, 1, 0, 100);
        level2.setNextLevel(level3);
        level1.setNextLevel(level2);
        game1.setCurrentLevel(level1);

        assertEquals(level1, game1.getCurrentLevel());
        game1.goNextLevel();
        assertEquals(level2, game1.getCurrentLevel());
        game1.goNextLevel();
        assertEquals(level3, game1.getCurrentLevel());
    }

    /**
     * Test isOver method
     */
    @Test
    public void testIsOver() {
        assertTrue(game1.isOver());
        assertFalse(game2.isOver());
        assertFalse(game3.isOver());
    }

    /**
     * Test winner method
     */
    @Test
    public void testWinner() {
        assertFalse(game1.winner());
        assertFalse(game2.winner());
        assertFalse(game3.winner());
        //TO-DO: incluir mas testing
    }

    /**
     * Test dropBall method
     */
    @Test
    public void testDropBall() {
        game1.dropBall();
        game2.dropBall();
        game3.dropBall();
        assertEquals(0,game1.getBalls());
        assertEquals(0,game2.getBalls());
        assertEquals(6,game3.getBalls());
    }

    /**
     * Test update method
     */
    @Test
    public void testUpdate() {
        Level level1 = game1.newLevelWithBricksFull("level 1", 4, 1, 0, 100);
        Level level2 = game2.newLevelWithBricksFull("level 2", 2, 1, 0, 100);
        Level level3 = game3.newLevelWithBricksFull("level 3", 2, 1, 1, 100);
        game1.setCurrentLevel(level1);
        game2.setCurrentLevel(level2);
        game3.setCurrentLevel(level3);

        for (Brick brick : level1.getBricks()) {
            brick.hit();
        }
        for (Brick brick : level2.getBricks()) {
            brick.hit();
        }
        for (Brick brick : level3.getBricks()) {
            for(int i = 0; i < 10; i++) {
                brick.hit();
            }
        }
        assertEquals(200, game1.getCurrentScore());
        assertEquals(100, game2.getCurrentScore());
        assertEquals(100, game3.getCurrentScore());

        assertEquals(0, game1.getBalls());
        assertEquals(1, game2.getBalls());
        assertEquals(9, game3.getBalls());
    }

    /**
     * Test newLevelWithBricksFull method
     */
    @Test
    public void testNewLevelWithBricksFull() {
        Level level1 = game1.newLevelWithBricksFull("level 1", 1, 1, 0.5, 100);
        Level level2 = game2.newLevelWithBricksFull("level 2", 1, 1, 0.6, 100);
        Level level3 = game3.newLevelWithBricksFull("level 3", 1, 1, 0.7, 100);
        assertEquals(new RealLevel("level 1", 1, 1, 0.5, 100), level1);
        assertEquals(new RealLevel("level 2", 1, 1, 0.6, 100), level2);
        assertEquals(new RealLevel("level 3", 1, 1, 0.7, 100), level3);

    }

    /**
     * Test newLevelWithBricksNoMetal method
     */
    @Test
    public void testNewLevelWithBricksNoMetal() {
        Level level1 = game1.newLevelWithBricksNoMetal("level 1", 1, 1, 100);
        Level level2 = game2.newLevelWithBricksNoMetal("level 2", 1, 1, 100);
        Level level3 = game3.newLevelWithBricksNoMetal("level 3", 1, 1, 100);
        assertEquals(new RealLevel("level 1", 1, 1, 0, 100), level1);
        assertEquals(new RealLevel("level 2", 1, 1, 0, 100), level2);
        assertEquals(new RealLevel("level 3", 1, 1, 0, 100), level3);
    }
}