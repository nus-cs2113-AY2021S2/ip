import java.util.Scanner;

public class Duke {
    private static final int ERR_OUT_OF_BOUNDS_MESSAGE = -2;
    private static final int ERR_WRONG_FORMAT_MESSAGE = -1;
    private static final int INVALID_DONE = -1;
    private static final int COMMAND_EXIT = 0;
    private static final int COMMAND_LIST = 1;
    private static final int COMMAND_MARK = 2;
    private static final int COMMAND_ADD = 3;
    private static final int ADD_TODO = 4;
    private static final int ADD_DEADLINE = 5;
    private static final int ADD_EVENT = 6;

    private static final String GREETING_MESSAGE = "Wagwan! I is Ali G. West side.\nWhat is we chattin' bout today?";
    private static final String GOODBYE_MESSAGE = "Goodbye, big up yourself, keep it real, respekt.";
    private static final String BORDER_LINE = "____________________________________________________________";
    private static final String LIST_ITEMS_MESSAGE = "Eez are the tings you added to the list";
    private static final String EXCEED_CAPACITY_MESSAGE = "Maximum capacity reached";
    private static final String ADDED_TO_LIST_MESSAGE = "Wicked. This ting is now on da list.";
    private static final String SET_TO_DONE_MESSAGE = " set to done. You're well smart innit?";
    private static final String OUT_OF_BOUNDS_MESSAGE = "Woah woah. Don't violate the rules aight. I is watching you.";
    private static final String WRONG_FORMAT_MESSAGE = "Are you spasticated? The format is wrong!";
    private static final String TOTAL_TASK_MESSAGE = "You is having %d task(s) on your list";

    public static Task[] Tasks = new Task[100];

    public static void main(String[] args) {
        printGreeting();
        loopCommands();
        sayGoodbye();
    }

    /**
     * Parses the command that user has keyed in.
     *
     * @param line input from user.
     * @return The type of command user inputted.
     */
    public static int getCommand(String line) {
        String[] subStrings = line.split(" ");
        if (subStrings[0].equalsIgnoreCase("bye")) {
            return COMMAND_EXIT;
        }
        if (subStrings[0].equalsIgnoreCase("done")) {
            return COMMAND_MARK;
        }
        if (subStrings[0].equalsIgnoreCase("list")) {
            return COMMAND_LIST;
        }
        return COMMAND_ADD;
    }
    /**
     * Parses the type of task to add to list.
     *
     * @param line input from user.
     * @return Type of task to add if valid input, error otherwise.
     */
    public static int getItemType(String line) {
        if (line.equalsIgnoreCase("todo")) {
            return ADD_TODO;
        }
        if (line.equalsIgnoreCase("deadline")) {
            return ADD_DEADLINE;
        }
        if (line.equalsIgnoreCase("event")) {
            return ADD_EVENT;
        }
        return ERR_WRONG_FORMAT_MESSAGE;
    }
    /**
     * Loops through all features available.
     * Returns if user inputs bye or number of tasks exceeds 100.
     */
    public static void loopCommands() {
        while (true) {
            if (Task.totalNumberOfTasks > 100) {
                printReachedMaxCapacity();
                return;
            }
            Scanner in = new Scanner(System.in);
            String line;
            while (in.hasNextLine()) {
                line = in.nextLine();
                int command = getCommand(line);
                // No fallthrough required
                switch (command) {
                // If user wants to mark a task as done
                case COMMAND_MARK:
                    markAsDone(line);
                    break;
                // If user wants to list all tasks
                case COMMAND_LIST:
                    listItems();
                    break;
                case COMMAND_ADD:
                    // If user wants to add an item
                    addItem(line);
                    break;
                case COMMAND_EXIT:
                    return;
                }
            }
        }
    }
    /**
     * Marks a task as done.
     * Checks for out of bounds access and presence of numerical value.
     *
     * @param line user input.
     */
    public static void markAsDone(String line) {
        int listNum = checkValidDone(line);
        if (listNum == INVALID_DONE) {
            printError(ERR_OUT_OF_BOUNDS_MESSAGE);
        } else {
            listNum -= 1;
            Tasks[listNum].setDone();
            printMarkedDone(Tasks[listNum].getName());
        }
        printBorderLine();
    }
    /**
     * Checks for presence of number on index 5 of input.
     * Then check if the number is within bounds.
     *
     * @param line input from user.
     * @return index of item to mark as done if valid, -1 otherwise.
     */
    public static int checkValidDone(String line) {
        if (line.substring(5).matches("[0-9]+")) {
            int listNum = Integer.parseInt(line.substring(5));
            // Check for illegal access to out of bounds index
            if (listNum > Task.totalNumberOfTasks || listNum == 0) {
                return INVALID_DONE;
            }
            return listNum;
        }
        return INVALID_DONE;
    }
    /** Lists all items that were added to the list. */
    public static void listItems() {
        System.out.println(LIST_ITEMS_MESSAGE);
        for(int i = 0; i < Task.totalNumberOfTasks; i++) {
            printListItem(i+1, Tasks[i].getType(), Tasks[i].getStatusIcon(), Tasks[i].getName(), Tasks[i].getDate());
        }
        printBorderLine();
    }
    /**
     * Append new item to back of the list.
     * Parses type of item to add.
     *
     * @param line raw input given by user.
     */
    public static void addItem(String line) {
        String prefix = line.split(" ")[0];
        int itemType = getItemType(prefix);
        // No fallthrough required
        switch (itemType) {
        case ADD_TODO:
            addTodo(line);
            break;
        case ADD_DEADLINE:
            addDeadline(line);
            break;
        case ADD_EVENT:
            addEvent(line);
            break;
        case ERR_WRONG_FORMAT_MESSAGE:
            printError(ERR_WRONG_FORMAT_MESSAGE);
        }
        printBorderLine();
    }
    public static void addTodo(String line) {
        int current = Task.totalNumberOfTasks;
        String nameOfTask = line.substring(5);
        Tasks[current] = new Todo(nameOfTask);
        Task.totalNumberOfTasks += 1;
        printAddedToList(current, nameOfTask);
    }
    public static void addDeadline(String line) {
        if (line.contains("/by")) {
            int current = Task.totalNumberOfTasks;
            // Details of task starts at index 9 of input
            String nameAndDeadline = line.substring(9);
            String[] split = nameAndDeadline.split(" /by ");
            String name = split[0];
            String deadline = split[1];
            Tasks[current] = new Deadline(name, deadline);
            Task.totalNumberOfTasks += 1;
            printAddedToList(current, name);
        } else {
            printError(ERR_WRONG_FORMAT_MESSAGE);
        }
    }
    public static void addEvent(String nameOfTask) {
        if (nameOfTask.contains("/at")) {
            int current = Task.totalNumberOfTasks;
            // Details of task starts at index 6 of input
            String nameAndTime = nameOfTask.substring(6);
            String[] split = nameAndTime.split(" /at ");
            String name = split[0];
            String time = split[1];
            Tasks[current] = new Event(name, time);
            Task.totalNumberOfTasks += 1;
            printAddedToList(current, name);
        } else {
            printError(ERR_WRONG_FORMAT_MESSAGE);
        }
    }
    public static void printReachedMaxCapacity() {
        System.out.println(EXCEED_CAPACITY_MESSAGE);
    }
    public static void printListItem(int index, String type, String status, String name, String date) {
        System.out.println(index + ". " + type + "[" + status + "] " + name + " " + date);
    }
    public static void printMarkedDone(String name) {
        System.out.println(name + SET_TO_DONE_MESSAGE);
    }
    public static void printGreeting() {
        System.out.println(GREETING_MESSAGE);
        printBorderLine();
    }
    public static void printBorderLine() {
        System.out.println(BORDER_LINE);
    }
    public static void sayGoodbye() {
        System.out.println(GOODBYE_MESSAGE);
        printBorderLine();
    }

    /**
     * Prints appropriate error message according to error type.
     * Error types are illegally accessing indexes and wrong command format.
     *
     * @param type type of error thrown.
     */
    public static void printError(int type) {
        // No fallthrough required
        switch (type) {
        case ERR_OUT_OF_BOUNDS_MESSAGE:
            System.out.println(OUT_OF_BOUNDS_MESSAGE);
            printBorderLine();
            break;
        case ERR_WRONG_FORMAT_MESSAGE:
            System.out.println(WRONG_FORMAT_MESSAGE);
            break;
        }
    }
    public static void printAddedToList(int current, String name) {
        System.out.println(ADDED_TO_LIST_MESSAGE);
        System.out.println(Tasks[current].getType() + "[ ] " + name + " " + Tasks[current].getDate());
        printTotalTasks();
    }
    public static void printTotalTasks() {
        System.out.println(String.format(TOTAL_TASK_MESSAGE, Task.totalNumberOfTasks));
    }
}
