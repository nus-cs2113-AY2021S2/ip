package tasks;

/**
 * Represents a todo Task
 */
public class ToDos extends Task {

    /**
     * @param task contains the task name
     */
    public ToDos(String task) {

        super(task);
    }

    /** Prints the task with its current status */
    @Override
    public void printStatus() {
        System.out.print("[T]");
        super.printStatus();
        System.out.print("\n");
    }
}
