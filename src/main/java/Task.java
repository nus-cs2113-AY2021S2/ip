public class Task {
    private String item;
    private int count;

    public Task(String item, int count) {
        this.item = count + ". " + item;
        this.count = count;
    }

    public String getItem() {
        return item;
    }
}
