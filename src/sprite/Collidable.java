package sprite;

import geomtry.Point;
import geomtry.Rectangle;
import geomtry.Velocity;

/**
 * The interface Collidable- object that can collide with other object.
 */
public interface Collidable {
    /**
     * Gets collision rectangle.
     *
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * The return is the new velocity expected after the hit
     * (based on the force the object inflicted on us).
     *
     * @param hitter          the hitter ball
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity before the hit
     * @return the new velocity expected after the hit.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
