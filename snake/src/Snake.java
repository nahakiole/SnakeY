import java.awt.*;

public class Snake extends FieldObject {
    public int direction = RIGHT;
    public Color c = new Color(0,80,0);
    public int speed = 5;

    public static final int UP = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 3;
    public static final int RIGHT = 4;

    public Snake(Point p){
        Position = p;
    }

    public Snake(Point p, Color cn){
        Position = p;
        c = cn;
    }

    public Snake(Point p, Color cn, int mspeed){
        Position = p;
        c = cn;
        speed = mspeed;
    }

    @Override
    public void draw(Graphics g) {

        if (Position.x > 500){
            Position.x = -40;
        }
        if (Position.y > 400){
            Position.y = -40;
        }
        if (Position.x < -40){
            Position.x = 500;
        }
        if (Position.y < -40){
            Position.y = 400;
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
        g.fillRoundRect(Position.x,Position.y,40,40,20,20);
        g.setColor(old);
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
