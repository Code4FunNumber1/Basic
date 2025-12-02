import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.entity.Entity;
import com.sun.javafx.geom.Point2D;
import javafx.scene.input.KeyCode;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

import static com.almasb.fxgl.dsl.FXGLForKtKt.*;

public class BasicGameApp extends GameApplication {

//    Game Constants
    private static final int PADDLE_WIDTH = 30;
    private static final int PADDLE_HEIGHT = 100;
    private static final int BRICK_WIDTH = 50;
    private static final int BRICK_HEIGHT = 25;
    private static final int BALL_SIZE = 20;
    private static final int PADDLE_SPEED = 5;
    private static final int BALL_SPEED = 5;

//    Game Varibles
    private Entity paddle1;
    private Entity paddle2;
    private Entity ball;

//    Game Loop
    public static void main(String[] args) {
        launch(args);
    }

//    Sets Settings
    @Override
    protected void initSettings(GameSettings settings) {
        settings.setTitle("Basic Game");
    }

//    Creates Game Environment
    @Override
    protected void initGame() {
        getGameWorld().addEntityFactory(new BasicGameFactory());

//        Creates Player Paddles
        paddle1 = spawn("bat", 0, getAppHeight() / 2 - PADDLE_HEIGHT / 2);
        paddle2 = spawn("bat", getAppWidth() - PADDLE_WIDTH, getAppHeight() / 2 - PADDLE_HEIGHT / 2);

//        Creates Ball
        ball = spawn("ball", getAppWidth() / 2 - BALL_SIZE / 2, getAppHeight() / 2 - BALL_SIZE / 2);

//        ???
        for (int i = 0; i < 10; i++) {
            spawn("brick", getAppWidth() / 2 - 200 - BRICK_WIDTH, 100 + i*(BRICK_HEIGHT + 20));
            spawn("brick", getAppWidth() / 2 + 200, 100 + i*(BRICK_HEIGHT + 20));
        }
    }

//    Creates Player Control
    @Override
    protected void initInput() {
//        Up
        OurAction up1 = new OurAction("Up 1", paddle1, -PADDLE_SPEED);
        getInput().addAction(up1, KeyCode.W);
        OurAction up2 = new OurAction("Up 2", paddle1, -PADDLE_SPEED);
        getInput().addAction(up1, KeyCode.UP);

//        Down
        OurAction down1 = new OurAction("Down 1", paddle1, PADDLE_SPEED);
        getInput().addAction(down1, KeyCode.S);
        OurAction down2 = new OurAction("Down 2", paddle1, PADDLE_SPEED);
        getInput().addAction(down2, KeyCode.DOWN);
    }

    @Override
    protected  void initPhysics() {
        CollideAction action = new CollideAction();
        onCollisionBegin(BasicGameType.BALL, BasicGameType.BRICK, action);
    }

    @Override
    protected void onUpdate(double tpf) {
        Point2D velocity = ball.getObject("velocity");
        ball.translate(velocity);

        if (ball.getX() == paddle1.getRightX()
        && ball.getY() < paddle1.getBottomY()
        && ball.getBottomY() >= paddle1.getY()) {

        }

        if (ball.getRightX() == paddle2.getX()
        && ball.getY() < paddle2.getBottomY()
        && ball.getBottomY() > paddle2.getY()) {
            ball.setProperty("velocity", new Point2D(-velocity));
        }
    }

    private void resetBall() {
        ball.setPosition(getAppWidth() / 2 - BALL_SIZE / 2, getAppHeight() / 2 - BALL_SIZE / 2);
        ball.setProperty("velocity", new Point2D(BALL_SPEED, BALL_SPEED));
    }
}
