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
public class ScoreTrackingListener implements HitListener {
  private GameLevel gameLevel;
  private Counter currentScore;


  /**
   * Instantiates a new Score tracking listener.
   *
   * @param gameLevel    the gameLevel
   * @param scoreCounter the score counter
   */
  public ScoreTrackingListener(GameLevel gameLevel, Counter scoreCounter) {
    this.gameLevel = gameLevel;
    this.currentScore = scoreCounter;
  }

  @Override
  public void hitEvent(Block beingHit, Ball hitter) {
    int scoreToAdd = 5;
    //add the score from this hit.
    this.currentScore.increase(scoreToAdd);
    //remove this hl from the block
    beingHit.removeHitListener(this);
  }
}