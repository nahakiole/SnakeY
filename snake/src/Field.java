import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Field extends JFrame implements ActionListener {

    public Snake snake = new Snake();
    public BufferedImage Buffer = new BufferedImage(500,400,BufferedImage.TYPE_INT_ARGB);

    public Field(){
        Timer timer = new Timer(16,this);
        super.setSize(500,400);
        this.setLocationRelativeTo(getRootPane());
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
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
        snake.draw(g2);
        g.drawImage(Buffer, 0, 0, null);
        g.dispose();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //System.out.println("Test");
        repaint();
    }

    public class MyKeylistener extends KeyAdapter {

        public void keyPressed(KeyEvent e) {
            System.out.println(e.getKeyCode());
            snake.direction = e.getKeyCode();
        }
    }
}
