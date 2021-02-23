package duke;

import duke.task.Task;

import java.util.Scanner;

/**
 * Handle operations related to user interactions.
 */
public class Ui {
    /**
     * Prints divider.
     */
    public void showDivider() {
        System.out.println("-----------------------------------------------------------------");
    }

    /**
     * Prints greeting message.
     */
    public void showGreeting() {
        showDivider();
        System.out.println("Hello! I'm Duke :)");
        System.out.println("What can I do for you?");
        showDivider();
    }

    /**
     * Reads user input from command line using the scanner object.
     *
     * @return Scanner object consisting the user input.
     */
    public String readCommand() {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    /**
     * Prints exit message.
     */
    public void showExit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints message after new task successfully added.
     *
     * @param task New task added to the task list.
     * @param taskList Latest version of task list after adding the new task.
     */
    public void showAddTaskMessage(Task task, TaskList taskList) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    /**
     * Prints all task available on task list.
     *
     * @param taskList Latest version of task list.
     */
    public void showTasks(TaskList taskList) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(i + 1 + ". " + taskList.get(i));
        }
    }

    /**
     * Prints message after task is marked as done.
     *
     * @param task Task that is marked as done.
     */
    public void showMarkAsDoneMessage(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
    }

    /**
     * Prints message after task successfully deleted.
     *
     * @param task Task removed from the task list.
     * @param taskList Latest version of task list after removing the task.
     */
    public void showDeleteTaskMessage(Task task, TaskList taskList) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    /**
     * Prints error message based on data in DukeException.
     *
     * @param de DukeException object consisting the exception information.
     */
    public void showErrorMessage(DukeException de) {
        System.out.println(de.getMessage());
    }

}
