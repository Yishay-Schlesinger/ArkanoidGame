package sprite;

import animations.GameLevel;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import geomtry.Point;
import geomtry.Rectangle;
import geomtry.Velocity;
import java.awt.Color;

/**
 * The type Paddle.
 */
public class Paddle implements Sprite, Collidable {
  /**
   * The paddle block.
   */
  private Block block;
  //the paddle Keyboard Sensor
  private biuoop.KeyboardSensor keyboard;
  //speed of the paddle
  private int paddleSpeed;

  /**
   * Instantiates a new Paddle.
   *
   * @param keyboardSensor the keyboard sensor.
   * @param rectangle      the rectangle of the paddle's block.
   * @param color          the paddle color.
   * @param speed          the speed
   */
  public Paddle(KeyboardSensor keyboardSensor, Rectangle rectangle, Color color, int speed) {
    this.keyboard = keyboardSensor;
    this.block = new Block(rectangle, color);
    this.paddleSpeed = speed;
  }

  /**
   * Move the paddle left.
   */
  public void moveLeft() {
    //set new rectangle for the paddle's block after moving it to the left
    double x = this.block.getRect().getUpperLeft().getX() - this.paddleSpeed;
    double y = this.block.getRect().getUpperLeft().getY();
    Rectangle left = this.block.getRect().getRectangleForNewUpperLeft(new Point(x, y));
    //if the new rectangle, after moving left, is in the valid range -> change the paddle location
    if (left.getUpperLeft().getX() >= GameLevel.FRAME_WIDTH) {
      this.block = new Block(left, this.block.getColor());
    } else {
      left = left.getRectangleForNewUpperLeft(new Point(GameLevel.FRAME_WIDTH, y));
      this.block = new Block(left, this.block.getColor());
    }
  }

  /**
   * Move right.
   */
  public void moveRight() {
    //set new rectangle for the paddle's block after moving it to the left
    double x = this.block.getRect().getUpperLeft().getX() + this.paddleSpeed;
    double y = this.block.getRect().getUpperLeft().getY();
    Rectangle right = this.block.getRect().getRectangleForNewUpperLeft(new Point(x, y));
    //if the new rectangle, after moving right, is in the valid range -> change the paddle location.
    if (right.getUpperLeft().getX() + right.getWidth()
        <= GameLevel.GUI_WIDTH - GameLevel.FRAME_WIDTH) {
      this.block = new Block(right, this.block.getColor());
    } else {
      right = right
          .getRectangleForNewUpperLeft(new Point(
              GameLevel.GUI_WIDTH - GameLevel.FRAME_WIDTH - this.block.getRect().getWidth(), y));
      this.block = new Block(right, this.block.getColor());
    }
  }

  // Sprite
  @Override
  public void timePassed() {
    if (this.keyboard.isPressed(keyboard.LEFT_KEY)) {
      moveLeft();
    }
    if (this.keyboard.isPressed(keyboard.RIGHT_KEY)) {
      moveRight();
    }
  }

  /**
   * Draw the sprite to the screen.
   *
   * @param d the surface to draw the sprite on.
   */
  @Override
  public void drawOn(DrawSurface d) {
    this.block.drawOn(d);
  }

  // Collidable
  @Override
  public Rectangle getCollisionRectangle() {
    return this.block.getCollisionRectangle();
  }

  @Override
  public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
    if (this.block.getRect().getRecSides()[Rectangle.UPPER_SIDE].isPointOnLine(collisionPoint)) {
      return verticalHit(collisionPoint, currentVelocity);
    }
    return currentVelocity;
  }

  /**
   * Vertical hit velocity.
   *
   * @param collisionPoint  the collision point
   * @param currentVelocity the current velocity
   * @return the velocity
   */
  public Velocity verticalHit(Point collisionPoint, Velocity currentVelocity) {
    //hit location on the block
    double hitLocation = collisionPoint.getX() - this.block.getRect().getUpperLeft().getX();
    //the speed of the object that collide with this collidable, before the hit.
    double currentSpeed =
        Math.sqrt(Math.pow(currentVelocity.getDx(), 2) + Math.pow(currentVelocity.getDy(), 2));
    //size of each area(5 areas,divide by five)
    double areaSize = this.block.getRect().getWidth() / 5.0;
    if (hitLocation >= 0 && hitLocation < areaSize) { //hit area 1
      currentVelocity = Velocity.fromAngleAndSpeed(300, currentSpeed);
    } else if (hitLocation >= areaSize && hitLocation < areaSize * 2) { //hit area 2
      currentVelocity = Velocity.fromAngleAndSpeed(330, currentSpeed);
    } else if (hitLocation >= areaSize * 2 && hitLocation < areaSize * 3) { //hit area 3
      currentVelocity.setDy(-currentVelocity.getDy());
    } else if (hitLocation >= areaSize * 3 && hitLocation < areaSize * 4) { //hit area 4
      currentVelocity = Velocity.fromAngleAndSpeed(30, currentSpeed);
    } else { //hit area 5
      currentVelocity = Velocity.fromAngleAndSpeed(60, currentSpeed);
    }
    return currentVelocity;
  }

  /**
   * Add to game.
   *
   * @param g the g
   */
// Add this paddle to the game.
  public void addToGame(GameLevel g) {
    g.addCollidable(this);
    g.addSprite(this);
  }
}