/**
 * @author ISSA
 * 19/5/18
 * hitlistener interface
 */
public interface HitNotifier {
    /**
     * // Add hl as a listener to hit events.
     * @param hl object of hit listener
     */
    void addHitListener(HitListener hl);

    /**
     * // Remove hl from the list of listeners to hit events.
     * @param hl object of hit listener.
     */
    void removeHitListener(HitListener hl);
}