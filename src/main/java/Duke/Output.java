package Duke;

import Duke.Tasks.Task;

import java.util.ArrayList;

/**
 * Output class that formats the output to be printed in the command line.
 */

public class Output {

    /**
     * Logo string
     * @return string of the DUKE logo
     */
    public static String printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        return logo;
    }

    /**
     *
     * @return string of line
     */
    public static String printLine() {
        String line = "____________________________________________________________";
        return line;
    }

    /**
     * Greeting for when the program boots up
     * @return string for greeting the user
     */
    public static String printGreet() {
        String greetMessage = "Hello! I'm Duke\n"
                + printLogo()
                + "\nWhat can I do for you?";
        return greetMessage;
    }

    /**
     *
     * @param taskName
     * @param size
     * @return string for notifying the user about the deleted task
     */
    public static String printDeleted(String taskName, int size) {
        String deleteMessage = String.format("Noted. I've removed this task:\n  %1$s \nNow you have %2$d tasks in the list.",
                taskName,
                size);
        return deleteMessage;
    }

    /**
     *
     * @param taskName
     * @param size
     * @return string for notifying the user about the added task
     */
    public static String printAdded(String taskName, int size) {
        String addMessage = String.format("Got it. I've added this task:\n  %1$s \nNow you have %2$d tasks in the list.",
                taskName,
                size);
        return addMessage;
    }

    /**
     *
     * @param taskName
     * @return string for notifying the user about the updated task
     */
    public static String printUpdated(String taskName) {
        String updateMessage = String.format("Nice! I've marked this task as done:\n  %s",
                taskName);
        return updateMessage;
    }

    /**
     *
     * @param task
     * @return string that represents the task
     */
    public static String printTask(Task task) {
        return task.toString();
    }

    /**
     *
     * @param taskList
     * @return string that lists out the tasks for the user
     */
    public static String printTaskList(ArrayList<Task> taskList) {
        if (taskList.isEmpty()) {
            return "You do not have any tasks currently.";
        }
        String listTasksMessage = "Here are the list of tasks that you have\n";
        for (int i = 0; i < taskList.size(); i++) {
            listTasksMessage += String.format("%d. ", i + 1);
            listTasksMessage += printTask(taskList.get(i));
            listTasksMessage += "\n";
        }
        return listTasksMessage;
    }

    /**
     *
     * @param taskList
     * @return string that lists out the filtered tasks for the user
     */
    public static String printFilteredTaskList(ArrayList<Task> taskList) {
        if (taskList.isEmpty()) {
            return "You do not have any tasks that matches the keyword.";
        }
        String listTasksMessage = "Here are the tasks that matches:\n";
        for (int i = 0; i < taskList.size(); i++) {
            listTasksMessage += String.format("%d. ", i + 1);
            listTasksMessage += printTask(taskList.get(i));
            listTasksMessage += "\n";
        }
        return listTasksMessage;
    }

    /**
     *
     * @return string to notify the user that the program is exiting
     */
    public static String printExit() {
        String exitMessage = "Bye. Hope to see you again soon!";
        return exitMessage;
    }

    /**
     *
     * @return string to notify the user that the user's task data has been saved
     */
    public static String printSave() {
        String saveMessage = "Sure! Your data has been saved.";
        return saveMessage;
    }

}