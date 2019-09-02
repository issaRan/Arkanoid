import biuoop.DialogManager;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.io.Reader;
import java.io.IOException;

/**
 * run the game.
 */
public class Ass6Game {
    private static final int GUI_WIDTH = 800;
    private static final int GUI_HEIGHT = 600;
    /**
     * running the program.
     *
     * @param args argument from commandLine.
     */
    public static void main(String[] args) {
        GUI gui = new GUI("TimeToPlayTheGame", GUI_WIDTH, GUI_HEIGHT);
        File f = new File("highscore");
        HighScoresTable highScoresTable = HighScoresTable.loadFromFile(f);
        AnimationRunner ar = new AnimationRunner(gui);
        KeyboardSensor ks = gui.getKeyboardSensor();
        Menu<Task<Void>> menu = new MenuAnimation<>("Arkanoid", ks, ar);
        MenuAnimation<Task<Void>> subMenu = new MenuAnimation<>("EasyOrHard", ks, ar);
        String fileToBe = null;
        if (args.length == 0) {
            fileToBe = "level_sets.txt";
        } else {
            fileToBe = args[0];
        }
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(fileToBe);
        Reader reader = new InputStreamReader(is);
        List<Selection<String>> helpV1 = LevelSets.fromReader(reader);
        for (int i = 0; i < helpV1.size(); i++) {
            String levelDef = helpV1.get(i).getReturnVal();
            Task<Void> play = new Task<Void>() {
                @Override
                public Void run() {
                    if (args.length == 0) {
                        DialogManager dialogManager = gui.getDialogManager();
                        GameFlow theGame = new GameFlow(ar, ks, gui, dialogManager, f, highScoresTable);
                        File fileToBe = new File(levelDef);
                        InputStreamReader inputStreamReader;
                        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(levelDef);
                        Reader reader = new InputStreamReader(is);
                        LevelSpecificationReader levelSpecificationReader = new LevelSpecificationReader();
                        List<LevelInformation> list = levelSpecificationReader.fromReader(reader);
                        theGame.runLevels(list);
                    }
                    return null;
                }
            };
            subMenu.addSelection(helpV1.get(i).getString(), helpV1.get(i).getMessage(), play);
        }
        menu.addSubMenu("s", "Start Game", subMenu);
        menu.addSelection("h", "High Scores", new ShowHiScoresTask(ar, (new KeyPressStoppableAnimation(ks, "space",
                (new HighScoresAnimation(highScoresTable))))));
        menu.addSelection("e", "Exit", new ExitFromGame());
        while (true) {
            ar.run(menu);
            Task<Void> task = menu.getStatus();
            task.run();
            ((MenuAnimation<Task<Void>>) menu).reset();
            Task<Void> task1 = subMenu.getStatus();
            if (task1 != null) {
                task1.run();
            }
            try {
                highScoresTable.load(f);
            } catch (IOException e) {
                e.printStackTrace();
            }
            subMenu.reset();
        }
    }
}