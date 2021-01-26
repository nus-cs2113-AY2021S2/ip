public class ListItem {
    private boolean isDone;
    private String itemContent;

    public ListItem(String listContent) {
        this.isDone = false;
        this.itemContent = listContent;
    }

    public ListItem() {
        this.isDone = false;
        this.itemContent = "";
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String getItemContent() {
        return itemContent;
    }

    public void setItemContent(String itemContent) {
        this.itemContent = itemContent;
    }
}
