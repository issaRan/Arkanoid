public class SetLevel {
    private String message;
    private String key;
    private String levelDefinitionPath;

    /**
     * Gets the message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message.
     *
     * @param msg the new message
     */
    public void setMessage(String msg) {
        this.message = msg;
    }

    /**
     * Gets the key.
     *
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * Sets the key.
     *
     * @param k the new key
     */
    public void setKey(String k) {
        this.key = k;
    }

    /**
     * Gets the level definition path.
     *
     * @return the level definition path
     */
    public String getLevelDefinitionPath() {
        return levelDefinitionPath;
    }

    /**
     * Sets the level definition path.
     *
     * @param lvlDefinitionPath the new level definition path
     */
    void setLevelDefinitionPath(String lvlDefinitionPath) {
        this.levelDefinitionPath = lvlDefinitionPath;
    }
}