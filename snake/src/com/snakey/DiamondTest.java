package com.snakey;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;

/**
 * @author Robin Glauser
 */
public class DiamondTest {

    Diamond diamond;

    @Before
    public void setUp(){
        diamond = new Diamond( new Point(10, 1), 10);
    }

    @Test
    public void testDraw() throws Exception {
        assertEquals(10, diamond.points, 0);
    }
}
