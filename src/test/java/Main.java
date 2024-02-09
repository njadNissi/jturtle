import com.njad.Turtle;

public class Main {
    public static void main(String[] args) {

        Turtle t = new Turtle(400, 400);

        for (int i = 0; i < 4; i++) {
            t.backward(100);
            t.left(90);
        }

        t.setDrawingSpeed(20);
        t.circle(50);

        for (int i = 1; i <= 6; i++) {
            t.left(15 * i);
            t.forward(50);
        }

        for (int i = 0; i < 4; i++) {
            t.forward(75);
            t.right(90 - i * 30);
        }

        t.circle(50);

        t.run();
    }
}