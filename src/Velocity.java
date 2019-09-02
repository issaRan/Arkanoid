/**
 * this class used for the velocity of the for each ball.
 *
 * @author issa.
 * @version 2/4/18.
 */
public class Velocity {
    private double dx;
    private double dy;

    // constructor

    /**
     * constructor for dx / dy.
     *
     * @param dx dx is relate to the x.
     * @param dy dy is relate to the y.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * get dx for the velocity.
     *
     * @return the dx.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * get dx for the velocity.
     *
     * @return the dy.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * the function set the dx, by giving her a parameter.
     *
     * @param numberForDx taking an double number an that will be the dx.
     */
    public void setDx(double numberForDx) {
        this.dx = numberForDx;
    }

    /**
     * the function set the dy, by giving her a parameter.
     *
     * @param numberForDy taking an double num (paratmer an that will be the dy).
     */
    public void setDy(double numberForDy) {
        this.dy = numberForDy;
    }

    // Take a point with position (x,y) and return a new point
    // with position (x+dx, y+dy)

    /**
     * take a point with position (x,y) and return a new point, with position (x+dx,y+dy).
     *
     * @param p a point.
     * @return a new point, that mean moved the ball forward with this function.
     */
    public Point applyToPoint(Point p) {
        double theNewXPoint = p.getX() + this.dx;
        double theNewYPoint = p.getY() + this.dy;
        return new Point(theNewXPoint, theNewYPoint);
    }

    /**
     * calculate the speed of the current velocity.
     * @return the speed of the current velocity.
     */
    public double theSpeed() {
        return Math.sqrt((this.dx * this.dx) + (this.dy * this.dy));
    }

    /**
     * the function takes the angle and the speed, using the angle will return the velocity
     * of the ball.
     *
     * @param angle the angle of the hitting ball.
     * @param speed the speed of the hitting ball.
     * @return a new velocity depend on the angle an the speed.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double angleInRadians = angle * Math.PI / 180;
        double dx = Math.sin(angleInRadians) * speed;
        double dy = Math.cos(angleInRadians) * speed * (-1);
        return new Velocity(dx, dy);
    }
}
