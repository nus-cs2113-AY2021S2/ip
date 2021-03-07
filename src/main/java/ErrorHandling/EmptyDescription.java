package ErrorHandling;

public class EmptyDescription extends Exception {
    String task;

    public EmptyDescription(String task) {
        this.task = task;
    }

    public String TaskName() {
        return task;
    }
}
