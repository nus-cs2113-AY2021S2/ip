package dukchess.commands;

import static dukchess.entity.TaskList.deleteTask;

import dukchess.ui.Ui;

/**
 * Command for deleting a task
 */
public class DeleteCommand extends Command {
    /**
     * Performs input validation before deleting a task from the list of tasks.
     * @param taskIdString - the task id to be deleted as a string
     */
    public static void handleDeleteTask(String taskIdString) {
        if (taskIdString.length() == 0) {
            Ui.printErrorMessage("You have to specify which task to delete!");
            return;
        }
        try {
            int taskIdToDelete = Integer.parseInt(taskIdString);
            String deletionOutcome = deleteTask(taskIdToDelete);
            System.out.println(deletionOutcome);
        } catch (NumberFormatException numberFormatException) {
            Ui.printErrorMessage("Invalid number passed to task deletion command.");
        }
    }

}
