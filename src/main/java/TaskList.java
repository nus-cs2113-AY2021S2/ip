import Task.Deadline;
import Task.Event;
import Task.Task;
import Task.Todo;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final ArrayList<Task> tasks;
    private Ui ui;

    public TaskList(Ui ui) {
        this.tasks = new ArrayList<>();
        this.ui = ui;
    }

    public void addToList(Task newTask) {
        this.addToList(newTask,true);
    }

    public void addToList(Task newTask, Boolean isPrinting) {
        tasks.add(newTask);
        if (isPrinting) {
            ui.addToListMessage(tasks.size(), getStatus(tasks.size() - 1));
        }
    }

    public void printList() {
        ui.printList(this);
    }

    public String getStatus(int index) {
        return tasks.get(index).getTypeString() + tasks.get(index).getCheckbox() +
                " " + tasks.get(index).toString();
    }

    public int getSize() {
        return tasks.size();
    }

    public void completeTask(String s) throws IndexOutOfBoundsException {
        completeTask(Integer.parseInt(s), true);
    }

    public void completeTask(int number) throws IndexOutOfBoundsException {
        completeTask(number, true);
    }

    public void completeTask(int number, boolean isPrinting) throws IndexOutOfBoundsException {
        int index = number - 1; // adjust for the list label starting from 1
        tasks.get(index).isDone(true);
        if (isPrinting) {
            ui.completeTaskMessage(getStatus(index));
        }
    }

    public void deleteTask(String s) throws IndexOutOfBoundsException {
        deleteTask(Integer.parseInt(s));
    }

    public void deleteTask(int i) throws IndexOutOfBoundsException {
        int index = i - 1; // adjust for the list label starting from 1
        String status = getStatus(index);
        tasks.remove(index);
        ui.deleteTaskMessage(status, this.getSize());
    }

    public void findTask(String query) {
        TaskList results = new TaskList(ui);

        for (Task task: tasks) {
            if (task.getLabel().contains(query)) {
                results.addToList(task,false);
            }
        }

        if (tasks.isEmpty()) {

        } else {
            ui.printDeleteTask(results, query);
        }

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
     * Followed by creating Task.Deadline object
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

    public List<String> saveTaskList() {
        List<String> taskStrings = new ArrayList<>();
        for (Task task: tasks) {
            taskStrings.add(task.formatSaveTask());
        }
        return taskStrings;
    }
}
