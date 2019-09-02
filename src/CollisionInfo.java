/**
 * @author ISSA
 * 21/4/18
 * this class handle the information of the point collision and the collidable object.
 */
public class CollisionInfo {
    private Point theCollisionPoint;
    private Collidable theInvolvedObject;

    /**
     * contructor.
     *
     * @param theInvolvedObject the object that got collided.
     * @param theCollisionPoint the collision point.
     */
    public CollisionInfo(Collidable theInvolvedObject, Point theCollisionPoint) {
        this.theInvolvedObject = theInvolvedObject;
        this.theCollisionPoint = theCollisionPoint;
    }

    /**
     * the point at which the collision occurs.
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.theCollisionPoint;
    }

    /**
     * the collidable object involved in the collision.
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.theInvolvedObject;
    }
}
