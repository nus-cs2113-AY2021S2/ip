import java.util.Scanner;

public class Duke {
    /**
     * Words for commands. 
     */
    private static final String COMMAND_TODO_WORD = "todo";
    private static final String COMMAND_DEADLINE_WORD = "deadline";
    private static final String COMMAND_EVENT_WORD = "event";
    private static final String COMMAND_MARK_WORD = "done";
    private static final String COMMAND_LIST_WORD = "list";
    private static final String COMMAND_EXIT_WORD = "bye";
    private static final String NO_COMMAND = "";
    
    /**
     * Display messages. 
     */
    private static final String MESSAGE_WELCOME = "Hello! I'm Duke, what can I do for you?";
    private static final String MESSAGE_BORDER = "__________".repeat(6);
    private static final String MESSAGE_ADDED = "Got it. I've added this task: ";
    private static final String MESSAGE_MARKED = "Nice! I've marked this task as done: ";
    private static final String MESSAGE_LIST = "Here are the tasks in your list: ";
    private static final String MESSAGE_EXIT = "Bye. Hope to see you again soon! ";
    private static final String MESSAGE_NUMBER_OF_TASKS = "Now you have %s tasks in the list. ";
    private static final String ERROR_INDEX_OUT_OF_RANGE = "Index out of range. ";
    private static final String ERROR_NO_COMMAND_RECEIVED = "No command detected. ";
    private static final String ERROR_INVALID_COMMAND_RECEIVED = "Invalid command. ";

    /**
     * Filter words to locate date for deadline and event tasks. 
     */
    private static final String DEADLINE_DATA_PREFIX_BY = "/by";
    private static final String EVENT_DATA_PREFIX_AT = "/at";

    /**
     * List of all tasks. 
     */
    private static Task[] tasks;
    private static String userCommand;

    /**
     * Maximum number of tasks that can be held. 
     */
    private static final int MAX_LIST_SIZE = 100;

    /**
     * Total number of tasks in task list. 
     */
    private static int sizeOfTaskList = 0;

    /**
     * Scanner for extracting user input. 
     */
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
     * Gets task number from the input string. 
     * 
     * @return Task number. 
     */
    private static int getTaskNumber(String command) {
        int taskNumber = Integer.parseInt(command.substring(command.indexOf(" ") + 1)) - 1;
        return taskNumber;
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
     * 
     * @return The user input obtained.  
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
        switch(getCommand()) {
        case COMMAND_EXIT_WORD:
            // Fallthrough
            displayToUser(MESSAGE_EXIT);
            executeExitProgramRequest();
        case COMMAND_LIST_WORD:
            executeListAllTasks();
            return;
        case COMMAND_MARK_WORD:
            int taskNumber = getTaskNumber(userCommand);
            if (isTaskNumberValid(taskNumber)) {
                executeMarkTask(taskNumber);
                displayMarkTaskSuccessMessage(taskNumber);
            } else {
                displayToUser(ERROR_INDEX_OUT_OF_RANGE);
            }
            return;
        case COMMAND_TODO_WORD:
            executeAddTodo(); 
            return;
        case COMMAND_DEADLINE_WORD:
            executeAddDeadline();
            return;
        case COMMAND_EVENT_WORD:
            executeAddEvent();
            return;
        case NO_COMMAND:
            displayToUser(ERROR_NO_COMMAND_RECEIVED);
            return;
        default:
            displayToUser(ERROR_INVALID_COMMAND_RECEIVED);
            return;
        }
    }

    /**
     * Extracts the command word from user input. 
     * 
     * @return Command extracted. 
     */
    private static String getCommand() {
        String commandWord = userCommand.split(" ")[0].toLowerCase();
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
     */
    private static void executeListAllTasks() {
        displayToUser(tasks);
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
     * @param taskNumber The task number to mark as done (starting with 1). 
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
     */
    private static void executeAddTodo() {
        tasks[sizeOfTaskList] = new Todo(userCommand);
        sizeOfTaskList++;
        displayAddTaskSuccessMessage();
    }

    /**
     * Adds a new deadline task to task list. 
     * Processes the user input to extract date. 
     * If date is not null, create new deadline task. 
     * Otherwise, display invalid command error. 
     */
    private static void executeAddDeadline() {
        String date = processParameters(DEADLINE_DATA_PREFIX_BY);
        if (date != null) {
            tasks[sizeOfTaskList] = new Deadline(userCommand, date);
            sizeOfTaskList++;
            displayAddTaskSuccessMessage();
        } else {
            displayToUser(ERROR_INVALID_COMMAND_RECEIVED);
        }
    }

    /**
     * Adds a new event task to task list. 
     */
    private static void executeAddEvent() {
        // Process event parameters
        String date = processParameters(EVENT_DATA_PREFIX_AT);
        if (date != null) {
            tasks[sizeOfTaskList] = new Event(userCommand, date);
            sizeOfTaskList++;
            displayAddTaskSuccessMessage();
        } else {
            // Display error message
            displayToUser(ERROR_INVALID_COMMAND_RECEIVED);
        }

    }

    /**
     * Finds the location of the filters and locate the date from the task. 
     * Removes the task date information from userCommand, keeping only the task name in userCommand. 
     * 
     * @param filterString The string to find in userCommand depending on an event (/at) or deadline (/by). 
     * @return The date extracted from userCommand. 
     */
    private static String processParameters(String filterString) {
        int indexOfDate = userCommand.indexOf(filterString);
        if (indexOfDate > 0) {
            String date = userCommand.substring(indexOfDate + 4);
            userCommand = userCommand.substring(0, indexOfDate - 1);
            return date;
        } else {
            return null;
        }
        
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
    private static void displayToUser(String... message) {
        System.out.println(String.format("\t%s", MESSAGE_BORDER));
        for (String m : message) {
            System.out.println(String.format("\t %s", m));
        }
        System.out.println(String.format("\t%s", MESSAGE_BORDER));
    }

    /**
     * Displays the list of tasks to the user. 
     * The list will be indexed, starting from 1. 
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