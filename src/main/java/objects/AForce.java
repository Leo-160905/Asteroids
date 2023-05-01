package objects;

public class AForce {
    double rotation, length;

    public AForce(double rotation, double length) {
        this.rotation = rotation;
        this.length = length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getRotation() {
        return rotation;
    }

    public double getLength() {
        return length;
    }
}