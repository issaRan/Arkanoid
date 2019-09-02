import biuoop.DrawSurface;
import java.util.List;
import java.util.ArrayList;
import java.awt.Color;
import java.util.Map;

/**
 * @author ISSA
 * 21/4/18
 * this class used to define the block.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private List<HitListener> hitListeners;
    private GameLevel remover;
    private Rectangle block;
    private Velocity v;
    private int hit;
    private Color color;
    private PrintingHitListener hi;
    private Point point;
    private int height;
    private int width;
    private int xpos;
    private int ypos;
    private int theWidth;
    private int theHieght;
    private Map<Integer, ColorOrImage> filling;
    private int hits;
    private Color stroke;

    /**
     * function to define the block that we got.
     *
     * @param block the specific block that we want to define.
     */
    public Block(Rectangle block) {
        this.block = block;
    }

    /**
     * constructor.
     *
     * @param rectangle getting the shape of the rectangle.
     * @param color     getting the color.
     * @param hit       the number that gives for each block.
     */
    public Block(Rectangle rectangle, Color color, int hit) {
        this.block = rectangle;
        this.color = color;
        this.hit = hit;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * constructor.
     *
     * @param xpos    start x point.
     * @param ypos    start y point.
     * @param width   width block.
     * @param height  height block.
     * @param stroke  stroke.
     * @param filling map.
     * @param hits    hit point.
     */
    public Block(int xpos, int ypos, int width, int height, Color stroke, Map<Integer,
            ColorOrImage> filling, int hits) {
        this.xpos = xpos;
        this.ypos = ypos;
        this.block = new Rectangle(new Point(xpos, ypos), width, height);
        this.theWidth = width;
        this.theHieght = height;
        this.stroke = stroke;
        this.filling = filling;
        this.hits = hits;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * add the block to the game.
     *
     * @param g getting a parameter in type GameLevel.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    @Override
    public void timePassed(double dt) {

    }
    // Return the "collision shape" of the object.

    /**
     * getting the shape of the that we collision on him.
     *
     * @return the "collision shape" of the object.
     */
    public Rectangle getCollisionRectangle() {
        return this.block;
    }

    /**
     * the function treat when the ball hit the block.
     *
     * @param hitter          the ball that made the hit.
     * @param collisionPoint  get the in which point the ball hit.
     * @param currentVelocity takes the current velocity.
     * @return Velocity after changing the direction of the ball.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Velocity theVelocity = currentVelocity;
        if (collisionPoint == null) {
            return theVelocity;
        }
        if (hits >= 0) {
            this.hits = hits - 1;
        }
        // first checking if the hit ( the collision ) take place on the edge of the block.
        if (collisionPoint.getY() == this.block.getUpperLeft().getY() && collisionPoint.getX()
                == this.block.getUpperLeft().getX()) {
            // if the edge hit and the dx is positive and dy is negative we cheange the dx, else we need to
            // change the dy.
            if (currentVelocity.getDy() < 0 && currentVelocity.getDx() > 0) {
                theVelocity = new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
            } else {
                theVelocity = new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
            }
            // now we checked the right upper of the block, checking if the hit is on the upperRight.
        } else if (collisionPoint.getY() == this.block.getUpperLeft().getY() && collisionPoint.getX()
                == this.block.getBottomRight().getX()) {
            // if the edge hit and the dx is positive and dy is negative we cheange the dx, else we need to
            // change the dy.
            if (currentVelocity.getDy() < 0 && currentVelocity.getDx() < 0) {
                theVelocity = new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
            } else {
                theVelocity = new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
            }
            // checking the left lower of the block
        } else if (collisionPoint.getY() == this.block.getBottomRight().getY() && collisionPoint.getX()
                == this.block.getUpperLeft().getX()) {
            // if the edge hit and the dx is positive and dy is negative we cheange the dx, else we need to
            // change the dy.
            if (currentVelocity.getDy() > 0 && currentVelocity.getDx() > 0) {
                theVelocity = new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
            } else {
                theVelocity = new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
            }
            // checking the right bottom of the block.
        } else if (collisionPoint.getY() == this.block.getBottomRight().getY() && collisionPoint.getX()
                == this.block.getBottomRight().getX()) {
            // if the edge hit and the dx is positive and dy is negative we cheange the dx, else we need to
            // change the dy.
            if (currentVelocity.getDy() > 0 && currentVelocity.getDx() < 0) {
                theVelocity = new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
            } else {
                theVelocity = new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
            }
            // case that the hit is on the top line.
        } else if (collisionPoint.getY() == this.block.getUpperLeft().getY()) {
            theVelocity = new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
            // case that the hit is on the bottom.
        } else if (collisionPoint.getY() == this.block.getBottomRight().getY()) {
            theVelocity = new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
            // case that the hit is on the left
        } else if (collisionPoint.getX() == this.block.getUpperLeft().getX()) {
            theVelocity = new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
            //if collision occurs on right line.
        } else if (collisionPoint.getX() == this.block.getBottomRight().getX()) { //if collision occurs on lower line.
            theVelocity = new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }
        this.notifyHit(hitter);
        return theVelocity;
    }

    /**
     * remove the block from the game.
     *
     * @param game the game.
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
        game.removeCollidable(this);

    }

    /**
     * get the hit point of the block.
     *
     * @return the amount of hitting.
     */
    public int getHitPoints() {
        return this.hits;
    }

    /**
     * Add hl as a listener to hit events.
     *
     * @param hl add the block to hitlistener.
     */
    // Add hl as a listener to hit events.
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * // Remove hl from the list of listeners to hit events.
     *
     * @param hl removed the object hl from the hitlistener
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * // Make a copy of the hitListeners before iterating over them.
     * // Notify all listeners about a hit event:
     *
     * @param hitter the ball that hit.
     */
    private void notifyHit(Ball hitter) {
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * thais function used to draw the rectangle.
     *
     * @param d DrawSurface to used the function to draw.
     */
    public void drawOn(DrawSurface d) {
        Rectangle rectangle = this.getCollisionRectangle();
        double helperX = block.getUpperLeft().getX();
        double helperY = block.getUpperLeft().getY();
        if (this.filling == null) {
            d.setColor(this.color);
            d.fillRectangle((int) helperX, (int) helperY, (int) this.block.getWidth(), (int) this.block.getHeight());
            return;
        }
        if (this.filling.containsKey(this.hits)) {
            if (this.filling.get(this.hits).color()) {
                d.setColor(this.filling.get(this.hits).getColor());
                d.fillRectangle((int) helperX, (int) helperY, (int) this.block.getWidth(),
                        (int) this.block.getHeight());
            } else if (this.filling.get(this.hits).image()) {
                d.drawImage((int) helperX, (int) helperY, this.filling.get(this.hits).getImage());
            }

        }
        if (!this.filling.containsKey(this.hits)) {
            if (this.filling.get(1).color()) {
                d.setColor(this.filling.get(1).getColor());
                d.fillRectangle((int) helperX, (int) helperY, (int) this.block.getWidth(),
                        (int) this.block.getHeight());
            } else if (this.filling.get(1).image()) {
                d.drawImage((int) helperX, (int) helperY, this.filling.get(1).getImage());
            }
        }
        if (this.stroke != null) {
            //rectangle.drawOnRectangle(this.color, d);
            d.setColor(this.stroke);
            d.drawRectangle((int) helperX, (int) helperY, (int) this.block.getWidth(), (int) this.block.getHeight());
        }
    }
}