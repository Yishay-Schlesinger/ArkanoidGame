package levels;

import animations.GameLevel;
import biuoop.DrawSurface;
import geomtry.Point;
import geomtry.Rectangle;
import geomtry.Velocity;
import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import sprite.Block;
import sprite.Sprite;

/**
 * The type Level 1.
 */
public class Level1 implements LevelInformation {
  private static final int NUM_OF_BALLS = 1;
  private static final double BALL_ANGLE = 0;
  private static final double BALL_SPEED = 5;
  private static final int PADDLE_SPEED = 7;
  private static final int PADDLE_WIDTH = 180;
  private static final String LEVEL_NAME = "Direct Hit";
  private static final int NUM_OF_BLOCKS = 1;
  private static final Color BACK_COLOR = Color.BLACK;

  @Override
  public int numberOfBalls() {
    return NUM_OF_BALLS;
  }

  @Override
  public List<Velocity> initialBallVelocities() {
    List<Velocity> list = new LinkedList<>();
    list.add(Velocity.fromAngleAndSpeed(BALL_ANGLE, BALL_SPEED));
    return list;
  }

  @Override
  public int paddleSpeed() {
    return PADDLE_SPEED;
  }

  @Override
  public int paddleWidth() {
    return PADDLE_WIDTH;
  }

  @Override
  public String levelName() {
    return LEVEL_NAME;
  }

  @Override
  public Sprite getBackground() {
    return new Sprite() {
      @Override
      public void drawOn(DrawSurface d) {
        d.setColor(BACK_COLOR);
        d.fillRectangle(0, 0, GameLevel.GUI_WIDTH, GameLevel.GUI_HEIGHT);
        drawTarget(d);
      }

      private void drawTarget(DrawSurface d) {
        //get the block center point
        List<Block> block = blocks();
        Block blockTarget = blocks().get(0);
        Point targetPoint = new Point(
            blockTarget.getRect().getUpperLeft().getX() + blockTarget.getRect().getWidth() / 2,
            blockTarget.getRect().getUpperLeft().getY() + blockTarget.getRect().getHeight() / 2);
        //draw the line and the circles of the target paint
        d.setColor(Color.BLUE);
        drawLines(d, targetPoint);
        drawCircles(d, targetPoint);
      }


      private void drawLines(DrawSurface d, Point target) {
        //distance of the lines from the middle point
        int distanceP1FromTarget = 30;
        int distanceP2FromTarget = 150;
        //x,y  values of middle point;
        int xTarget = (int) target.getX();
        int yTarget = (int) target.getY();
        //draw four target lines
        d.drawLine(xTarget - distanceP1FromTarget, yTarget, xTarget - distanceP2FromTarget,
            yTarget);
        d.drawLine(xTarget + distanceP1FromTarget, yTarget, xTarget + distanceP2FromTarget,
            yTarget);
        d.drawLine(xTarget, yTarget - distanceP1FromTarget, xTarget,
            yTarget - distanceP2FromTarget);
        d.drawLine(xTarget, yTarget + distanceP1FromTarget, xTarget,
            yTarget + distanceP2FromTarget);
      }

      private void drawCircles(DrawSurface d, Point target) {
        //x,y  values of middle point;
        int xTarget = (int) target.getX();
        int yTarget = (int) target.getY();
        //draw 3 circles of the target
        d.drawCircle(xTarget, yTarget, 50);
        d.drawCircle(xTarget, yTarget, 90);
        d.drawCircle(xTarget, yTarget, 130);
      }

      @Override
      public void timePassed() {
      }
    };
  }

  @Override
  public List<Block> blocks() {
    //create the block for this level
    Rectangle rectangle = new Rectangle(new Point((GameLevel.GUI_WIDTH - GameLevel.FRAME_WIDTH) / 2,
        250),
        GameLevel.REGULAR_BLOCK_WIDTH / 2, GameLevel.REGULAR_BLOCK_WIDTH / 2);
    Block block = new Block(rectangle, Color.RED);
    //create list and return it
    List<Block> l = new LinkedList<>();
    l.add(block);
    return l;
  }

  @Override
  public int numberOfBlocksToRemove() {
    return NUM_OF_BLOCKS;
  }
}
