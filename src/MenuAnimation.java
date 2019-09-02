import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @param <T> task.
 * @author ISSA class that runner the task.
 */
public class MenuAnimation<T> implements Menu<T> {
    private String string;
    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;
    private List<Selection<T>> selections;
    private T theChosen;
    private boolean stop;
    private int checking;

    /**
     * constructor.
     *
     * @param string          name of the animation.
     * @param keyboardSensor  keyboardSensor.
     * @param animationRunner animation runner.
     */
    public MenuAnimation(String string, KeyboardSensor keyboardSensor, AnimationRunner animationRunner) {
        this.string = string;
        this.keyboardSensor = keyboardSensor;
        this.animationRunner = animationRunner;
        this.selections = new ArrayList<>();
        this.stop = false;
        this.checking = 0;
        //this.keys = new ArrayList<>();
        //this.message = new ArrayList<>();
        //this.returnVal = new ArrayList<>();
    }

    /**
     * @param key       the key to play.
     * @param message   the name of the task.
     * @param returnVal the task.
     */
    public void addSelection(String key, String message, T returnVal) {
        this.selections.add(new Selection<>(key, message, returnVal));
    }

    /**
     * submenu menu.
     *
     * @param key     the key to play.
     * @param message the name of the task.
     * @param subMenu the task.
     */
    public void addSubMenu(String key, String message, Menu<T> subMenu) {
        //this.subMenu.add(new Selection<>(key,message,subMenu));
        Task<Void> beginGame = new Task<Void>() {

            @Override
            public Void run() {
                animationRunner.run(subMenu);
                return null;
            }
        };
        Selection<T> selection = new Selection<>(key, message, (T) beginGame);
        this.selections.add(selection);

    }

    /**
     * task.
     *
     * @return the task that we chose.
     */
    public T getStatus() {
        return this.theChosen;
    }

    /**
     * to draw the animatiom.
     *
     * @return to stop the action.
     */
    public boolean shouldStop() {
        return this.stop;

    }

    /**
     * reset the boolean to be able to move from screen to screen.
     */
    public void reset() {
        this.stop = false;
        this.theChosen = null;
    }

    /**
     * draw the animation.
     *
     * @param d  used the drawSurface package.
     * @param dt delta time.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        //d.drawImage(0, 0, ColorOrImage.parseImage("background_images/darth_vader.jpg"));
        d.setColor(Color.black);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(new Color(255, 204, 51));
        d.drawText(210, 100, "Welcome to Arkanoid", 40);
        d.setColor(Color.white);
        d.drawText(300, 400, "May the force be with you", 40);
        for (int i = 0; i < this.selections.size(); i++) {
            d.drawText(70, 200 + i * 45, "(" + (this.selections.get(i).getString()) + ") ", 40);
            d.drawText(120, 200 + i * 45, this.selections.get(i).getMessage(), 40);
        }
        for (int i = 0; i < this.selections.size(); ++i) {
            if (this.keyboardSensor.isPressed(this.selections.get(i).getString())) {
                this.stop = true;
            }
        }
        for (int i = 0; i < selections.size(); i++) {
            if (this.keyboardSensor.isPressed(this.selections.get(i).getString())) {
                this.theChosen = this.selections.get(i).getReturnVal();
            }
        }
    }
}
