package com.njad;

public class Main {
    public static void main(String[] args) {

        Turtle t = new Turtle(400, 400);

        for (int i = 0; i < 4; i++) {
            t.backward(100);
            t.left(90);
//            t.forward(100);
//            t.right(90);
        }
        t.circle(50);

        t.run();
    }
}