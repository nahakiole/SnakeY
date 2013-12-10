import javafx.geometry.Pos;

import java.awt.*;
import java.awt.geom.Area;

public class Diamond extends FieldObject {
    @Override
    public void draw(Graphics g) {
        g.fill3DRect(Position.x,Position.y,40,40,true);
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public Diamond(Point p){
        Position = p;
    }

    @Override
    public Area getArea() {
        return new Area(new Rectangle(Position.x,Position.y,40,40));
    }
}
