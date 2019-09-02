/**
 * exit for the game.
 */
public class ExitFromGame implements Task<Void> {
    @Override
    public Void run() {
        System.exit(0);
        return null;
    }
}
