package other;

import geomtry.Line;
import geomtry.Point;
import java.util.ArrayList;
import java.util.List;
import sprite.Collidable;


/**
 * The type GameLevel environment.
 */
public class GameEnvironment {
  private List<Collidable> collidables;

    /**
     * Construct new game environment.
     */
    public GameEnvironment() {
    this.collidables = new ArrayList();
  }

    /**
     * add the given collidable to the environment.
     *
     * @param c the collidable to add
     */
    public void addCollidable(Collidable c) {
    collidables.add(c);
  }

    /**
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     *
     * @param trajectory the trajectory line, to check which collision point is the closest to.
     * @return the closest collision point to the given line, or null if there is not a collision point.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
    //the collisionInfo to return
    CollisionInfo collisionInfo = null;
    //list of two closest collision points to send to other func, for checking which one is the closest
    List<Point> collisionPoints = new ArrayList();
    //each loop check which point in collision points list is the closest,
    //if its new point, change collisionInfo to the new closest point and this collidable(in the loop)
    for (Collidable collid : collidables) {
      //the closest point from this specific collidable from the list
      Point currentClosestPoint =
          trajectory.closestIntersectionToStartOfLine(collid.getCollisionRectangle());
      if (currentClosestPoint != null) {
        //the first closet point, initiate collisionInfo
        if (collisionInfo == null) {
          collisionInfo = new CollisionInfo(currentClosestPoint, collid);
        } else {
          //check all the points, and change collisionInfo if needed
          collisionPoints.add(currentClosestPoint);
          collisionPoints.add(collisionInfo.collisionPoint());
          //the closest point to trajectory line
          Point closestPoint = trajectory.closestIntersectionToStartOfLineFromList(collisionPoints);
          if (!collisionInfo.collisionPoint().equals(closestPoint)) {
            collisionInfo = new CollisionInfo(closestPoint, collid);
          }
          //for good complexity, it cause that each loop the func sends only 2 object in the list,
          //to 'closestIntersectionToStartOfLineFromList' func. so it's going to take O(n) and not O(n^2).
          collisionPoints.clear();
        }
      }
    }
    //return the collisionInfo point
    return collisionInfo;
  }

    /**
     * Remove collidable.
     *
     * @param c the collidable to remove.
     */
    public void removeCollidable(Collidable c) {
    this.collidables.remove(c);
  }
}