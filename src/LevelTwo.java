import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/**
 * @author ISSA
 * 19/5/18
 * level 2
 */
public class LevelTwo implements LevelInformation {
    /**
     * return the numbers of the balls.
     * @return number of balls.
     */
    public int numberOfBalls() {
        return 10;
    }

    /**
     * // The initial velocity of each ball.
     // Note that initialBallVelocities().size() == numberOfBalls()
     * @return list of velocity.
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> theVelocity = new ArrayList<>();
        for (int i = -50; i < 51; i += 10) {
            if (i == 0) {
                continue;
            }
            theVelocity.add(Velocity.fromAngleAndSpeed(i, 5));
        }
        return theVelocity;
    }

    /**
     * paddle speed.
     * @return paddle speed.
     */
    public int paddleSpeed() {
        return 5;
    }

    /**
     * paddle width.
     * @return paddle width.
     */
    public int paddleWidth() {
        return 500;
    }

    /**
     * // the level name will be displayed at the top of the screen.
     * @return the string.
     */
    public String levelName() {
        return "Wide Easy";
    }

    /**
     * background.
     * @return // Returns a sprite with the background of the level
     */
    public Sprite getBackground() {
        return new Background2();
    }

    /**
     * // The Blocks that make up this level, each block contains
     // its size, color and location.
     * @return list of the blocks.
     */
    public List<Block> blocks() {
        List<Block> theBlocks = new ArrayList<>();
        theBlocks.add(new Block(new Rectangle(new Point(20.1, 270), 50.7, 20), Color.RED, 1));
        theBlocks.add(new Block(new Rectangle(new Point(70.7, 270), 50.7, 20), Color.RED, 1));
        theBlocks.add(new Block(new Rectangle(new Point(121.3, 270), 50.7, 20), Color.ORANGE, 1));
        theBlocks.add(new Block(new Rectangle(new Point(171.9, 270), 50.7, 20), Color.ORANGE, 1));
        theBlocks.add(new Block(new Rectangle(new Point(222.5, 270), 50.7, 20), Color.YELLOW, 1));
        theBlocks.add(new Block(new Rectangle(new Point(273.1, 270), 50.7, 20), Color.YELLOW, 1));
        theBlocks.add(new Block(new Rectangle(new Point(323.7, 270), 50.7, 20), Color.GREEN, 1));
        theBlocks.add(new Block(new Rectangle(new Point(374.3, 270), 50.7, 20), Color.GREEN, 1));
        theBlocks.add(new Block(new Rectangle(new Point(424.9, 270), 50.7, 20), Color.GREEN, 1));
        theBlocks.add(new Block(new Rectangle(new Point(475.5, 270), 50.7, 20), Color.BLUE, 1));
        theBlocks.add(new Block(new Rectangle(new Point(526.1, 270), 50.7, 20), Color.BLUE, 1));
        theBlocks.add(new Block(new Rectangle(new Point(576.7, 270), 50.7, 20), Color.PINK, 1));
        theBlocks.add(new Block(new Rectangle(new Point(627.3, 270), 50.7, 20), Color.PINK, 1));
        theBlocks.add(new Block(new Rectangle(new Point(677.9, 270), 50.7, 20), Color.CYAN, 1));
        theBlocks.add(new Block(new Rectangle(new Point(728.5, 270), 50.7, 20), Color.CYAN, 1));
        return theBlocks;
    }

    /**
     * // Number of levels that should be removed
     // before the level is considered to be "cleared".
     // This number should be <= blocks.size();
     * @return number of block to removed.
     */
    public int numberOfBlocksToRemove() {
        return 15;
    }
}
