package objects;

import main.APoint;

public class BoxedCollider {
    APoint point1, point2;// to points to make a square (Boxedcollider) around the object

    public BoxedCollider(int x1, int y1, int x2, int y2) {
        this.point1 = new APoint(x1, y1);
        this.point2 = new APoint(x2, y2);
    }

    public APoint getPoint1() {
        return point1;
    }

    public APoint getPoint2() {
        return point2;
    }
}