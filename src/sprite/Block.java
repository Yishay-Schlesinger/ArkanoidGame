package sprite;

import animations.GameLevel;
import biuoop.DrawSurface;
import geomtry.Line;
import geomtry.Point;
import geomtry.Rectangle;
import geomtry.Velocity;
import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import listeners.HitListener;

/**
 * The type Block.
 */
public class Block implements Collidable, Sprite, HitNotifier {
  //The block shape
  private Rectangle rect;
  //The block color
  private Color color;
  //list of listener to notify when there is a hit
  private List<HitListener> hitListeners;

  /**
   * Instantiates a new Block.
   *
   * @param rect  the block shape - rectangle
   * @param color the color of the block
   */
  public Block(Rectangle rect, Color color) {
    this.rect = rect;
    this.color = color;
    this.hitListeners = new LinkedList<>();
  }

  /**
   * Gets rect.
   *
   * @return the block's rectangle
   */
  public Rectangle getRect() {
    return this.rect;
  }

  /**
   * Gets color.
   *
   * @return the block's color
   */
  public Color getColor() {
    return this.color;
  }

  @Override
  public Rectangle getCollisionRectangle() {
    return getRect();
  }

  @Override
  public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
    //notify to all listeners that hit happened
    this.notifyHit(hitter);
    //if the point on one of the horizontal sides of the rectangle, change dy of the velocity.
    if (this.rect.getRecSides()[Rectangle.UPPER_SIDE].isPointOnLine(collisionPoint)
        || this.rect.getRecSides()[Rectangle.DOWN_SIDE].isPointOnLine(collisionPoint)) {
      currentVelocity.setDy(-currentVelocity.getDy());
    }
    //if the point on one of the vertical sides of the rectangle, change dx of the velocity.
    if (this.rect.getRecSides()[Rectangle.RIGHT_SIDE].isPointOnLine(collisionPoint)
        || this.rect.getRecSides()[Rectangle.LEFT_SIDE].isPointOnLine(collisionPoint)) {
      currentVelocity.setDx(-currentVelocity.getDx());
    }
    //return the new velocity
    return currentVelocity;
  }

  @Override
  public void drawOn(DrawSurface d) {
    this.getRect().drawRectangle(d, this.color);
    drawBlockSides(d, Color.BLACK);
  }

  /**
   * Draw block's rectangle sides(lines) on a given surface.
   *
   * @param d the draw surface to draw on.
   * @param c the color to draw with.
   */
  public void drawBlockSides(DrawSurface d, Color c) {
    //draw each line of the rectangle.
    for (Line line : this.getRect().getRecSides()) {
      line.drawLine(d, c);
    }
  }

  @Override
  public void timePassed() {
  }

  /**
   * Add the block to the given game as a sprite and as a collidable.
   *
   * @param g the game to add the block to.
   */
  public void addToGame(GameLevel g) {
    g.addCollidable(this);
    g.addSprite(this);
  }

  /**
   * Remove this block from gameLevel.
   *
   * @param gameLevel the gameLevel to remove this block from.
   */
  public void removeFromGame(GameLevel gameLevel) {
    gameLevel.removeCollidable(this);
    gameLevel.removeSprite(this);
  }

  /**
   * Notify that hit happened.
   *
   * @param hitter the game to remove this block from.
   */
  private void notifyHit(Ball hitter) {
    // Make a copy of the hitListeners before iterating over them.
    List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
    // Notify all listeners about a hit event:
    for (HitListener hl : listeners) {
      hl.hitEvent(this, hitter);
    }
  }

  @Override
  public void addHitListener(HitListener hl) {
    this.hitListeners.add(hl);
  }

  @Override
  public void removeHitListener(HitListener hl) {
    this.hitListeners.remove(hl);
  }
}
