package dukchess.commands;

import dukchess.entity.Task;
import dukchess.ui.Ui;

/**
 * Command for setting a task to done
 */
public final class DoneCommand extends Command {
    private static String setAddedTaskStatus(int taskId, boolean isDone) {
        int actualTaskId = taskId - 1;
        if (actualTaskId >= tasks.size() || actualTaskId < 0) {
            return String.format(
                    "I'm terribly sorry Sir/Madam/Other :(\n"
                            + "%d is not a valid task id for the current list of tasks.",
                    taskId);
        }
        Task selectedTask = tasks.get(actualTaskId);
        String originalTaskStatus = selectedTask.toString();
        selectedTask.setDone(isDone);
        return String.format("Setting to done, original task status: %s\n>>> New task status: %s",
                originalTaskStatus, selectedTask.toString());
    }

    /**
     * Set a task to done.
     * @param taskIdString - A task ID in integer form
     */
    public static void handleDoneTask(String taskIdString) {
        try {
            int taskId = Integer.parseInt(taskIdString);
            String taskStatusAdditionOutcome = setAddedTaskStatus(taskId, true);
            System.out.println(taskStatusAdditionOutcome);
        } catch (NumberFormatException e) {
            Ui.printErrorMessage("The task ID provided must be a string.");
        }
    }
}
