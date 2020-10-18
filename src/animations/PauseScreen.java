package animations;

import biuoop.DrawSurface;

/**
 * Pause screen class.
 */
public class PauseScreen implements Animation {

  @Override
  public void doOneFrame(DrawSurface d) {
    d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
  }

  @Override
  public boolean shouldStop() {
    return false;
  }
}