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
public class BlockRemover implements HitListener {
  private GameLevel gameLevel;
  private Counter remainingBlocks;

  /**
   * Instantiates a new Block remover.
   *
   * @param gameLevel     the gameLevel of the Block Remover.
   * @param removedBlocks the removed blocks counter.
   */
  public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
    this.gameLevel = gameLevel;
    this.remainingBlocks = removedBlocks;
  }

  // Blocks that are hit should be removed
  // from the gameLevel. Remove this listener from the block
  // that is being removed from the gameLevel.
  @Override
  public void hitEvent(Block beingHit, Ball hitter) {
    //remove this listener from the block that is being removed from the gameLevel.
    beingHit.removeHitListener(this);
    //remove block from the gameLevel.
    beingHit.removeFromGame(this.gameLevel);
    //set the counter after removing
    this.remainingBlocks.decrease(1);
  }
}