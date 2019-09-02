/**
 * @param <T> task
 * @author ISSA 10/06/18
 */
public class Selection<T> {
    private String string;
    private String message;
    private T returnVal;

    /**
     * constructor.
     *
     * @param string    string.
     * @param message   message.
     * @param returnVal task.
     */
    public Selection(String string, String message, T returnVal) {
        this.string = string;
        this.message = message;
        this.returnVal = returnVal;
    }
    /*
    public Selection(String string,String message,){
        this.string = string;
        this.message = message;
        this.levelSet = levelSet;
    }
    */

    /**
     * get function.
     *
     * @return return the option
     */
    public String getString() {
        return string;
    }

    /**
     * return the message.
     *
     * @return return the message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * return the task.
     *
     * @return return the task
     */
    public T getReturnVal() {
        return returnVal;
    }
}
