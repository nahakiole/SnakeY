import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Field extends JFrame implements ActionListener {

    public Snake snake = new Snake();

    public Field(){
        Timer timer = new Timer(16,this);
        super.setSize(500,200);
        this.setLocationRelativeTo(getRootPane());
        super.setVisible(true);
        repaint();
        timer.start();
    }

    public void paint(Graphics g){

        g.clearRect(0,0,500,500);
        snake.draw(g);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //System.out.println("Test");
        repaint();
    }
}
