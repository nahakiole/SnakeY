import java.awt.*;
import java.awt.event.KeyEvent;

public class Snake extends FieldObject {
    private Point position = new Point(-40,80);
    public int direction = KeyEvent.VK_RIGHT;

    @Override
    public void draw(Graphics g) {

        if (position.x > 500){
            position.x = 0;
        }
        switch (direction){
            case KeyEvent.VK_RIGHT:
                position.x++; //Right
                break;
            case KeyEvent.VK_UP:
                position.y--; //Down
                break;
            case KeyEvent.VK_LEFT:
                position.x--; //Left
                break;
            case KeyEvent.VK_DOWN:
                position.y++; //Up
                break;
        }
        g.fillRoundRect(position.x,position.y,40,40,20,20);
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
