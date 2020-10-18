package geomtry;
import java.util.Random;

/**
 * A Point class.
 */
public class Point {
    //small number to check if double==double
    private static final double EPSILON = Math.pow(10, -12);
    /**
     * The x coordinate.
     */
    private double x;
    /**
     * The y coordinate.
     */
    private double y;

    /**
     * Constructor - construct a point by x and y coordinate.
     *
     * @param x the x coordinate.
     * @param y the y coordinate.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Return random point, given max value of x and y.
     *
     * @param max the max value of x and y.
     * @return random point.
     */
    public static Point randomPoint(int max) {
        int min = 0;
        return randomPoint(min, max);
    }

    /**
     * Return random point, given  min and max values of x and y.
     *
     * @param min the min value of x and y.
     * @param max the max value of x and y.
     * @return random point.
     */
    public static Point randomPoint(int min, int max) {
        return randomPoint(min, max, min, max);
    }

    /**
     * Return random point, given min and max values of x and min and max values of y.
     *
     * @param minX the min value of x.
     * @param maxX the max value of x.
     * @param minY the min value of y.
     * @param maxY the max value of y.
     * @return random point.
     */
    public static Point randomPoint(int minX, int maxX, int minY, int maxY) {
        Random rand = new Random(); // create a random-number generator
        int x = rand.nextInt(maxX - minX) + minX + 1; // get integer in range minX - maxX
        int y = rand.nextInt(maxY - minY) + minY + 1; // get integer in range minY - maxY
        return new Point(x, y);
    }

    /**
     * Calculate and return the distance between this point to other given point.
     *
     * @param other the other point to calculate the distance from it to this point.
     * @return the distance between this point to other given one.
     */
    public double distance(Point other) {
        //distance = square root of: (x1-x2)^2+(y1-y2)^2
        return Math.sqrt((this.x - other.x) * (this.x - other.x) + (this.y - other.y) * (this.y - other.y));
    }

    /**
     * Check if this point and other given point are equals.
     *
     * @param other point to check if equal to this point.
     * @return true if this point equals to the other given point, otherwise false.
     */
// equals -- return true is the points are equal, false otherwise
    public boolean equals(Point other) {
        //check if all the fields in this point equal to all the fields in the other given point
        //use Epsilon and no '==' because we compare doubles.
        return Math.abs(this.x - other.x) <= EPSILON && Math.abs(this.y - other.y) <= EPSILON;
    }

    /**
     * Gets x.
     *
     * @return the x coordinate of this point.
     */
    public double getX() {
        return this.x;
    }

    /**
     * Gets y.
     *
     * @return the y coordinate of this point.
     */
    public double getY() {
        return this.y;
    }

    @Override
    public String toString() {
        return "Point{"
                + "x=" + x
                + ", y=" + y
                + '}';
    }
}