package animations;

import biuoop.DrawSurface;
import other.Counter;

/**
 * Game Over Screen class - losing screen.
 */
public class GameOverScreen implements Animation {
  private Counter score;


  /**
   * Instantiates a new Pause screen.
   *
   * @param score the score to print
   */
  public GameOverScreen(Counter score) {
    this.score = score;
  }

  @Override
  public void doOneFrame(DrawSurface d) {
    d.drawText(70, d.getHeight() / 2, "Game Over. Your score is " + this.score.getValue(), 50);
  }

  @Override
  public boolean shouldStop() {
    return false;
  }
}
