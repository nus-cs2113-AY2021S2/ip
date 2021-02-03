import java.util.Scanner;

public class Duke {

    static final int COMMAND_DEFAULT = 0;
    static final int COMMAND_TODO = 1;
    static final int COMMAND_DEADLINE = 2;
    static final int COMMAND_EVENT = 3;

    // Prints horizontal line
    public static void printDividerLine() {
        System.out.println("____________________________________________________________");
    }
    // Prints hello message
    public static void printHello() {
        printDividerLine();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I'm Duke\n" + logo);
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
    public static void printList(Task[] tasks, int count) {
        printDividerLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < count; i++) {
            System.out.print(i + 1 + ".");
            tasks[i].printTask();
        }
        printDividerLine();
    }
    // Adds task to list
    public static int addTask(Task[] tasks, int taskCount, String userInput, int command) {
        String description;
        String date;
        int indexOfSlash = userInput.indexOf("/");

        printDividerLine();

        switch (command) {
        case COMMAND_TODO:
            description = userInput.substring(5);
            tasks[taskCount] = new Todo(description);
            break;
        case COMMAND_DEADLINE:
            description = userInput.substring(9, indexOfSlash);
            date = userInput.substring(indexOfSlash + 3);
            tasks[taskCount] = new Deadline(description, date);
            break;
        case COMMAND_EVENT:
            description = userInput.substring(6, indexOfSlash);
            date = userInput.substring(indexOfSlash + 3);
            tasks[taskCount] = new Event(description, date);
            break;
        default:
            tasks[taskCount] = new Task(userInput);
        }

        System.out.print("Got it. I've added this task:\n  ");
        tasks[taskCount++].printTask();
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        printDividerLine();
        return taskCount;
    }
    // Marks task as done
    public static void markTask(Task[] tasks, int count, String userInput) {
        int length = userInput.length();
        String number = userInput.substring(5);
        int taskNumber = Integer.parseInt(number) - 1;

        printDividerLine();
        // Check if input is valid
        if (length <= 5) {
            return;
        }
        if (taskNumber < count && taskNumber >= 0) {
            tasks[taskNumber].markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            tasks[taskNumber].printTask();
            printDividerLine();
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
                printList(tasks, taskCount);
            } else if (userInput.startsWith("done")) {
                markTask(tasks, taskCount, userInput);
            } else if (userInput.startsWith("todo")) {
                taskCount = addTask(tasks, taskCount, userInput, COMMAND_TODO);
            } else if (userInput.startsWith("deadline")) {
                taskCount = addTask(tasks, taskCount, userInput, COMMAND_DEADLINE);
            } else if (userInput.startsWith("event")) {
                taskCount = addTask(tasks, taskCount, userInput, COMMAND_EVENT);
            } else {
                taskCount = addTask(tasks, taskCount, userInput, COMMAND_DEFAULT);
            }
        }
    }
}
