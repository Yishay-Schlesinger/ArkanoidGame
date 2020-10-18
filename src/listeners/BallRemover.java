package listeners;

import animations.GameLevel;
import other.Counter;
import sprite.Ball;
import sprite.Block;

/**
 * The type Block remover.
 */
// a BlockRemover is in charge of removing blocks from the gameLevel, as well as keeping count
// of the number of blocks that remain.
public class BallRemover implements HitListener {
  private GameLevel gameLevel;
  private Counter remainingBalls;

  /**
   * Instantiates a new Ball remover.
   *
   * @param gameLevel    the gameLevel of the Ball Remover.
   * @param removedBalls the removed Ball counter.
   */
  public BallRemover(GameLevel gameLevel, Counter removedBalls) {
    this.gameLevel = gameLevel;
    this.remainingBalls = removedBalls;
  }

  @Override
  public void hitEvent(Block beingHit, Ball hitter) {
    //remove ball from the gameLevel.
    hitter.removeFromGame(this.gameLevel);
    //set the counter after removing
    this.remainingBalls.decrease(1);
  }
}