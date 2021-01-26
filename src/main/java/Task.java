public class Task {
    private String item;
    private int index;
    private String status;

    public Task(String item, int index, String status) {
        this.item = item;
        this.index = index;
        this.status = status;
    }

    public String getItem() {
        return this.item;
    }

    public int getIndex() {
        return this.index;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
