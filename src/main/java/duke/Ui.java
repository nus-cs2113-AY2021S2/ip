package duke;

import duke.error.TaskListEmptyException;
import duke.task.Task;

/**
 * Represents the user interface of the application. A Ui objects corresponds to all the messages 
 * displayed to a user. Also includes getting input from the user. 
 */
public class Ui {
    /**
     * Displays the welcome message.
     */
    protected void displayWelcomeMessage() {
        displayToUser(Constants.MESSAGE_WELCOME);
    }

    /**
     * Displays the message border. 
     */
    public void displayMessageBorder () {
        System.out.println(Constants.MESSAGE_BORDER);
    }

    /**
     * Displays a given message to the user.
     * 
     * @param message Message to be displayed.
     */
    public void displayToUser(String... messages) {
        displayMessageBorder();
        for (String m : messages) {
            System.out.println(String.format("\t %s", m));
        }
    }

    /**
     * Obtains user input from console. Input stored into userCommand.
     * Removes trailing sapces before returning. 
     * 
     * @return Input from user. 
     */
    protected String getUserInput() {
        return Constants.SCANNER.nextLine().trim();
    }

    /**
     * Displays the list of tasks to the user. The list will be numbered, starting from 1. 
     * 
     * @param tasks Tasks to be listed.
     */
    public void displayToUser(TaskList tasks) {
        String listAsString = getDisplayString(tasks);
        displayToUser(Constants.MESSAGE_LIST, listAsString);
    }

    /**
     * Displays the list of tasks to the user. The list will be numbered, starting
     * from 1.
     * 
     * @param tasks Tasks to be listed.
     */
    public void displayToUser(TaskList tasks, String keyword) throws TaskListEmptyException {
        String listAsString = getDisplayString(tasks, keyword);
        displayToUser(Constants.MESSAGE_FIND, listAsString);
    }

    /**
     * Displays success message after adding new task.
     */
    public void displayAddTaskSuccessMessage(TaskList tasks) {
        displayToUser(Constants.MESSAGE_ADDED, "  " + tasks.getTask(tasks.getSize()-1).toString(), 
                String.format(Constants.MESSAGE_NUMBER_OF_TASKS, tasks.getSize()));
    }

    /**
     * Displays the success message after deleting the task. 
     * 
     * @param deletedTask The description of deleted task. 
     */
    public void displayDeleteTaskSuccessMessage(String deletedTask) {
        displayToUser(Constants.MESSAGE_DELETE, deletedTask);
    }

    /**
     * Displays the success message after marking task as done.
     * 
     * @param taskNumber The task number (starting from 1) that was marked as done.
     */
    public void displayMarkTaskSuccessMessage(TaskList tasks, int taskNumber) {
        displayToUser(Constants.MESSAGE_MARKED, tasks.getTask(taskNumber).toString());
    }

    /**
     * Returns the display string representation of the list of tasks.
     * For displaying the entire task list. 
     * 
     * @param tasks Task list used.
     * @return The list of all items in list, formatted with numberings and the
     *         total number of tasks in list.
     */
    protected String getDisplayString(TaskList tasks) {
        StringBuilder message = new StringBuilder();
        for (int i = 0; i < tasks.getSize(); i++) {
            int displayIndex = i + 1;
            message.append(System.lineSeparator() + String.format("\t %d. %s", displayIndex, tasks.getTask(i).toString()));
        }
        return message.toString();
    }

    /**
     * Returns the display string representation of the list of tasks.
     * For displaying a selection of the task list (i.e. tasks that macthes the keyword). 
     * 
     * @param tasks Task list used.
     * @return The list of all items in list, formatted with numberings and the
     *         total number of tasks in list.
     * @throws TaskListEmptyException If the filtered list is empty (i.e. no matches found). 
     */
    public String getDisplayString(TaskList tasks, String keyword) throws TaskListEmptyException {
        StringBuilder message = new StringBuilder();
        int matchNumber = 0;
        for (int i = 0; i < tasks.getSize(); i++) {
            Task task = tasks.getTask(i);
            if (task.getTaskDescription().toLowerCase().contains(keyword)) {
                matchNumber++;
                message.append(System.lineSeparator() + String.format("\t %d. %s", matchNumber, task.toString()));
            }
        }

        if (matchNumber == 0) {
            throw new TaskListEmptyException();
        }
        return message.toString();
    }
    
    /**
     * Gets the syntax of the commands depending on the one given by the user.
     * 
     * @param filterString The string to find in userCommand depending on an event
     *                     (/at) or deadline (/by).
     * @return The syntax to task command given by the user.
     */
    protected String getSyntaxMessage(String command) {
        switch (command) {
        case Constants.COMMAND_TODO_WORD:
            return Constants.MESSAGE_TODO_SYNTAX;
        case Constants.COMMAND_DEADLINE_WORD: 
            return Constants.MESSAGE_DEADLINE_SYNTAX;
        case Constants.COMMAND_EVENT_WORD:
            return Constants.MESSAGE_EVENT_SYNTAX;
        case Constants.COMMAND_MARK_WORD:
            return Constants.MESSAGE_MARK_SYNTAX;
        case Constants.COMMAND_DELETE_WORD:
            return Constants.MESSAGE_DELETE_SYNTAX;
        case Constants.COMMAND_FIND_WORD:
            return Constants.MESSAGE_FIND_SYNTAX;
        default:
            return null;
        }
    }
}
