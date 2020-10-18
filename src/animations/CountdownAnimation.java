package animations;

import biuoop.DrawSurface;
import java.awt.Color;
import other.SpriteCollection;

/**
 * The type Countdown animation.
 */
// The CountdownAnimation will display the given gameScreen,
// for numOfSeconds seconds, and on top of them it will show
// a countdown from countFrom back to 1, where each number will
// appear on the screen for (numOfSeconds / countFrom) seconds, before
// it is replaced with the next one.
public class CountdownAnimation implements Animation {
  //numbers of frames per digit in countdown
  private double numOfFrames;
  private int framesCounter;
  //the number to countdown from
  private int countdown;
  //all the game level sprite to print before prints this animation
  private SpriteCollection gameScreen;
  //stop/continue with this animation
  private boolean stop;


  /**
   * Instantiates a new Countdown animation.
   *
   * @param numOfSeconds the num of seconds of counting.
   * @param countFrom    the count from.
   * @param gameScreen   the game screen sprites to draw.
   */
  public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
    //frames per digit(every second there is 60 frames)
    this.numOfFrames = (numOfSeconds / countFrom) * 60;
    this.framesCounter = 0;
    this.countdown = countFrom;
    this.gameScreen = gameScreen;
    this.stop = false;
  }

  @Override
  public void doOneFrame(DrawSurface d) {
    this.framesCounter++;
    //draw all the sprites of the game level
    this.gameScreen.drawAllOn(d);
    //change the countdown if enough frames were counted
    if (this.numOfFrames < this.framesCounter) {
      this.countdown--;
      //if 0 the animation should stop
      if (this.countdown == 0) {
        this.stop = true;
      }
      this.framesCounter = 0;
    }
    //print the count down on the screen
    d.setColor(Color.RED);
    d.drawText(GameLevel.GUI_WIDTH / 2 - 30, GameLevel.GUI_HEIGHT * 2 / 3 + 10, "" + this.countdown,
        100);
  }


  @Override
  public boolean shouldStop() {
    return this.stop;
  }
}