package dukehandler;

import exceptions.EmptyCommandDescriptionException;
import exceptions.EmptyListException;
import exceptions.IllegalTaskMarkedDoneException;
import exceptions.IllegalTaskRemovedException;
import exceptions.InvalidCommandException;
import exceptions.TaskAlreadyMarkedException;

public class Checker {
    private final TaskList taskList;

    public Checker(TaskList taskList) {
        this.taskList = taskList;
    }

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

    public void checkTaskToRemove(int removeIndexInt)
            throws IllegalTaskRemovedException, EmptyListException {
        if (taskList.tasks.isEmpty()) {
            throw new EmptyListException();
        } else if (removeIndexInt < 1 || removeIndexInt > taskList.tasks.size()) {
            throw new IllegalTaskRemovedException();
        }
    }

}
