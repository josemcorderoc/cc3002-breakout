package gui;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.settings.GameSettings;
import javafx.scene.input.KeyCode;
import facade.HomeworkTwoFacade;

import static gui.BreakoutFactory.*;

public class BreakoutApp extends GameApplication {

    protected void initSettings(GameSettings gameSettings){
        gameSettings.setWidth(800);
        gameSettings.setHeight(800);
        gameSettings.setTitle("Breakout");
        gameSettings.setVersion("1.0");
    }

    public static void main(String[] args){
        launch(args);
    }

    @Override
    protected void initGame(){
        Entity bg = newBackground();
        Entity player = newPlayer(300, 550);
        Entity ball = newBall(500,500);
        Entity walls = newWalls();
        getGameWorld().addEntities(bg, player, ball, walls);

        HomeworkTwoFacade game = new HomeworkTwoFacade();

    }

    @Override
    protected void initInput(){
        Input input = getInput();

        input.addAction(new UserAction("Move right") {
            @Override
            protected void onAction() {
                getGameWorld().getEntitiesByType(EntityType.PLAYER)
                        .forEach(entity -> entity.translateX(5));
            }
        }, KeyCode.D);

        input.addAction(new UserAction("Move left") {
            @Override
            protected void onAction() {
                getGameWorld().getEntitiesByType(EntityType.PLAYER)
                        .forEach(entity -> entity.translateX(-5));
            }
        }, KeyCode.A);

    }

    @Override
    protected void initPhysics() {
        getPhysicsWorld().setGravity(0,0);

        // ball collides with bottom wall
        getPhysicsWorld().addCollisionHandler(
                new CollisionHandler(EntityType.BALL, EntityType.WALL) {
                    @Override
                    protected void onHitBoxTrigger(Entity ball, Entity wall,
                                                   HitBox boxBall, HitBox boxWall) {
                        if (boxWall.getName().equals("BOT")) {
                            ball.removeFromWorld();
                        }
                    }
                }

        );

        // ball collides with player
        getPhysicsWorld().addCollisionHandler(
                new CollisionHandler(EntityType.BALL, EntityType.PLAYER) {
                    @Override
                    protected void onHitBoxTrigger(Entity ball, Entity player,
                                                   HitBox boxBall, HitBox boxPlayer) {
                        //TO-DO: arreglar caso choque lateral
                        PhysicsComponent physics = ball.getComponent(PhysicsComponent.class);
                        physics.setVelocityY(-physics.getVelocityY());
                    }
                }
        );

    }

}
