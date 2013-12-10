import java.awt.*;
import java.awt.geom.Area;

abstract public class FieldObject {

public Point Position = new Point();

abstract public void draw(Graphics g);
abstract public Area getArea();

}
