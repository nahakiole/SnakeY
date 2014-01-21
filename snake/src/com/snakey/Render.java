package com.snakey;

import java.util.Date;

/**
 * @author Robin Glauser
 * @version 1.0
 */
public class Render {

    public long time = new Date().getTime();
    public long delta = time - new Date().getTime();
    public boolean running = true;
    public int fps = 60;
    public Field Field;


    public Render(Field Field) {
        this.Field = Field;

    }

    public void start() {
        while (running) {
            if (Field.Pause) {

            } else {
                if (new Date().getTime() - time >= 1000 / fps) {
                    Field.repaint();
                    time = new Date().getTime();
                }
            }
        }
    }

    public void stop(){
        running = false;
    }
}
