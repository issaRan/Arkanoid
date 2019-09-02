import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ISSA
 * 21/4/18
 * the game class used to create the game with the paddle and ball.
 */
public class GameLevel implements Animation {
    private GUI gui;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter howManyBlocks;
    private Counter howManyBalls;
    private Counter score;
    private AnimationRunner runner;
    private boolean running;
    private ScoreIndicator takestheScore;
    private LivesIndicator takeTheLives;
    private LevelInformation theLevel;
    private NameIndicator takeTheName;
    private Counter lives;
    private Paddle thePaddle;
    private KeyboardSensor keyboard;
    private String string;
    private static final int WIDTH_SCREEN = 800;
    private static final int Y_START_PADDLE = 540;
    private static final int HEIGHT_PADDLE = 1;

    /**
     * created the gui, the sprites and the enviroment.
     *
     * @param levelInformation information of the level.
     * @param keyboardSensor   the keyboard.
     * @param animationRunner  the animation runner.
     * @param lives            the lives for the game.
     * @param score            score in the game.
     * @param gui              gui to the screen.
     * @param string           for the level name.
     */
    public GameLevel(LevelInformation levelInformation, KeyboardSensor keyboardSensor,
                     AnimationRunner animationRunner, Counter lives, Counter score, GUI gui, String string) {
        this.gui = gui;
        this.runner = new AnimationRunner(gui);
        this.theLevel = levelInformation;
        this.runner = animationRunner;
        this.environment = new GameEnvironment();
        this.sprites = new SpriteCollection();
        this.howManyBlocks = new Counter();
        this.howManyBalls = new Counter();
        this.score = score;
        this.lives = lives;
        this.string = string;
        this.keyboard = keyboardSensor;
        // the Name and live is duplicated ( Maybe ).
        this.takeTheName = new NameIndicator(string);
        this.takeTheLives = new LivesIndicator(lives);
        this.takestheScore = new ScoreIndicator(score);
    }

    /**
     * add an Collidable object to the environment.
     *
     * @param c prameter of collidable.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * adding sprite to the sprite collection.
     *
     * @param s takes as the parameter as Sprite.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * remove a collidable object.
     *
     * @param c a collidable object.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeTheCollidable(c);

    }

    /**
     * remove a sprite object.
     *
     * @param s a sprite object.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * // Initialize a new game: create the Blocks and Ball (and Paddle)
     * // and add them to the game.
     */
    public void initialize() {
        BlockRemover blockRemover = new BlockRemover(this, this.howManyBlocks);
        BallRemover ballRemover = new BallRemover(this, this.howManyBalls);
        ScoreTrackingListener theScore = new ScoreTrackingListener(this.score);
        LivesIndicator livesIndicator = new LivesIndicator(this.lives);
        NameIndicator theName = new NameIndicator(this.string);
        addSprite(this.theLevel.getBackground());
        this.sprites.addSprite(livesIndicator);
        takestheScore.addToGame(this);
        livesIndicator.addToGame(this);
        theName.addToGame(this);
        this.keyboard = this.gui.getKeyboardSensor();
        this.thePaddle = new Paddle(keyboard,
                new Rectangle(new Point(WIDTH_SCREEN / 2 - theLevel.paddleWidth() / 2, Y_START_PADDLE),
                        theLevel.paddleWidth(), HEIGHT_PADDLE), theLevel.paddleSpeed());
        thePaddle.addToGame(this);
        for (Block block : this.theLevel.blocks()) {
            block.addToGame(this);
            block.addHitListener(blockRemover);
            block.addHitListener(theScore);
        }
        this.howManyBlocks.increase(this.theLevel.numberOfBlocksToRemove());
        Block block2 = new Block(new Rectangle(new Point(0, 20), 800, 20), Color.gray, 0);
        block2.addToGame(this);
        Block block3 = new Block(new Rectangle(new Point(775, 40), 25, 600), Color.gray, 0);
        block3.addToGame(this);
        Block block4 = new Block(new Rectangle(new Point(0, 20), 25, 580), Color.gray, 0);
        block4.addToGame(this);
        //up
        Block block5 = new Block(new Rectangle(new Point(0, 600), 800, 1), Color.gray, 0);
        addCollidable(block5);
        block5.addHitListener(ballRemover);
    }

    /**
     * contain the logic of the game.
     * @param dt delta time.
     * @param d used the drawSurface package.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed(dt);
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new PauseScreen(this.keyboard));
        }

        if (this.howManyBlocks.getValue() == 0) {
            score.increase(100);
            this.running = false;
        }
        if (this.howManyBalls.getValue() == 0) {
            if (lives.getValue() == -1) {
                gui.close();
            }
            this.removeSprite(thePaddle);
            this.removeCollidable(thePaddle);
            this.thePaddle = new Paddle(this.keyboard, new Rectangle(
                    new Point(WIDTH_SCREEN / 2 - theLevel.paddleWidth() / 2, Y_START_PADDLE),
                    theLevel.paddleWidth(), HEIGHT_PADDLE), theLevel.paddleSpeed());
            this.thePaddle.addToGame(this);
            lives.decrease(1);
            running = false;
        }
    }

    /**
     * when the program should start.
     *
     * @return boolean.
     */
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * how many block we got.
     *
     * @return the number of the blocks.
     */
    public int howManyBlocks() {
        return this.howManyBlocks.getValue();
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void playOneTurn() {
        this.createBallsOnTopOfPaddle();
        this.runner.run(new CountdownAnimation(1, 3, this.sprites, 800, 600));
        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);
    }

    /**
     * created the balls on the top of the paddle.
     */
    public void createBallsOnTopOfPaddle() {
        List<Ball> theBalls = new ArrayList<>();
        List<Velocity> theVelocity = this.theLevel.initialBallVelocities();
        for (int i = 0; i < theVelocity.size(); i++) {
            //this.the.add(new Ball(new Point(400, 525), 5, Color.WHITE, this.environment));
            theBalls.add(new Ball(new Point(400, 525), 5, Color.white, this.environment));
            theBalls.get(i).addToGame(this);
            theBalls.get(i).setVelocity(theVelocity.get(i));
        }
        this.howManyBalls.increase(this.theLevel.numberOfBalls());
    }
}