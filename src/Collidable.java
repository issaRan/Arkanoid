/**
 * @author ISSA
 * 21/4/18
 * interface of the the object that we can collida with.
 */
public interface Collidable {
    // Return the "collision shape" of the object.

    /**
     * get the collision of the rectangle.
     * @return "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * function used to treat the hit after it's had collision with a rectangle.
     * @param hitter the ball that hit.
     * @param collisionPoint the collision point with the rectangle.
     * @param currentVelocity the velocity that the ball got when it's hit the rectangle.
     * @return the new velocity, after changed the direction.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

}
