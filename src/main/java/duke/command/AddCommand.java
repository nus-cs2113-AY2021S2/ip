package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.error.EmptyNameFieldException;
import duke.error.WrongFormatException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;

public class AddCommand extends Command {
    private static final int ERR_NO_NAME = -4;
    private static final int ERR_WRONG_FORMAT_MESSAGE = -2;
    private static final int ADD_TODO = 4;
    private static final int ADD_DEADLINE = 5;
    private static final int ADD_EVENT = 6;

    private final TaskList taskList;
    private final String input;
    private final Ui ui;

    public AddCommand(String input, TaskList taskList, Ui ui) {
        this.input = input;
        this.taskList = taskList;
        this.ui = ui;
    }

    @Override
    public void execute(Ui ui) {
        ArrayList<Task> Tasks = taskList.getTasks();
        addItem(this.input, Tasks);
    }
    /**
     * Parses type of item to add and calls appropriate method.
     *
     * @param line raw input given by user.
     */
    public void addItem(String line, ArrayList<Task> Tasks) {
        try {
            String prefix = line.split(" ")[0];
            int itemType = getItemType(prefix);
            // No fallthrough required
            switch (itemType) {
            case ADD_TODO:
                addTodo(line, Tasks);
                break;
            case ADD_DEADLINE:
                addDeadline(line, Tasks);
                break;
            case ADD_EVENT:
                addEvent(line, Tasks);
            }
            ui.printBorderLine();
        } catch (WrongFormatException e) {
            ui.printError(ERR_WRONG_FORMAT_MESSAGE);
        } catch (EmptyNameFieldException e) {
            ui.printError(ERR_NO_NAME);
        }
    }
    /**
     * Parses the type of task to add to list.
     *
     * @param line input from user.
     * @return Type of task to add if valid input, error otherwise.
     * @throws WrongFormatException If type of task is invalid.
     */
    public static int getItemType(String line) throws WrongFormatException {
        if (line.equalsIgnoreCase("todo")) {
            return ADD_TODO;
        }
        if (line.equalsIgnoreCase("deadline")) {
            return ADD_DEADLINE;
        }
        if (line.equalsIgnoreCase("event")) {
            return ADD_EVENT;
        }
        throw new WrongFormatException();
    }
    public void addTodo(String line, ArrayList<Task> tasks) throws EmptyNameFieldException {
        if (line.length() < 6) {
            throw new EmptyNameFieldException();
        }
        int current = Task.totalNumberOfTasks;
        String nameOfTask = line.substring(5);
        tasks.add(new Todo(nameOfTask));
        Task.totalNumberOfTasks += 1;
        this.ui.printAddedToList(current, tasks);
    }
    public void addDeadline(String line, ArrayList<Task> tasks) throws EmptyNameFieldException, WrongFormatException {
        if (line.length() < 10) {
            throw new EmptyNameFieldException();
        }
        if (line.contains("/by")) {
            int current = Task.totalNumberOfTasks;
            // Details of task starts at index 9 of input
            String nameAndDeadline = line.substring(9);
            String[] split = nameAndDeadline.split(" /by ");
            String name = split[0];
            String deadline = split[1];
            tasks.add(new Deadline(name, deadline));
            Task.totalNumberOfTasks += 1;
            this.ui.printAddedToList(current, tasks);
        } else {
            throw new WrongFormatException();
        }
    }
    public void addEvent(String nameOfTask, ArrayList<Task> tasks) throws EmptyNameFieldException, WrongFormatException {
        if (nameOfTask.length() < 7) {
            throw new EmptyNameFieldException();
        }
        if (nameOfTask.contains("/at")) {
            int current = Task.totalNumberOfTasks;
            // Details of task starts at index 6 of input
            String nameAndTime = nameOfTask.substring(6);
            String[] split = nameAndTime.split(" /at ");
            String name = split[0];
            String time = split[1];
            tasks.add(new Event(name, time));
            Task.totalNumberOfTasks += 1;
            this.ui.printAddedToList(current, tasks);
        } else {
            throw new WrongFormatException();
        }
    }
}
