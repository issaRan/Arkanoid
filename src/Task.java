/**
 * generic task.
 * @param <T>
 */
public interface Task<T> {
    /**
     * run the chosen task.
     * @return T
     */
    T run();
}
