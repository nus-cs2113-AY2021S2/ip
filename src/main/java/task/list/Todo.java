package task.list;

/**
 * Represents a todo task in the list
 */
public class Todo extends TaskList {

    private static final String TASK_TITLE = "T";
    public static final String NEW_TASK = "Aight Crewmate!! I've got a new task for you to do!!! (¬‿¬): ";
    public static final String COMMAND_HEADER = "todo ";

    // creates a todo task
    public Todo(String line) {
        this.description = line.trim();
        this.isDone = false;
        printAddedTask();
    }

    // overloads constructor as subclasses will not have the same constructor
    public Todo() {
    }

    @Override
    public void printAddedTask() {
        System.out.println(NEW_TASK + getTaskDescription());
    }

    @Override
    public void printTask() {
        String phrase = "[" + TASK_TITLE + "]" + "[" + getStatusIcon() + "]"
                + getTaskDescription();
        System.out.println(phrase);
    }

    @Override
    public String getTaskToPrintInFile() {
        return (COMMAND_HEADER + this.description + System.lineSeparator() + this.isDone + System.lineSeparator());
    }
}