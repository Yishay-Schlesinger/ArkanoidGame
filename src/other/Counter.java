package other;

/**
 * The type Counter.
 */
public class Counter {
  private int counter;

  /**
   * Instantiates a new Counter.
   *
   * @param counter the counter
   */
  public Counter(int counter) {
    this.counter = counter;
  }

  /**
   * Add number to current count.
   *
   * @param number the number
   */
  public void increase(int number) {
    this.counter += number;
  }

  /**
   * Decrease number from current count.
   *
   * @param number the number to decrease.
   */
  public void decrease(int number) {
    this.counter -= number;
  }

  /**
   * Gets value.
   *
   * @return the value of the counter.
   */
  public int getValue() {
    return this.counter;
  }
}
