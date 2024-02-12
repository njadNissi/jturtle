import com.njad.Turtle;

import java.awt.*;

public class Main {
    public static void main(String[] args) {

        Turtle t = new Turtle(400, 400);

//        t.setPenSize(5);
        for (int i = 0; i < 4; i++) {
            t.backward(100);
            t.left(90);
        }

        t.setSpeed(20);
        t.circle(50);
        t.setColor(Color.RED);

        for (int i = 1; i <= 6; i++) {
            t.left(15 * i);
            t.forward(50);
        }

        t.setColor(Color.BLUE);
        for (int i = 0; i < 4; i++) {
            t.forward(75);
            t.right(90 - i * 30);
        }

        t.circle(50);

        t.run();
    }
}