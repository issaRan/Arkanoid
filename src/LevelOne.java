import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

/**
 * @author ISSA
 * 19/5/18
 * level 1
 */
public class LevelOne implements LevelInformation {
    /**
     * number of balls.
     * @return number of balls.
     */
    public int numberOfBalls() {
        return 1;
    }

    /**
     * // The initial velocity of each ball.
     // Note that initialBallVelocities().size() == numberOfBalls()
     * @return a list of velocity.
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> theVelocity = new ArrayList<>();
        Velocity velocity = Velocity.fromAngleAndSpeed(0, 6);
        theVelocity.add(velocity);
        return theVelocity;
    }

    /**
     * speed of paddle.
     * @return the speed.
     */
    public int paddleSpeed() {
        return 4;
    }

    /**
     * width of the paddle.
     * @return width of the paddle.
     */
    public int paddleWidth() {
        return 80;
    }

    /**
     * // the level name will be displayed at the top of the screen.
     * @return a string.
     */
    public String levelName() {
        return "Direct hit";
    }

    /**
     * // Returns a sprite with the background of the level.
     * @return the background.
     */
    public Sprite getBackground() {
        return new Background1();
    }

    /**
     * // The Blocks that make up this level, each block contains
     // its size, color and location.
     * @return list of blocks.
     */
    public List<Block> blocks() {
        List<Block> theBlock = new ArrayList<>();
        Block b1 = new Block(new Rectangle(new Point(385, 150), 30, 30), Color.RED, 1);
        theBlock.add(b1);
        return theBlock;
    }

    /**
     * // Number of levels that should be removed
     // before the level is considered to be "cleared".
     // This number should be <= blocks.size();
     * @return the numbersofblocks
     */
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
