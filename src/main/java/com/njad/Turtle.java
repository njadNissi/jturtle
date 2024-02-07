package com.njad;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class Turtle implements Drawer{
    public static int W=500, H=500;
    public JFrame screen;
    private DrawingSheet sheet;
    private final LinkedList<String> cmds;
    private final LinkedList<Point> path;
    private Point cp;//currentPt
    private Heading dir = Heading.RIGHT; // motion direction
    private double angle = 0;
    private Heading side = Heading.RIGHT; // curvature direction

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

        Point fp = new Point(); // finalPt

        int X = (int)(distance * Math.cos(angle) + cp.x);
        int Y = (int)(distance * Math.sin(angle) + cp.y);
        fp.setLocation(X, Y);

        LinePoints lp = new LinePoints(cp, fp);
        path.addAll(lp.getPoints(30));

        cp = fp;
    }

    @Override
    public void backward(int distance) {

        Point fp = new Point(); // finalPt

        int X = (int)(-distance * Math.cos(angle) + cp.x);
        int Y = (int)(-distance * Math.sin(angle) + cp.y);
        fp.setLocation(X, Y);

        LinePoints lp = new LinePoints(cp, fp);
        path.addAll(lp.getPoints(100));

        cp = fp;
    }

    @Override
    public void setDrawingSpeed(int speed) {
        sheet.timer.setDelay(1000 / speed);
    }

    @Override
    public void right(double angle) { // turn right with given angle
        this.angle += angle * Math.PI / 180;
    }

    @Override
    public void left(double angle) {
        this.angle -= angle * Math.PI / 180;
    }

    public void run(){
        this.sheet.setPath(path);
        this.screen.add(sheet);
        this.screen.setVisible(true);
        sheet.timer.start();
    }

}
