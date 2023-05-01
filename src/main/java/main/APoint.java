package main;

public class APoint {
    public double x,y;

    public APoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return x + " : " + y;
    }
}
