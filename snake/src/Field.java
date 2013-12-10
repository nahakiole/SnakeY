import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Vector;

import static java.awt.event.KeyEvent.*;

public class Field extends JFrame implements ActionListener {

    public BufferedImage Buffer = new BufferedImage(Game.FIELDWIDTH, Game.FIELDHEIGHT, BufferedImage.TYPE_INT_ARGB);
    public Vector<FieldObject> FieldObjects = new Vector<FieldObject>();
    public Vector<Diamond> Diamonds = new Vector<Diamond>();
    public Snake Snake = new Snake(new Point(0, 320), new Color(50, 0, 0));
    //    public Snake Snake2 = new Snake(new Point(200, 100));
    public Timer Timer = new Timer(16, this);
    public int Points = 0;

    public Field() {
        setSize(Game.FIELDWIDTH, Game.FIELDHEIGHT);
        setLocationRelativeTo(getRootPane());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Snake 1.0");
        setResizable(false);
        FieldObjects.add(Snake);
//        FieldObjects.add(Snake2);
        Diamond diamond1 = new Diamond(new Point(40, 60));
        Diamond diamond2 = new Diamond(new Point(100, 200));
        FieldObjects.add(diamond1);
        Diamonds.add(diamond1);
        FieldObjects.add(diamond2);
        Diamonds.add(diamond2);
        setVisible(true);
        addKeyListener(new MyKeylistener());
        repaint();
        Timer.start();
    }

    public void paint(Graphics g) {
        Graphics2D g2 = Buffer.createGraphics();
        g2.setBackground(new Color(255, 255, 255));
        g2.clearRect(0, 0, Game.FIELDWIDTH, Game.FIELDHEIGHT);
        g2.setColor(new Color(15, 75, 100));

        if (Snake.Position.x > Game.FIELDWIDTH-40
         || Snake.Position.y > Game.FIELDHEIGHT-40
         || Snake.Position.x < 0
         || Snake.Position.y < 0) {
            finish(g);
        }

        for (FieldObject o : FieldObjects) {
            o.draw(g2);
        }

        for (int i=0;i<Diamonds.size();i++) {
            Diamond d = Diamonds.get(i);
            if (collide(Snake.getArea(), d.getArea())) {
                Points += 10;
                Snake.addDefaultPoints(20);
                Diamonds.remove(d);
                FieldObjects.remove(d);
            }
        }

        g2.drawString("Punkte " + Points, 20, 50
        );

        g.drawImage(Buffer, 0, 0, null);
//        if (collide(Snake.getArea(), Snake2.getArea())) {
//            finish(g);
//        }
        g.dispose();
    }

    public boolean collide(Area area1, Area area2) {
        boolean collide = false;

        Area collide1 = new Area(area1);
        collide1.subtract(area2);
        if (!collide1.equals(area1)) {
            collide = true;
        }

        Area collide2 = new Area(area2);
        collide2.subtract(area1);
        if (!collide2.equals(area2)) {
            collide = true;
        }

        return collide;
    }

    public void finish(Graphics g) {
        Timer.stop();
//        g.clearRect(0, 0, Game.FIELDWIDTH, Game.FIELDHEIGHT);
//        g.setFont(new Font("Arial", 10, 50));
//        g.drawString("Spiel fertig", 150, 200);
//        g.dispose();
//        System.out.println("Fertig");
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    public class MyKeylistener extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
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
