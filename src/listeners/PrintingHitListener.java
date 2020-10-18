package listeners;

import sprite.Ball;
import sprite.Block;

/**
 * Hit listener that prints a hit massage.
 */
public class PrintingHitListener implements HitListener {
  @Override
  public void hitEvent(Block beingHit, Ball hitter) {
    System.out.println("A Block was hit.");
  }
}