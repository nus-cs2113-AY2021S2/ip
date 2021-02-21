package dukehandler;

import exceptions.EmptyCommandDescriptionException;
import exceptions.EmptyListException;
import exceptions.IllegalTaskMarkedDoneException;
import exceptions.IllegalTaskRemovedException;
import exceptions.InvalidCommandException;
import exceptions.TaskAlreadyMarkedException;

/**
 * Checks if task command can be parsed correctly
 */
public class Checker {
    private final TaskList taskList;

    public Checker(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Checks if index to mark done is valid.
     *
     * @param doneIndexInt the index of the task to be marked done
     * @throws IllegalTaskMarkedDoneException If index is out of bounds
     * @throws TaskAlreadyMarkedException     If task is already marked as done
     * @throws EmptyListException             If list is empty
     */
    public void checkTaskToMarkDone(int doneIndexInt)
            throws IllegalTaskMarkedDoneException,
            TaskAlreadyMarkedException,
            EmptyListException {
        if (taskList.tasks.isEmpty()) {
            throw new EmptyListException();
        } else if (doneIndexInt < 1 || doneIndexInt > taskList.tasks.size()) {
            throw new IllegalTaskMarkedDoneException();
        } else if ((taskList.tasks.get(doneIndexInt - 1).getStatusIcon()).equals("X")) {
            throw new TaskAlreadyMarkedException();
        }
    }

    /**
     * Checks if new task input is in the correct format
     *
     * @param taskType    whether it is deadline, event, or todo
     * @param description the name of the task (may include time and/or date)
     * @throws InvalidCommandException          if command cannot be parsed (wrong input).
     * @throws EmptyCommandDescriptionException if command is followed by an invalid character.
     */
    public void checkNewTaskToAdd(String taskType, String description)
            throws InvalidCommandException, EmptyCommandDescriptionException {
        if (taskType.equals("deadline") && !description.contains("/by")
                || taskType.equals("event") && !description.contains("/at")) {

            throw new InvalidCommandException();

        } else if (taskType.equals("todo") && description.trim().isEmpty()
                || taskType.equals("deadline") && (description.split("/by")[0]).trim().isEmpty()
                || taskType.equals("event") && (description.split("/at")[0].trim()).isEmpty()) {

            throw new EmptyCommandDescriptionException();

        } else if (taskType.equals("deadline") && description.split("/by")[1].trim().isEmpty()
                || taskType.equals("event") && description.split("/at")[1].trim().isEmpty()) {

            throw new EmptyCommandDescriptionException();
        }
    }


    /**
     * Checks if index to remove is valid.
     *
     * @param removeIndexInt the index of the task to be removed
     * @throws IllegalTaskRemovedException if index is out of bounds
     * @throws EmptyListException          if list is empty
     */
    public void checkTaskToRemove(int removeIndexInt)
            throws IllegalTaskRemovedException, EmptyListException {
        if (taskList.tasks.isEmpty()) {
            throw new EmptyListException();
        } else if (removeIndexInt < 1 || removeIndexInt > taskList.tasks.size()) {
            throw new IllegalTaskRemovedException();
        }
    }

}
