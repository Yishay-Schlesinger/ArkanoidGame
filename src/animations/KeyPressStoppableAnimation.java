package animations;


import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The type Key press stoppable animation.
 */
public class KeyPressStoppableAnimation implements Animation {
  private Animation animation;
  private KeyboardSensor keyboard;
  private String key;
  private boolean stop;
  private boolean isAlreadyPressed;

  /**
   * Instantiates a new Key press stoppable animation.
   *
   * @param sensor    the sensor
   * @param key       the key
   * @param animation the animation
   */
  public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
    this.keyboard = sensor;
    this.key = key;
    this.animation = animation;
    this.stop = false;
    this.isAlreadyPressed = true;
  }

  @Override
  public void doOneFrame(DrawSurface d) {
    this.animation.doOneFrame(d);
    //for fixing the bug
    if (this.keyboard.isPressed(this.key)) {
      if (!this.isAlreadyPressed) {
        this.stop = true;
      }
    } else {
      this.isAlreadyPressed = false;
    }
  }

  @Override
  public boolean shouldStop() {
    return this.stop;
  }
}