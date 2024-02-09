package com.njad;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class DrawingSheet extends JPanel implements ActionListener {
    private LinkedList<Point> path;
    private final int DELAY = 10; // speed=10
    public Timer timer;
    private BufferedImage img;
    private Graphics G;
    private Point cp;
    private final Color BG;
    private final int W, H;
    public int penSize = 3;

    public DrawingSheet(int width, int height, Color bg){
        W = width;
        H = height;
        BG = bg;
        initImg();
        this.timer = new Timer(DELAY, this);
    }

    public void setPath(LinkedList<Point> path){
        this.path = path;
        this.cp = new Point(path.getFirst());
    }

    private void initImg() {
        this.img = new BufferedImage(W, H, BufferedImage.TYPE_INT_RGB);
        G = img.getGraphics();
        G.setColor(BG);
        G.fillRect(0, 0, W, H);
    }

    void drawTurtle(Graphics g, Point p){
        g.setColor(Color.YELLOW);
        int size = 10;
        for (int i = -size; i <= size; i++) {
            if(i > 0) // all the x coordinates are on the back
                g.fillOval(p.x - i, p.y + i/2, 3, 3);
            else
                g.fillOval(p.x + i, p.y + i/2, 3, 3);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.drawImage(img, 0, 0, this);
        drawTurtle(g, cp);

//        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(path.isEmpty()) {
            timer.stop();
            return;
        }

        G.setColor(Color.BLACK);
        cp = path.removeFirst();
        G.fillOval(cp.x, cp.y, penSize, penSize);
//        G.drawString(cp.x + ":"+cp.y, cp.x, cp.y - 10);

        repaint();
    }

}
