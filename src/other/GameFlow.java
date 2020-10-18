package other;

import animations.AnimationRunner;
import animations.GameLevel;
import animations.GameOverScreen;
import animations.KeyPressStoppableAnimation;
import animations.YouWinScreen;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import java.util.List;
import levels.LevelInformation;

/**
 * The type Game flow.
 */
public class GameFlow {
  private AnimationRunner animationRunner;
  private KeyboardSensor keyboardSensor;
  private Counter score;
  private GUI gui;
  private boolean isLoser;

  /**
   * Instantiates a new Game flow.
   *
   * @param ar  the Animation Runner
   * @param kr  the Keyboard Sensor
   * @param gui the gui
   */
  public GameFlow(AnimationRunner ar, KeyboardSensor kr, GUI gui) {
    this.animationRunner = ar;
    this.keyboardSensor = kr;
    this.score = new Counter(0);
    this.gui = gui;
    this.isLoser = false;
  }

  /**
   * Run levels.
   *
   * @param levels the levels
   */
  public void runLevels(List<LevelInformation> levels) {
    for (LevelInformation levelInfo : levels) {
      GameLevel level =
          new GameLevel(levelInfo, this.animationRunner, this.keyboardSensor, this.score);
      level.initialize();
      level.run();
      if (level.getLeftBallsNum() == 0) {
        this.isLoser = true;
        break;
      }
    }
    if (isLoser) {
      animationRunner.run(
          new KeyPressStoppableAnimation(this.keyboardSensor, KeyboardSensor.SPACE_KEY,
              new GameOverScreen(this.score)));
    } else {
      animationRunner.run(
          new KeyPressStoppableAnimation(this.keyboardSensor, KeyboardSensor.SPACE_KEY,
              new YouWinScreen(this.score)));
    }
    this.gui.close();
  }
}
