import biuoop.DialogManager;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author ISSA
 * 19/5/18
 * remove the ball.
 */
public class GameFlow {
    private AnimationRunner ar;
    private KeyboardSensor ks;
    private GUI gui;
    private Counter lives;
    private Counter score;
    private HighScoresTable highScoresTable;
    private DialogManager dialogManager;
    private File f;

    /**
     * constructor.
     *
     * @param ar              animation runner object
     * @param ks              keynoardsensor for the paddle
     * @param gui             to the screen
     * @param dialogManager   ask for the name.
     * @param f               ask for the file.
     * @param highScoresTable the table highscore
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui, DialogManager dialogManager, File f,
                    HighScoresTable highScoresTable) {
        this.ar = ar;
        this.ks = ks;
        this.gui = gui;
        this.score = new Counter();
        this.lives = new Counter();
        this.lives.increase(7);
        this.highScoresTable = highScoresTable;
        this.dialogManager = dialogManager;
        this.f = f;
    }

    /**
     * run the level by for, one after one.
     *
     * @param levels list of level.
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.ks, this.ar, this.lives,
                    this.score, this.gui, levelInfo.levelName());
            level.initialize();
            while (this.lives.getValue() >= 0 && level.howManyBlocks() != 0) {
                level.playOneTurn();
                if (this.lives.getValue() == -1) {
                    this.ar.run(new KeyPressStoppableAnimation(this.ks, "space", new Lose(this.score.getValue())));
                    insertData();
                    this.ar.run(new KeyPressStoppableAnimation(this.ks, "space",
                            new HighScoresAnimation(this.highScoresTable)));
                    return;
                }
            }
        }
        score.increase(100);
        this.ar.run(new KeyPressStoppableAnimation(this.ks, "space", new Winning(this.score.getValue())));
        insertData();
        this.ar.run(new KeyPressStoppableAnimation(this.ks, "space", new HighScoresAnimation(this.highScoresTable)));
    }

    /**
     * used to insert the new player.
     */
    public void insertData() {
        if (highScoresTable.getRank(this.score.getValue()) <= highScoresTable.size()) {
            DialogManager dialogManager1 = this.dialogManager;
            String name = dialogManager1.showQuestionDialog("Name", "What is your name?", "");
            highScoresTable.add(new ScoreInfo(name, score.getValue()));
            try {
                highScoresTable.save(f);
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
            try {
                highScoresTable.load(f);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
/*
File theFile = new File("highscore");
        if(!f.exists()){
            try {
                this.highScoresTable.save(new File("highscore"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            try {
                this.highScoresTable.load(theFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
 */