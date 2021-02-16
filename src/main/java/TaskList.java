import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    protected static final String LINE_STRING = "____________________________________________________________\n";

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addToList(Task newTask) {
        tasks.add(newTask);
        addToListMessage(tasks.size()-1);
    }

    private void addToListMessage(int index) {
        System.out.print(LINE_STRING);
        System.out.println("Say no more fam. The task is added:\n  " + this.getStatus(index));
        System.out.println((index + 1) + " tasks in the list.");
        System.out.println(LINE_STRING);
    }

    public void printList() {
        System.out.print(LINE_STRING);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i+1) + "." + this.getStatus(i));
        }
        System.out.println(LINE_STRING);
    }


    private String getStatus(int index) {
        return tasks.get(index).getTypeString() + tasks.get(index).getCheckbox() +
                " " + tasks.get(index).toString();
    }

    public void completeTask(String s) {
        completeTask(Integer.parseInt(s));
    }

    // Set flag isDone in Task class of index = number - 1
    public void completeTask(int i) {
        int index = i- 1; // adjust for the list label starting from 1
        tasks.get(index).isDone(true);
        completeTaskMessage(index);
    }

    private void completeTaskMessage(int index) {
        System.out.print(LINE_STRING);
        System.out.println("Task marked as done, gg ez");
        System.out.println("  " + this.getStatus(index));
        System.out.println(LINE_STRING);
    }

    public void addTodo(String command) throws NoCommandLabelException {
        String commandType = Command.TODO.name().toLowerCase();
        String label = getLabel(command,commandType);

        addToList(new Todo(label));
    }

    /**
     * Phrases command into label and startTime parts
     * Followed by creating event object
     */
    public void addEvent(String command) throws NoCommandLabelException, NoCommandFormatException {
        final String TIME_MARKER = "/at";
        String commandType = Command.EVENT.name().toLowerCase();
        String[] commandArray = command.split(TIME_MARKER);

        if (commandArray.length < 2) {
            throw new NoCommandFormatException();
        }

        String label = getLabel(commandArray[0],commandType);
        String startTime = commandArray[1].trim();

        addToList(new Event(label, startTime));
    }

    /**
     * Phrases command into label and dueTime parts
     * Followed by creating Deadline object
     */
    public void addDeadline(String command) throws NoCommandLabelException, NoCommandFormatException {
        final String TIME_MARKER = "/by";
        String commandType = Command.DEADLINE.name().toLowerCase();
        String[] commandArray = command.split(TIME_MARKER);

        if (commandArray.length < 2) {
            throw new NoCommandFormatException();
        }

        String label = getLabel(commandArray[0],commandType);
        String dueTime = commandArray[1].trim();

        addToList(new Deadline(label, dueTime));
    }

    private static String getLabel(String string, String commandType) throws NoCommandLabelException {
        String label = string.replaceFirst(commandType,"").trim();
        if (label.isEmpty()) {
            throw new NoCommandLabelException();
        }
        return label;
    }
}
