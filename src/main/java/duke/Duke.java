package duke;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import duke.task.*;
import duke.error.*;

public class Duke {
    // Words for commands.
    private static final String COMMAND_TODO_WORD = "todo";
    private static final String COMMAND_DEADLINE_WORD = "deadline";
    private static final String COMMAND_EVENT_WORD = "event";
    private static final String COMMAND_MARK_WORD = "done";
    private static final String COMMAND_LIST_WORD = "list";
    private static final String COMMAND_DELETE_WORD = "delete";
    private static final String COMMAND_EXIT_WORD = "bye";

    // Display messages.
    private static final String MESSAGE_WELCOME = "Hello! I'm Duke, what can I do for you?";
    private static final String MESSAGE_BORDER = "__________".repeat(6);
    private static final String MESSAGE_ADDED = "Got it. I've added this task: ";
    private static final String MESSAGE_MARKED = "Nice! I've marked this task as done: ";
    private static final String MESSAGE_LIST = "Here are the tasks in your list: ";
    private static final String MESSAGE_DELETE = "Noted. I have removed this task: ";
    private static final String MESSAGE_EXIT = "Bye. Hope to see you again soon! ";
    private static final String MESSAGE_NUMBER_OF_TASKS = "You have %s task(s) in the list. "; 

    private static final String ERROR_INDEX_OUT_OF_RANGE = "Index out of range. ";
    private static final String ERROR_INVALID_COMMAND_RECEIVED = "Invalid command. Available commands: "
            + System.lineSeparator() + "\t  list, done, todo, deadline, event, delete, bye";
    private static final String ERROR_EMPTY_LIST = "You have no tasks recorded.";
    private static final String ERROR_INVALID_SYNTAX_RECEIVED = "Invalid syntax! Usage: ";
    private static final String ERROR_UPDATING_DATA = "IO Error, please try again. ";

    // Syntax messages for the commands.
    private static final String MESSAGE_MARK_SYNTAX = " done <task number>";
    private static final String MESSAGE_TODO_SYNTAX = " todo <task name>";
    private static final String MESSAGE_DEADLINE_SYNTAX = " deadline <task name> /by <date>";
    private static final String MESSAGE_EVENT_SYNTAX = " event <task name> /at <date>";
    private static final String MESSAGE_DELETE_SYNTAX = "delete <task number>";

    private static final String FILE_PATH = "Duke.txt";

    // Filter words to locate date for deadline and event tasks.
    private static final String DEADLINE_DATA_PREFIX_BY = "/by";
    private static final String EVENT_DATA_PREFIX_AT = "/at";

    // List of all tasks. 
    private static ArrayList<Task> tasks;
    private static String userCommand;
    private static String commandWord;

    // Scanner for extracting user input.
    private static final Scanner SCANNER = new Scanner(System.in);

    /**
     * Gets a task object from the tasks list.
     * 
     * @param index Location of the task on the tasks list.
     * @return The task at the index.
     */
    private static Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Check if list is empty.
     * 
     * @return True if sizeOfTaskList is 0 and false if not.
     */
    private static boolean isTaskListEmpty() {
        return tasks.size() == 0;
    }

    /**
     * Starts the program.
     */
    public static void main(String[] args) {
        initTaskList();
        displayWelcomeMessage();
        while (true) {
            getUserInput();
            executeCommand();
        }
    }

    /**
     * Initializes the task list.
     * Extracts stored data and add to task list. 
     */
    private static void initTaskList() {
        tasks = new ArrayList<Task>();
        try {
            readFromFile();
        } catch (IOException exception) {
            displayToUser(ERROR_UPDATING_DATA);
        }
        // Reset userCommand
        userCommand = ""; 
    }

    /**
     * Read the file contents and add tasks to task list. 
     * 
     * @throws IOException If there is an error opening file. 
     * @throws FileNotFoundException If the file is not found. 
     */
    private static void readFromFile() throws IOException, FileNotFoundException {
        File file = new File(FILE_PATH);
        if (file.exists()) {
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String[] data = fileScanner.nextLine().split(", ");
                addToTaskList(data);
            }
            fileScanner.close();
        }
    }

    /**
     * Adds the tasks from storage to the task list. 
     * 
     * @param data List of parameters in storage file. 
     */
    private static void addToTaskList(String[] data) {
        switch(data[0]) {
        case "todo":
            userCommand = data[2];
            executeAddTodo();
            break;
        case "deadline":
            userCommand = data[2] + DEADLINE_DATA_PREFIX_BY + data[3];
            executeAddDeadline();
            break;
        case "event":
            userCommand = data[2] + EVENT_DATA_PREFIX_AT + data[3];
            executeAddEvent();
            break;
        }
        if (data[1].equals("true")) {
            executeMarkTask(tasks.size() - 1);
        }
    }

    /**
     * Displays the welcome message.
     */
    private static void displayWelcomeMessage() {
        displayToUser(MESSAGE_WELCOME);
    }

    /**
     * Obtains user input from console. Input stored into userCommand.
     */
    private static void getUserInput() {
        // Remove trailing spaces
        userCommand = SCANNER.nextLine().trim();
    }

    /**
     * Executes each function according to command word given.
     * 
     * The following explains the behaviour of the command words: 
     * COMMAND_EXIT_WORD:
     * - Displays an exit message. 
     * - Exits program. 
     * COMMAND_LIST_WORD: 
     * - Lists all tasks in tasks list. 
     * COMMAND_MARK_WORD: 
     * - Obtains task number. 
     * - Checks if task is valid. 
     * - If valid, mark task as done then display success message. 
     * - Otherwise, display error message. 
     * COMMAND_TODO_WORD: 
     * - Add new todo task to list. 
     * COMMAND_DEADLINE_WORD: 
     * - Add new deadline task to list.
     * COMMAND_EVENT_WORD: 
     * - Add new event task to list. 
     * COMMAND_DELETE_WORD:
     * - Delete a given task from the list. 
     * - Add new event task to list. 
     * NO_COMMAND: 
     * - No command detected, display error message. 
     * DEFAULT: 
     * - Invalid command, display error message.
     */
    private static void executeCommand() {
        int taskNumber;
        try {
            switch (getCommand()) {
            case COMMAND_EXIT_WORD:
                // Fallthrough
                displayToUser(MESSAGE_EXIT);
                executeExitProgramRequest();
            case COMMAND_LIST_WORD:
                executeListAllTasks();
                return;
            case COMMAND_MARK_WORD:
                taskNumber = getTaskNumber();
                if (isTaskNumberValid(taskNumber)) {
                    executeMarkTask(taskNumber);
                    saveToFile();
                    displayMarkTaskSuccessMessage(taskNumber);
                    return;
                }
                throw new IndexOutOfBoundsException();
            case COMMAND_TODO_WORD:
                executeAddTodo();
                saveToFile();
                displayAddTaskSuccessMessage();
                return;
            case COMMAND_DEADLINE_WORD:
                executeAddDeadline();
                saveToFile();
                displayAddTaskSuccessMessage();
                return;
            case COMMAND_EVENT_WORD:
                executeAddEvent();
                saveToFile();
                displayAddTaskSuccessMessage();
                return;
            case COMMAND_DELETE_WORD:
                taskNumber = getTaskNumber();
                if (isTaskNumberValid(taskNumber)) {
                    String deletedTask = executeDeleteTask(taskNumber);
                    saveToFile();
                    displayDeleteTaskSuccessMessage(deletedTask);
                    return;
                }
                throw new IndexOutOfBoundsException();
            default:
                throw new IllegalThreadStateException();
            }
        } catch (IllegalThreadStateException exception) {
            // If command detected is not found in available commands
            displayToUser(ERROR_INVALID_COMMAND_RECEIVED);
        } catch (IndexOutOfBoundsException exception) {
            // If task number input by user is out of range
            displayToUser(ERROR_INDEX_OUT_OF_RANGE, String.format(MESSAGE_NUMBER_OF_TASKS, tasks.size()));
        } catch (TaskListEmptyException exception) {
            // If task list is empty
            displayToUser(ERROR_EMPTY_LIST);
        } catch (IllegalArgumentException exception) {
            // If the syntax for the command is invalid
            displayToUser(ERROR_INVALID_SYNTAX_RECEIVED, getSyntaxMessage());
        } catch (IOException exception) { 
            displayToUser(ERROR_UPDATING_DATA);
        } catch (NullPointerException exception) {
            displayToUser(ERROR_UPDATING_DATA);
        }
    }

    /**
     * Extracts the command word from user input. Removes command name from
     * userCommand. If there are parameters after command word (i.e. length > 4),
     * set userCommand to the parameters. Otherwise, set userCommand to null.
     * 
     * @return Command word extracted.
     */
    private static String getCommand() {
        // First word of userCommand is the task command
        commandWord = userCommand.split(" ")[0].toLowerCase();
        // Removes command name with has 4 letters
        if (userCommand.length() > 4) {
            userCommand = userCommand.substring(userCommand.indexOf(" ") + 1, userCommand.length());
        } else {
            userCommand = null;
        }
        return commandWord;
    }

    /**
     * Exits the program.
     */
    private static void executeExitProgramRequest() {
        System.exit(0);
    }

    /**
     * Lists all tasks current in the tasks list. Shows the type of task (T, D, E)
     * and marks X if the task is done.
     * 
     * @throws TaskListEmptyException If task list is empty.
     */
    private static void executeListAllTasks() throws TaskListEmptyException {
        if (isTaskListEmpty()) {
            throw new TaskListEmptyException();
        }
        displayToUser();
    }

    /**
     * Gets task number from the input string.
     * 
     * @return Task number (Starts with 0).
     * @throws NumberFormatException    If userCommand is not an integer.
     * @throws IllegalArgumentException If no task number is detected.
     * @throws TaskListEmptyException   If task list is empty.
     */
    private static int getTaskNumber() throws NumberFormatException, IllegalArgumentException, TaskListEmptyException {
        if (userCommand == null) {
            throw new IllegalArgumentException();
        } else if (isTaskListEmpty()) {
            throw new TaskListEmptyException();
        }
        // Since user input task number starts from 1, remove 1 from taskNumber to
        // reflect the correct index in tasks.
        int taskNumber = Integer.parseInt(getTaskNumberString()) - 1;
        return taskNumber;
    }

    /**
     * Gets task number in string.
     * 
     * @return Task number.
     */
    private static String getTaskNumberString() {
        // Gets the taskNumber from the index of the first " "
        // Adds 1 to the index remove the " " from string
        return userCommand.substring(userCommand.indexOf(" ") + 1);
    }

    /**
     * Checks if task number is valid.
     * 
     * @param taskNumber Task number.
     * @return True if number is valid and false if invalid.
     */
    private static boolean isTaskNumberValid(int taskNumber) {
        return taskNumber >= 0 && taskNumber < tasks.size();
    }

    /**
     * Marks a given task number as done.
     * 
     * @param taskNumber The task number (starting from 1) that was marked as done.
     */
    private static void executeMarkTask(int taskNumber) {
        tasks.get(taskNumber).setTaskStatus();
    }

    /**
     * Displays the success message after marking task as done.
     * 
     * @param taskNumber The task number (starting from 1) that was marked as done.
     */
    private static void displayMarkTaskSuccessMessage(int taskNumber) {
        displayToUser(MESSAGE_MARKED, tasks.get(taskNumber).toString());
    }

    /**
     * Adds a new todo task to task list. If userCommand does not contain task
     * description, throw invalid command syntax error.
     * 
     * @throws IllegalArgumentException If userCommand is null.
     */
    private static void executeAddTodo() throws IllegalArgumentException {
        if (userCommand == null) {
            throw new IllegalArgumentException();
        } 
        tasks.add(new Todo(userCommand));
    }

    /**
     * Adds a new deadline task to task list. Processes the user input to extract
     * date. If date is not null, create new deadline task. Otherwise, throw invalid
     * command syntax error.
     * 
     * @throws IllegalArgumentException If date is not found.
     */
    private static void executeAddDeadline() throws IllegalArgumentException {
        String date = processParameters(DEADLINE_DATA_PREFIX_BY);
        tasks.add(new Deadline(userCommand, date));
    }

    /**
     * Adds a new deadline task to task list. 
     * Processes the user input to exit. 
     * If date is not null, create new deadline task.  
     * 
     * @throws IllegalArgumentException If date is not found.
     */
    private static void executeAddEvent() throws IllegalArgumentException {
        String date = processParameters(EVENT_DATA_PREFIX_AT);
        tasks.add(new Event(userCommand, date));
    }

    /**
     * Delete a task assigned to the task number. 
     * 
     * @param taskNumber The task number (starting from 1) that was marked as done. 
     */
    private static String executeDeleteTask(int taskNumber) {
        String deletedTask = tasks.get(taskNumber).toString();
        tasks.remove(taskNumber);
        return deletedTask;
    }

    /**
     * Displays the success message after deleting the task. 
     * 
     * @param deletedTask The description of deleted task. 
     */
    private static void displayDeleteTaskSuccessMessage(String deletedTask) {
        displayToUser(MESSAGE_DELETE, deletedTask);
    }

    /**
     * Finds the location of the filters and locate the date from the task. Removes
     * the task date information from userCommand, keeping only the task name in
     * userCommand.
     * 
     * @param filterString The string to find in userCommand depending on an event
     *                     (/at) or deadline (/by).
     * @return The date extracted from userCommand.
     * @throws IllegalArgumentException If date is not found.
     */
    private static String processParameters(String filterString) throws IllegalArgumentException {
        int indexOfDate = userCommand.indexOf(filterString);
        if (indexOfDate > 0) {
            String date = getDate(indexOfDate, filterString);
            userCommand = userCommand.substring(0, indexOfDate).trim();
            return date;
        }
        throw new IllegalArgumentException();
    }

    /**
     * Get the syntax of the commands depending on the one given by the user.
     * 
     * @param filterString The string to find in userCommand depending on an event
     *                     (/at) or deadline (/by).
     * @return The syntax to task command given by the user.
     */
    private static String getSyntaxMessage() {
        switch (commandWord) {
        case COMMAND_TODO_WORD:
            return MESSAGE_TODO_SYNTAX;
        case COMMAND_DEADLINE_WORD: 
            return MESSAGE_DEADLINE_SYNTAX;
        case COMMAND_EVENT_WORD:
            return MESSAGE_EVENT_SYNTAX;
        case COMMAND_MARK_WORD:
            return MESSAGE_MARK_SYNTAX;
        case COMMAND_DELETE_WORD:
            return MESSAGE_DELETE_SYNTAX;
        default:
            return null;
        }
    }

    /**
     * Extracts the date from userCommand.
     * 
     * @param indexOfDate  The index for the location of /by in userCommand.
     *                     Indicates the begining of date.
     * @param filterString The string to find in userCommand depending on an event
     *                     (/at) or deadline (/by).
     * @return Date specified in user input (userCommand).
     * @throws IllegalArgumentException If no date is detected after the /by
     *                                  parameter.
     */
    private static String getDate(int indexOfDate, String filterString) throws IllegalArgumentException {
        // Check if string contains date after filterString
        // +3 to remove filterString and +1 to convert index to length. Total: +4
        if (userCommand.length() < indexOfDate + 4) {
            throw new IllegalArgumentException(getSyntaxMessage());
        }
        // Add 3 to indexOfDate to remove the "/by" or "/at" filter strings
        return userCommand.substring(indexOfDate + 3).trim();
    }

    /**
     * Displays success message after adding new task.
     */
    private static void displayAddTaskSuccessMessage() {
        displayToUser(MESSAGE_ADDED, "  " + getTask(tasks.size()-1).toString(), String.format(MESSAGE_NUMBER_OF_TASKS,
                tasks.size()));
    }

    /**
     * Displays a given message to the user.
     * 
     * @param message Message to be displayed.
     */
    private static void displayToUser(String... messages) {
        System.out.println(String.format("\t%s", MESSAGE_BORDER));
        for (String m : messages) {
            System.out.println(String.format("\t %s", m));
        }
        System.out.println(String.format("\t%s", MESSAGE_BORDER));
    }

    /**
     * Displays the list of tasks to the user. The list will be numbered, starting
     * from 1.
     * 
     * @param tasks Tasks to be listed.
     */
    private static void displayToUser() {
        String listAsString = getDisplayString(tasks);
        displayToUser(MESSAGE_LIST, listAsString);
    }

    /**
     * Returns the display string representation of the list of tasks.
     * 
     * @param tasks Task list used.
     * @return The list of all items in list, formatted with numberings and the
     *         total number of tasks in list.
     */
    private static String getDisplayString(ArrayList<Task> tasks) {
        StringBuilder message = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            int displayIndex = i + 1;
            message.append(System.lineSeparator() + String.format("\t %d. %s", displayIndex, getTask(i).toString()));
        }
        return message.toString();
    }

    /**
     * Checks for presence of file before saving. 
     * If file is not found, create file for storing task list. 
     * Then, store task list into file. 
     * 
     * @throws IOException If there is an error reating file. 
     */
    private static void saveToFile() throws IOException {
        File file = new File(FILE_PATH);
        file.createNewFile();
        writeToFile(file);
    }

    /**
     * Writes the new task list into file. 
     * 
     * @throws IOException If there is an error writing to file. 
     */
    private static void writeToFile(File file) throws IOException {
        FileWriter fw = new FileWriter(file.getAbsolutePath(), false);
        fw.write(convertToFileFormat());
        fw.close();
    }

    /**
     * Converts the task list into storage format. 
     */
    private static String convertToFileFormat() {
        StringBuilder message = new StringBuilder();
        for (Task t : tasks) {
            message.append(t.toStorageString() + System.lineSeparator());
        }
        return message.toString();
    }
}