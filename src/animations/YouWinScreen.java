package animations;

import biuoop.DrawSurface;
import other.Counter;

/**
 * You Win screen class.
 */
public class YouWinScreen implements Animation {
  private Counter score;


  /**
   * Instantiates a new Pause screen.
   *
   * @param score the score to print
   */
  public YouWinScreen(Counter score) {
    this.score = score;

  }

  @Override
  public void doOneFrame(DrawSurface d) {
    d.drawText(70, d.getHeight() / 2, "You Win! Your score is " + this.score.getValue(), 50);
  }

  @Override
  public boolean shouldStop() {
    return false;
  }
}

