package dukehandler;

import exceptions.*;

public class Checker {
    public Checker() {
    }

    public static void checkTaskToMarkDone(int doneIndexInt)
            throws IllegalTaskMarkedDoneException,
            TaskAlreadyMarkedException,
            EmptyListException {
        if (TaskManager.tasks.isEmpty()) {
            throw new EmptyListException();
        } else if (doneIndexInt < 1 || doneIndexInt > TaskManager.tasks.size()) {
            throw new IllegalTaskMarkedDoneException();
        } else if ((TaskManager.tasks.get(doneIndexInt - 1).getStatusIcon()).equals("X")) {
            throw new TaskAlreadyMarkedException();
        }
    }

    public static void checkNewTaskToAdd(String taskType, String description)
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

    public static void checkTaskToRemove(int removeIndexInt)
            throws IllegalTaskRemovedException, EmptyListException {
        if (TaskManager.tasks.isEmpty()) {
            throw new EmptyListException();
        } else if (removeIndexInt < 1 || removeIndexInt > TaskManager.tasks.size()) {
            throw new IllegalTaskRemovedException();
        }
    }

    public static void checkTaskTypeStreamToPrint(String taskTypeInput) throws StreamErrorException {
        if (!taskTypeInput.equals("d")
                && !taskTypeInput.equals("e")
                && !taskTypeInput.equals("t")) {
            throw new StreamErrorException();
        }
    }


}
