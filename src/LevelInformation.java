import java.util.List;

/**
 * interface.
 */
public interface LevelInformation {
    /**
     * numbers of balls.
     *
     * @return numbers of balls.
     */

    int numberOfBalls();
    /**
     * // The initial velocity of each ball.
     * // Note that initialBallVelocities().size() == numberOfBalls()
     *
     * @return the list of velocity.
     */
    List<Velocity> initialBallVelocities();
    /**
     * paddle speed.
     *
     * @return speed paddle.
     */
    int paddleSpeed();
    /**
     * paddle width.
     *
     * @return paddle width.
     */
    int paddleWidth();
    /**
     * // the level name will be displayed at the top of the screen.
     *
     * @return the level name of the level.
     */
    String levelName();
    /**
     * // Returns a sprite with the background of the level.
     *
     * @return the background.
     */
    // Returns a sprite with the background of the level
    Sprite getBackground();
    /**
     * // The Blocks that make up this level, each block contains
     * // its size, color and location.
     *
     * @return list of blocks.
     */
    // The Blocks that make up this level, each block contains
    // its size, color and location.
    List<Block> blocks();

    // Number of levels that should be removed
    // before the level is considered to be "cleared".

    /**
     * // Number of levels that should be removed
     // before the level is considered to be "cleared".
     // This number should be <= blocks.size();
     * @return numbers of blocks.
     */// This number should be <= blocks.size();
    int numberOfBlocksToRemove();
}
