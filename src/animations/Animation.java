package animations;

import biuoop.DrawSurface;

/**
 * The interface Animation.
 */
public interface Animation {
  /**
   * Do one frame.
   *
   * @param d the draw surface to show one frame of.
   */
  void doOneFrame(DrawSurface d);

  /**
   * @return true- if the program should stop. Otherwise - return false.
   */
  boolean shouldStop();
}
