import java.util.Scanner;

public class Duke {

    static final int COMMAND_TODO = 0;
    static final int COMMAND_DEADLINE = 1;
    static final int COMMAND_EVENT = 2;
    static final String DIVIDER_LINE = "____________________________________________________________";
    static final String INVALID_NUMBER = "OOPS!!! Invalid task number =/\n" + DIVIDER_LINE;
    static final String UNKNOWN_COMMAND = "OOPS!!! I'm sorry, but I don't know what that means =/\n" + DIVIDER_LINE;
    static final String INVALID_TODO = "OOPS!!! Invalid todo description\n" + DIVIDER_LINE;
    static final String INVALID_DEADLINE = "OOPS!!! Invalid deadline description\n" + DIVIDER_LINE;
    static final String INVALID_EVENT = "OOPS!!! Invalid event description\n" + DIVIDER_LINE;
    static final String LIST_EMPTY = "The list is currently empty!\n" + DIVIDER_LINE;
    static final String LIST_FULL = "The list is currently full!\n" + DIVIDER_LINE;

    // Prints horizontal line
    public static void printDividerLine() {
        System.out.println(DIVIDER_LINE);
    }
    // Prints hello message
    public static void printHello() {
        printDividerLine();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I'm Duke!\n" + logo);
        System.out.println("What can I do for you?\n" + "List of commands: todo, deadline, event, done, bye");
        printDividerLine();
    }
    // Prints bye message
    public static void printBye() {
        printDividerLine();
        System.out.println("Bye. Hope to see you again soon!");
        printDividerLine();
    }
    // Prints list of tasks
    public static void printList(Task[] tasks, int count) throws DukeException {
        printDividerLine();
        if (count == 0) {
            throw new DukeException();
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < count; i++) {
            System.out.print(i + 1 + ".");
            tasks[i].printTask();
        }
        printDividerLine();
    }
    // Adds task to list
    public static int addTask(Task[] tasks, int taskCount, String userInput, int command) throws DukeException {
        String description;
        String date;
        int indexOfSlash = userInput.indexOf("/");
        int stringLength = userInput.length();
        int indexOfSpace = userInput.indexOf(" ");

        printDividerLine();
        if (taskCount == 100) {
            System.out.println(LIST_FULL);
            throw new DukeException();
        }
        switch (command) {
        case COMMAND_TODO:
            if (stringLength < 6 || indexOfSpace != 4) {
                System.out.println(INVALID_TODO);
                throw new DukeException();
            }
            description = userInput.substring(5);
            tasks[taskCount] = new Todo(description);
            break;
        case COMMAND_DEADLINE:
            if (stringLength < 10 || indexOfSpace != 8 || indexOfSlash == -1) {
                System.out.println(INVALID_DEADLINE);
                throw new DukeException();
            }
            description = userInput.substring(9, indexOfSlash);
            date = userInput.substring(indexOfSlash + 3);
            tasks[taskCount] = new Deadline(description, date);
            break;
        case COMMAND_EVENT:
            if (stringLength < 7 || indexOfSpace != 5 || indexOfSlash == -1) {
                System.out.println(INVALID_EVENT);
                throw new DukeException();
            }
            description = userInput.substring(6, indexOfSlash);
            date = userInput.substring(indexOfSlash + 3);
            tasks[taskCount] = new Event(description, date);
            break;
        default:
        }

        System.out.print("Got it. I've added this task:\n  ");
        tasks[taskCount++].printTask();
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        printDividerLine();
        return taskCount;
    }
    // Marks task as done
    public static void markTask(Task[] tasks, int count, String userInput) throws DukeException {
        String number = userInput.substring(5);
        int taskNumber = Integer.parseInt(number) - 1;

        if (taskNumber < count && taskNumber >= 0) {
            tasks[taskNumber].markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            tasks[taskNumber].printTask();
            printDividerLine();
        }
        else {
            throw new DukeException();
        }
    }

    public static void main(String[] args) {
        String userInput;
        Scanner in = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int taskCount = 0;

        printHello();

        while(true) {
            userInput = in.nextLine();
            if (userInput.equals("bye")) {
                printBye();
                break;
            } else if (userInput.equals("list")) {
                try {
                    printList(tasks, taskCount);
                } catch (DukeException e) {
                    System.out.println(LIST_EMPTY);
                }
            } else if (userInput.startsWith("done")) {
                try {
                    markTask(tasks, taskCount, userInput);
                } catch (NumberFormatException e) {
                    System.out.println(INVALID_NUMBER);
                } catch (DukeException e) {
                    System.out.println(INVALID_NUMBER);
                }
            } else if (userInput.startsWith("todo")) {
                try {
                    taskCount = addTask(tasks, taskCount, userInput, COMMAND_TODO);
                } catch (DukeException e) {
                }
            } else if (userInput.startsWith("deadline")) {
                try {
                    taskCount = addTask(tasks, taskCount, userInput, COMMAND_DEADLINE);
                } catch (DukeException e) {
                }
                catch (StringIndexOutOfBoundsException e) {
                    System.out.println(INVALID_DEADLINE);
                }
            } else if (userInput.startsWith("event")) {
                try {
                    taskCount = addTask(tasks, taskCount, userInput, COMMAND_EVENT);
                } catch (DukeException e) {
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println(INVALID_EVENT);
                }
            } else {
                System.out.println(UNKNOWN_COMMAND);
            }
        }
    }
}
