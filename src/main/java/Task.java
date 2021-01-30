public class Task {

    protected String name;
    protected boolean isDone = false;

    public Task (String nameInit) {
        this.name = nameInit;
    }

    public void setDone() {
        isDone = true;
    }

}
