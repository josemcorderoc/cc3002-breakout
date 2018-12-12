package test.java;

import logic.brick.Brick;
import logic.level.Level;
import org.junit.Test;

import facade.HomeworkTwoFacade;

public class TestTest {

    @Test
    public void test() {
        HomeworkTwoFacade a = new HomeworkTwoFacade();

        Level level = a.newLevelWithBricksNoMetal("bla", 1, 1, 10);
        for (Brick brick : level.getBricks()) {
            brick.hit();
        }

        System.out.println(level.winned());

    }

}
