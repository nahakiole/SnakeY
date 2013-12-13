import java.awt.*;
import java.awt.geom.Area;
import java.util.Vector;

public class Snake extends FieldObject {
    public int direction = RIGHT;
    public Vector<Point> Track = new Vector<Point>();
    public Color c = new Color(0,80,0);
    public double speed = 4;
    public int padding = 2;
    public Color Eyes = new Color(71, 119, 255);

    public static final int UP = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 3;
    public static final int RIGHT = 4;

    public Snake(Point p){
        Position = p;
        addDefaultPoints(30);

    }

    public Snake(Point p, Color cn){
        Position = p;
        addDefaultPoints(30);

        c = cn;
    }

    public Snake(Point p, Color cn, int mspeed){
        Position = p;
        addDefaultPoints(30);
        c = cn;
        speed = mspeed;
    }



    public void addDefaultPoints(int total){
        for (Integer i = 0; i<total; i++){
            Track.add(new Point(Position.x,Position.y));
        }
    }



    @Override
    public void draw(Graphics g) {
        switch (direction){
            case RIGHT:
                Position.x = Position.x+padding; //Right
                break;
            case UP:
                Position.y = Position.y-padding; //UP
                break;
            case LEFT:
                Position.x = Position.x-padding; //Left
                break;
            case DOWN:
                Position.y = Position.y+padding; //Up
                break;
        }
        Color old = g.getColor();
        g.setColor(c);
        Track.remove(0);
        for (Point p: Track){
            g.fillRoundRect(p.x,p.y,30,30,10,10);
        }
        g.setColor(c.brighter());
        g.fillRoundRect(Position.x-5,Position.y-5,40,40,35,35);
        g.setColor(Color.WHITE);
        Point EyeL = new Point();
        Point EyeR = new Point();
        switch(direction){
            case RIGHT:
                EyeL.setLocation(Position.x+20, Position.y+20);
                EyeR.setLocation(Position.x+20, Position.y);
                break;
            case UP:
                EyeL.setLocation(Position.x, Position.y);
                EyeR.setLocation(Position.x+20, Position.y);
                break;
            case LEFT:
                EyeL.setLocation(Position.x, Position.y+20);
                EyeR.setLocation(Position.x, Position.y);
                break;
            case DOWN:
                EyeL.setLocation(Position.x, Position.y+20);
                EyeR.setLocation(Position.x+20, Position.y+20);
                break;
        }
        g.fillOval(EyeR.x,EyeR.y,7,7);
        g.fillOval(EyeL.x,EyeL.y,7,7);
        g.setColor(Eyes);
        g.fillOval(EyeR.x+2,EyeR.y+2,3,3);
        g.fillOval(EyeL.x+2,EyeL.y+2,3,3);
        Track.add(new Point(Position.x,Position.y));
        g.setColor(old);
    }

    @Override
    public Area getArea() {
        Area area = new Area();
        for (Point p: Track){
            Area tmp = new Area(new Rectangle(p.x,p.y,30,30));
            area.add(tmp);
        }
        return area;
    }
}
