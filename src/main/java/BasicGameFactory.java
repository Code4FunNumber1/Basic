import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


import static com.almasb.fxgl.dsl.FXGLForKtKt.entityBuilder;

public class BasicGameFactory implements EntityFactory {

    @Spawns("bat")
    public Entity newBat(SpawnData data) {
        Rectangle rectangle = new Rectangle(30, 100);
        return entityBuilder()
                .from(data)
                .viewWithBBox(rectangle)
                .build();
    }

    @Spawns("ball")
    public Entity newBall(SpawnData data) {
        Rectangle rectangle = new Rectangle(20, 20, Color.FIREBRICK);
        Point2D velocity = new Point2D(5, 5);
        return entityBuilder()
                .from(data)
                .type(BasicGameType.BALL)
                .viewWithBBox(rectangle)
                .collidable()
                .with("velocity", velocity)
                .build();
    }

    @Spawns("brick")
    public Entity newBrick(SpawnData data) {
        Rectangle rectangle = new Rectangle(50, 25, Color.CHARTREUSE);
        return entityBuilder()
                .from(data)
                .type(BasicGameType.BRICK)
                .viewWithBBox(rectangle)
                .collidable()
                .build();
    }

}
