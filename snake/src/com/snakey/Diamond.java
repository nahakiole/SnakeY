package com.snakey;

import java.awt.*;
import java.awt.geom.Area;

public class Diamond extends FieldObject {

    public Integer points;
    @Override
    public void draw(Graphics g) {

        g.setColor(Color.BLACK);
        g.fill3DRect(Position.x,Position.y,40,40,true);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", 10, 20));
        g.drawString( points.toString(), Position.x+9,Position.y+27);
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public Diamond(Point p, Integer po){
        Position = p;
        points = po;
    }

    @Override
    public Area getArea() {
        return new Area(new Rectangle(Position.x,Position.y,40,40));
    }
}
