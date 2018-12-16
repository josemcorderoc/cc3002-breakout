package gui.component;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.components.PositionComponent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import logic.brick.Brick;

public class BrickComponent extends Component {

    private PositionComponent position;
    private Brick brick;
    private Color color;

    public BrickComponent(double x, double y, Brick brick, Color color) {
        position = new PositionComponent();
        position.setX(x);
        position.setY(y);

        this.brick = brick;
        this.color = color;
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

    public boolean isDestroyed() {
        return brick.isDestroyed();
    }

    @Override
    public void onUpdate(double tpf) {
    }

}
