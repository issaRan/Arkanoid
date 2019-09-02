import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * @author ISSA
 * 19/5/18
 * runner for the animaion.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private double dt;

    /**
     * constructor.
     *
     * @param gui take the screen that we will display it.
     */
    public AnimationRunner(GUI gui) {
        this.gui = gui;
        this.framesPerSecond = 60;
        this.dt = 1 / (double) framesPerSecond;
    }

    /**
     * used to display the gui.
     *
     * @param animation the animation, this.
     */
    public void run(Animation animation) {
        Sleeper sleeper = new Sleeper();
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis();
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d, this.dt);
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = 1000 / this.framesPerSecond - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}