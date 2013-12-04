import java.awt.*;
import java.util.Vector;

public class Snake extends FieldObject {
    public int direction = RIGHT;
    public Vector<Point> Track = new Vector<Point>();
    public Color c = new Color(0,80,0);
    public int speed = 20;

    public static final int UP = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 3;
    public static final int RIGHT = 4;

    public Snake(Point p){
        addDefaultPoints(p);
        Position = p;
    }

    public Snake(Point p, Color cn){
        addDefaultPoints(p);
        Position = p;
        c = cn;
    }

    public Snake(Point p, Color cn, int mspeed){
        addDefaultPoints(p);
        Position = p;
        c = cn;
        speed = mspeed;
    }



    public void addDefaultPoints(Point p){
        Track.add(new Point(p.x,p.y));
        Track.add(new Point(p.x,p.y));
        Track.add(new Point(p.x,p.y));
        Track.add(new Point(p.x,p.y));
        Track.add(new Point(p.x,p.y));
        Track.add(new Point(p.x,p.y));
        Track.add(new Point(p.x,p.y));
    }

    @Override
    public void draw(Graphics g) {

        if (Position.x > Game.FIELDWIDTH){
            Position.x = -30;
        }
        if (Position.y > Game.FIELDHEIGHT){
            Position.y = -30;
        }
        if (Position.x < -30){
            Position.x = Game.FIELDWIDTH;
        }
        if (Position.y < -30){
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
        for (Point p: Track){
            g.fillRoundRect(p.x,p.y,30,30,10,10);
        }
        g.fillRoundRect(Position.x-5,Position.y-5,40,40,35,35);
        g.setColor(new Color(255,255,255));
        switch(direction){
            case RIGHT:
                g.fillOval(Position.x+20, Position.y+20, 5,5);
                g.fillOval(Position.x+20, Position.y, 5,5);
                break;
            case UP:
                g.fillOval(Position.x, Position.y, 5,5);
                g.fillOval(Position.x+20, Position.y, 5,5);
                break;
            case LEFT:
                g.fillOval(Position.x, Position.y+20, 5,5);
                g.fillOval(Position.x, Position.y, 5,5);
                break;
            case DOWN:
                g.fillOval(Position.x, Position.y+20, 5,5);
                g.fillOval(Position.x+20, Position.y+20, 5,5);
                break;
        }
        Track.add(new Point(Position.x,Position.y));

//        g.fillRoundRect(Position.x,Position.y,40,40,20,20);
        g.setColor(old);
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
