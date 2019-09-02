/**
 * @author ISSA 10/06/18
 */
public class ShowHiScoresTask implements Task<Void> {
    private AnimationRunner runner;
    //private KeyboardSensor sensor;
    private Animation highScoresTable;

    /**
     * constructor.
     *
     * @param runner          animation runner.
     * @param highScoresTable Amination of highscoretable.
     */
    public ShowHiScoresTask(AnimationRunner runner, Animation highScoresTable) {
        this.runner = runner;
        this.highScoresTable = highScoresTable;
    }

    /**
     * run the program.
     *
     * @return just run the animation.
     */
    public Void run() {
        this.runner.run(this.highScoresTable);
        return null;
    }
}
