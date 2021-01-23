public class Task {

    public String name;

    public boolean isDone = false;

    @Override
    public String toString() {
        return name;
    }

    public Task (String nameInit) {
        this.name = nameInit;
    }

}