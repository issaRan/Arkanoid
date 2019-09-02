/**
 * @param <T> the task
 * @author ISSA
 */
public interface Menu<T> extends Animation {
    /**
     * the selcetion.
     *
     * @param key       the key.
     * @param message   the message.
     * @param returnVal the task.
     */
    void addSelection(String key, String message, T returnVal);

    /**
     * the task.
     *
     * @return the task.
     */
    T getStatus();

    /**
     * sub menu.
     *
     * @param key     the key.
     * @param message the message.
     * @param subMenu the submenu.
     */
    void addSubMenu(String key, String message, Menu<T> subMenu);
}
