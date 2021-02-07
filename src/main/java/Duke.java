import java.util.Scanner;

public class Duke {
    // Words for commands. 
    private static final String COMMAND_TODO_WORD = "todo";
    private static final String COMMAND_DEADLINE_WORD = "deadline";
    private static final String COMMAND_EVENT_WORD = "event";
    private static final String COMMAND_MARK_WORD = "done";
    private static final String COMMAND_LIST_WORD = "list";
    private static final String COMMAND_EXIT_WORD = "bye";
    
    // Display messages. 
    private static final String MESSAGE_WELCOME = "Hello! I'm Duke, what can I do for you?";
    private static final String MESSAGE_BORDER = "__________".repeat(6);
    private static final String MESSAGE_ADDED = "Got it. I've added this task: ";
    private static final String MESSAGE_MARKED = "Nice! I've marked this task as done: ";
    private static final String MESSAGE_LIST = "Here are the tasks in your list: ";
    private static final String MESSAGE_EXIT = "Bye. Hope to see you again soon! ";
    private static final String MESSAGE_NUMBER_OF_TASKS = "You have %s task(s) in the list. ";
    private static final String ERROR_INDEX_OUT_OF_RANGE = "Index out of range. ";
    private static final String ERROR_INVALID_COMMAND_RECEIVED = "Invalid command. Available commands: " 
            + System.lineSeparator() + "\t  list, done, todo, deadline, event, bye";
    private static final String ERROR_EMPTY_LIST = "You have no tasks recorded.";
    private static final String ERROR_INVALID_SYNTAX_RECEIVED = "Invalid syntax! Usage: ";

    // Syntax messages for the commands. 
    private static final String MESSAGE_MARK_SYNTAX = " done <task number>";
    private static final String MESSAGE_TODO_SYNTAX = " todo <task name>";
    private static final String MESSAGE_DEADLINE_SYNTAX = " deadline <task name> /by <date>";
    private static final String MESSAGE_EVENT_SYNTAX = " event <task name> /at <date>";

    // Filter words to locate date for deadline and event tasks. 
    private static final String DEADLINE_DATA_PREFIX_BY = "/by";
    private static final String EVENT_DATA_PREFIX_AT = "/at";


    // List of all tasks. 
    private static Task[] tasks;
    private static String userCommand;

    // Maximum number of tasks that can be held. 
    private static final int MAX_LIST_SIZE = 100;

    // Total number of tasks in task list. 
    private static int sizeOfTaskList = 0;

    // Scanner for extracting user input. 
    private static final Scanner SCANNER = new Scanner(System.in);
    
    /**
     * Gets a task object from the tasks list. 
     * 
     * @param index Location of the task on the tasks list. 
     * @return The task at the index. 
     */
    private static Task getTask(int index) {
        return tasks[index];
    }

    /**
     * Check if list is empty. 
     * 
     * @return True if sizeOfTaskList is 0 and false if not. 
     */
    private static boolean isTaskListEmpty() {
        return sizeOfTaskList == 0;
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
     */
    private static void initTaskList() {
        tasks = new Task[MAX_LIST_SIZE];
        sizeOfTaskList = 0;
    }

    /**
     * Displays the welcome message. 
     */
    private static void displayWelcomeMessage() {
        displayToUser(MESSAGE_WELCOME);
    }

    /**
     * Obtains user input from console. 
     * Input stored into userCommand. 
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
     *  - Displays an exit message. 
     *  - Exits program. 
     * COMMAND_LIST_WORD: 
     *  - Lists all tasks in tasks list. 
     * COMMAND_MARK_WORD: 
     *  - Obtains task number. 
     *  - Checks if task is valid. 
     *  - If valid, mark task as done then display success message. 
     *  - Otherwise, display error message. 
     * COMMAND_TODO_WORD:
     *  - Add new todo task to list. 
     * COMMAND_DEADLINE_WORD: 
     *  - Add new deadline task to list.
     * COMMAND_EVENT_WORD: 
     *  - Add new event task to list. 
     * NO_COMMAND:
     *  - No command detected, display error message. 
     * DEFAULT: 
     *  - Invalid command, display error message. 
     */
    private static void executeCommand() {
        try {
            switch(getCommand()) {
            case COMMAND_EXIT_WORD:
                // Fallthrough
                displayToUser(MESSAGE_EXIT);
                executeExitProgramRequest();
            case COMMAND_LIST_WORD:
                executeListAllTasks();
                return;
            case COMMAND_MARK_WORD:
                int taskNumber = getTaskNumber();
                if (isTaskNumberValid(taskNumber)) {
                    executeMarkTask(taskNumber);
                    displayMarkTaskSuccessMessage(taskNumber);
                    return;
                }
                throw new IndexOutOfBoundsException();
            case COMMAND_TODO_WORD:
                executeAddTodo(); 
                displayAddTaskSuccessMessage();
                return;
            case COMMAND_DEADLINE_WORD:
                executeAddDeadline();
                displayAddTaskSuccessMessage();
                return;
            case COMMAND_EVENT_WORD:
                executeAddEvent();
                displayAddTaskSuccessMessage();
                return;
            default:
                throw new IllegalCommandException();
            }
        } catch (IllegalCommandException exception) {
            // If command detected is not found in available commands
            displayToUser(ERROR_INVALID_COMMAND_RECEIVED);
        } catch (IndexOutOfBoundsException exception) {
            // If task number input by user is out of range
            displayToUser(ERROR_INDEX_OUT_OF_RANGE, String.format(MESSAGE_NUMBER_OF_TASKS, sizeOfTaskList));
        } catch (NumberFormatException exception) {
            // If task number is not an integer
            displayToUser(ERROR_INVALID_SYNTAX_RECEIVED, MESSAGE_MARK_SYNTAX);
        } catch (TaskListEmptyException exception) {
            // If task list is empty
            displayToUser(ERROR_EMPTY_LIST);
        } catch (IllegalArgumentException exception) {
            // If incorrect input parameters given for the task command (deadline or event)
            displayToUser(ERROR_INVALID_SYNTAX_RECEIVED, exception.getMessage());
        }
    }

    /**
     * Extracts the command word from user input. 
     * Removes command name from userCommand. 
     * If there are parameters after command word (i.e. length > 4), set userCommand to the parameters. 
     * Otherwise, set userCommand to null. 
     * 
     * @return Command word extracted. 
     */
    private static String getCommand() {
        // First word of userCommand is the task command
        String commandWord = userCommand.split(" ")[0].toLowerCase();
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
     * Lists all tasks current in the tasks list. 
     * Shows the type of task (T, D, E) and marks X if the task is done. 
     * 
     * @throws TaskListEmptyException If task list is empty. 
     */
    private static void executeListAllTasks() throws TaskListEmptyException {
        if (isTaskListEmpty()) {
            throw new TaskListEmptyException();
        }
        displayToUser(tasks);
    }

    /**
     * Gets task number from the input string. 
     * 
     * @return Task number (Starts with 0). 
     * @throws NumberFormatException If userCommand is not an integer. 
     * @throws IllegalArgumentException If no task number is detected. 
     * @throws TaskListEmptyException If task list is empty. 
     */
    private static int getTaskNumber() throws NumberFormatException, IllegalArgumentException, TaskListEmptyException {
        if (userCommand == null) {
            throw new IllegalArgumentException(MESSAGE_MARK_SYNTAX);
        } else if (isTaskListEmpty()) {
            throw new TaskListEmptyException();
        }
        // Since user input task number starts from 1, remove 1 from taskNumber to reflect the correct index in tasks. 
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
        return taskNumber >= 0 && taskNumber < sizeOfTaskList;
    }

    /**
     * Marks a given task number as done. 
     * 
     * @param taskNumber The task number (starting from 1) that was marked as done. 
     */
    private static void executeMarkTask(int taskNumber) {
        tasks[taskNumber].setTaskStatus();
    }

    /**
     * Displays the success message after marking task as done. 
     * 
     * @param taskNumber The task number (starting from 1) that was marked as done. 
     */
    private static void displayMarkTaskSuccessMessage(int taskNumber) {
        displayToUser(MESSAGE_MARKED, tasks[taskNumber].toString());
    }

    /**
     * Adds a new todo task to task list. 
     * If userCommand does not contain task description, throw invalid command syntax error. 
     * 
     * @throws IllegalArgumentException If userCommand is null. 
     */
    private static void executeAddTodo() throws IllegalArgumentException {
        if (userCommand == null) {
            throw new IllegalArgumentException(MESSAGE_TODO_SYNTAX);
        } 
        tasks[sizeOfTaskList] = new Todo(userCommand);
        sizeOfTaskList++;
    }

    /**
     * Adds a new deadline task to task list. 
     * Processes the user input to extract date. 
     * If date is not null, create new deadline task. 
     * Otherwise, throw invalid command syntax error. 
     * 
     * @throws IllegalArgumentException If date is not found. 
     */
    private static void executeAddDeadline() throws IllegalArgumentException {
        String date = processParameters(DEADLINE_DATA_PREFIX_BY);
        tasks[sizeOfTaskList] = new Deadline(userCommand, date);
        sizeOfTaskList++;
    }

    /**
     * Adds a new deadline task to task list. 
     * Processes the user input to extract date. 
     * If date is not null, create new deadline task.  
     * 
     * @throws IllegalArgumentException If date is not found. 
     */
    private static void executeAddEvent() throws IllegalArgumentException {
        String date = processParameters(EVENT_DATA_PREFIX_AT);
        tasks[sizeOfTaskList] = new Event(userCommand, date);
        sizeOfTaskList++;
    }

    /**
     * Finds the location of the filters and locate the date from the task. 
     * Removes the task date information from userCommand, keeping only the task name in userCommand. 
     * 
     * @param filterString The string to find in userCommand depending on an event (/at) or deadline (/by). 
     * @return The date extracted from userCommand. 
     * @throws IllegalArgumentException If date is not found. 
     */
    private static String processParameters(String filterString) throws IllegalArgumentException {
        int indexOfDate = userCommand.indexOf(filterString);
        if (indexOfDate > 0) {
            String date = getDate(indexOfDate, filterString);
            userCommand = userCommand.substring(0, indexOfDate - 1);
            return date;
        }
        throw new IllegalArgumentException(getSyntaxMessage(filterString));
    }

    /**
     * Get the syntax of the commands depending on the one given by the user. 
     * 
     * @param filterString The string to find in userCommand depending on an event (/at) or deadline (/by). 
     * @return The syntax to task command given by the user. 
     */
    private static String getSyntaxMessage(String filterString) {
        if (filterString.equals(DEADLINE_DATA_PREFIX_BY)) {
            return MESSAGE_DEADLINE_SYNTAX;
        }
        return MESSAGE_EVENT_SYNTAX;
    }

    /**
     * Extracts the date from userCommand. 
     * 
     * @param indexOfDate The index for the location of /by in userCommand. Indicates the begining of date. 
     * @param filterString The string to find in userCommand depending on an event (/at) or deadline (/by). 
     * @return Date specified in user input (userCommand). 
     * @throws IllegalArgumentException If no date is detected after the /by parameter. 
     */
    private static String getDate(int indexOfDate, String filterString) throws IllegalArgumentException {
        // Check if string contains date after filterString 
        // +4 to remove filterString and +1 to convert index to length. Total: +5
        if (userCommand.length() < indexOfDate + 5) {
            throw new IllegalArgumentException(getSyntaxMessage(filterString));
        }
        // Add 4 to indexOfDate to remove the "/by " or "/at " filter strings
        return userCommand.substring(indexOfDate + 4);
    }

    /**
     * Displays success message after adding new task. 
     */
    private static void displayAddTaskSuccessMessage() {
        displayToUser(MESSAGE_ADDED, "  " + getTask(sizeOfTaskList-1).toString(), String.format(MESSAGE_NUMBER_OF_TASKS,
                sizeOfTaskList));
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
     * Displays the list of tasks to the user. 
     * The list will be numbered, starting from 1. 
     * 
     * @param tasks Tasks to be listed. 
     */
    private static void displayToUser(Task[] tasks) {
        String listAsString = getDisplayString(tasks);
        displayToUser(listAsString);
    }

    /**
     * Returns the display string representation of the list of tasks.
     * 
     * @param tasks Task list used. 
     * @return The list of all items in list, formatted with numberings and the total number of tasks in list. 
     */
    private static String getDisplayString(Task[] tasks) {
        StringBuilder message = new StringBuilder();
        message.append(String.format("%s", MESSAGE_LIST));
        for (int i = 0; i < sizeOfTaskList; i++) {
            int displayIndex = i + 1;
            message.append(String.format("\n\t %d. %s", displayIndex, getTask(i).toString()));
        }
        return message.toString();
    }
}