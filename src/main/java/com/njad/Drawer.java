package com.njad;

import java.awt.*;

public interface Drawer {

    public static int W = 300, H = 300;

    public void showTurtle();
    public void hideTurtle();
    public void startAt(int x, int y);
    /*forward:
    * distance: length of motion
    * returns the current position*/
    public void forward(int distance);
    public void backward(int distance);
    public void setSpeed(int speed);
    public void setPenSize(int size);
    public void right(double angle);
    public void left(double angle);
    public void circle(int radius);
    public void setColor(Color c);
    public void fill(Color c);

}
