import java.awt.*;
import java.awt.event.KeyEvent;

public class Snake extends FieldObject {
    public int direction = KeyEvent.VK_RIGHT;


    public Snake(){
        Position.setLocation(80,80);
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
            case KeyEvent.VK_RIGHT:
                Position.x++; //Right
                break;
            case KeyEvent.VK_UP:
                Position.y--; //Down
                break;
            case KeyEvent.VK_LEFT:
                Position.x--; //Left
                break;
            case KeyEvent.VK_DOWN:
                Position.y++; //Up
                break;
        }
        g.fillRoundRect(Position.x,Position.y,40,40,20,20);
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
