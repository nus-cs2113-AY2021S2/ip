public class ListItem {
    /**
     * The status of the list item.
     */
    private boolean isDone;
    /**
     * The content of the list item.
     */
    private String itemContent;

    /**
     * Initializes the list item object with given parameter as content.
     *
     * @param listContent The content of the list item.
     */
    public ListItem(String listContent) {
        this.isDone = false;
        this.itemContent = listContent;
    }

    /**
     * Initializes the list item object with content of an empty string.
     */
    public ListItem() {
        this.isDone = false;
        this.itemContent = "";
    }

    /**
     * Returns the status of the list item.
     *
     * @return the status of the list item.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Sets a new status of the list item.
     *
     * @param done the new status of the list item.
     */
    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * Returns the content of the list item.
     *
     * @return the content of the list item.
     */
    public String getItemContent() {
        return itemContent;
    }

    /**
     * Sets a new content of the list item.
     *
     * @param itemContent the new content of the list item.
     */
    public void setItemContent(String itemContent) {
        this.itemContent = itemContent;
    }
}
