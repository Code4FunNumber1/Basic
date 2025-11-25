import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;

public class OurAction extends UserAction {

    private final Entity entity;
    private final double speed;

    public OurAction(String name,
                     Entity entity,
                     double speed) {
        super(name);
        this.entity = entity;
        this.speed = speed;
    }

    @Override
    protected void onAction() {
        entity.translateY(speed);
    }
}
