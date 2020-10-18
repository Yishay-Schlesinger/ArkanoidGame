package geomtry;

/**
 * The type Velocity.
 */
public class Velocity {
  /**
   * The dx of the velocity.
   */
  private double dx;
  /**
   * The dy of the velocity.
   */
  private double dy;

    /**
     * Instantiates a new Velocity.
     *
     * @param dx the dx of the velocity.
     * @param dy the dy of the velocity.
     */
// constructor
  public Velocity(double dx, double dy) {
    this.dx = dx;
    this.dy = dy;
  }

    /**
     * Return velocity from angle and speed.
     *
     * @param angle the angle of the velocity.
     * @param speed the speed of the velocity.
     * @return the velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
    //change angle to radians because Math.sin / Math.cos need to get angle in radians.
    angle = Math.toRadians(angle);
    // calculate by trigonometry rolls on a right triangle, when the speed is the hypotenuse of the triangle.
    double dx = speed * Math.sin(angle);
    double dy = -speed * Math.cos(angle);
    // return new velocity that calculated by the speed and the angle.
    return new Velocity(dx, dy);
  }

    /**
     * Gets dx.
     *
     * @return the dx velocity.
     */
    public double getDx() {
    return this.dx;
  }

    /**
     * Sets dx.
     *
     * @param dxValue sets the dx of the velocity.
     */
    public void setDx(double dxValue) {
    this.dx = dxValue;
  }

    /**
     * Gets dy.
     *
     * @return the dy
     */
    public double getDy() {
    return this.dy;
  }

    /**
     * Sets dy.
     *
     * @param dyValue the dy
     */
    public void setDy(double dyValue) {
    this.dy = dyValue;
  }

    /**
     * Take a point with position (x,y) and return a new point, with position (x+dx, y+dy).
     *
     * @param p the p
     * @return the point
     */
    public Point applyToPoint(Point p) {
    //add the velocity values to the point x and y.
    double xCoordinate = p.getX() + this.dx;
    double yCoordinate = p.getY() + this.dy;
    //return new point after added the velocity
    return new Point(xCoordinate, yCoordinate);
  }
}