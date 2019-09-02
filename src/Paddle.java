import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * @author ISSA
 * 21/4/18
 * this class is relate to the paddle of the game.
 */
public class Paddle implements Sprite, Collidable {
    private static final int BORDER_PADDLE_RIGHT = 764;
    private static final int BORDER_PADDLE_LEFT = 35;
    private KeyboardSensor keyboard;
    private Rectangle rectangle;
    private int speedOfPaddle;
    private int frames;
    private boolean entering;
    /**
     * the constructor.
     *
     * @param keyboard      takes what the user is typed in the keyboard.
     * @param rectangle     get the shape of the recatangle that is relate to the paddle.
     * @param speedOfPaddle get the speed that we want from the paddle to move.
     */
    public Paddle(biuoop.KeyboardSensor keyboard, Rectangle rectangle, int speedOfPaddle) {
        //this.keyboard = sensor;
        this.rectangle = rectangle;
        this.speedOfPaddle = speedOfPaddle;
        this.keyboard = keyboard;
        this.frames = 60;
        this.entering = true;
    }

    /**
     * this function is used to control the moves of the paddle to the left side, it's reach the edge of the screen
     * it will be blocked.
     */
    public void moveLeft() {
        if (this.rectangle.getUpperLeft().getX() <= BORDER_PADDLE_LEFT) {
            this.rectangle = new Rectangle(new Point(BORDER_PADDLE_LEFT, this.rectangle.getUpperLeft().getY()),
                    this.rectangle.getWidth(), this.rectangle.getHeight());
        }
        this.rectangle = new Rectangle(new Point(this.rectangle.getUpperLeft().getX() - speedOfPaddle,
                this.rectangle.getUpperLeft().getY())
                , this.rectangle.getWidth(), this.rectangle.getHeight());

    }

    /**
     * this function is used to control the moves of the paddle to the right side, it's reach the edge of the screen
     * it will be blocked.
     */
    public void moveRight() {
        if (this.rectangle.getUpperRight().getX() >= BORDER_PADDLE_RIGHT) {
            this.rectangle = new Rectangle(new Point(BORDER_PADDLE_RIGHT - this.rectangle.getWidth(),
                    this.rectangle.getUpperLeft().getY()), this.rectangle.getWidth(), this.rectangle.getHeight());
//            System.out.println(this.rectangle.getUpperRight().getX());
        }
        double newX = this.rectangle.getUpperLeft().getX() + speedOfPaddle;
        this.rectangle = new Rectangle(new Point(newX, this.rectangle.getUpperLeft().getY())
                , this.rectangle.getWidth(), this.rectangle.getHeight());
        //System.out.println(this.rectangle.getUpperRight().getX());
    }

    /**
     * this function plays when the user push the left key or the right key, according that it call the
     * function that makes the operation.
     * @param dt delta time.
     */
    public void timePassed(double dt) {
        if (this.entering) {
            double newSpeed = dt * this.speedOfPaddle;
            this.speedOfPaddle = (int) newSpeed;
            this.entering = false;
        }
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }

    }

    /**
     * the function used to draw the rectangle on the screen, given the shape of him.
     *
     * @param d takes the DrawSurface prameter to draw the rectangle.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.YELLOW);
        d.fillRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), 30);
        d.setColor(Color.black);
        d.drawRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), 30);
    }

    // Collidable

    /**
     * the function return the collision of this rectangle that we want to check.
     *
     * @return the given rectangle.
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * the function used to check the region of the ball hit in the paddle, and return based of the collision Point
     * the direction of this Point.
     *
     * @param collisionPoint  the collision point of the hit.
     * @param currentVelocity the velocity of the current ball.
     * @param hitter          the ball that hitting.
     * @return the new velocity after, the direction on the ball had changed.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double theWidthOftheRectangle = this.rectangle.getWidth();
        double thePartsOftheRectangle = theWidthOftheRectangle / 5;
        Line ds = new Line(this.rectangle.getUpperLeft(), this.rectangle.getBottomLeft());
        Line rightSide = new Line(this.rectangle.getUpperRight(), this.rectangle.getBottomRight());
        Line leftSide = new Line(this.rectangle.getBottomLeft(), this.rectangle.getBottomRight());
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        Velocity theVelocity = currentVelocity;
        if (collisionPoint.getX() == this.rectangle.getUpperRight().getX()
                && collisionPoint.getY() == this.rectangle.getUpperRight().getY()) {
            theVelocity = Velocity.fromAngleAndSpeed(60, currentVelocity.theSpeed());
        }
        if (collisionPoint.getX() == this.rectangle.getUpperLeft().getX()
                && collisionPoint.getY() == this.rectangle.getUpperLeft().getY()) {
            theVelocity = Velocity.fromAngleAndSpeed(300, currentVelocity.theSpeed());
        }
        if (collisionPoint.getX() > this.rectangle.getUpperLeft().getX()
                && collisionPoint.getX() <= this.rectangle.getUpperLeft().getX() + thePartsOftheRectangle) {
            theVelocity = Velocity.fromAngleAndSpeed(300, currentVelocity.theSpeed());
        }
        if (collisionPoint.getX() > this.rectangle.getUpperLeft().getX() + thePartsOftheRectangle
                && collisionPoint.getX() <= this.rectangle.getUpperLeft().getX() + 2 * thePartsOftheRectangle) {
            theVelocity = Velocity.fromAngleAndSpeed(330, currentVelocity.theSpeed());
        }
        if (collisionPoint.getX() > this.rectangle.getUpperLeft().getX() + 2 * (thePartsOftheRectangle)
                && collisionPoint.getX() <= this.rectangle.getUpperLeft().getX() + 3 * thePartsOftheRectangle) {
            theVelocity = new Velocity(dx, -dy);
        }
        if (collisionPoint.getX() > this.rectangle.getUpperLeft().getX() + 3 * (thePartsOftheRectangle)
                && collisionPoint.getX() <= this.rectangle.getUpperLeft().getX() + 4 * thePartsOftheRectangle) {
            theVelocity = Velocity.fromAngleAndSpeed(30, currentVelocity.theSpeed());
        }
        if (collisionPoint.getX() > this.rectangle.getUpperLeft().getX() + 4 * (thePartsOftheRectangle)
                && collisionPoint.getX() <= this.rectangle.getUpperLeft().getX() + 5 * (thePartsOftheRectangle)) {
            theVelocity = Velocity.fromAngleAndSpeed(60, currentVelocity.theSpeed());
        }

        if (ds.isOnTheLine(collisionPoint)) {
            return new Velocity(-dx, dy);
        }
        if (rightSide.isOnTheLine(collisionPoint)) {
            return new Velocity(-dx, dy);
        }
        if (leftSide.isOnTheLine(collisionPoint)) {
            theVelocity.setDy(Math.abs(dy));
        }
        return theVelocity;

    }

    /**
     * Add this paddle to the game.
     *
     * @param g taked an prameter which in GameLevel type.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}
