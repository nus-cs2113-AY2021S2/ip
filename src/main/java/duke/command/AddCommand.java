package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.task.*;
import duke.main.*;
import java.util.Arrays;

/**
 * Add task to taskList
 */
public class AddCommand implements Command {
    public AddCommand(String input) {
    }

    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";

    /**
     * Add task to task list
     *
     * @param input
     * @throws DukeException
     */
    public void execute(String input) throws DukeException {

        String[] command = input.trim().split(" ");
        String action = command[0];

        if (!action.equals(TODO) && !action.equals(DEADLINE) && !action.equals(EVENT)) { //to check if the action required is recognised
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        else if (command.length == 1) { //to check if there is more than just a recognised action for todo, deadline and event commands
            throw new DukeException("OOPS!!! The description of a " + action + " cannot be empty.");
        }

        switch (action) {
            case TODO:
                Todo todoTask = new Todo(getDescription(input));
                addTask(todoTask);
                break;
            case DEADLINE:
                Deadline deadlineTask = new Deadline(getDescription(input), getBy(input));
                addTask(deadlineTask);
                break;
            case EVENT:
                Event eventTask = new Event(getDescription(input), getOn(input));
                addTask(eventTask);
                break;
            default:
                System.out.println("This function is not recognized!");
        }

        Ui.commandDone();
    }

    /**
     * Get the descriptions of the tasks
     */

    public String getDescription(String input) {
        if (input.contains("/")) {
            String[] split = input.trim().split("/");
            String[] commandPlusDescription = split[0].trim().split(" ");
            return splitCommand(commandPlusDescription);
        } else {
            String[] commandPlusDescription = input.trim().split(" ");
            return splitCommand(commandPlusDescription);
        }
    }

    /**
     * Split the command to get description of the tasks
     *
     * @param commandPlusDescription
     * @return description of the task
     */
    private String splitCommand(String[] commandPlusDescription) {

        String[] description = new String[commandPlusDescription.length - 1];

        for (int i = 1; i < commandPlusDescription.length; i++) {
            description[i - 1] = commandPlusDescription[i];
        }

        return Arrays.toString(description).replace(",", "")
                .replace("[", "")
                .replace("]", "")
                .trim();
    }

    /**
     * Get the deadline for Deadline tasks
     *
     * @param input description of task
     * @return deadline for Deadline tasks
     */
    public static String getBy(String input) {

        String[] split = input.trim().split("/by");

        return split[1];
    }

    /**
     * Get the on date for Event tasks
     *
     * @param input description of task
     * @return on date for Event tasks
     */
    public static String getOn(String input) {

        String[] split = input.trim().split("/at");

        return split[1];
    }

    /**
     * Adds task to the ArrayList of tasks
     *
     * @param task
     * @throws DukeException
     */
    public static void addTask(Task task) throws DukeException {

        // checks if there is already the same task in the list
        for (int i = 0; i < TaskList.numOfTasks; i++) {
            if (TaskList.getTask(i).toString().equals(task.toString())) {
                throw new DukeException("Task already exists!");
            }
        }

        TaskList.addTask(task);
        Ui.taskAdded(task);
    }
}
