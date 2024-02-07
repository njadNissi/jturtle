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
    public void setDrawingSpeed(int speed);
    public void right(double angle);
    public void left(double angle);

}
