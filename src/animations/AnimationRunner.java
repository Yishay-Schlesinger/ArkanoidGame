// ID: 208438119

package animations;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * The type Animation runner.
 */
public class AnimationRunner {
  private GUI gui;
  private int framesPerSecond;
  private Sleeper sleeper;

  /**
   * Instantiates a new Animation runner.
   *
   * @param gui             the gui of the animation
   * @param framesPerSecond the frames per second
   */
  public AnimationRunner(GUI gui, int framesPerSecond) {
    this.gui = gui;
    this.framesPerSecond = framesPerSecond;
    this.sleeper = new Sleeper();
  }

  /**
   * Run the animation.
   *
   * @param animation the animation to run.
   */
  public void run(Animation animation) {
    int millisecondsPerFrame = 1000 / this.framesPerSecond;
    while (!animation.shouldStop()) {
      long startTime = System.currentTimeMillis(); // timing
      //draw and show all the objects of game on a surface
      DrawSurface d = gui.getDrawSurface();
      animation.doOneFrame(d);
      gui.show(d);
      // timing
      long usedTime = System.currentTimeMillis() - startTime;
      long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
      if (milliSecondLeftToSleep > 0) {
        this.sleeper.sleepFor(milliSecondLeftToSleep);
      }
    }
  }
}

