package logic.brick;

import visitor.NotifyVisitor;
import visitor.Visitable;

import java.util.Observable;

/**
 * Models behavior and properties of {@link Brick}s
 *
 * @author Jose Miguel Cordero
 */
public abstract class AbstractBrick extends Observable implements Brick, Visitable {

    private int totalHP;
    private int currentHP;
    private int score;
    private int i,j;

    AbstractBrick(int hitPoints, int score, int i, int j) {
        this.totalHP = hitPoints;
        this.currentHP = hitPoints;
        this.score = score;
        this.i = i;
        this.j = j;
    }

    /**
     * row (i) getter
     * @return i
     */
    public int getI() {
        return i;
    }

    /**
     * column (j) getter
     * @return j
     */
    public int getJ() {
        return j;
    }

    /**
     * Position (i,j) setter
     * @param i row
     * @param j column
     */
    public void setPosition(int i, int j) {
        this.i = i;
        this.j = j;
    }

    /**
     * totalHP getter
     * @return totalHP
     */
    public int getTotalHP() {
        return totalHP;
    }

    /**
     * totalHP setter
     * @param newTotalHP totalHP to set
     */
    public void setTotalHP(int newTotalHP) {
        totalHP = newTotalHP;
    }

    /**
     * currentHP getter
     * @return currentHP
     */
    public int getCurrentHP() {
        return currentHP;
    }

    /**
     * currentHP setter
     * @param newCurrentHP currentHP to set
     */
    public void setCurrentHP(int newCurrentHP) {
        currentHP = newCurrentHP;
    }

    /**
     * score getter
     * {@inheritDoc}
     */
    @Override
    public int getScore() {
        return score;
    }

    /**
     * score setter
     * @param newScore score to set
     */
    public void setScore(int newScore) {
        score = newScore;
    }

    /**
     * Check if 2 bricks are the same
     * @param o brick to compare
     * @return true if equals, false else
     */
    @Override
    public boolean equals(Object o) {
        if (this.getClass() == o.getClass()) {
            return this.currentHP == ((AbstractBrick)o).currentHP &&
                    this.totalHP == ((AbstractBrick)o).totalHP &&
                    this.score == ((AbstractBrick)o).score &&
                    this.i == ((AbstractBrick)o).i && this.j == ((AbstractBrick)o).j;
        }
        return false;
    }

    /**
     * Defines that a brick has been hit.
     * Implementations should consider the events that a hit to a brick can trigger.
     */
    @Override
    public void hit() {
        if (getCurrentHP() > 0) {
            setCurrentHP(getCurrentHP()-1);
            if (isDestroyed()) {
                setChanged();
                accept(new NotifyVisitor());
            }
        }
    }

    /**
     * Gets whether the brick object is destroyed or not.
     *
     * @return true if the brick is destroyed, false otherwise
     */
    @Override
    public boolean isDestroyed() {
        return getCurrentHP() <= 0;
    }

    /**
     * Gets the remaining hits the brick has to receive before being destroyed.
     *
     * @return the remaining hits to destroy de brick
     */
    @Override
    public int remainingHits() {
        return getCurrentHP();
    }

}
