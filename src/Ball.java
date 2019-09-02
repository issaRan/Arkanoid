import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

/**
 * class of the ball.
 *
 * @author issa
 * @version 2/4/18
 */
public class Ball implements Sprite {
    private Point center;
    private int r;
    private java.awt.Color color;
    private Velocity velocity1;
    private List<HitListener> hitListeners;
    private GameEnvironment environment;
    private int topBoundry;
    private int lowerBoundry;
    private int rightBoundry;
    private int leftBoundry;
    private boolean entering = true;

    /**
     * this function is a construct, which we use to initialize the parameter that we will use .
     *
     * @param center that point to the center of each ball
     * @param r      the radios of the ball ( in our case, it's the size)
     * @param color  aa.
     * @param environment the environment that the ball running on him.
     */
    public Ball(Point center, int r, Color color, GameEnvironment environment) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.environment = environment;
        this.hitListeners = new ArrayList<>();
    }

    // accessors

    /**
     * accessor for the x of the center of the ball.
     *
     * @return the x of the center of the ball.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * accessor for the x of the center of the ball.
     *
     * @return the x of the center of the ball.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * accessor for the radios of the center of the ball.
     *
     * @return the radios of the center of the ball.
     */
    public int getSize() {
        return r;
    }

    /**
     * an object from the gameEnvironment to initialize the ball.
     *
     * @param gameEnvironment setting the gameEnviroment.
     */
    public void setGameEnvironment(GameEnvironment gameEnvironment) {
        this.environment = gameEnvironment;
    }

    /**
     * add this sprite ( ball ) to the game.
     *
     * @param g game parameter.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * accessor for the color of the center of the ball.
     *
     * @return the color of the center of the ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * the function set the limit of the left of the shape.
     *
     * @param leftBoundryTheSecond getting the left boundry of the shape.
     */
    public void setLeftBoundry(int leftBoundryTheSecond) {
        this.leftBoundry = leftBoundryTheSecond;
    }

    /**
     * the function set the limit of the left of the shape.
     *
     * @param lowerBoundryTheSecond getting the lower boundry of the shape.
     */
    public void setLowerBoundry(int lowerBoundryTheSecond) {
        this.lowerBoundry = lowerBoundryTheSecond;
    }

    /**
     * the function set the limit of the right of the shape.
     *
     * @param rightBoundryTheSecond getting the right boundry of the shape.
     */
    public void setRightBoundry(int rightBoundryTheSecond) {
        this.rightBoundry = rightBoundryTheSecond;
    }

    /**
     * the function set the limit of the top of the shape.
     *
     * @param topBoundryTheSecond getting the top boundry of the shape.
     */
    public void setTopBoundry(int topBoundryTheSecond) {
        this.topBoundry = topBoundryTheSecond;
    }

    /**
     * the function set the velocity of the each ball.
     *
     * @param v getting the velocity as an object.
     */
    public void setVelocity(Velocity v) {
        this.velocity1 = v;
    }

    /**
     * set the velocity of the ball using dx / dy.
     *
     * @param dx getting the dx of the velocity
     * @param dy getting the dy of the velocity
     */
    public void setVelocity(double dx, double dy) {
        setVelocity(new Velocity(dx, dy));
    }

    /**
     * a function that used to get velocity for the ball.
     *
     * @return new velocity which include dx,dy.
     */
    public Velocity getVelocity() {
        //return new Velocity(dx,dy);
        return this.velocity1;
    }

    /**
     * remove the ball when it's reached to the death zone.
     * @param g the object that the ball playing on.
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }

    /**
     * the function used to move the ball in the Rectangle one step forward(by calling for the
     * function applyToPoint, if the ball hits the wall it's will go to the opposite direction.
     */
    public void moveOneStep() {
        // getting the (x,y) of the ball
        double xOfTheBall = this.center.getX();
        double yOfTheBall = this.center.getY();
        //created the tragectory, which start if the center of the ball, and ending when we adding the speed (dx,dy)
        //of the ball to the tragectory.
        Line tragectory = new Line(xOfTheBall, yOfTheBall, xOfTheBall + this.velocity1.getDx(),
                yOfTheBall + this.velocity1.getDy());
        CollisionInfo collision = this.environment.getClosestCollision(tragectory);
        // case we dont have a collision.
        if (collision == null) {
            this.center = this.getVelocity().applyToPoint(this.center);
        } else {
            if (this.velocity1.getDx() < 0) {
                this.center = new Point(xOfTheBall + this.r / 2, yOfTheBall);
            }
            if (this.velocity1.getDx() > 0) {
                this.center = new Point(xOfTheBall - this.r / 2, yOfTheBall);
            }
            if (this.velocity1.getDy() < 0) {
                this.center = new Point(xOfTheBall, yOfTheBall + this.r / 2);
            }
            if (this.velocity1.getDy() > 0) {
                this.center = new Point(xOfTheBall, yOfTheBall - this.r / 2);
            }
            this.setVelocity(collision.collisionObject().hit(this, collision.collisionPoint(), this.velocity1));
        }
    }
    // draw the ball on the given DrawSurface

    /**
     * draw the ball on the given DrawSurface.
     *
     * @param surface taking surface to use the function setColor and Fill circle.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.black);
        //Drawing the frame of the ball.
        surface.drawCircle(this.getX(), this.getY(), this.getSize());
        //Setting the color of the ball.
        surface.setColor(this.getColor());
        //Drawing the ball.
        surface.fillCircle(this.getX(), this.getY(), this.getSize());
    }

    /**
     * used to call the MoveOneStep.
     * @param dt delta time.
     */
    public void timePassed(double dt) {
        if (this.entering) {
            this.setVelocity(dt * this.velocity1.getDx(), dt * this.velocity1.getDy());
            this.entering = false;
        }
        moveOneStep();
    }
}