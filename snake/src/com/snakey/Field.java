package com.snakey;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.Vector;

import static java.awt.event.KeyEvent.*;

/**
 * @author Robin Glauser
 * @version 1.0
 */
public class Field extends JFrame {

    public BufferedImage Buffer = new BufferedImage(Game.FIELDWIDTH, Game.FIELDHEIGHT, BufferedImage.TYPE_INT_ARGB);
    public Vector<FieldObject> FieldObjects = new Vector<FieldObject>();
    public Vector<Diamond> Diamonds = new Vector<Diamond>();
    public Snake Snake1;
    public Render Render;
    //    public Snake1 Snake2 = new Snake1(new Point(200, 100));
    public int Points = 0;
    public boolean Pause = false;

    public Field() {
        setSize(Game.FIELDWIDTH, Game.FIELDHEIGHT);
        setLocationRelativeTo(getRootPane());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Snake 4.0");
        setResizable(false);
        Render = new Render(this);
        Snake1 = new Snake( new Point(100, 320), new Color(50, 0, 0));
        FieldObjects.add(Snake1);
        addDiamond();
        addDiamond();
        addDiamond();
        setVisible(true);
        addKeyListener(new MyKeylistener());
        Render.start();

    }

    public void addDiamond() {
        Point p = new Point((int) (Math.random() * (Game.FIELDWIDTH - 40) + 10), (int) (Math.random() * (Game.FIELDHEIGHT - 30) + 10));
        int points = (int) (Math.random() * 20 + 30);
        Diamond diamond = new Diamond( p, points);
        FieldObjects.add(diamond);
        Diamonds.add(diamond);
    }

    public void paint(Graphics g) {
        Graphics2D g2 = Buffer.createGraphics();
        g2.setBackground(new Color(255, 255, 255));
        g2.clearRect(0, 0, Game.FIELDWIDTH, Game.FIELDHEIGHT);
        g2.setColor(new Color(15, 75, 100));

        if (Snake1.Position.x > Game.FIELDWIDTH - 40
                || Snake1.Position.y > Game.FIELDHEIGHT - 40
                || Snake1.Position.x < 10
                || Snake1.Position.y < 30) {
            finish(g, false);
        }

        for (FieldObject o : FieldObjects) {
            o.draw(g2);
        }

        for (int i = 0; i < Diamonds.size(); i++) {
            Diamond d = Diamonds.get(i);
            if (collide(Snake1.getArea(), d.getArea())) {
                Points += d.points;
                Snake1.addDefaultPoints(d.points);
                Diamonds.remove(d);
                FieldObjects.remove(d);
                if (Points < 1000) {
                    addDiamond();
                }
            }
        }

        Point head = new Point(Snake1.Position.x + 20, Snake1.Position.y + 20);
        Point direction = new Point(1, 30);
        g2.setColor(Color.GREEN);
        if (collide(Snake1.getArea(), new Area(new Rectangle(head.x, head.y, direction.x, direction.y)))) {
            finish(g2, false);
        }
        g2.setColor(Color.black);
        g2.setFont(new Font("Arial", 10, 18));
        g2.drawString("Punkte " + Points, 15, 50);
        if (Pause) {
            g2.setFont(new Font("Arial", 10, 30));
            g2.drawString("Pause ", 250, 200);
        }
        g.drawImage(Buffer, 0, 0, null);

        if (Diamonds.size() == 0) {
            g.clearRect(0, 0, Game.FIELDWIDTH, Game.FIELDHEIGHT);
            finish(g, true);
        }
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

    public void finish(Graphics g, Boolean won) {
        Snake1.die();

        setTitle("Snake 4.0: You Won");

        g.setFont(new Font("Arial", 10, 50));

        if (won) {
            g.setColor(Color.GREEN);
            g.drawString("You won the Game", 100, 200);
        } else {
            g.setColor(Color.RED);
            g.drawString("Game Over", 150, 200);
        }
        g.setFont(new Font("Arial", 10, 40));
        g.drawString(Points + " Punkte", 190, 250);
        g.dispose();
        Render.stop();
    }


    public class MyKeylistener extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case VK_P:
                    Pause = !Pause;
                    break;
                case VK_RIGHT:
                    if (Snake1.direction != Snake.LEFT) {
                        Snake1.direction = Snake1.RIGHT;
                    }
                    break;
                case VK_UP:
                    if (Snake1.direction != Snake.DOWN) {
                        Snake1.direction = Snake1.UP;
                    }
                    break;
                case VK_LEFT:
                    if (Snake1.direction != Snake.RIGHT) {
                        Snake1.direction = Snake1.LEFT;
                    }
                    break;
                case VK_DOWN:
                    if (Snake1.direction != Snake.UP) {
                        Snake1.direction = Snake1.DOWN;
                    }
                    break;
//                case 87:
//                    Snake2.direction = Snake1.UP;
//                    break;
//                case 83:
//                    Snake2.direction = Snake1.DOWN;
//                    break;
//                case 65:
//                    Snake2.direction = Snake1.LEFT;
//                    break;
//                case 68:
//                    Snake2.direction = Snake1.RIGHT;
//                    break;
            }
        }
    }
}
