import java.util.List;
import java.util.ArrayList;

/**
 * the environment class used to define the collidable object.
 */
public class GameEnvironment {
    private List<Collidable> collidables;

    /**
     * created an arrayList of collidables thing.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }


    /**
     * add the given collidable to the environment.
     *
     * @param c represent the collidable.
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);

    }

    /**
     * remove the collidable.
     *
     * @param c remove the object.
     */
    public void removeTheCollidable(Collidable c) {
        this.collidables.remove(c);
    }
    // Assume an object moving from line.start() to line.end().
    // If this object will not collide with any of the collidables
    // in this collection, return null. Else, return the information
    // about the closest collision that is going to occur.

    /**
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     *
     * @param trajectory the line mobe of the ball without obstcales
     * @return list of collisioninfo
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        List<CollisionInfo> collection = new ArrayList<>();
        CollisionInfo close = null;
        for (int i = 0; i < this.collidables.size(); i++) {
            Point thePointThatAreCloset = trajectory.closestIntersectionToStartOfLine(
                    this.collidables.get(i).getCollisionRectangle());
            if (thePointThatAreCloset != null) {
                collection.add(new CollisionInfo(this.collidables.get(i), thePointThatAreCloset));
            }
        }
        if (collection.size() == 0) {
            return null;
        }
        int theIndexThatWeWant = 0;
        double theMinimumDistance = Double.POSITIVE_INFINITY;
        for (int i = 0; i < collection.size(); i++) {
            //for the first time, for sure it will be smaller the Positive Infinity, and we can get the smaller one.
            if (collection.get(i).collisionPoint().distance(trajectory.start()) < theMinimumDistance) {
                theMinimumDistance = collection.get(i).collisionPoint().distance(trajectory.start());
                theIndexThatWeWant = i;
            }
        }
        return collection.get(theIndexThatWeWant);
    }

}