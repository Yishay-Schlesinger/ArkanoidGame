package animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import geomtry.Point;
import geomtry.Rectangle;
import geomtry.Velocity;
import java.awt.Color;
import java.util.List;
import levels.LevelInformation;
import listeners.BallRemover;
import listeners.BlockRemover;
import listeners.HitListener;
import listeners.ScoreTrackingListener;
import other.Counter;
import other.GameEnvironment;
import other.SpriteCollection;
import sprite.Ball;
import sprite.Block;
import sprite.Collidable;
import sprite.NameLevelWriter;
import sprite.Paddle;
import sprite.ScoreIndicator;
import sprite.Sprite;

/**
 * Class GameLevel.
 */
public class GameLevel implements Animation {
  /**
   * The default GUI_WIDTH.
   */
  public static final int GUI_WIDTH = 800;
  /**
   * The default GUI_HEIGHT.
   */
  public static final int GUI_HEIGHT = 600;
  /**
   * The default FRAME_WIDTH.
   */
  public static final int FRAME_WIDTH = 25;
  /**
   * The constant REGULAR_BLOCK_WIDTH.
   */
//the default regular block width value
  public static final int REGULAR_BLOCK_WIDTH = 50;
  /**
   * The constant REGULAR_BLOCK_HEIGHT.
   */
//the default regular block height value
  public static final int REGULAR_BLOCK_HEIGHT = 30;
  //the current level information
  private LevelInformation levelInfo;
  //game sprites
  private SpriteCollection sprites;
  //game environment of the game.
  private GameEnvironment environment;
  //the paddle of the game.
  private Paddle paddle;
  //boolean if the game was initialized already
  private boolean isInitialize;
  //counter of regular Blocks
  private Counter blocksCounter;
  //counter of balls
  private Counter ballsCounter;
  //score counter
  private Counter scoreCounter;
  //animation runner
  private AnimationRunner runner;
  //boolean to know if to continue in the program
  private boolean running;
  //Keyboard Sensor for the game
  private KeyboardSensor keyboard;

  /**
   * construct a new GameLevel.
   *
   * @param levelInfo the level info of this game level
   * @param ar        the AnimationRunner of this game level
   * @param kr        the KeyboardSensor of this game level
   * @param score     the score
   */
  public GameLevel(LevelInformation levelInfo, AnimationRunner ar, KeyboardSensor kr,
                   Counter score) {
    this.levelInfo = levelInfo;
    this.sprites = new SpriteCollection();
    this.environment = new GameEnvironment();
    this.isInitialize = false;
    this.blocksCounter = new Counter(0);
    this.ballsCounter = new Counter(0);
    this.scoreCounter = score;
    this.keyboard = kr;
    this.runner = ar;
  }

  /**
   * Add collidable.
   *
   * @param c the collidable to add.
   */
  public void addCollidable(Collidable c) {
    environment.addCollidable(c);
  }

  /**
   * Add sprite.
   *
   * @param s the sprite to add.
   */
  public void addSprite(Sprite s) {
    sprites.addSprite(s);
  }

  /**
   * Initialize a new game: create the Blocks and Ball (and Paddle) and add them to the game.
   */
  public void initialize() {
    this.isInitialize = true;
    //add the blocks for the frame and for the background.
    addFrameBlocks();
    //add the games block to game
    addRegularBlocks();
    //add paddle to the game.
    addPaddle();
    //add 3 balls to the game.
    addBalls();
  }

  /**
   * Add frame blocks.
   */
  private void addFrameBlocks() {
    //add the death block for the balls,
    addDeathBlock();
    //add the specific level background
    this.sprites.addSprite(levelInfo.getBackground());
    //add block for the background
    addBackgroundBlocks();
    //add score indicator
    addScoreIndicator();
    //add name level writer
    addNameLevelWriter(this.levelInfo.levelName());
  }

  /**
   * Add death block.
   */
  private void addDeathBlock() {
    //set the death block
    Rectangle deathRectangle = new Rectangle(new Point(0,
        600 - FRAME_WIDTH), GUI_WIDTH, FRAME_WIDTH);
    Block deathBlock = new Block(deathRectangle, java.awt.Color.GRAY);
    HitListener hl = new BallRemover(this, ballsCounter);
    //set listener and add to the game
    deathBlock.addHitListener(hl);
    deathBlock.addToGame(this);
  }

  /**
   * Add background blocks.
   */
  private void addBackgroundBlocks() {
    //set 3 blocks for the surface frame(upper, right, left)
    Rectangle upperRectangle = new Rectangle(new Point(0, 0), GUI_WIDTH,
        FRAME_WIDTH * 2);
    Block upperBlock = new Block(upperRectangle, Color.GRAY);
    Rectangle leftRectangle = new Rectangle(new Point(0, 0), FRAME_WIDTH, GUI_HEIGHT);
    Block leftBlock = new Block(leftRectangle, java.awt.Color.GRAY);
    Rectangle rightRectangle =
        new Rectangle(new Point(GUI_WIDTH - FRAME_WIDTH, 0), FRAME_WIDTH, GUI_HEIGHT);
    Block rightBlock = new Block(rightRectangle, java.awt.Color.GRAY);
    //add all the 5 blocks to the game.
    Block[] frameBlocks = new Block[] {upperBlock, leftBlock, rightBlock};
    for (Block b : frameBlocks) {
      b.addToGame(this);
    }
  }

  /**
   * Add score indicator.
   */
  private void addScoreIndicator() {
    Rectangle upperRectangle = new Rectangle(new Point(0, 0),
        GUI_WIDTH, FRAME_WIDTH);
    Block scoreBlock = new ScoreIndicator(upperRectangle, Color.LIGHT_GRAY, this.scoreCounter);
    scoreBlock.addToGame(this);
  }

  /**
   * Add name level writer.
   *
   * @param nameLevel the name of this specific level.
   */
  private void addNameLevelWriter(String nameLevel) {
    addSprite(new NameLevelWriter(nameLevel));
  }


  /**
   * Add regular blocks of the game.6 rows, in each row one less rectangle then the last row.
   */
  private void addRegularBlocks() {
    //set block remover hl for all the regular blocks
    HitListener hlBlockRemover = new BlockRemover(this, this.blocksCounter);
    //new hl-ScoreTrackingListener for all blocks
    HitListener hlScoreTrack = new ScoreTrackingListener(this, this.scoreCounter);
    //add the specific level regular blocks to the game
    List<Block> blocks = this.levelInfo.blocks();
    for (Block b : blocks) {
      b.addToGame(this);
      b.addHitListener(hlBlockRemover);
      b.addHitListener(hlScoreTrack);
    }
    this.blocksCounter.increase(this.levelInfo.numberOfBlocksToRemove());
  }

  /**
   * Add paddle to the game.
   */
  private void addPaddle() {
    //set rectangle for the paddle, the location is on the middle of the lower frame block.
    Rectangle rectangle = new Rectangle(new Point((GUI_WIDTH - this.levelInfo.paddleWidth()) / 2,
        GUI_HEIGHT - FRAME_WIDTH - 1),
        this.levelInfo.paddleWidth(), 10);
    //set the paddle and add it to the game.
    this.paddle = new Paddle(this.keyboard, rectangle, Color.ORANGE, this.levelInfo.paddleSpeed());
    this.paddle.addToGame(this);
  }


  /**
   * Add ball to the game.
   */
  public void addBalls() {
    List<Velocity> velocities = this.levelInfo.initialBallVelocities();
    for (Velocity v : velocities) {
      //set the balls location in place that it does not collide with any other objects.
      //with the velocity of this specific level, and add to the game.
      Ball ball = new Ball(new Point(GUI_WIDTH / 2, 480),
          6, Color.lightGray);
      ball.setVelocity(v);
      ball.setGameEnvironment(this.environment);
      ball.addToGame(this);
    }
    //set the balls counter by the number of balls in this level
    this.ballsCounter.increase(this.levelInfo.numberOfBalls());
  }

  /**
   * Remove collidable from game.
   *
   * @param c the collidable to remove.
   */
  public void removeCollidable(Collidable c) {
    this.environment.removeCollidable(c);
  }

  /**
   * Remove sprite from the game.
   *
   * @param s the sprite to remove.
   */
  public void removeSprite(Sprite s) {
    this.sprites.remove(s);
  }

  @Override
  public void doOneFrame(DrawSurface d) {
    //draw and show all the objects of game on a surface
    this.sprites.drawAllOn(d);
    this.sprites.notifyAllTimePassed();
    //check if the program should continue to run
    if (!(blocksCounter.getValue() != 0 && ballsCounter.getValue() != 0)) {
      this.running = false;
    }
    if (this.keyboard.isPressed("p")) {
      this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY,
          new PauseScreen()));
    }
  }


  @Override
  public boolean shouldStop() {
    return !this.running;
  }

  /**
   * Run the game.
   */
  public void run() {
    //make sure the game initialized before running.
    if (!isInitialize) {
      initialize();
    }
    this.runner.run(new CountdownAnimation(2, 3, this.sprites)); // countdown before turn starts.
    this.running = true;
    // use our runner to run the current animation -- which is one turn of
    // the game.
    this.runner.run(this);
    //if all blocks were hit by the user, add 100 points to his score.
    if (blocksCounter.getValue() == 0) {
      this.scoreCounter = new Counter(this.scoreCounter.getValue() + 100);
    }
  }

  /**
   * Gets left balls num.
   *
   * @return the current number of balls, that left in the game.
   */
  public int getLeftBallsNum() {
    return this.ballsCounter.getValue();
  }

  /**
   * Gets left blocks num.
   *
   * @return the current number of balls, that left in the game.
   */
  public int getLeftBlocksNum() {
    return this.blocksCounter.getValue();
  }
}