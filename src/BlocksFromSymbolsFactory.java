import java.util.Map;

/**
 * @author ISSA 10/06/18
 */
public class BlocksFromSymbolsFactory {
    private Map<String, Integer> spacerWidths;
    private Map<String, BlockCreator> blockCreators;

    /**
     * constructor.
     *
     * @param spacers map that point to space between the empty.
     * @param blocks  a map that used to define a block according his symbol he stored.
     */
    public BlocksFromSymbolsFactory(Map<String, Integer> spacers, Map<String, BlockCreator> blocks) {
        this.spacerWidths = spacers;
        this.blockCreators = blocks;
    }

    /**
     * function that used to check if the symbol is in the map.
     *
     * @param s a char that indicate to space.
     * @return true or false.
     */
    public boolean isSpaceSymbol(String s) {
        return this.spacerWidths.containsKey(s);
    }

    /**
     * used to check if the block is in the map according to the symbol.
     *
     * @param s char that point to the block
     * @return true or false
     */
    public boolean isBlockSymbol(String s) {
        return this.blockCreators.containsKey(s);
    }

    /**
     * // Return a block according to the definitions associated
     * // with symbol s. The block will be located at position (xpos, ypos).
     * @param s symbol
     * @param xpos start x
     * @param ypos stary y
     * @return a block.
     */
    public Block getBlock(String s, int xpos, int ypos) {
        Block block = this.blockCreators.get(s).create(xpos, ypos);
        return block;
    }

    /**
     * // Returns the width in pixels associated with the given spacer-symbol.
     * @param s symbol.
     * @return the space.
     */
    public int getSpaceWidth(String s) {
        return this.spacerWidths.get(s);
    }
}
