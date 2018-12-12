package gui;

import com.almasb.fxgl.entity.*;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.physics.box2d.dynamics.FixtureDef;
import gui.component.BrickComponent;
import gui.component.PlayerComponent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import logic.brick.Brick;

public final class BreakoutFactory implements EntityFactory {

    public static Entity newPlayer(double x, double y, double width) {
        return Entities.builder()
                .at(x, y)
                .type(EntityType.PLAYER)
                .bbox(new HitBox("Player", BoundingShape.box(100,30)))
                .viewFromNode(new Rectangle(width, 30, Color.BLUE))
                .with(new PlayerComponent(x,y, width, 30, 10), new CollidableComponent(true))
                .build();
    }

    public static Entity newBackground() {
        return Entities.builder()
                .viewFromNode(new Rectangle(800,800,Color.BLACK))
                .renderLayer(RenderLayer.BACKGROUND)
                .build();
    }

    public static Entity newBall(double x, double y, double speed) {

        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);
        physics.setFixtureDef(
                new FixtureDef().restitution(1f).density(0.1f).friction(0f)
        );

        return Entities.builder()
                .at(x, y)
                .type(EntityType.BALL)
                .bbox(new HitBox("Ball", BoundingShape.circle(10)))
                .viewFromNode(new Circle(10,Color.WHITE))
                .with(physics, new CollidableComponent(true))
                .build();
    }

    public static Entity newWalls() {
        Entity walls = Entities.makeScreenBounds(100);
        walls.setType(EntityType.WALL);
        walls.addComponent(new CollidableComponent(true));
        return walls;
    }

    public static Entity newBrick(double x, double y, double width, double height, Brick brick) {

        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.STATIC);
        physics.setFixtureDef(
                new FixtureDef().restitution(1f).density(0.1f)
        );

        Color color = brick.isGlassBrick() ? Color.GREEN : brick.isWoodenBrick() ? Color.BROWN : Color.STEELBLUE;

        return Entities.builder()
                .at(x, y)
                .type(EntityType.BRICK)
                .bbox(new HitBox("Brick", BoundingShape.box(width,height)))
                .viewFromNode(new Rectangle(width, height, color))
                .with(physics, new BrickComponent(x, y, brick), new CollidableComponent(true))
                .build();

    }

}
