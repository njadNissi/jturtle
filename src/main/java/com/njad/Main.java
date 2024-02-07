package com.njad;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        Turtle t = new Turtle(800, 750);

//        t.startAt(250, 250);
        t.setDrawingSpeed(100);

        for (int i = 0; i < 4; i++) {
            t.backward(100);
            t.left(90);
        }

        t.run();
    }
}