import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class Field extends JFrame implements ActionListener {

    public Snake snake = new Snake();
    public BufferedImage Buffer = new BufferedImage(500,200,BufferedImage.TYPE_INT_ARGB);

    public Field(){
        Timer timer = new Timer(16,this);
        super.setSize(500,200);
        this.setLocationRelativeTo(getRootPane());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        super.setVisible(true);
        repaint();
        timer.start();
    }

    public void paint(Graphics g){
        Graphics g2 = Buffer.getGraphics();

        g2.setColor(new Color(255, 255, 255));
        g2.clearRect(0,0,500,500);
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
}
