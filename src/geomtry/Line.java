package geomtry;

import biuoop.DrawSurface;
import java.awt.Color;
import java.util.List;

/**
 * A Line class.
 */
public class Line {
  //small number to check if double==double
  private static final double EPSILON = Math.pow(10, -12);
  /**
   * Start point of the line.
   */
  private Point start;
  /**
   * End point of the line.
   */
  private Point end;
  /**
   * m = the line's slope.(y=mx+b).
   */
  private double m;
  /**
   * b = y value when x=0.(y=mx+b).
   */
  private double b;

//constructors

  /**
   * Instantiates a new Line, by given 2 points.
   *
   * @param start the start point of the line.
   * @param end   the end point of the line.
   */
  public Line(Point start, Point end) {
    //sending the given points' coordinates to the other constructor
    this(start.getX(), start.getY(), end.getX(), end.getY());
  }

  /**
   * Instantiates a new Line, by given 4 coordinates.
   *
   * @param x1 the x coordinate of the start point of the line.
   * @param y1 the y coordinate of the start point of the line.
   * @param x2 the x coordinate of the end point of the line.
   * @param y2 the y coordinate of the end point of the line.
   */
  public Line(double x1, double y1, double x2, double y2) {
    //define this line points, start and end.
    this.start = new Point(x1, y1);
    this.end = new Point(x2, y2);
    // m=(y2-y1)/(x2-x1), so if x2-x1==0, it's error.
    // so we need to define this situation, it's x equation
    if (x2 == x1) {
      this.m = 0;
      this.b = 0;
    } else {
      //define the line's slope=m, m=(y2-y1)/(x2-x1)
      this.m = (y2 - y1) / (x2 - x1);
      //define b, b=y-mx=y1-mx1
      this.b = y1 - (this.m * x1);
    }
  }

  /**
   * Return random line given x and y max range.
   * <p>
   * Using 'Point.randomPoint'(static function from Point),to get 2 random point. define min as 0.
   * </p>
   *
   * @param maxX the max range of x
   * @param maxY the max range of y
   * @return new random line.
   */
  public static Line generateRandomLine(int maxX, int maxY) {
    //define both of min as 0
    int minX = 0;
    int minY = 0;
    return new Line(Point.randomPoint(minX, maxX, minY, maxY),
        Point.randomPoint(minX, maxX, minY, maxY));
  }

  /**
   * Return line's length by calculate distance between the 2 line's point.
   *
   * @return the length of the line.
   */
  public double length() {
    //return the distance between this line's start point to this line's end point.
    return this.start.distance(this.end);
  }

  /**
   * Calculate and return the middle point on the line.
   *
   * @return the middle point of the line.
   */
// Returns the middle point of the line
  public Point middle() {
    // calculate the middle between x coordinates of line's  points start and end.
    double middleOfx = (this.start.getX() + this.end.getX()) / 2;
    // calculate the middle between y coordinates of line's  points start and end.
    double middleOfy = (this.start.getY() + this.end.getY()) / 2;
    //define the middle point by the middles coordinates of x and y that the program just calculate.
    Point middle = new Point(middleOfx, middleOfy);
    return middle;
  }

  /**
   * Start point.
   *
   * @return the line's start point.
   */
// Returns the start point of the line
  public Point start() {
    return this.start;
  }

  /**
   * End point.
   *
   * @return the line's end point.
   */
// Returns the end point of the line
  public Point end() {
    return this.end;
  }

  /**
   * Gets slope.
   *
   * @return the slope
   */
  public double getSlope() {
    return this.m;
  }

  /**
   * Gets b from line equation.
   *
   * @return the b from line equation
   */
  public double getBFromLineEquation() {
    return this.b;
  }

  /**
   * Calculate and return the intersection point between this line and other given line.
   * <p>
   * If this func has been called by other func it says that the lines' slope=m are not equals,
   * and it says the the lines are not parallels to each other, so they have intersection point.
   * To calculate the intersection point the func using the line equation: y=mx+b.
   * </p>
   *
   * @param other the other line to find the intersection point to.
   * @return the intersection point of this line and the other given line.
   */
  public Point intersectPoint(Line other) {
    //Point to return
    Point intersect = null;
    if (this.start.getX() == this.end.getX()) { //if this line is x equation,this:x=x1,
      // other:y=m2x+b2 -> y=m1x1+b1
      double xIntersect = this.start.getX();
      double yIntersect = (other.m * xIntersect) + other.b;
      intersect = new Point(Math.abs(xIntersect), yIntersect);
    } else if (other.m == 0) { // if the other line is x/y equation
      intersect = other.intersectPoint(this);
    } else { //this:y=m1x+b1,other:y=m2x+b2 -> m1x+b1=m2x+b2 -> m1x-m2x=b2-b1 ->
      // x(m1-m2)=b2-b1 -> x=(b2-b1)/(m1-m2)
      double xIntersect = (other.b - this.b) / (this.m - other.m);
      //y=m1(xIntersect)+b1
      double yIntersect = (this.m * xIntersect) + this.b;
      //define and return the intersection point.
      intersect = new Point(Math.abs(xIntersect), yIntersect);
    }
    return intersect;
  }

  /**
   * Check if this line and other given line have the same slope=m.
   *
   * @param other line to check it's slope=m.
   * @return true if the line's slopes are equals, otherwise false.
   */
  private boolean isSameSlope(Line other) {
    //if slope in both of the lines=0, check if both of line are not only X equation , or only Y.
    if (this.m == 0 && other.m == 0) {
      //if they are not the same(both of them only Y/ only X),
      // return false - they doesn't have the same slope
      //both of the are the same (only Y/ only X), return true- the have the same slope
      return (this.start.getX() != this.end.getX() || other.start.getY() != other.end.getY())
          && (this.start.getY() != this.end.getY() || other.start.getX() != other.end.getX());
    }
    //return if parallel or the same line or merge to one line in some point.
    return Math.abs(this.m - other.m) <= EPSILON;
  }

  /**
   * Check if given point is in this line's range.
   * <p>
   * If this func has been called by other func it says that this point holds the line's equations.
   * NOTE: this func by itself doesn't says that the given point is on the line,
   * it just says that the x and y coordinates of the given point are in
   * the range of the x and y this line's point's coordinates.
   * </p>
   *
   * @param check the point to check if it's in the line's range.
   * @return true, if the given point is in line's range, otherwise false.
   */
  public boolean isPointInThisLineRange(Point check) {
    //check if xIntersect in the range of this.line x values
    double xMin = Math.min(this.start.getX(), this.end.getX());
    double xMax = Math.max(this.start.getX(), this.end.getX());
    // compares doubles, so add/decrement EPSILON to enlarge the valid range
    if (check.getX() < xMin - EPSILON || check.getX() - EPSILON > xMax) {
      return false;
    }
    //check if xIntersect in the range of this.line y values
    double yMin = Math.min(this.start.getY(), this.end.getY());
    double yMax = Math.max(this.start.getY(), this.end.getY());
    //return if in the y range
    //because the x range has already been checked,
    //so if it in y range it's in this line's range.
    // compares doubles, so add/decrement EPSILON to enlarge the valid range
    return (!(check.getY() < yMin - EPSILON)) && (!(check.getY() - EPSILON > yMax));
  }

  /**
   * Check if this line and other given line have intersection point.
   *
   * @param other the other line to check if their is intersection point to.
   * @return true, if there is intersection point, otherwise false.
   */
// Returns true if the lines intersect, false otherwise
  public boolean isIntersecting(Line other) {
    //if parallel or the same line or merge to one line --> return false;
    if (isSameSlope(other)) {
      return false;
    }
    //there is a intersection point, so define it by 'intersectPoint' func
    Point intersect = intersectPoint(other);
    //return if intersect point is in the range of this.line x and y values of both of the lines
    //if its true there is intersection point and its in this lines' range,
    //so there is intersection point
    return (isPointInThisLineRange(intersect) && other.isPointInThisLineRange(intersect));
  }

  /**
   * Check if there is intersection point to this line and to other given line, and return it.
   *
   * @param other the other line to check if their is intersection point to.
   * @return if there is intersection point return it, otherwise return null
   */
// Returns the intersection point if the lines intersect,
  // and null otherwise.
  public Point intersectionWith(Line other) {
    //check if this line and other given line are intersecting
    if (isIntersecting(other)) {
      //intersecting -> return the intersection point
      return intersectPoint(other);
    }
    //does not intersecting -> return null
    return null;
  }

  /**
   * Check if this line and other given line are equals.
   *
   * @param other the other given line to check if equals to this line.
   * @return true if equals, otherwise return false.
   */
  public boolean equals(Line other) {
    //compare all both lines fields, and check if equals
    //check if starts point are equals
    if (this.start.equals(other.start)) {
      //check if end point are equals
      if (this.end.equals(other.end)) {
        //check if m and b equals --> if true, lines equals
        return this.m == other.m && this.b == other.b;
      }
    }
    //lines does not equals.
    return false;
  }

  /**
   * If this line does not intersect with the rectangle, return null.
   * Otherwise, return the closest intersection point to the
   * start of the line. using 'closestIntersectionToStartOfLineFromList' func.
   *
   * @param rect the rectangle to get the closest intersection point To start of line
   * @return 'closestIntersectionToStartOfLineFromList' func,
   * that return the closest intersection point to the rectangle.
   */
  public Point closestIntersectionToStartOfLine(Rectangle rect) {
    // get list of the line intersection points with the rectangle
    java.util.List<Point> list = rect.intersectionPoints(this);
    //return the closest point or null
    return closestIntersectionToStartOfLineFromList(list);
  }

  /**
   * Closest intersection to start of line from list point.
   *
   * @param list the list of the intersection point, to check which one is the closest to the line.
   * @return the closest point to line, or null if the list is empty
   */
  public Point closestIntersectionToStartOfLineFromList(List<Point> list) {
    if (list.isEmpty()) {
      return null;
    } else {
      // set the return point to the first point in the list
      Point closestIntersectPoint = list.get(0);
      // the min distance from the start point of this line to intersect point
      double minDistance = this.start.distance(list.get(0));
      //find the closest intersection point to the line.
      for (int i = 1; i < list.size(); i++) {
        // distance between this intersection point to the line
        double currentDistance = this.start.distance(list.get(i));
        //if this intersection point closer to line,
        // change the values minDistance and closestIntersectPoint,
        //to the value of this intersection point.
        if (minDistance > currentDistance) {
          minDistance = currentDistance;
          closestIntersectPoint = list.get(i);
        }
      }
      // return the closet intersection point of this line
      return closestIntersectPoint;
    }
  }

  /**
   * Is point on line boolean.
   *
   * @param p the point to check if on the line
   * @return true - if the point on the line, false otherwise.
   */
  public boolean isPointOnLine(Point p) {
    //return if the point values uphold the line's equation
    if (this.start.getX() == this.end.getX()) { //if the line is x equation
      return p.getX() == this.start.getX();
    } else { //if the line is y/regular line equation
      return p.getY() == this.m * p.getX() + this.b;
    }
  }

  /**
   * Draw line.
   *
   * @param d     the draw surface to draw on the line.
   * @param color the color to draw the line with.
   */
  public void drawLine(DrawSurface d, Color color) {
    d.setColor(color);
    d.drawLine((int) this.start().getX(), (int) this.start().getY(),
        (int) this.end().getX(), (int) this.end().getY());
  }

  @Override
  public String toString() {
    return "Line{"
        + "start=" + start
        + ", end=" + end
        + ", m=" + m
        + ", b=" + b
        + '}';
  }
}