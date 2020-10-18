package sprite;

import geomtry.Rectangle;
import java.awt.Color;
import other.Counter;

/**
 * The type Score indicator.
 */
public class ScoreIndicator extends Block {
  private Counter scoreCounter;

  /**
   * Instantiates a new Block.
   *
   * @param rect         the block shape - rectangle
   * @param color        the color of the block
   * @param scoreCounter the score counter
   */
  public ScoreIndicator(Rectangle rect, Color color, Counter scoreCounter) {
    super(rect, color);
    this.scoreCounter = scoreCounter;
  }

  @Override
  public void drawOn(biuoop.DrawSurface d) {
    super.drawOn(d);
    d.setColor(Color.BLACK);
    d.drawText(150, 20, "SCORE:  " + this.scoreCounter.getValue(), 20);
  }
}
