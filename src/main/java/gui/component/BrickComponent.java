package gui.component;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.components.PositionComponent;
import logic.brick.Brick;

public class BrickComponent extends Component {

    private PositionComponent position;
    private Brick brick;

    public BrickComponent(double x, double y, Brick brick) {
        position = new PositionComponent();
        position.setX(x);
        position.setY(y);

        this.brick = brick;
    }

    public Brick getBrick() {
        return brick;
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
