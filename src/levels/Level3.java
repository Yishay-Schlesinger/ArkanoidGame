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
 * The type Level 3.
 */
public class Level3 implements LevelInformation {
  private static final int NUM_OF_BALLS = 2;
  private static final int PADDLE_SPEED = 12;
  private static final int PADDLE_WIDTH = 150;
  private static final String LEVEL_NAME = "Green 3";
  private static final int NUM_OF_BLOCKS = 40;
  private static final Color BACK_COLOR = new Color(42, 130, 21);
  private int signCounter;

  /**
   * Instantiates a new Level 3.
   */
  public Level3() {
    this.signCounter = 0;
  }

  @Override
  public int numberOfBalls() {
    return NUM_OF_BALLS;
  }

  @Override
  public List<Velocity> initialBallVelocities() {
    double firstAngle = 340;
    double ballSpeed = 6.5;
    List<Velocity> list = new LinkedList<>();
    for (int i = 0; i < NUM_OF_BALLS; i++) {
      list.add(Velocity.fromAngleAndSpeed(firstAngle, ballSpeed));
      firstAngle += 40;
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
        Rectangle poleRect = new Rectangle(new Point(150, 200), 20, 400);
        Block poleBlock = new Block(poleRect, Color.GRAY);
        poleBlock.drawOn(d);
        Rectangle signRect = new Rectangle(new Point(50, 100), 200, 270);
        Block signBlock = new Block(signRect, new Color(239, 231, 176, 255));
        signBlock.drawOn(d);
        d.setColor(new Color(229, 86, 99, 255));
        signCounter++;
        if (signCounter >= 70) {
          d.drawText(90, 160, " THE", 50);
        }
        if (signCounter >= 140) {
          d.drawText(54, 210, "   BEST", 45);
        }
        if (signCounter >= 180) {
          d.drawText(52, 260, "    GAME", 40);
          d.drawText(70, 310, " IN THE", 45);
          d.drawText(55, 360, "  WORLD!", 40);
        }
        if (signCounter >= 300) {
          signCounter = 0;
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
    Color[] colors = new Color[] {Color.GRAY, Color.RED, Color.YELLOW, Color.BLUE, Color.WHITE};
    //5 row to add rectangles, in the first row add 10,in the second add 9, atc..
    int rectangleCounterToAdd = 10;
    int row = 5;
    Block block; //set a block for the loop
    for (int i = 0; i < row; i++) { //loop for each row
      //set the rectangle for the first block in the row.
      // the location is near to the left frame block.
      //set the first rectangle for the first block
      Rectangle rectangle = new Rectangle(
          new Point(GameLevel.GUI_WIDTH - GameLevel.FRAME_WIDTH - 50,
              GameLevel.GUI_HEIGHT / 4 + GameLevel.REGULAR_BLOCK_HEIGHT * i),
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
      rectangleCounterToAdd--; //one less block to add in the next row.
    }
    return l;
  }

  @Override
  public int numberOfBlocksToRemove() {
    return NUM_OF_BLOCKS;
  }
}
