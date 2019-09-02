import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

/**
 * @author ISSA
 * 19/5/18
 * level 1
 */
public class LevelThree implements LevelInformation {
    /**
     * numbers of the balls.
     *
     * @return the number of the balls.
     */
    public int numberOfBalls() {
        return 2;
    }

    /**
     * // The initial velocity of each ball.
     * // Note that initialBallVelocities().size() == numberOfBalls()
     *
     * @return the list of the velocity.
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> theVelocity = new ArrayList<>();
        theVelocity.add(Velocity.fromAngleAndSpeed(30, 5));
        theVelocity.add(Velocity.fromAngleAndSpeed(330, 5));
        return theVelocity;
    }

    /**
     * paddle speed.
     *
     * @return paddle speed.
     */
    public int paddleSpeed() {
        return 5;
    }

    /**
     * paddle width.
     *
     * @return paddle width.
     */
    public int paddleWidth() {
        return 80;
    }

    /**
     * // the level name will be displayed at the top of the screen.
     *
     * @return the name of the level.
     */
    public String levelName() {
        return "Green 3";
    }

    /**
     * // Returns a sprite with the background of the level.
     *
     * @return the background.
     */
    public Sprite getBackground() {
        return new Background3();
    }

    /**
     * // The Blocks that make up this level, each block contains
     * // its size, color and location.
     *
     * @return list of the blocks.
     */
    public List<Block> blocks() {
        List<Block> listBlocks = new ArrayList<Block>();

        Color[] colors = {Color.decode("#F37A30"), Color.decode("#8133F3"),
                Color.decode("#B9AC26"), Color.decode("#6196F3"), Color.decode("#732546")};
        int[] hitPoints = {2, 1, 1, 1, 1};
        int y = 140;
        for (int i = 0; i < colors.length; i++) {
            for (int j = i + 5; j < 15; j++) {
                listBlocks.add(new Block(new Rectangle(new Point(j * 51 + 25, y), 51, 20), colors[i], hitPoints[i]));
            }
            y += 20;
        }
        return listBlocks;
    }

    /**
     * // Number of levels that should be removed.
     // before the level is considered to be "cleared".
     // This number should be <= blocks.size();
     * @return numbers of blocks to remove.
     */
    public int numberOfBlocksToRemove() {
        return 40;
    }
}
