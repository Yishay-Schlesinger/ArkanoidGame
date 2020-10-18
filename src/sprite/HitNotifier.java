package sprite;

import listeners.HitListener;

/**
 * The interface Hit notifier.
 */
public interface HitNotifier {
  /**
   * Add hl as a listener to hit events.
   *
   * @param hl the hit listener to add.
   */
  void addHitListener(HitListener hl);

  /**
   * Remove hl from the list of listeners to hit events.
   *
   * @param hl the hit listener to remove.
   */
  void removeHitListener(HitListener hl);
}