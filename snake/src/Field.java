import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Vector;

import static java.awt.event.KeyEvent.*;

public class Field extends JFrame implements ActionListener {

    public BufferedImage Buffer = new BufferedImage(Game.FIELDWIDTH,Game.FIELDHEIGHT,BufferedImage.TYPE_INT_ARGB);
    public Vector<FieldObject> FieldObjects = new Vector<FieldObject>();
    public Snake Snake = new Snake(new Point(0, 320), new Color(50,0,0));
//    public Snake Snake2 = new Snake(new Point(200, 100));
    public Timer timer = new Timer(400,this);

    public Field(){
        super.setSize(Game.FIELDWIDTH,Game.FIELDHEIGHT);
        this.setLocationRelativeTo(getRootPane());
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        FieldObjects.add(Snake);
//        FieldObjects.add(Snake2);
        FieldObjects.add(new Diamond());
        super.setVisible(true);
        addKeyListener(new MyKeylistener());
        repaint();
        timer.start();
    }



    public void paint(Graphics g){
        Graphics2D g2 = Buffer.createGraphics();
        g2.setBackground(new Color(255,255,255));
        g2.clearRect(0,0,Game.FIELDWIDTH,Game.FIELDHEIGHT);
        g2.setColor(new Color(15, 75, 100));
        for (FieldObject o : FieldObjects) {
            o.draw(g2);
        }
        g.drawImage(Buffer, 0, 0, null);
//        if (collide(Snake, Snake2)){
//            finish(g);
//        }
        g.dispose();
    }

    public boolean collide(FieldObject a, FieldObject b){
        return a.Position.x >= b.Position.x &&  a.Position.x < b.Position.x+40 && a.Position.y >= b.Position.y && a.Position.y < b.Position.y+40;
    }

    public void finish(Graphics g){
        timer.stop();
        g.clearRect(0, 0, Game.FIELDWIDTH, Game.FIELDHEIGHT);
        g.setFont(new Font("Arial", 10, 50));
        g.drawString("Spiel fertig", 150, 200);
        g.dispose();
        System.out.println("Fertig");
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    public class MyKeylistener extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()){
                case VK_RIGHT:
                    Snake.direction = Snake.RIGHT;
                    break;
                case VK_UP:
                    Snake.direction = Snake.UP;
                    break;
                case VK_LEFT:
                    Snake.direction = Snake.LEFT;
                    break;
                case VK_DOWN:
                    Snake.direction = Snake.DOWN;
                    break;
//                case 87:
//                    Snake2.direction = Snake.UP;
//                    break;
//                case 83:
//                    Snake2.direction = Snake.DOWN;
//                    break;
//                case 65:
//                    Snake2.direction = Snake.LEFT;
//                    break;
//                case 68:
//                    Snake2.direction = Snake.RIGHT;
//                    break;
            }
        }
    }
}
