package gui.component;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.components.PositionComponent;
import javafx.scene.paint.Color;
import logic.brick.Brick;

public class BrickComponent extends Component {

    private PositionComponent position;
    private Brick brick;
    private Color color;
    private String hitSound;

    public BrickComponent(double x, double y, Brick brick, Color color) {
        position = new PositionComponent();
        position.setX(x);
        position.setY(y);

        this.brick = brick;
        this.color = color;

        hitSound = brick.isGlassBrick() ? "glass.wav" : brick.isWoodenBrick() ? "wooden.wav" :
                brick.isMetalBrick() ? "metal.wav" : "plastic.wav";
    }

    public Brick getBrick() {
        return brick;
    }

    public Color colorDowngrade() {
        color = color.desaturate();
        return color;
    }

    public void hit() {
        brick.hit();
    }

    public String getHitSound() {
        return hitSound;
    }

    public boolean isDestroyed() {
        return brick.isDestroyed();
    }

    @Override
    public void onUpdate(double tpf) {
    }

}
