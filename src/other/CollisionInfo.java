package other;

import geomtry.Point;
import sprite.Collidable;

/**
 * The type Collision info.
 */
public class CollisionInfo {
  // the point at which the collision occurs.
  private Point collisionPoint;

  // the collidable object involved in the collision.
  private Collidable collisionObject;

  /**
   * Instantiates a new Collision info.
   *
   * @param collisionPoint  the collision point
   * @param collisionObject the collision object
   */
  public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
    this.collisionPoint = collisionPoint;
    this.collisionObject = collisionObject;
  }

  /**
   * Collision point point.
   *
   * @return // the point at which the collision occurs.
   */
  public Point collisionPoint() {
    return this.collisionPoint;
  }

  /**
   * Gets collision object.
   *
   * @return the collidable object involved in the collision
   */
  public Collidable collisionObject() {
    return collisionObject;
  }
}
