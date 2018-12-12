package gui;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityEvent;
import com.almasb.fxgl.event.EventTrigger;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.settings.GameSettings;
import gui.component.BrickComponent;
import gui.component.PlayerComponent;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.StringPropertyBase;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import facade.HomeworkTwoFacade;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import logic.brick.Brick;

import java.util.Collections;
import java.util.List;

import static gui.BreakoutFactory.*;

public class BreakoutApp extends GameApplication {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final double WindowEdge =  WIDTH/10.0;

    private int NumberOfLevels = 0;
    private int CurrentLevel = 0;
    private final int ColumnSize = 10;

    // Level config
    private final int BricksInLevel = 5;
    private final double ProbOfGlass = 1;
    private final double ProfOfMetal = 0;



    private StringProperty levelNameLabel = new SimpleStringProperty("Level 0");
    private StringProperty numberOfLevelsLabel = new SimpleStringProperty("/ 0");
    private StringProperty scoreLabel = new SimpleStringProperty("900");
    private StringProperty totalScoreLabel = new SimpleStringProperty("1000");
    private StringProperty ballsLabel = new SimpleStringProperty("Balls: 3");

    // Player speed
    private final double PlayerSpeed = 5;
    private final double BallSpeed = 12;

    private boolean gameOn = false;

    private HomeworkTwoFacade game = new HomeworkTwoFacade();

    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setWidth(WIDTH);
        gameSettings.setHeight(HEIGHT);
        gameSettings.setTitle("Breakout");
        gameSettings.setVersion("1.0");
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    protected void initGame() {
        getGameWorld().addEntities(newBackground(), newWalls());

        /*
        getEventBus().addEventTrigger(new EventTrigger<EntityEvent>(
                () ->
        ));
        */
    }

    @Override
    protected void initUI() {

        Font font = new Font(HEIGHT/20.0);
        Color color = Color.RED;
        double y_pos = WindowEdge*0.5;

        // levels
        Text levels = initText(WIDTH*0.05,y_pos, levelNameLabel, color,font);
        Text numberOfLevels = initText(WIDTH*0.3,y_pos,numberOfLevelsLabel,color,font);
        Text score = initText(WIDTH*0.4,y_pos,scoreLabel,color,font);
        Text totalScore = initText(WIDTH*0.6,y_pos,totalScoreLabel,color,font);
        Text balls = initText(WIDTH*0.8,y_pos, ballsLabel,color,font);


        getGameScene().addUINodes(levels, score, totalScore,numberOfLevels,balls);
    }

    private Text initText(double x, double y, StringProperty label, Color color, Font font) {
        Text text = new Text();
        text.setX(x);
        text.setY(y);
        text.textProperty().bind(label);
        text.setFont(font);
        text.setFill(color);
        return text;
    }


    private void initEventTriggers() {

    }

    private void initLevel() {
        gameOn = false;
        getGameWorld().getEntitiesByType(EntityType.BALL, EntityType.PLAYER, EntityType.BRICK)
                .forEach(Entity::removeFromWorld);
        levelNameLabel.setValue(game.getCurrentLevel().getName());
        initLevelBricks();

        initPlayerAndBall();
    }

    private void initPlayerAndBall() {
        gameOn = false;
        getGameWorld().getEntitiesByType(EntityType.BALL, EntityType.PLAYER)
                .forEach(Entity::removeFromWorld);
        Entity player = newPlayer(0.5*WIDTH - 100/2, 0.9*HEIGHT, 100);
        Entity ball = newBall(0.5*WIDTH,0.9*HEIGHT - 20, BallSpeed);
        getGameWorld().addEntities(player, ball);
    }

    private void initLevelBricks() {
        int row = 0;
        int column = 0;

        List<Brick> currentLevelBricks = game.getBricks();
        Collections.shuffle(currentLevelBricks);

        for (Brick brick : currentLevelBricks) {
            //TO-DO: arreglar x,y
            if (column == ColumnSize) {
                column = 0;
                row++;
            }
            double width = (WIDTH - 2*WindowEdge)/10;
            double height = HEIGHT/20.0;
            getGameWorld().addEntity(newBrick(column*width + WindowEdge, row*height + WindowEdge,
                    width, height, brick));
            column++;
        }
    }

    @Override
    protected void initInput(){
        Input input = getInput();

        input.addAction(new UserAction("Move right") {
            @Override
            protected void onAction() {
                getGameWorld().getEntitiesByType(EntityType.PLAYER)
                        .forEach(entity -> entity.getComponent(PlayerComponent.class)
                                .right());
                if (!gameOn) {
                    getGameWorld().getEntitiesByType(EntityType.BALL)
                            .forEach(entity -> entity.getComponent(PhysicsComponent.class)
                                    .reposition(new Point2D(entity.getX() + 10, entity.getY())));
                }

            }
        }, KeyCode.D);

        input.addAction(new UserAction("Move left") {
            @Override
            protected void onAction() {
                getGameWorld().getEntitiesByType(EntityType.PLAYER)
                        .forEach(entity -> entity.getComponent(PlayerComponent.class).left());
                if (!gameOn) {
                    getGameWorld().getEntitiesByType(EntityType.BALL)
                            .forEach(entity -> entity.getComponent(PhysicsComponent.class)
                                    .reposition(new Point2D(entity.getX() - 10, entity.getY())));
                }
            }
        }, KeyCode.A);

        input.addAction(new UserAction("Launch ball") {
            @Override
            protected void onActionBegin() {
                if (!gameOn) {
                    getGameWorld().getEntitiesByType(EntityType.BALL)
                            .forEach(entity -> entity.getComponent(PhysicsComponent.class)
                                    .setLinearVelocity(300,-300));
                }
                gameOn = true;
            }
        }, KeyCode.SPACE);

        input.addAction(new UserAction("New level") {
            @Override
            protected void onActionBegin() {
                if (NumberOfLevels == 0) {
                    game.setCurrentLevel(
                            game.newLevelWithBricksNoMetal(
                                    "Level " + Integer.toString(NumberOfLevels+1),
                                    BricksInLevel,
                                    ProbOfGlass,
                                    System.currentTimeMillis()
                            )
                    );
                    initLevel();
                }
                else {
                    game.addPlayingLevel(
                            game.newLevelWithBricksNoMetal(
                                    "Level " + Integer.toString(NumberOfLevels+1),
                                    BricksInLevel,
                                    ProbOfGlass,
                                    System.currentTimeMillis()
                            )
                    );
                }
                NumberOfLevels++;
                numberOfLevelsLabel.setValue("/ " + Integer.toString(NumberOfLevels));

            }
        }, KeyCode.N);

        input.addAction(new UserAction("Testing") {
            @Override
            protected void onAction() {
                System.out.println(game.getCurrentLevel().getName());
            }
        }, KeyCode.W);
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
                            game.dropBall();
                            ballsLabel.setValue("Balls: " + Integer.toString(game.getBallsLeft()));
                            initPlayerAndBall();
                            if (game.isGameOver()) {
                                gameOver();
                            }
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

        // ball collides with brick
        getPhysicsWorld().addCollisionHandler(
                new CollisionHandler(EntityType.BALL, EntityType.BRICK) {
                    @Override
                    protected void onHitBoxTrigger(Entity ball, Entity brick,
                                                   HitBox boxBall, HitBox boxBrick) {
                        brick.getComponent(BrickComponent.class).hit();
                        if (brick.getComponent(BrickComponent.class).isDestroyed()){
                            brick.removeFromWorld();
                            if (!game.getCurrentLevel().getName().equals(levelNameLabel.getValue())) {
                                // new level
                                initLevel();
                            }
                        }
                    }
                }
        );
    }

    private void gameOver() {
        getDisplay().showMessageBox(
                "I'm sorry, you lost!\n" +
                        "          :(          ", this::exit);
    }

}
