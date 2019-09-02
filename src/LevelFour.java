import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

/**
 * @author ISSA
 * 19/5/18
 * level 1
 */
public class LevelFour implements LevelInformation {
    /**
     * numbers of balls.
     *
     * @return numbers of balls.
     */
    public int numberOfBalls() {
        return 3;
    }

    /**
     * // The initial velocity of each ball.
     * // Note that initialBallVelocities().size() == numberOfBalls()
     *
     * @return the list of velocity.
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> theVelocity = new ArrayList<>();
        theVelocity.add(Velocity.fromAngleAndSpeed(35, 5));
        theVelocity.add(Velocity.fromAngleAndSpeed(325, 5));
        theVelocity.add(Velocity.fromAngleAndSpeed(0, 5));
        return theVelocity;
    }

    /**
     * paddle speed.
     *
     * @return speed paddle.
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
        return 130;
    }

    /**
     * // the level name will be displayed at the top of the screen.
     *
     * @return the level name of the level.
     */
    public String levelName() {
        return "Final Four";
    }
    /**
     * // Returns a sprite with the background of the level.
     *
     * @return the background.
     */
    public Sprite getBackground() {
        return new Background4();
    }

    /**
     * // The Blocks that make up this level, each block contains
     * // its size, color and location.
     *
     * @return list of blocks.
     */
    public List<Block> blocks() {
        List<Block> theBlocks = new ArrayList<>();
        int y = 120;
        Color[] colors = {Color.lightGray, Color.red, Color.yellow, Color.blue, Color.pink, Color.green,
                Color.gray};
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 15; j++) {
                theBlocks.add(new Block(new Rectangle(new Point(j * 50.6 + 20, y), 50.6, 20), colors[i], 1));
            }
            y += 20;
        }
        return theBlocks;
    }

    /**
     * // Number of levels that should be removed
     // before the level is considered to be "cleared".
     // This number should be <= blocks.size();
     * @return numbers of blocks.
     */
    public int numberOfBlocksToRemove() {
        return 105;
    }
}
