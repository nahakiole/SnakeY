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

    public BufferedImage Buffer = new BufferedImage(500,400,BufferedImage.TYPE_INT_ARGB);
    public Vector<FieldObject> FieldObjects = new Vector<FieldObject>();
    public Snake Snake = new Snake();

    public Field(){
        Timer timer = new Timer(16,this);
        super.setSize(500,400);
        this.setLocationRelativeTo(getRootPane());
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        FieldObjects.add(Snake);
        super.setVisible(true);
        addKeyListener(new MyKeylistener());
        repaint();
        timer.start();
    }



    public void paint(Graphics g){
        Graphics2D g2 = Buffer.createGraphics();
        g2.setBackground(new Color(255,255,255));
        g2.clearRect(0,0,500,400);
        g2.setColor(new Color(15, 75, 100));
        for (FieldObject o : FieldObjects) {
            o.draw(g2);
        }
        g.drawImage(Buffer, 0, 0, null);
        g.dispose();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    public class MyKeylistener extends KeyAdapter {

        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()){
                case VK_RIGHT:
                case VK_UP:
                case VK_LEFT:
                case VK_DOWN:
                    Snake.direction = e.getKeyCode();
                    break;
            }
        }
    }
}
