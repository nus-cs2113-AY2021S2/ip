package dukchess.commands;

import dukchess.ui.Ui;

/**
 * Command for listing all commands
 */
public final class ListCommand extends Command {
    /**
     * Prints out all added tasks
     */
    public static void printAddedTasks() {
        if (tasks.size() == 0) {
            System.out.println("No previously added tasks to list Sir/Madam/Other :(");
            return;
        }
        System.out.println("Here are the tasks in your list, Sir/Madam/Other:");
        Ui.printListOfTasks(tasks);
    }
}
