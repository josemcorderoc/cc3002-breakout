package gui.component;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.components.PositionComponent;
import gui.BreakoutApp;

/**
 * Component of logic of Player entity
 *
 * @author Jose Miguel Cordero
 */
public class PlayerComponent extends Component {

    private PositionComponent position;
    private double width;
    private double height;
    private double speed;

    public PlayerComponent(double x, double y, double width, double height, double speed) {
        position = new PositionComponent();
        position.setX(x);
        position.setY(y);
        this.width = width;
        this.height = height;
        this.speed = speed;
    }

    public void left() {
        if ( 0 < position.getX()) {
            position.translateX(-speed);
        }
    }

    public void right() {
        if (position.getX() < BreakoutApp.WIDTH - width) {
            position.translateX(speed);
        }
    }

    @Override
    public void onUpdate(double tpf) {
    }
}
