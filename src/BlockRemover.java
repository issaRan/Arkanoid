/**
 * @author ISSA
 * 19/5/18
 * remove the ball.
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * Constructor.
     *
     * @param game          the game which occur.
     * @param removedBlocks object that pointer to the amountBlocks.
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * // Blocks that are hit and reach 0 hit-points should be removed
     * // from the game. Remember to remove this listener from the block
     * // that is being removed from the game.
     *
     * @param beingHit the block.
     * @param hitter   the ball
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        //System.out.println("A Block with " + beingHit.getHitPoints() + " points was hit.");
        if (beingHit.getHitPoints() == 0) {
            beingHit.removeFromGame(this.game);
            beingHit.removeHitListener(this);
            this.remainingBlocks.decrease(1);
        }
    }
}