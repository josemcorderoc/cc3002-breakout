package gui;

import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.entity.Entity;
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
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import facade.HomeworkTwoFacade;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import logic.brick.Brick;
import visitor.NotifyVisitor;

import java.util.*;

import static gui.BreakoutFactory.*;

/**
 * Contains the main app of the Breakout game
 *
 * @author Jose Miguel Cordero
 */
public class BreakoutApp extends GameApplication implements Observer {

    // Window config
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    private static final double WindowEdge =  WIDTH/10.0;
    private final int ColumnSize = 10;
    private final double LabelYpos = WindowEdge*0.5;
    private final double BallEdgeX = 0.85*WIDTH;
    private final double BallEdgeY = 0.8*LabelYpos;
    private final double BrickWidth = (WIDTH - 2*WindowEdge)/10;
    private final double BrickHeight = HEIGHT/20.0;

    private Stack<Circle> ballsLeftImages = new Stack<>();

    // Game config
    private HomeworkTwoFacade game;

    private int NumberOfLevels = 0;
    private final int BricksInLevel = 20;
    private final double ProbOfGlass = 0.5;
    private final double ProbOfMetal = 0.5;
    private final double ProbOfPlastic = 0.1;
    private boolean gameOn = false;

    // Text labels
    private StringProperty levelNameLabel = new SimpleStringProperty("Level 0");
    private StringProperty numberOfLevelsLabel = new SimpleStringProperty("/ 0");
    private StringProperty scoreLabel = new SimpleStringProperty("Score:  000000");
    private StringProperty ballsLabel = new SimpleStringProperty("Balls:");


    private Text createLevels;
    private Text launchBall;

    // Player and ball speed
    private final double PlayerSpeed = 15;
    private final double BallSpeed = 10;

    /**
     * Run the game
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setWidth(WIDTH);
        gameSettings.setHeight(HEIGHT);
        gameSettings.setTitle("Breakout");
        gameSettings.setVersion("1.0");
    }

    private void drawDropBall() {
        getGameScene().removeUINode(ballsLeftImages.pop());
    }

    private void drawNewBall() {
        double x, y;

        if (ballsLeftImages.isEmpty()) {
            x = BallEdgeX;
            y = BallEdgeY;
        }
        else {
            x = ballsLeftImages.peek().getCenterX() + 25;
            y = ballsLeftImages.peek().getCenterY();
            if (Math.abs(x - WIDTH) < 10) {
                x = BallEdgeX;
                y = ballsLeftImages.peek().getCenterY() + 25;
            }
        }
        Circle newBall = new Circle(x, y, 7, Color.WHITE);
        getGameScene().addUINodes(newBall);
        ballsLeftImages.push(newBall);
    }

    private void initBallsLeft() {
        for (int i = 0; i < game.getBallsLeft(); i++) {
            drawNewBall();
        }
    }

    @Override
    protected void initGame() {
        game = new HomeworkTwoFacade();
        game.game.addObserver(this);
        getGameWorld().addEntities(newBackground(), newWalls());
    }

    @Override
    protected void initUI() {

        Font font = FXGL.getAssetLoader().loadFont("robotcrush.ttf").newFont(HEIGHT/20.0);
        Color color = Color.WHITE;
        double y_pos = LabelYpos;

        // levels
        Text levels = initText(WIDTH*0.05,y_pos, levelNameLabel, color,font);
        Text numberOfLevels = initText(WIDTH*0.2,y_pos,numberOfLevelsLabel,color,font);
        Text score = initText(WIDTH*0.35,y_pos,scoreLabel,color,font);
        Text balls = initText(WIDTH*0.7,y_pos, ballsLabel,color,font);

        Font font2 = FXGL.getAssetLoader().loadFont("robotcrush.ttf").newFont(HEIGHT/15.0);


        createLevels = initText(0.2*WIDTH,0.7*HEIGHT, new SimpleStringProperty("Press key N to create levels!"),
                color,font2);
        launchBall = initText(0.3*WIDTH,0.7*HEIGHT, new SimpleStringProperty("Press SPACE to start!"),
                color,font2);


        getGameScene().addUINodes(levels, score, numberOfLevels, balls, createLevels);

        initBallsLeft();
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
        Entity player = newPlayer(0.5*WIDTH - 50, 0.9*HEIGHT, 100, PlayerSpeed);
        Entity ball = newBall(0.5*WIDTH,0.9*HEIGHT - 20, BallSpeed);
        getGameWorld().addEntities(player, ball);
    }

    private void initLevelBricks() {
        int row = 0;
        int column = 0;

        List<Brick> currentLevelBricks = game.getBricks();
        Collections.shuffle(currentLevelBricks);

        for (Brick brick : currentLevelBricks) {
            if (column == ColumnSize) {
                column = 0;
                row++;
            }
            getGameWorld().addEntity(newBrick(column*BrickWidth + WindowEdge, row*BrickHeight + WindowEdge,
                    BrickWidth, BrickHeight, brick));
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
                                    .reposition(new Point2D(entity.getX() + PlayerSpeed, entity.getY())));
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
                                    .reposition(new Point2D(entity.getX() - PlayerSpeed, entity.getY())));
                }
            }
        }, KeyCode.A);

        input.addAction(new UserAction("Launch ball") {
            @Override
            protected void onActionBegin() {
                if (!gameOn) {
                    getGameScene().removeUINode(launchBall);
                    getGameWorld().getEntitiesByType(EntityType.BALL)
                            .forEach(entity -> entity.getComponent(PhysicsComponent.class)
                                    .setLinearVelocity(60*BallSpeed,-60*BallSpeed));
                }
                gameOn = true;
            }
        }, KeyCode.SPACE);

        input.addAction(new UserAction("New level") {
            @Override
            protected void onActionBegin() {
                if (NumberOfLevels == 0) {
                    game.setCurrentLevel(
                            game.newLevelWithAllBricks(
                                    "Level " + Integer.toString(NumberOfLevels+1),
                                    BricksInLevel,
                                    ProbOfGlass,
                                    ProbOfMetal,
                                    ProbOfPlastic,
                                    System.currentTimeMillis()
                            )
                    );
                    initLevel();
                    getGameScene().removeUINode(createLevels);
                    getGameScene().addUINode(launchBall);
                }
                else {
                    game.addPlayingLevel(
                            game.newLevelWithAllBricks(
                                    "Level " + Integer.toString(NumberOfLevels+1),
                                    BricksInLevel,
                                    ProbOfGlass,
                                    ProbOfMetal,
                                    ProbOfPlastic,
                                    System.currentTimeMillis()
                            )
                    );
                }
                NumberOfLevels++;
                numberOfLevelsLabel.setValue("/  " + Integer.toString(NumberOfLevels));

            }
        }, KeyCode.N);
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
                            drawDropBall();

                            initPlayerAndBall();
                            if (game.isGameOver()) {
                                gameOver(false);
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
                        getAudioPlayer().playSound(brick.getComponent(BrickComponent.class).getHitSound());
                        brick.setView(
                                new Rectangle(
                                        BrickWidth,
                                        BrickHeight,
                                        brick.getComponent(BrickComponent.class).colorDowngrade()
                                )
                        );
                        if (brick.getComponent(BrickComponent.class).isDestroyed()){
                            getAudioPlayer().playSound("destroy.wav");
                            brick.removeFromWorld();
                        }
                    }
                }
        );
    }

    private void gameOver(boolean winned) {
        String message;
        if (winned) {
            message = "Congratulations, you winned!\n" +
                    "          :)          ";
        }
        else {
            message = "I'm sorry, you lost!\n" +
                    "          :(          ";
        }
        getDisplay().showMessageBox(message, this::exit);
    }


    @Override
    public void update(Observable observable, Object o) {
        NotifyVisitor visitor = (NotifyVisitor) o;
        scoreLabel.setValue("Score:  " + String.format("%06d",visitor.getScore()));
        for (int i = 0; i < visitor.getExtraBalls(); i++) {
            drawNewBall();
        }
        if (visitor.getWinner()) {
            gameOver(true);
        }
        if (visitor.getChangeLevel()) {
            initLevel();
            getGameScene().addUINode(launchBall);
        }
    }
}
