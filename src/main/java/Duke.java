import java.util.Scanner;

public class Duke {
    // Prints horizontal line
    public static void printLine() {
        System.out.println("____________________________________________________________");
    }
    // Prints horizontal line with an extra new line
    public static void printLineWithNewLine() {
        System.out.println("____________________________________________________________\n");
    }
    // Prints hello message
    public static void printHello() {
        printLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printLineWithNewLine();
    }
    // Prints bye message
    public static void printBye() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLineWithNewLine();
    }
    // Prints list of tasks
    public static void printList(Task[] tasks, int count) {
        printLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < count; i++) {
            System.out.print(i + 1 + ".");
            tasks[i].printTask();
        }
        printLineWithNewLine();
    }
    // Adds task to list
    public static int addTask(Task[] tasks, int count, String line, int command) {
        printLine();
        int indexOfSlash = line.indexOf("/");
        String description;
        String date;
        if (command == 0) {
            tasks[count] = new Task(line);
        } else if (command == 1) {
            description = line.substring(5);
            tasks[count] = new Todo(description);
        } else if (command == 2) {
            description = line.substring(9,indexOfSlash);
            date = line.substring(indexOfSlash+3);
            tasks[count] = new Deadline(description, date);
        } else if (command == 3) {
            description = line.substring(6,indexOfSlash);
            date = line.substring(indexOfSlash+3);
            tasks[count] = new Event(description, date);
        }
        System.out.println("Got it. I've added this task:");
        System.out.print("  ");
        tasks[count++].printTask();
        System.out.println("Now you have " + count + " tasks in the list.");
        printLineWithNewLine();
        return count;
    }
    // Marks task as done
    public static void markTask(Task[] tasks, int count, String line) {
        printLine();
        int length = line.length();
        // Check if input is valid
        if (length <= 5) {
            return;
        }
        String digits = line.substring(5);
        int number = Integer.parseInt(digits) - 1;
        // Check if number is valid
        if (number >= count || number < 0) {
            return;
        }
        tasks[number].markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        tasks[number].printTask();
        printLineWithNewLine();
    }

    public static void main(String[] args) {

        printHello();

        String line;
        Scanner in = new Scanner(System.in);
        int count = 0;
        Task[] tasks = new Task[100];

        while(true) {
            line = in.nextLine();
            if (line.equals("bye")) {
                printBye();
                break;
            } else if (line.equals("list")) {
                printList(tasks, count);
            } else if (line.startsWith("done")) {
                markTask(tasks, count, line);
            } else if (line.startsWith("todo")) {
                count = addTask(tasks, count, line, 1);
            } else if (line.startsWith("deadline")) {
                count = addTask(tasks, count, line, 2);
            } else if (line.startsWith("event")) {
                count = addTask(tasks, count, line, 3);
            } else {
                count = addTask(tasks, count, line, 0);
            }
        }
    }
}
