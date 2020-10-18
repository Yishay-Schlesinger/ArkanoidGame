package sprite;

import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The type Name level writer.
 */
public class NameLevelWriter implements Sprite {
  private String levelName;

  /**
   * Instantiates a new Name level writer.
   *
   * @param levelName the level name
   */
  public NameLevelWriter(String levelName) {
    this.levelName = levelName;
  }

  @Override
  public void drawOn(DrawSurface d) {
    d.setColor(Color.BLACK);
    d.drawText(500, 20, "Level Name: " + levelName, 20);
  }

  @Override
  public void timePassed() {
  }
}
