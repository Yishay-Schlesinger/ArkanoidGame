package other;

import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;
import sprite.Sprite;

/**
 * The type Sprite collection.
 */
public class SpriteCollection {
  private List<Sprite> sprites;

  /**
   * Instantiates a new Sprite collection.
   */
  public SpriteCollection() {
    this.sprites = new ArrayList();
  }

  /**
   * Add sprite to the sprites List.
   *
   * @param s the sprite to add
   */
  public void addSprite(Sprite s) {
    this.sprites.add(s);
  }

  /**
   * Notify all sprites time passed.
   */
  public void notifyAllTimePassed() {
    for (Sprite s : this.sprites) {
      s.timePassed();
    }
  }

  /**
   * Draw on the given surface all sprites.
   *
   * @param d the draw surface, to draw all the sprites on.
   */
  public void drawAllOn(DrawSurface d) {
    for (Sprite s : this.sprites) {
      s.drawOn(d);
    }
  }


  /**
   * Gets sprites list.
   *
   * @return the sprites list.
   */
  public List<Sprite> getSpritesList() {
    return sprites;
  }

  /**
   * Remove sprite.
   *
   * @param s the sprite to remove.
   */
  public void remove(Sprite s) {
    // Make a copy of the hitListeners before iterating over them.
    List<Sprite> spriteList = new ArrayList<Sprite>(this.sprites);
    spriteList.remove(s);
    this.sprites = spriteList;
  }
}