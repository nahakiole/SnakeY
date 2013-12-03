import java.awt.*;

public class Snake extends FieldObject {
    private int i = 0;

    @Override
    public void draw(Graphics g) {
        i++;
        g.fillRoundRect(i,80,40,40,0,0);
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
