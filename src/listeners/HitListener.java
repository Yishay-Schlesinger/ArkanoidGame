package listeners;

import sprite.Ball;
import sprite.Block;

/**
 * The interface Hit listener.
 */
public interface HitListener {
  /**
   * This method is called whenever the beingHit object is hit.
   * The hitter parameter is the Ball that's doing the hitting.
   *
   * @param beingHit the being hit block
   * @param hitter   the hitter ball
   */
  void hitEvent(Block beingHit, Ball hitter);
}
