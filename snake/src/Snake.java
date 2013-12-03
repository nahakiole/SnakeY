import java.awt.*;
import java.util.Vector;

public class Snake extends FieldObject {
    public int direction = RIGHT;
    public Vector<Point> Track = new Vector<Point>();
    public Color c = new Color(0,80,0);
    public int speed = 40;

    public static final int UP = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 3;
    public static final int RIGHT = 4;

    public Snake(Point p){
        Track.add(new Point(p.x,p.y));
        Track.add(new Point(p.x,p.y));
        Track.add(new Point(p.x,p.y));
        Track.add(new Point(p.x,p.y));
        Position = p;
    }

    public Snake(Point p, Color cn){
        Track.add(new Point(p.x,p.y));
        Track.add(new Point(p.x,p.y));
        Track.add(new Point(p.x,p.y));
        Track.add(new Point(p.x,p.y));
        Position = p;
        c = cn;
    }

    public Snake(Point p, Color cn, int mspeed){
        Track.add(new Point(p.x,p.y));
        Track.add(new Point(p.x,p.y));
        Track.add(new Point(p.x,p.y));
        Track.add(new Point(p.x,p.y));
        Position = p;
        c = cn;
        speed = mspeed;
    }

    @Override
    public void draw(Graphics g) {

        if (Position.x > Game.FIELDWIDTH){
            Position.x = -40;
        }
        if (Position.y > Game.FIELDHEIGHT){
            Position.y = -40;
        }
        if (Position.x < -40){
            Position.x = Game.FIELDWIDTH;
        }
        if (Position.y < -40){
            Position.y = Game.FIELDHEIGHT;
        }
        switch (direction){
            case RIGHT:
                Position.x = Position.x+speed; //Right
                break;
            case UP:
                Position.y = Position.y-speed; //UP
                break;
            case LEFT:
                Position.x = Position.x-speed; //Left
                break;
            case DOWN:
                Position.y = Position.y+speed; //Up
                break;
        }
        Color old = g.getColor();
        g.setColor(c);
        Track.remove(0);
        Track.add(new Point(Position.x,Position.y));
        for (Point p: Track){
            g.fillRoundRect(p.x,p.y,40,40,20,20);
        }

//        g.fillRoundRect(Position.x,Position.y,40,40,20,20);
        g.setColor(old);
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
