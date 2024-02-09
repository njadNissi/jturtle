package com.njad;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import com.njad.Heading;

import static com.njad.Heading.*;
import static java.lang.Math.*;

public class Turtle implements Drawer{
    public static int W=500, H=500;
    public JFrame screen;
    private DrawingSheet sheet;
    private final LinkedList<String> cmds;
    private final LinkedList<Point> path;
    private Point cp;//currentPt
    private Heading dir = RIGHT, side = RIGHT; // motion direction
    private double angle = 0;
    private int lineNumOfPts = 75;

    public Turtle(int sceneW, int sceneH){
        W = sceneW;
        H = sceneH;
        this.screen = new JFrame();
        sheet = new DrawingSheet(W, H, Color.WHITE);
        this.cmds = new LinkedList<>();
        this.path = new LinkedList<>();
        this.screen.setSize(W, H);
        this.cp = new Point(W/2, W/2);
    }

    @Override
    public void showTurtle() {
        this.cmds.add("TURTLE true");
    }

    @Override
    public void hideTurtle() {
        this.cmds.add("TURTLE false");
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

        path.addAll(Shape.getLinePoints(cp, fp, lineNumOfPts));

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
    public void setDrawingSpeed(int speed) {
        sheet.timer.setDelay(1000 / speed);
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
        switch (dir) {
            case RIGHT -> { // facing up before
                c.setLocation(cp.x, cp.y - radius);
            }
            case DOWN -> {
                c.setLocation(cp.x, cp.y - radius);
            }
            case LEFT -> {
                System.out.println();
            }
        }
        path.addAll(Shape.getCirclePoints(c, radius));
    }

    public void run(){
        this.sheet.setPath(path);
        this.screen.add(sheet);
        this.screen.setVisible(true);
        sheet.timer.start();
    }

}
