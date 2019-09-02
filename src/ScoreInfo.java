import java.io.Serializable;

/**
 * @author ISSA 10/06/18
 */
public class ScoreInfo implements Serializable {
    private String name;
    private int score;

    /**
     * constructor.
     *
     * @param name  name of the players.
     * @param score score of the players.
     */
    public ScoreInfo(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * get function.
     *
     * @return return the name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * get function.
     *
     * @return return the score.
     */
    public int getScore() {
        return this.score;
    }
}