package com.njad;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.lang.Math.*;

public class Shape {
    public Point p1, p2;

    public Shape() {

    }

    public static Collection<? extends Point> getLinePoints(Point p1, Point p2, int quantity) {
        var points = new Point[quantity];
        int deltaY = p2.y - p1.y;
        int deltaX = p2.x - p1.x;
        double slope = (double)(p2.y - p1.y) / (p2.x - p1.x);
        double x, y;

        --quantity;

        for (double i = 0; i < quantity; i++) {
            y = slope == 0 ? 0 : deltaY * (i / quantity);
            x = slope == 0 ? deltaX * (i / quantity) : y / slope;
            points[(int)i] = new Point((int)Math.round(x) + p1.x, (int)Math.round(y) + p1.y);
        }

        points[quantity] = p2;
        return List.of(points);
    }

    public static Collection<? extends Point> getCirclePoints(Point center, int r) {
        List<Point> points = new ArrayList<>();
        int startAngle = 90;
        for (double angle = startAngle; angle <= startAngle + 360; angle+=.25) {
            var X = r * cos(toRadians(angle));
            var Y = r * sin(toRadians(angle));
            if(pow(X, 2) + pow(Y, 2) == pow(r, 2))
                points.add(new Point((int) (center.x +- X), (int) (center.y + Y)));
        }

        return points;
    }
}
