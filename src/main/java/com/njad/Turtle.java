package com.njad;

import javax.swing.*;
import java.awt.*;

import static com.njad.Heading.*;
import static java.lang.Math.*;

public class Turtle implements Drawer{
    public static int W=500, H=500;
    public JFrame screen;
    private DrawingSheet sheet;
    private Point cp;//currentPt
    private Heading dir = RIGHT, side = RIGHT; // motion direction
    private double angle = 0;
    private int LINE_NO_POINTS = 75;
    private int count = 0;

    public Turtle(int sceneW, int sceneH){
        W = sceneW;
        H = sceneH;
        this.screen = new JFrame();
        sheet = new DrawingSheet(W, H, Color.WHITE);
        this.screen.setSize(W, H);
        this.cp = new Point(W/2, W/2);
    }

    @Override
    public void showTurtle() {
        sheet.showTurtle = true;
    }

    @Override
    public void hideTurtle() {
        sheet.showTurtle = false;
    }

    @Override
    public void startAt(int x, int y) {
        this.cp.setLocation(x, y);
    }

    @Override
    public void forward(int distance) {
        move(distance);
    }

    @Override
    public void backward(int distance) {
        move(-distance);

        reverseHeading();
    }

    private void reverseHeading() {
        switch (dir){
            case RIGHT -> dir = LEFT;
            case LEFT -> dir = RIGHT;
            case UP -> dir = DOWN;
            case DOWN -> dir = UP;
        }
    }

    private void move(int distance){
        var fp = new Point(); // finalPt

        var X = (int)(distance * cos(angle) + cp.x);
        int Y = (int)(distance * sin(angle) + cp.y);
        fp.setLocation(X, Y);

        sheet.path.addAll(Shape.getLinePoints(cp, fp, LINE_NO_POINTS));

        cp = fp;
    }

    private void updateHeading() {
        switch (side) {
            case RIGHT -> {
                if(dir == UP) dir = RIGHT;
                else if(dir == RIGHT) dir = DOWN;
                else if(dir == DOWN) dir = LEFT;
                else dir = UP;
            }
            case LEFT -> {
                if(dir == UP) dir = LEFT;
                else if(dir == LEFT) dir = DOWN;
                else if(dir == DOWN) dir = RIGHT;
                else dir = UP;
            }
        }
    }

    @Override
    public void setSpeed(int speed) {
        sheet.timer.setDelay(100 / speed);
    }

    @Override
    public void setPenSize(int size) {
        this.sheet.penSize = size;
    }

    @Override
    public void right(double angle) { // turn right with given angle
        this.angle += toRadians(angle);
        System.out.println("---> " + dir);
        updateHeading();
    }

    @Override
    public void left(double angle) {
        this.angle -= toRadians(angle);
        System.out.println("---> " + dir);
        updateHeading();
    }

    @Override
    public void circle(int radius) {
        Point c = new Point();//center
    /*    switch (dir) {
            case RIGHT -> { // facing up before
                c.setLocation(cp.x, cp.y - radius);
            }
            case DOWN -> { // facing
                c.setLocation(cp.x, cp.y - radius);
            }
            case LEFT -> {
                System.out.println();
            }
        }

*/
        c.setLocation(cp.x, cp.y - radius);

        sheet.path.addAll(Shape.getCirclePoints(c, radius));
    }

    @Override
    public void setColor(Color c) { // outline color
        //add a point p with p.x = -MIN_VALUE, p.y=color for outline. p.x= MAX_VALUE and p.y=color for fill
        // the color is not saved inn the path but the p.y is the index of that color in colors list.
        sheet.path.add(new Point(Integer.MIN_VALUE, count++));
        sheet.colors.add(c);
    }

    @Override
    public void fill(Color c) {
        //add a point p with p.x = -MIN_VALUE, p.y=color for outline. p.x= MAX_VALUE and p.y=color for fill
        // the color is not saved inn the path but the p.y is the index of that color in colors list.
        sheet.path.add(new Point(Integer.MAX_VALUE, count++));
        sheet.colors.add(c);
    }

    public void run(){
//        this.sheet.setPath(path);
        this.screen.add(sheet);
        this.screen.setVisible(true);
        sheet.timer.start();
    }

}
