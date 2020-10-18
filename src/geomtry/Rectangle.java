package geomtry;

import biuoop.DrawSurface;
import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 * Rectangle class.
 */
public class Rectangle {
    /**
     * The constant UPPERSIDE.
     */
//rectangle sides lines
  //set static final constant for each side
  public static final int UPPER_SIDE = 0;
    /**
     * The constant DOWNSIDE.
     */
    public static final int DOWN_SIDE = 1;
    /**
     * The constant RIGHTSIDE.
     */
    public static final int RIGHT_SIDE = 2;
    /**
     * The constant LEFTSIDE.
     */
    public static final int LEFT_SIDE = 3;
  //start point of the rectangle
  private Point upperLeft;
  //rectangle width
  private double width;
  //rectangle height
  private double height;
  //rectangle sides lines array
  private Line[] recSides = new Line[4];

    /**
     * Instantiates a new Rectangle.
     *
     * @param upperLeft the rectangle upper left point
     * @param width     the rectangle width
     * @param height    the rectangle height
     */
    public Rectangle(Point upperLeft, double width, double height) {
    this.upperLeft = upperLeft;
    this.width = width;
    this.height = height;
    //define points that help to set the rectangle sides lines
    Point upperRight = new Point(upperLeft.getX() + width, upperLeft.getY());
    Point downLeft = new Point(upperLeft.getX(), upperLeft.getY() + height);
    Point downRight = new Point(upperRight.getX(), downLeft.getY());
    //set the rectangle sides lines in the array by the const sides names
    this.recSides[UPPER_SIDE] = new Line(upperLeft, upperRight);
    this.recSides[DOWN_SIDE] = new Line(downLeft, downRight);
    this.recSides[RIGHT_SIDE] = new Line(upperRight, downRight);
    this.recSides[LEFT_SIDE] = new Line(upperLeft, downLeft);
  }

    /**
     * Return a (possibly empty) List of intersection points
     * with the specified line.
     *
     * @param line the line
     * @return the java . util . list
     */
// Return a (possibly empty) List of intersection points
  // with the specified line.
  public java.util.List<Point> intersectionPoints(Line line) {
    List list = new LinkedList();
    for (Line l : this.recSides) {
      if (l.isIntersecting(line)) {
        list.add(line.intersectionWith(l));
      }
    }
    return list;
  }

    /**
     * Gets width.
     *
     * @return the width rectangle.
     */
    public double getWidth() {
    return this.width;
  }

    /**
     * Gets height.
     *
     * @return the rectangle height.
     */
    public double getHeight() {
    return this.height;
  }

    /**
     * Gets upper left.
     *
     * @return the upper left point of the rectangle.
     */
    public Point getUpperLeft() {
    return upperLeft;
  }

    /**
     * Get rec sides line [ ].
     *
     * @return the rectangle sides lines array.
     */
    public Line[] getRecSides() {
    return recSides;
  }

    /**
     * Gets rectangle for new upper left with the other same values of this rectangle.
     *
     * @param upLeft the upper left
     * @return the rectangle for new upper left
     */
    public Rectangle getRectangleForNewUpperLeft(Point upLeft) {
    return new Rectangle(upLeft, this.width, this.height);
  }


    /**
     * Is point in rectangle boolean.
     *
     * @param point the point to check if it is in the rectangle.
     * @return true - the point in the rectangle, false- otherwise.
     */
    public boolean isPointInRectangle(Point point) {
    //if point in the range of vertical and horizonal line's of the rectangle, the point is in the rectangle.
    //the point is not in the rectangle
    return this.recSides[UPPER_SIDE].isPointInThisLineRange(point)
        && (this.recSides[RIGHT_SIDE].isPointInThisLineRange(point));
  }

    /**
     * Draw this rectangle on given draw surface.
     *
     * @param d     the surface to draw on.
     * @param color the color to draw the rectangle with.
     */
    public void drawRectangle(DrawSurface d, Color color) {
    //get the all the needed values from the rectangle to draw the block
    int x = (int) this.getUpperLeft().getX();
    int y = (int) this.getUpperLeft().getY();
    int recWidth = (int) this.getWidth();
    int recHeight = (int) this.getHeight();
    d.setColor(color);
    //draw the rectangle
    d.fillRectangle(x, y, recWidth, recHeight);
  }
}
