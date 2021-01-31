import java.util.Scanner;

public class Duke {
    private static final int ERR_OUT_OF_BOUNDS = -2;
    private static final int ERR_WRONG_FORMAT = -1;
    private static final int INVALID_DONE = -1;
    private static final int EXIT = 0;
    private static final int LIST = 1;
    private static final int MARK = 2;
    private static final int ADD = 3;
    private static final int ADD_TODO = 4;
    private static final int ADD_DEADLINE = 5;
    private static final int ADD_EVENT = 6;

    public static Task[] Tasks = new Task[100];

    public static void main(String[] args) {
        printGreeting();
        loopCommands();
        sayGoodbye();
    }

    public static int getCommand(String line) {
        String[] subStrings = line.split(" ");
        if (subStrings[0].equalsIgnoreCase("bye")) {
            return EXIT;
        }
        if (subStrings[0].equalsIgnoreCase("done")) {
            return MARK;
        }
        if (subStrings[0].equalsIgnoreCase("list")) {
            return LIST;
        }
        return ADD;
    }
    public static int getItemType(String line) {
        if(line.equalsIgnoreCase("todo")) {
            return ADD_TODO;
        }
        if(line.equalsIgnoreCase("deadline")) {
            return ADD_DEADLINE;
        }
        if(line.equalsIgnoreCase("event")) {
            return ADD_EVENT;
        }
        return ERR_WRONG_FORMAT;
    }
    /**
     * Loops through all features available.
     * Returns if user inputs bye or number of tasks exceeds 100
     */
    public static void loopCommands() {
        while (true) {
            if (Task.totalTasks > 100) {
                System.out.println("Maximum capacity reached");
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
                case MARK:
                    markAsDone(line);
                    break;
                // If user wants to list all tasks
                case LIST:
                    listItems();
                    break;
                case ADD:
                    // If user wants to add an item
                    addItem(line);
                    break;
                case EXIT:
                    return;
                }
            }
        }
    }
    /**
     * Marks a task as done
     * Checks for out of bounds access and presence of numerical value
     * @param line user input
     */
    public static void markAsDone(String line) {
        int listNum = checkValidDone(line);
        if(listNum == INVALID_DONE) {
            printError(ERR_OUT_OF_BOUNDS);
        } else {
            listNum -= 1;
            Tasks[listNum].setDone();
            System.out.println(Tasks[listNum].getName() + " set to done. You're well smart innit?");
        }
        printLine();
    }

    /**
     * Checks for presence of number in input AND for any illegal access of indexes
     * @param line input from user
     * @return index of item to mark as done if valid, -1 otherwise
     */
    public static int checkValidDone(String line) {
        if (line.substring(5).matches("[0-9]+")) {
            int listNum = Integer.parseInt(line.substring(5));
            // Check for illegal access to out of bounds index
            if (listNum > Task.totalTasks || listNum == 0) {
                return INVALID_DONE;
            }
            return listNum;
        }
        return INVALID_DONE;
    }
    /**
     * Lists all items that were added to the list
     */
    public static void listItems() {
        System.out.println("Eez are the tings you added to the list");
        for(int i = 0; i < Task.totalTasks; i++) {
            printListItem(i+1, Tasks[i].getType(), Tasks[i].getStatusIcon(),
                    Tasks[i].getName(), Tasks[i].getDate());
        }
        printLine();
    }
    /**
     * Append new item to back of the list
     * @param line raw input given by user
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
        case ERR_WRONG_FORMAT:
            printError(ERR_WRONG_FORMAT);
        }
        printLine();
    }
    public static void addTodo(String line) {
        int current = Task.totalTasks;
        String nameOfTask = line.substring(5);
        Tasks[current] = new Todo(nameOfTask);
        Task.totalTasks += 1;
        printAddedToList(current, nameOfTask);
    }
    public static void addDeadline(String line) {
        if(line.contains("/by")) {
            int current = Task.totalTasks;
            // Details of task starts at index 9 of input
            String nameAndDeadline = line.substring(9);
            String[] split = nameAndDeadline.split(" /by ");
            String name = split[0];
            String deadline = split[1];
            Tasks[current] = new Deadline(name, deadline);
            Task.totalTasks += 1;
            printAddedToList(current, name);
        } else {
            printError(ERR_WRONG_FORMAT);
        }
    }
    public static void addEvent(String nameOfTask) {
        if(nameOfTask.contains("/at")) {
            int current = Task.totalTasks;
            // Details of task starts at index 6 of input
            String nameAndTime = nameOfTask.substring(6);
            String[] split = nameAndTime.split(" /at ");
            String name = split[0];
            String time = split[1];
            Tasks[current] = new Event(name, time);
            Task.totalTasks += 1;
            printAddedToList(current, name);
        } else {
            printError(ERR_WRONG_FORMAT);
        }
    }
    public static void printListItem(int index, String type, String status, String name, String date) {
        System.out.println(index + ". " + type + "[" + status + "] " + name + " " + date);
    }
    public static void printGreeting() {
        System.out.println("Wagwan! I is Ali G. West side.\nWhat is we chattin' bout today?");
        printLine();
    }
    public static void printLine() {
        System.out.println("____________________________________________________________");
    }
    public static void sayGoodbye() {
        System.out.println("Goodbye, big up yourself, keep it real, respekt.");
        printLine();
    }
    public static void printError(int type) {
        // No fallthrough required
        switch (type) {
        case ERR_OUT_OF_BOUNDS:
            System.out.println("Woah woah. Don't violate the rules aight. I is watching you.");
            printLine();
            break;
        case ERR_WRONG_FORMAT:
            System.out.println("Are you spasticated? The format is wrong!");
            break;
        }
    }
    public static void printAddedToList(int current, String name) {
        System.out.println("Wicked. This ting is now on da list.");
        System.out.println(Tasks[current].getType() + "[ ] " + name + " " + Tasks[current].getDate());
        printTotalTasks();
    }
    public static void printTotalTasks() {
        System.out.println("You is having " + Task.totalTasks + " task(s) on your list");
    }
}
