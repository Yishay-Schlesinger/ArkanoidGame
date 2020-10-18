import animations.AnimationRunner;
import animations.GameLevel;
import biuoop.GUI;
import java.util.LinkedList;
import java.util.List;
import levels.Level1;
import levels.Level2;
import levels.Level3;
import levels.Level4;
import levels.LevelInformation;
import other.GameFlow;

/**
 * class ArkaniodGame create a game object, initialize it, and run it.
 */
public class ArkaniodGame {
  /**
   * The program set new game, initialize it, and run it.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    List<LevelInformation> levels = getListOfLevels(args);
    //if args are not valid
    if (levels.isEmpty()) {
      levels.add(new Level1());
      levels.add(new Level2());
      levels.add(new Level3());
      levels.add(new Level4());
    }
    //play the game
    GUI gui = new GUI("Arkanoid", GameLevel.GUI_WIDTH, GameLevel.GUI_HEIGHT);
    GameFlow gameFlow = new GameFlow(new AnimationRunner(gui, 60), gui.getKeyboardSensor(), gui);
    gameFlow.runLevels(levels);
  }

  /**
   * Gets list of levels.
   *
   * @param args the args
   * @return the list of levels
   */
  private static List<LevelInformation> getListOfLevels(String[] args) {
    List<LevelInformation> levels = new LinkedList<LevelInformation>();
    for (String s : args) {
      switch (s) {
        case "1":
          levels.add(new Level1());
          break;
        case "2":
          levels.add(new Level2());
          break;
        case "3":
          levels.add(new Level3());
          break;
        case "4":
          levels.add(new Level4());
          break;
        default:break;
      }
    }
    return levels;
  }
}
