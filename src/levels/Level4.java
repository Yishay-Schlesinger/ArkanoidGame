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
 * The type Level 4.
 */
public class Level4 implements LevelInformation {
  private static final int NUM_OF_BALLS = 3;
  private static final int PADDLE_SPEED = 12;
  private static final int PADDLE_WIDTH = 130;
  private static final String LEVEL_NAME = "Final Four";
  private static final int NUM_OF_BLOCKS = 40;
  private static final Color BACK_COLOR = new Color(23, 136, 208);


  @Override
  public int numberOfBalls() {
    return NUM_OF_BALLS;
  }

  @Override
  public List<Velocity> initialBallVelocities() {
    //the first angle and speed
    double firstAngle = 350;
    double ballSpeed = 6.5;
    //add all the velocities to list
    List<Velocity> list = new LinkedList<>();
    for (int i = 0; i < NUM_OF_BALLS; i++) {
      list.add(Velocity.fromAngleAndSpeed(firstAngle, ballSpeed));
      firstAngle += 10;
    }
    //return the list
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
        //first clouds
        drawRainClouds(d, 200, 350, 180);
        //second clouds
        drawRainClouds(d, 600, 450, 550);
      }

      private void drawRainClouds(DrawSurface d, int xP1, int yP1, int xP2) {
        //values for this method
        int yP2 = 800;
        int radius = 30;
        int minus = -1;
        int colorBright = 170;
        Color cloudColor = new Color(colorBright, colorBright, colorBright);
        //draw lines for the rain and circles for the clouds
        for (int i = 0; i < 6; i++) {
          d.setColor(Color.WHITE);
          d.drawLine(xP1, yP1, xP2, yP2);
          d.drawLine(xP1 + 10, yP1, xP2 + 10, yP2);
          d.setColor(cloudColor);
          d.fillCircle(xP1, yP1, radius);
          yP1 += minus * 10;
          xP1 += 20;
          xP2 += 20;
          radius += 3;
          minus = -minus;
          if (minus < 0) {
            colorBright += 17;
            cloudColor = new Color(colorBright, colorBright, colorBright);
          }
        }
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
    Color[] colors = new Color[] {Color.GRAY, Color.RED, Color.YELLOW, Color.GREEN, Color.WHITE,
        Color.PINK, Color.CYAN};
    //5 row to add rectangles, in the first row add 10,in the second add 9, atc..
    int rectangleCounterToAdd = 15;
    int row = 7;
    Block block; //set a block for the loop
    for (int i = 0; i < row; i++) { //loop for each row
      //set the rectangle for the first block in the row.
      // the location is near to the left frame block.
      //set the first rectangle for the first block
      Rectangle rectangle = new Rectangle(
          new Point(GameLevel.GUI_WIDTH - GameLevel.FRAME_WIDTH - 50,
              GameLevel.GUI_HEIGHT / 8 + GameLevel.REGULAR_BLOCK_HEIGHT * i),
          GameLevel.REGULAR_BLOCK_WIDTH, GameLevel.REGULAR_BLOCK_HEIGHT);
      for (int j = 0; j < rectangleCounterToAdd; j++) { //loop for each rectangle in the row
        //set a new block and add it to the game
        block = new Block(rectangle, colors[i]);
        l.add(block);
        //set the rectangle for the next block in the same row
        rectangle = rectangle.getRectangleForNewUpperLeft(
            new Point(rectangle.getUpperLeft().getX() - GameLevel.REGULAR_BLOCK_WIDTH,
                rectangle.getUpperLeft().getY()));
      }
    }
    return l;
  }

  @Override
  public int numberOfBlocksToRemove() {
    return NUM_OF_BLOCKS;
  }
}
