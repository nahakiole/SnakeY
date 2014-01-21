package com.snakey;

import java.awt.*;
import java.awt.geom.Area;

/**
 * The FieldObject is the base class for any game object.
 *
 * The FieldObject has a Position, a Renderer.
 * You can draw the FieldObject or you can get the area.
 *
 * @author Robin Glauser
 * @version 1.0
 */
abstract public class FieldObject {

    public Point Position = new Point();


    /**
     * Abstract method to draw the Object on the jFrame.
     *
     * @param g Graphics Object
     */
    abstract public void draw(Graphics g);

    /**
     * Abstract Method to generate the Area for a FieldObject.
     * Used for collision detection in Field Class.
     *
     * @return Area
     */
    abstract public Area getArea();

}
