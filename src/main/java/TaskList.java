import Task.Deadline;
import Task.Event;
import Task.Task;
import Task.Todo;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages list of tasks stored by Bob
 * Stores the ui of Bob class for easier reference
 */

public class TaskList {
    private final ArrayList<Task> tasks;
    private Ui ui;

    private boolean isEmpty() {
        return tasks.isEmpty();
    }

    public TaskList(Ui ui) {
        this.tasks = new ArrayList<>();
        this.ui = ui;
    }

    /**
     * Takes taskStrings and adds them to tasks
     * @param taskStrings list of strings read from saved file
     */
    public void load(List<String> taskStrings) {
        try {
            for (String taskString: taskStrings) {
                loadTask(taskString);
            }
        } catch (NoSuchMethodException e) {
            ui.printNoSuchMethod();
        } catch (NumberFormatException e) {
            ui.printNumberFormatException();
        }
    }

    private void loadTask(String taskString) throws NoSuchMethodException, NumberFormatException{
        String[] taskStringArray = taskString.split(Storage.DATA_DELIMITER);
        Command command = Parser.getCommandType(taskStringArray[0]);
        boolean isDone = Integer.parseInt(taskStringArray[1]) == 1;
        String label = taskStringArray[2];
        String time = taskStringArray[3];

        switch (command) {
        case TODO:
            addToList(new Todo(label),false);
            break;
        case DEADLINE:
            addToList(new Deadline(label, time),false);
            break;
        case EVENT:
            addToList(new Event(label, time),false);
            break;
        }

        if (isDone) {
            completeTask(getSize(), false);
        }
    }

    /**
     * Converts tasks to a string format to be written into saved file
     * @return Save file formatted list of strings
     */
    public List<String> saveTaskList() {
        List<String> taskStrings = new ArrayList<>();
        for (Task task: tasks) {
            taskStrings.add(task.formatSaveTask());
        }
        return taskStrings;
    }

    /**
     * Adds a task to tasks
     * Assumes that user wants printed output
     * @param newTask Task to be added
     */
    public void addToList(Task newTask) {
        this.addToList(newTask,true);
    }

    /**
     * Adds a task to tasks
     * Gives the choice of having printed output
     * @param newTask Task to be added
     * @param isPrinting Flag to check if output is desired
     */
    public void addToList(Task newTask, Boolean isPrinting) {
        tasks.add(newTask);
        if (isPrinting) {
            ui.printAddToList(tasks.size(), getStatus(tasks.size() - 1));
        }
    }

    /**
     * Prints a list of tasks
     * Prints an error message if no tasks are found
     */
    public void printList() {
        if (this.isEmpty()) {
            ui.printNoResultsFound();
        } else {
            ui.printList(this);
        }
    }

    /**
     * Outputs a formatted version task to be printed
     * @param index index of task in tasks to be printed
     * @return String of task details
     */
    public String getStatus(int index) {
        return tasks.get(index).getType() + tasks.get(index).getCheckbox() +
                " " + tasks.get(index).toString();
    }

    /**
     * Gets size of tasks since the ArrayList is private
     * @return size of task list
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Parses String to int for completeTask
     * @param s String of index
     * @throws IndexOutOfBoundsException If index is out of tasks range
     */
    public void completeTask(String s) throws IndexOutOfBoundsException {
        completeTask(Integer.parseInt(s), true);
    }

    /**
     * Completes a task and prints a completion message
     * @param number index of task + 1
     * @param isPrinting Flag to check if output is desired
     * @throws IndexOutOfBoundsException If index is out of tasks range
     */
    public void completeTask(int number, boolean isPrinting) throws IndexOutOfBoundsException {
        int index = number - 1; // adjust for the list label starting from 1
        tasks.get(index).isDone(true);
        if (isPrinting) {
            ui.printCompleteTask(getStatus(index));
        }
    }

    /**
     * Parses String to int for deleteTask
     * @param s String of index
     * @throws IndexOutOfBoundsException If index is out of tasks range
     */
    public void deleteTask(String s) throws IndexOutOfBoundsException {
        deleteTask(Integer.parseInt(s));
    }

    /**
     * Deletes a task and prints a deletion message
     * @param number index of task + 1
     * @throws IndexOutOfBoundsException If index is out of tasks range
     */
    public void deleteTask(int number) throws IndexOutOfBoundsException {
        int index = number - 1; // adjust for the list label starting from 1
        String status = getStatus(index);
        tasks.remove(index);
        ui.printDeleteTask(status, this.getSize());
    }

    /**
     * Searches tasks for label that contains query
     * Prints the result of matches
     * @param query String user wants to match
     */
    public void findTask(String query) {
        TaskList results = new TaskList(ui);

        for (Task task: tasks) {
            if (task.getLabel().contains(query)) {
                results.addToList(task,false);
            }
        }

        if (results.isEmpty()) {
            ui.printNoResultsFound();
        } else {
            ui.printFindTask(results, query);
        }

    }

    /**
     * Creates a Todo and adds it to tasks
     * @param command User arguments to create the Todo
     * @throws NoTaskLabelException If label field is not found in command
     */
    public void addTodo(String command) throws NoTaskLabelException {
        String commandType = Command.TODO.name().toLowerCase();
        String label = getLabel(command,commandType);

        addToList(new Todo(label));
    }

    /**
     * Creates a Event and adds it to tasks
     * @param command User arguments to create the Event
     * @throws NoTaskLabelException If label field is not found in command
     * @throws NoCommandFormatException If /at is not found in command
     */
    public void addEvent(String command) throws NoTaskLabelException, NoCommandFormatException {
        final String TIME_MARKER = "/at";
        String commandType = Command.EVENT.name().toLowerCase();
        String[] commandArray = command.split(TIME_MARKER);

        if (commandArray.length < 2) {
            throw new NoCommandFormatException();
        }

        String label = getLabel(commandArray[0],commandType);
        String commandTime = commandArray[1].trim();
        String startTime = DateParser.readTime(commandTime);

        addToList(new Event(label, startTime));
    }

    /**
     * Creates a Deadline and adds it to tasks
     * @param command User arguments to create the Deadline
     * @throws NoTaskLabelException If label field is not found in command
     * @throws NoCommandFormatException If /by is not found in command
     */
    public void addDeadline(String command) throws NoTaskLabelException, NoCommandFormatException {
        final String TIME_MARKER = "/by";
        String commandType = Command.DEADLINE.name().toLowerCase();
        String[] commandArray = command.split(TIME_MARKER);

        if (commandArray.length < 2) {
            throw new NoCommandFormatException();
        }

        String label = getLabel(commandArray[0],commandType);
        String commandTime = commandArray[1].trim();
        String dueTime = DateParser.readTime(commandTime);

        addToList(new Deadline(label, dueTime));
    }

    private static String getLabel(String string, String commandType) throws NoTaskLabelException {
        String label = string.replaceFirst(commandType, "").trim();
        if (label.isEmpty()) {
            throw new NoTaskLabelException();
        }
        return label;
    }
}
