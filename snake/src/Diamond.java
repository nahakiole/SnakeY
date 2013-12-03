import java.awt.*;

public class Diamond extends FieldObject {
    @Override
    public void draw(Graphics g) {
        g.draw3DRect(200,200,40,40,true);
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
