import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Expo5 on 4/16/2016.
 */
public abstract class RenderObj {

    public static ArrayList<RenderObj> allRenderObj = new ArrayList<RenderObj>();

    public RenderObj() {
        allRenderObj.add(this);
    }

    public abstract void render(Graphics g);
    public abstract void update();
}
