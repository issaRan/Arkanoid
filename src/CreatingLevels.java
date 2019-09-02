import java.util.List;

/**
 * @author ISSA 10/06/18. generic level.
 */
public class CreatingLevels implements LevelInformation {
    private int numberBlocks;
    private int balls;
    private String levelName;
    private List<Velocity> velocities;
    private int speedPaddle;
    private int widthPaddle;
    private List<Block> blocks;
    private Sprite imageForBackground;

    /**
     * how many blocks.
     *
     * @param numberOfblocks amount of blocks.
     */
    public void setNumberOfblocks(int numberOfblocks) {
        this.numberBlocks = numberOfblocks;
    }

    /**
     * set the veolicty for the balls.
     *
     * @param velocities1 the velocity.
     */
    public void setInitialBallVelocities(List<Velocity> velocities1) {
        this.velocities = velocities1;
    }

    /**
     * paddle speed.
     *
     * @param speed speed paddle.
     */
    public void setPaddleSpeed(int speed) {
        this.speedPaddle = speed;
    }

    /**
     * paddle width.
     *
     * @param width paddle width.
     */
    public void setPaddleWidth(int width) {
        this.widthPaddle = width;
    }

    /**
     * set the level of the name.
     *
     * @param name the level name of the level.
     */
    public void setlevelName(String name) {
        this.levelName = name;
    }

    /**
     * set imageforbackground.
     *
     * @param imageForBackground1 the image after convert to image from string.
     */
    public void setGetBackground(Sprite imageForBackground1) {
        this.imageForBackground = imageForBackground1;
    }

    /**
     * list of blocks.
     *
     * @param theBlocks list of blocks.
     */
    public void setBlocks(List<Block> theBlocks) {
        this.blocks = theBlocks;
    }

    /**
     * numbers of balls.
     *
     * @return numbers of balls.
     */

    public int numberOfBalls() {
        return this.velocities.size();
    }

    /**
     * // The initial velocity of each ball.
     * // Note that initialBallVelocities().size() == numberOfBalls()
     *
     * @return the list of velocity.
     */
    public List<Velocity> initialBallVelocities() {
        return this.velocities;
    }

    /**
     * paddle speed.
     *
     * @return speed paddle.
     */
    public int paddleSpeed() {
        return this.speedPaddle;
    }

    /**
     * paddle width.
     *
     * @return paddle width.
     */
    public int paddleWidth() {
        return this.widthPaddle;
    }

    /**
     * // the level name will be displayed at the top of the screen.
     *
     * @return the level name of the level.
     */
    public String levelName() {
        return this.levelName;
    }

    /**
     * // Returns a sprite with the background of the level.
     *
     * @return the background.
     */
    // Returns a sprite with the background of the level
    public Sprite getBackground() {
        return this.imageForBackground;
    }

    /**
     * // The Blocks that make up this level, each block contains
     * // its size, color and location.
     *
     * @return list of blocks.
     */
    // The Blocks that make up this level, each block contains
    // its size, color and location.
    public List<Block> blocks() {
        return this.blocks;
    }

    // Number of levels that should be removed
    // before the level is considered to be "cleared".

    /**
     * // Number of levels that should be removed
     * // before the level is considered to be "cleared".
     * // This number should be <= blocks.size();
     *
     * @return numbers of blocks.
     */// This number should be <= blocks.size();
    public int numberOfBlocksToRemove() {
        return this.numberBlocks;
    }
}