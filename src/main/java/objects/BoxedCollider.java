package objects;

import main.APoint;

public class BoxedCollider {
    APoint point1, point2;

    public BoxedCollider(int x1, int y1, int x2, int y2) {
        this.point1 = new APoint(x1,y1);
        this.point2 = new APoint(x2,y2);
    }
    public BoxedCollider(APoint point1, APoint point2) {
        this.point1 = point1;
        this.point2 = point2;
    }

    public APoint getPoint1() {
        return point1;
    }

    public void setPoint1(APoint point1) {
        this.point1 = point1;
    }

    public APoint getPoint2() {
        return point2;
    }

    public void setPoint2(APoint point2) {
        this.point2 = point2;
    }

    public void setX1(int x){
        this.point1 = new APoint(x, this.point1.y);
    }

    public void setY1(int y){
        this.point1 = new APoint(this.point1.x, y);
    }

    public void setX2(int x){
        this.point2 = new APoint(x, this.point2.y);
    }

    public void setY2(int y){
        this.point2 = new APoint(this.point2.x, y);
    }
}