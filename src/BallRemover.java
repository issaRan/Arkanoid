/**
 * @author ISSA
 * 19/5/18
 * remove the ball.
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter amountBalls;

    /**
     * Constructor.
     * @param game the game which occur.
     * @param amountBalls object that pointer to the amountBalls.
     */
    public BallRemover(GameLevel game, Counter amountBalls) {
        this.game = game;
        this.amountBalls = amountBalls;
    }

    /**
     * function apply when the ball should remove.
     * @param beingHit the block that the hit occur.
     * @param hitter the ball hitter.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        this.amountBalls.decrease(1);
    }
}
