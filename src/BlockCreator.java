/**
 *@author ISSA  10/6/18
 * blockCtretor
 */
public interface BlockCreator {
    /**
     * create a block.
     * @param xpos start x point.
     * @param ypos start y point.
     * @return a block.
     */
    Block create(int xpos, int ypos);
}
