public class Task {

    private final String name;
    private boolean isDone;

    public Task(String name) {
        this.name = name;
        setDone(false);
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getName() {
        return name;
    }

    public boolean getDone() {
        return isDone;
    }

    /**
     * Prints task status.
     */
    public void printStatus() {
        if (isDone) {
            System.out.print("[✓] " + name);
        } else {
            System.out.print("[ ] " + name);
        }
    }

}
