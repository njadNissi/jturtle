package com.njad;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class DrawingSheet extends JPanel implements ActionListener, KeyListener {
    public ArrayList<Point> path;
    public ArrayList<Color> colors;
    private final int DELAY = 10; // speed=10
    public Timer timer;
    private BufferedImage img;
    private Graphics G;
    private Point cp;
    private final Color BG;
    private final int W, H;
    public int penSize = 3;
    private int colorInd = 0, count = 0 ; // count the pos of current point
    public boolean showTurtle = true;

    public DrawingSheet(int width, int height, Color bg){
        W = width;
        H = height;
        BG = bg;
        path = new ArrayList<>();
        cp = new Point();
        colors = new ArrayList<>();
        initImg();
        timer = new Timer(DELAY, this);
        setFocusable(true);
        addKeyListener(this);
    }

    private void initImg() {
        this.img = new BufferedImage(W, H, BufferedImage.TYPE_INT_RGB);
        G = img.getGraphics();
        G.setColor(BG);
        G.fillRect(0, 0, W, H);
        G.setColor(Color.BLACK);
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
        if(showTurtle) drawTurtle(g, cp);

//        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(count == path.size()) {
            timer.stop();
            return;
        }
        cp = path.get(count++);

        if(cp.x == Integer.MIN_VALUE){ // outline
            G.setColor(colors.get(cp.y));
        } else if (cp.x == Integer.MAX_VALUE) { // fill color
            G.setColor(colors.get(colorInd));
        } else {
            G.fillOval(cp.x, cp.y, penSize, penSize);
        }

//        G.drawString(cp.x + ":"+cp.y, cp.x, cp.y - 10);

        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_R){
            this.initImg();
            count = 0;
            colorInd = 0;
            timer.start();
        }
    }
}
