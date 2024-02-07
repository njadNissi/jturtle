package com.njad;

import java.awt.*;
import java.util.Collection;
import java.util.List;

public class LinePoints {
    public Point p1, p2;

    public LinePoints(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public Collection<? extends Point> getPoints(int quantity) {
        var points = new Point[quantity];
        int ydiff = p2.y - p1.y, xdiff = p2.x - p1.x;
        double slope = (double)(p2.y - p1.y) / (p2.x - p1.x);
        double x, y;

        --quantity;

        for (double i = 0; i < quantity; i++) {
            y = slope == 0 ? 0 : ydiff * (i / quantity);
            x = slope == 0 ? xdiff * (i / quantity) : y / slope;
            points[(int)i] = new Point((int)Math.round(x) + p1.x, (int)Math.round(y) + p1.y);
        }

        points[quantity] = p2;
        return List.of(points);
    }
}
