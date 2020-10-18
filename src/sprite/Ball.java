package sprite;

import animations.GameLevel;
import biuoop.DrawSurface;
import geomtry.Line;
import geomtry.Point;
import geomtry.Velocity;
import java.awt.Color;
import java.awt.Rectangle;
import other.CollisionInfo;
import other.GameEnvironment;

/**
 * Ball class.
 */
public class Ball implements Sprite {
  /**
   * The ball's game environment.
   */
  private GameEnvironment gameEnvironment;
  /**
   * The Center point.
   */
  private Point center;
  /**
   * The ball's color.
   */
  private java.awt.Color color;
  /**
   * The ball's radius.
   */
  private double radius;
  /**
   * The ball's velocity.
   */
  private Velocity velocity;

// constructor

  /**
   * Instantiates a new Ball.
   *
   * @param x      the x coordination of  the ball's center point.
   * @param y      the y coordination of center point.
   * @param radius the radius of the ball.
   * @param color  the color of the ball.
   */
  public Ball(double x, double y, double radius, java.awt.Color color) {
    this.center = new Point(x, y);
    this.color = color;
    this.radius = radius;
  }

  /**
   * Instantiates a new Ball.
   *
   * @param center the center point of the ball.
   * @param radius the radius of the ball.
   * @param color  the color of the ball.
   */
  public Ball(Point center, double radius, java.awt.Color color) {
    this(center.getX(), center.getY(), radius, color);
  }

  /**
   * Calculate ball speed by the ball radius size.
   *
   * @return ball's speed.
   */
  private double calculateBallSpeed() {
    if (this.radius > 50) {
      //if radius> 50, speed is the slowest=0.5
      return 0.5;
    } else {
      //if 50 > radius, speed between 5.6 to 0.6
      return 5.6 - this.radius * 0.1;
    }
  }

  /**
   * Gets x.
   *
   * @return the x coordination of ball's center point.
   */
// accessors
  public int getX() {
    return (int) this.center.getX();
  }

  /**
   * Gets y.
   *
   * @return the y coordination of ball's center point.
   */
  public int getY() {
    return (int) this.center.getY();
  }

  /**
   * Gets size.
   *
   * @return the ball's radius.
   */
  public int getSize() {
    return (int) this.radius;
  }

  /**
   * Gets color.
   *
   * @return ball 's color.
   */
  public java.awt.Color getColor() {
    return this.color;
  }

  /**
   * Gets velocity.
   *
   * @return the ball's velocity.
   */
  public Velocity getVelocity() {
    return this.velocity;
  }

  /**
   * Sets ball's velocity, by given velocity.
   *
   * @param v the ball's velocity.
   */
  public void setVelocity(Velocity v) {
    double dx = v.getDx();
    double dy = v.getDy();
    this.velocity = new Velocity(dx, dy);
  }

  /**
   * Set ball's velocity by given angle.
   *
   * @param angle the angle of the velocity.
   */
  public void setVelocityByAngle(double angle) {
    //calculate ball speed by it's radius.
    double speed = this.calculateBallSpeed();
    //set ball's velocity.
    this.velocity = Velocity.fromAngleAndSpeed(angle, speed);
  }

  /**
   * Fix ball's location(center point) if it is needed.
   * Checks if the ball inside the rectangle area, if not changes the ball's center point.
   * ERROR!!!!!!!!!!!ERROR!!!!!!!!!!!!!ERROR!!!!!!!!!!!!!!!!ERROR!!!!!!!!!!!!!!!!!!!!
   *
   * @param rectangle the rectangle to check if the ball inside it.
   */
  public void fixBallLocationIfNeed(Rectangle rectangle) {
    // the max x on the ball's scope
    double maxBallX = this.center.getX() + this.radius;
    // the max y on the ball's scope
    double maxBallY = this.center.getY() + this.radius;
    // the max x of the rectangle scope.
    double maxRecX = rectangle.getWidth() + rectangle.getX();
    // the max y of the rectangle scope.
    double maxRecY = rectangle.getHeight() + rectangle.getY();
    //if the ball is not in the x line scope of the surface,
    // change it's center point to a valid location.
    if (maxBallX < 2 * this.radius + rectangle.getX()) {
      this.center = new Point(this.radius + rectangle.getX(), this.center.getY());
    }
    if (maxBallX > maxRecX) {
      this.center = new Point(maxRecX - this.radius, this.center.getY());
    }
    //if the ball is not in the y line scope of the surface,
    // change it's center point to a valid location.
    if (maxBallY < 2 * this.radius + rectangle.getY()) {
      this.center = new Point(this.center.getX(), this.radius + rectangle.getY());
    }
    if (maxBallY > maxRecY) {
      this.center = new Point(this.center.getX(), maxRecY - this.radius);
    }
  }

  /**
   * Fix ball's location(center point) if it is needed.
   * <p>
   * Uses 'fixBallLocationIfNeed(Rectangle rectangle)'
   * that Checks if the ball inside the surface area,
   * if not changes the ball's center point.
   * for using this function define rectangle with the surface values.
   * </p>
   *
   * @param surface the surface to check if the ball inside it.
   */
  public void fixBallLocationIfNeed(DrawSurface surface) {
    //for using 'fixBallLocationIfNeed(Rectangle rectangle)'
    // function define rectangle with the surface values.
    Rectangle rectangle = new Rectangle(0, 0, surface.getWidth(), surface.getHeight());
    this.fixBallLocationIfNeed(rectangle);
  }

  /**
   * Sets velocity, by given dx and dy.
   *
   * @param dx the dx of the velocity to set.
   * @param dy the dy of the velocity to set.
   */
  public void setVelocity(double dx, double dy) {
    this.velocity = new Velocity(dx, dy);
  }

  /**
   * Move one step inside a given surface.
   * <p>
   * Uses 'moveOneStep(Rectangle rectangle)'
   * that move ball one step inside a rectangle area,
   * so for using this function define rectangle with the surface values.
   * </p>
   *
   * @param surface the surface to move the ball one step inside.
   */
  public void moveOneStep(DrawSurface surface) {
    //define rectangle with the surface values.
    Rectangle rectangle = new Rectangle(0, 0, surface.getWidth(), surface.getHeight());
    //move one step in the rectangle with the surface values.
    moveOneStep(rectangle);
  }

  /**
   * Move one step inside a given rectangle area.
   * <p>
   * change the center point by the ball's velocity'
   * then checks if the new location is valid.
   * if not valid change the velocity direction to the other side,
   * by multiply it with -1, then call this function again, with the new defined velocity.
   * </p>
   *
   * @param rectangle the rectangle to move the ball one step inside.
   */
  public void moveOneStep(Rectangle rectangle) {
    this.center = this.velocity.applyToPoint(this.center);
    boolean isVelocityChanged = false;
    // the max x on the ball's scope
    double maxBallX = this.center.getX() + this.radius;
    // the max y on the ball's scope
    double maxBallY = this.center.getY() + this.radius;
    // the max x of the rectangle scope.
    double maxRecX = rectangle.getWidth() + rectangle.getX();
    // the max y of the rectangle scope.
    double maxRecY = rectangle.getHeight() + rectangle.getY();
    //if the ball is not in the x line scope of the surface,
    // change it's velocity direction by multiply with -1.
    if (maxBallX > maxRecX || maxBallX < this.radius * 2 + rectangle.getX()) {
      //change it's velocity direction by multiply with -1
      this.velocity.setDx(-this.velocity.getDx());
      // velocity direction changed
      isVelocityChanged = true;
    }
    //if the ball is not in the y line scope of the surface,
    //change it's velocity direction by multiply with -1.
    if (maxBallY > maxRecY || maxBallY < this.radius * 2 + rectangle.getY()) {
      //change it's velocity direction by multiply with -1
      this.velocity.setDy(-this.velocity.getDy());
      // velocity direction changed
      isVelocityChanged = true;
    }
    // velocity direction changed, so call this function again to check it's new velocity.
    if (isVelocityChanged) {
      moveOneStep(rectangle);
    }
  }

  /**
   * Move the ball one step.
   * If the ball's path to the next ball's location
   * (by adding the ball's velocity to last location)
   * doesn't collide with anything, change the ball's location. Otherwise (if collide),
   * put the ball almost on the collision point and change the ball's velocity.
   */
  public void moveOneStep() {
    // the line that represent the ball's path to the next ball's location
    Line trajectory = new Line(this.center, this.velocity.applyToPoint(this.center));
    // get the collision info of the ball
    CollisionInfo collisionInfo = gameEnvironment.getClosestCollision(trajectory);
    //if collisionInfo=null the ball doesn't collide with anything in the game gameEnvironment
    if (collisionInfo == null) {
      this.center = trajectory.end();
      //there was collision point, so put the ball almost on the collision point
      // and change the velocity
    } else {
      this.center = moveP1AlmsotToP2(this.center, collisionInfo.collisionPoint());
      this.velocity = collisionInfo.collisionObject().
          hit(this, collisionInfo.collisionPoint(), this.velocity);
    }
  }

  /**
   * Move point a almost to point b. put a 2^10 closer to a than before by using line.middle() func.
   *
   * @param p1 the point to bring almost to p2
   * @param p2 the point to bring p1 almost to p2
   * @return p1 - the new location of p1 that almost on p2.
   */
  public Point moveP1AlmsotToP2(Point p1, Point p2) {
    //each loop brings p1 closer to p2 *2
    for (int i = 0; i <= 10; i++) {
      //the line between them, because we want use line.middle() func.
      Line line = new Line(p1, p2);
      //line.middle() bring the largest point in case of line.length()<1,
      // and we want to prevent it.
      //because we want p1 to be almost on p2, and not on p2.
      if (line.length() > 1) {
        //put p1 closer to p2
        p1 = line.middle();
      }
    }
    return p1;
  }

  /**
   * Draw the ball on the given DrawSurface.
   *
   * @param surface the surface to draw inside.
   */
  @Override
  public void drawOn(DrawSurface surface) {
    //cast the ball radius to int.
    int r = this.getSize();
    int x = this.getX();
    int y = this.getY();
    surface.setColor(this.color);
    //draw circle on the surface
    surface.fillCircle(x, y, r);
    surface.setColor(Color.BLACK);
    surface.drawCircle(x, y, r);
  }

  /**
   * notify the sprite that time has passed.
   */
  @Override
  public void timePassed() {
    moveOneStep();
  }

  /**
   * Gets game environment.
   *
   * @return the game environment
   */
  public GameEnvironment getGameEnvironment() {
    return this.gameEnvironment;
  }

  /**
   * Sets game environment.
   *
   * @param gameEnv the game environment.
   */
  public void setGameEnvironment(GameEnvironment gameEnv) {
    this.gameEnvironment = gameEnv;
  }

  /**
   * Add the ball to the given game as a sprite.
   *
   * @param g the game to add the ball as a sprite to.
   */
  public void addToGame(GameLevel g) {
    g.addSprite(this);
  }

  /**
   * Remove from game.
   *
   * @param g the game to remove the ball from
   */
  public void removeFromGame(GameLevel g) {
    g.removeSprite(this);
  }
}