package com.snakey;

import java.awt.*;
import java.awt.geom.Area;
import java.util.Vector;

/**
 * @author Robin Glauser
 * @version 1.0
 */
public class Snake extends FieldObject {
    public int direction = RIGHT;
    public Vector<Point> Track = new Vector<Point>();
    public Color c = new Color(0, 80, 0);
    public double speed = 4;
    public int padding = 2;
    public boolean alive = true;
    public Color Eyes = new Color(71, 119, 255);
    public static final int UP = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 3;
    public static final int RIGHT = 4;

    public Snake(Point p) {

        addDefaultPoints(10);
        this.Position = p;
    }

    public Snake( Point p, Color cn) {
        this(p);
        c = cn;
    }

    public Snake( Point p, Color cn, int mspeed) {
        this(p, cn);
        speed = mspeed;
    }

    public void addDefaultPoints(int total) {
        for (Integer i = 0; i < total; i++) {
            Track.add(new Point(Position.x, Position.y));
        }
    }

    @Override
    public void draw(Graphics g) {
        switch (direction) {
            case RIGHT:
                Position.x = Position.x + padding; //Right
                break;
            case UP:
                Position.y = Position.y - padding; //UP
                break;
            case LEFT:
                Position.x = Position.x - padding; //Left
                break;
            case DOWN:
                Position.y = Position.y + padding; //Up
                break;
        }
        Color old = g.getColor();
        g.setColor(c);
        if (alive) {
            Track.remove(0);
            Track.add(new Point(Position.x, Position.y));
        }

        Polygon poly = new Polygon();
        for (Point p: Track){
            poly.addPoint(p.x,p.y);
        }
        poly.addPoint(Track.get(Track.size()-1).x-30,Track.get(Track.size()-1).y-30);
        for (int i = Track.size()-1; i > 1; i--){
            poly.addPoint(Track.get(i).x+30,Track.get(i).y+30);
        }
        g.fillPolygon(poly);
        g.setColor(c.brighter());

        g.fillRoundRect(Position.x-5,Position.y-5,40,40,35,35);
        g.fillRect(Track.get(0).x, Track.get(0).y, 30, 30);
        g.fillRoundRect(Position.x - 5, Position.y - 5, 40, 40, 35, 35);
        g.setColor(Color.WHITE);
        Point EyeL = new Point();
        Point EyeR = new Point();
        switch (direction) {
            case RIGHT:
                EyeL.setLocation(Position.x + 20, Position.y + 20);
                EyeR.setLocation(Position.x + 20, Position.y);
                break;
            case UP:
                EyeL.setLocation(Position.x, Position.y);
                EyeR.setLocation(Position.x + 20, Position.y);
                break;
            case LEFT:
                EyeL.setLocation(Position.x, Position.y + 20);
                EyeR.setLocation(Position.x, Position.y);
                break;
            case DOWN:
                EyeL.setLocation(Position.x, Position.y + 20);
                EyeR.setLocation(Position.x + 20, Position.y + 20);
                break;
        }
        g.fillOval(EyeR.x, EyeR.y, 7, 7);
        g.fillOval(EyeL.x, EyeL.y, 7, 7);
        g.setColor(Eyes);
        g.fillOval(EyeR.x + 2, EyeR.y + 2, 3, 3);
        g.fillOval(EyeL.x + 2, EyeL.y + 2, 3, 3);
        g.setColor(old);
    }

    public Point getHead() {
        Point directionVector;
        switch (direction) {
            case Snake.RIGHT:
                return new Point(Position.x + 35, Position.y);
            case Snake.UP:
                return new Point(Position.x, Position.y - 5);
            case Snake.LEFT:
                return new Point(Position.x - 5, Position.y);
            default:
                return new Point(Position.x, Position.y + 35);
        }
    }

    public void die() {
        alive = false;
    }

    @Override
    public Area getArea() {
        Area area = new Area();
        Area tmp = new Area(new Rectangle(Track.get(0).x, Track.get(0).y, 30, 30));
        area.add(tmp);
        return area;
    }
}
