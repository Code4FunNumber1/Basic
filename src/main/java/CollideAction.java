import com.almasb.fxgl.entity.Entity;
import com.sun.javafx.geom.Point2D;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

import java.util.Random;

public class CollideAction implements Function2<Entity, Entity, Unit> {

    Random random = new Random();

    @Override
    public Unit invoke(Entity ball, Entity brick) {
        brick.removeFromWorld();
        Point2D velocity = ball.getObject("velocity");

        if (random.nextBoolean()) {
            Point2D newVelocity = new Point2D(-velocity.x, velocity.y);
            ball.setProperty("velocity", newVelocity);
        } else  {
            Point2D newVelocity = new Point2D(velocity.x, -velocity.y);
            ball.setProperty("velocity", newVelocity);
        }

        return Unit.INSTANCE;
    }
}
