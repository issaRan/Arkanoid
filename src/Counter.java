/**
 * @author ISSA
 * 19/5/18
 * remove the ball.
 */
public class Counter {
    private int count;

    /**
     * constructor.
     */
    public Counter() {
        this.count = 0;
    }

    /**
     * // add number to current count.
     * @param number an int.
     */
    public void increase(int number) {
        this.count += number;
    }

    /**
     * // subtract number from current count.
     * @param number an int.
     */
    public void decrease(int number) {
        this.count -= number;
    }

    /**
     * // get current count.
     * @return the the number of the blocks / number.
     */
    public int getValue() {
        return this.count;
    }
}