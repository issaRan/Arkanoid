public class Point
{
    private double x;
    private double y;
    public Point(double x,double y){
        this.x = x;
        this.y = y;
    }
    public double distance(Point otherPoint) {
        double xAfterSub = this.x - otherPoint.getX();
        double yAfterSub = this.y - otherPoint.getY();
        return Math.sqrt(xAfterSub * xAfterSub + yAfterSub * yAfterSub);
    }
    public boolean equals(Point otherPoint) {
        return this.x == otherPoint.getX() && this.y == otherPoint.getY();
    }
    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }
}
