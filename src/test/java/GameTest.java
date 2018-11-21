package test.java;

import controller.Game;
import logic.level.NullLevel;
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

        assertEquals(game1.getCurrentLevel(), new NullLevel(false));
        assertEquals(game2.getCurrentLevel(),new NullLevel(false));
        assertEquals(game3.getCurrentLevel(),new NullLevel(false));
    }

    /**
     * Test goNextLevel method
     */
    @Test
    public void testGoNextLevel() {
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
    }

    /**
     * Test newLevelWithBricksFull method
     */
    @Test
    public void testNewLevelWithBricksFull() {
    }

    /**
     * Test newLevelWithBricksNoMetal method
     */
    @Test
    public void testNewLevelWithBricksNoMetal() {
    }
}