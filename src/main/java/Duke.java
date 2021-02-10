import java.util.Scanner;


public class Duke {
    /**
     * A divider (horizontal line).
     */
    public static final String DIVIDER_LINE_ONLY = "__________________________________________";
    /**
     * A divider (horizontal line) with line break at the end.
     */
    public static final String DIVIDER = "__________________________________________\n";

    /**
     * Prints greetings when the program starts.
     */
    public static void printGreetings() {
        System.out.println(
                DIVIDER
                        + " Welcome.\n"
                        + " I am your assistant Friday. ✧ʕ̢̣̣̣̣̩̩̩̩·͡˔·ོɁ̡̣̣̣̣̩̩̩̩✧\n"
                        + " Just FYI, I am developed by Song Yu.\n"
                        + " May I know what I can help you?\n"
                        + DIVIDER_LINE_ONLY
        );
    }

    /**
     * Prints greetings when the program ends.
     */
    public static void printExitGreetings() {
        System.out.print(
                DIVIDER
                        + " Thank you for getting in touch.\n"
                        + " See you next time.\n"
                        + "✧( ु•⌄• )◞ᴴᴬᵛᴱ ᴬ ᴳᴼᴼᴰ ᵀᴵᴹᴱ\n"
                        + DIVIDER_LINE_ONLY
        );
    }

    /**
     * Prints the error message when user's input does not match any command.
     */
    public static void printNotCommand() {
        System.out.println(
                DIVIDER
                        + " Sorry, your command is not recognized\n"
                        + " See 'help'. \n"
                        + DIVIDER_LINE_ONLY
        );
    }

    /**
     * Prints the help message.
     */
    public static void printHelp() {
        String helpMessage = " 'help'     : Display tips on using this application\n"
                + " 'exit'     : Exit the application\n"
                + " 'bye'      : Exit the application\n"
                + " 'list'     : List all type of tasks you added to your task list\n"
                + " 'done'     : Mark a task as done\n\t"
                + " e.g. 'done 2' will mark the second task as done\n"
                + " 'todo'     : Add a new todo task\n\t"
                + " e.g. 'todo read book' will add \"read book\" to your task list\n"
                + " 'deadline' : Add a new deadline task\n\t"
                + " e.g. 'deadline read book /by Sunday' will"
                + " add \"read book\" to your task list with deadline \"Sunday\"\n"
                + " 'event' : Add a new event task\n\t"
                + " e.g. 'event read book /at Mon 2-4pm' will"
                + " add \"read book\" to your task list with period  \"Mon 2-4pm\"\n";

        System.out.println(
                DIVIDER_LINE_ONLY + DIVIDER
                        + helpMessage
                        + DIVIDER_LINE_ONLY + DIVIDER_LINE_ONLY
        );
    }

    /**
     * Gets command from user input.
     *
     * @param userInput user's input.
     * @return command.
     */
    private static String getCommand(String userInput) {
        String[] inputWords = userInput.split(" ");
        return inputWords[0];
    }

    /**
     * Gets user input and execute corresponding command until the loop is exit.
     *
     * @param sc       Java Scanner to get user input.
     * @param taskList The list of Task objects.
     */
    private static void operateMainLoop(Scanner sc, TaskList taskList) {
        String userInput;
        while (true) {
            userInput = sc.nextLine();
            String userCommand = getCommand(userInput);
            switch (userCommand) {
            case "help":
                printHelp();
                break;
            case "list":
                taskList.printCurrentList();
                break;
            case "todo":
                addTodoTaskToList(taskList, userInput);
                break;
            case "deadline":
                addDeadlineTaskToList(taskList, userInput);
                break;
            case "event":
                addEventTaskToList(taskList, userInput);
                break;
            case "done":
                handleDoneTask(taskList, userInput);
                break;
            case "exit":
                //FALL-THROUGH
            case "bye":
                printExitGreetings();
                return;
            default:
                printNotCommand();
                break;
            }
        }
    }

    /**
     * Marks a task with a given integer index as done, if the index is not out of range.
     *
     * @param taskList  The list that stores Task objects.
     * @param userInput User's keyboard input in String.
     */
    private static void handleDoneTask(TaskList taskList, String userInput) {
        try {
            String taskIndexString = getTaskIndexString(userInput);
            int itemIndex = Integer.parseInt(taskIndexString);
            if (isItemIndexOutOfRange(taskList, itemIndex)) {
                return;
            }
            setTaskAsDone(taskList, itemIndex);
        } catch (Exception e) {
            printInvalidIntegerWarning();
        }
    }

    /**
     * Prints a warning that the input is not a valid integer.
     */
    private static void printInvalidIntegerWarning() {
        System.out.println(DIVIDER
                + "Invalid input!\n"
                + "The item number should be a valid integer!\n"
                + DIVIDER
                + "Try again:"
        );
    }

    /**
     * Returns the task index in integer.
     *
     * @param userInput User's keyboard input in String.
     * @return an integer task index.
     */
    private static String getTaskIndexString(String userInput) {
        return userInput.split(" ")[1];
    }

    /**
     * Sets the status of a Task object as done.
     *
     * @param taskList  The list that stores Task objects.
     * @param itemIndex The index of the task object in the task list.
     */
    private static void setTaskAsDone(TaskList taskList, int itemIndex) {
        taskList.updateTaskStatus(itemIndex - 1, true);
    }

    /**
     * Returns true if the index of the task is out of range, false if in range.
     *
     * @param taskList  The list that stores Task objects.
     * @param itemIndex The index of the task object in the task list.
     * @return true if the index of the task is out of range.
     */
    private static boolean isItemIndexOutOfRange(TaskList taskList, int itemIndex) {
        if (!taskList.isIndexInRange(itemIndex - 1)) {
            System.out.println(DIVIDER
                    + "The task index input is out of range!\n"
                    + DIVIDER
                    + "Try again:"
            );
            return true;
        }
        return false;
    }

    /**
     * Adds an event type task into the taskList.
     *
     * @param taskList  The list that stores Task objects.
     * @param userInput User's keyboard input in String.
     */
    private static void addEventTaskToList(TaskList taskList, String userInput) {
        try {
            Event.isCommandValid(userInput);
            String[] parseResult = Event.parseTaskContent(userInput);
            String taskContent = parseResult[0];
            String taskPeriod = parseResult[1];
            taskList.addEventTask(taskContent, taskPeriod);
        } catch (Exception e) {
            System.out.println(DIVIDER + e.getMessage() + DIVIDER_LINE_ONLY);
        }
    }

    /**
     * Adds a deadline type task into the taskList.
     *
     * @param taskList  The list that stores Task objects.
     * @param userInput User's keyboard input in String.
     */
    private static void addDeadlineTaskToList(TaskList taskList, String userInput) {
        try {
            Deadline.isCommandValid(userInput);
            String[] parseResult = Deadline.parseTaskContent(userInput);
            String taskContent = parseResult[0];
            String taskDeadline = parseResult[1];
            taskList.addDeadlineTask(taskContent, taskDeadline);
        } catch (Exception e) {
            System.out.println(DIVIDER + e.getMessage() + DIVIDER_LINE_ONLY);
        }
    }

    /**
     * Adds a to-do type task into the taskList.
     *
     * @param taskList  The list that stores Task objects.
     * @param userInput User's keyboard input in String.
     */
    private static void addTodoTaskToList(TaskList taskList, String userInput) {
        try {
            String[] parseResult = Todo.parseTaskContent(userInput);
            taskList.addTodoTask(parseResult[0]);
        } catch (Exception e) {
            System.out.println(DIVIDER + e.getMessage() + DIVIDER_LINE_ONLY);
        }
    }

    /**
     * The main method that drives the application.
     *
     * @param args Commandline arguments.
     */
    public static void main(String[] args) {
        printGreetings();
        Scanner sc = new Scanner(System.in);
        TaskList taskList = new TaskList();
        operateMainLoop(sc, taskList);
    }
}
