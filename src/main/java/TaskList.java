import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final ArrayList<Task> tasks;
    protected static final String LINE_STRING = "____________________________________________________________\n";

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addToList(Task newTask) {
        this.addToList(newTask,true);
    }

    public void addToList(Task newTask, Boolean isPrinting) {
        tasks.add(newTask);
        if (isPrinting) {
            addToListMessage(tasks.size() - 1);
        }
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
        completeTask(Integer.parseInt(s), true);
    }

    public int getSize() {
        return tasks.size();
    }

    public void completeTask(int number) {
        completeTask(number, true);
    }

    public void completeTask(int number, boolean isPrinting) {
        int index = number - 1; // adjust for the list label starting from 1
        tasks.get(index).isDone(true);
        if (isPrinting) {
            completeTaskMessage(index);
        }
    }

    private void completeTaskMessage(int index) {
        System.out.print(LINE_STRING);
        System.out.println("Task marked as done, gg ez");
        System.out.println("  " + this.getStatus(index));
        System.out.println(LINE_STRING);
    }

    public void deleteTask(String s) {
        deleteTask(Integer.parseInt(s));
    }

    public void deleteTask(int i) {
        int index = i - 1; // adjust for the list label starting from 1
        deleteTaskMessage(index);
        tasks.remove(index);
    }

    private void deleteTaskMessage(int index) {
        System.out.print(LINE_STRING);
        System.out.println("You are a quitter 👎 Anyways, I removed this:");
        System.out.println("  " + this.getStatus(index));
        System.out.println((tasks.size() - 1) + " tasks left in the list.");
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
        String label = string.replaceFirst(commandType, "").trim();
        if (label.isEmpty()) {
            throw new NoCommandLabelException();
        }
        return label;
    }

    public List<String> saveTaskList() {
        List<String> taskStrings = new ArrayList<>();
        for (Task task: tasks) {
            taskStrings.add(task.formatSaveTask());
        }
        return taskStrings;
    }
}
