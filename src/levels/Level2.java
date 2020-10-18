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
 * The type Level 2.
 */
public class Level2 implements LevelInformation {
  private static final int NUM_OF_BALLS = 10;
  private static final int PADDLE_SPEED = 4;
  private static final int PADDLE_WIDTH = 500;
  private static final String LEVEL_NAME = "Wide Easy";
  private static final int NUM_OF_BLOCKS = 15;
  private static final Color BACK_COLOR = Color.WHITE;


  @Override
  public int numberOfBalls() {
    return NUM_OF_BALLS;
  }

  @Override
  public List<Velocity> initialBallVelocities() {
    double firstAngle = 328.5;
    double ballSpeed = 7;
    List<Velocity> list = new LinkedList<>();
    for (int i = 0; i < NUM_OF_BALLS; i++) {
      list.add(Velocity.fromAngleAndSpeed(firstAngle, ballSpeed));
      firstAngle += 7;
    }
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
        drawSun(d);
      }

      private void drawSun(DrawSurface d) {
        //center of the sun
        Point middle = new Point(150, 150);
        //drow the lines of the sun and it's circles
        drawLines(d, middle);
        drawCircles(d, middle);
      }

      private void drawLines(DrawSurface d, Point middle) {
        //x,y values of middle point
        int xMiddle = (int) middle.getX();
        int yMiddle = (int) middle.getY();
        //y value of p2 in each line
        int yP2 = (int) blocks().get(0).getRect().getUpperLeft().getY();
        //draw all the suns lines
        //range of the lines is 750 to -100 on x line
        d.setColor(new Color(0xEFE7B0));
        int xP2 = 750;
        while (xP2 > -100) {
          d.drawLine(xMiddle, yMiddle, xP2, yP2);
          xP2 -= 8;
        }
      }

      private void drawCircles(DrawSurface d, Point middle) {
        //x,y values of middle point
        int xMiddle = (int) middle.getX();
        int yMiddle = (int) middle.getY();
        //first circle:
        d.setColor(new Color(239, 231, 176, 255));
        d.fillCircle(xMiddle, yMiddle, 80);
        //second circle:
        d.setColor(new Color(236, 215, 73));
        d.fillCircle(xMiddle, yMiddle, 70);
        //third circle:
        d.setColor(new Color(255, 225, 24));
        d.fillCircle(xMiddle, yMiddle, 60);
      }

      @Override
      public void timePassed() {
      }
    };
  }

  @Override
  public List<Block> blocks() {
    //the list to return
    List<Block> l = new LinkedList<>();
    //color array for each line
    Color[] colors = new Color[] {Color.cyan, Color.cyan, Color.PINK, Color.PINK, Color.BLUE,
        Color.BLUE, Color.GREEN, Color.GREEN, Color.GREEN, Color.YELLOW, Color.YELLOW, Color.ORANGE,
        Color.ORANGE, Color.RED, Color.RED};
    //set the first rectangle for the first block
    Rectangle rectangle = new Rectangle(
        new Point(GameLevel.GUI_WIDTH - GameLevel.FRAME_WIDTH - 50,
            GameLevel.GUI_HEIGHT / 2),
        GameLevel.REGULAR_BLOCK_WIDTH, GameLevel.REGULAR_BLOCK_HEIGHT);
    Block block; //set a block for the loop
    for (int i = 0; i < NUM_OF_BLOCKS; i++) { //loop for each row
      //set the rectangle for the first block in the row.
      // the location is near to the left frame block.
      //set a new block and add it to the game
      block = new Block(rectangle, colors[i]);
      l.add(block);
      //set the rectangle for the next block in the same row
      rectangle = rectangle.getRectangleForNewUpperLeft(
          new Point(rectangle.getUpperLeft().getX() - GameLevel.REGULAR_BLOCK_WIDTH,
              rectangle.getUpperLeft().getY()));
    }
    return l;
  }

  @Override
  public int numberOfBlocksToRemove() {
    return NUM_OF_BLOCKS;
  }
}