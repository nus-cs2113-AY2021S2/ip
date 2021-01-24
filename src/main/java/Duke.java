import java.util.Scanner;

public class Duke {
    // Print horizontal line
    public static void printLine(){
        System.out.println("____________________________________________________________");
    }
    // Print horizontal line with an extra new line
    public static void printLineWithNewLine(){
        System.out.println("____________________________________________________________\n");
    }
    // Print logo
    public static void printLogo(){
        printLine();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printLineWithNewLine();
    }
    // Print hello message
    public static void printHello() {
        printLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printLineWithNewLine();
    }
    // Print bye message
    public static void printBye() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLineWithNewLine();
    }
    // Print list of tasks
    public static void printList(Task[] tasks, int count) {
        printLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < count; i++) {
            System.out.println(i + 1 + "." + "[" + tasks[i].getStatusIcon() + "] "+ tasks[i].description);
        }
        printLineWithNewLine();
    }
    // Add task to list
    public static void addTask(Task[] tasks, int count, String line){
        printLine();
        tasks[count] = new Task(line);
        System.out.println("added: " + line);
        printLineWithNewLine();
    }
    // Mark task as done
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
        System.out.println("  [" + tasks[number].getStatusIcon() + "] "+ tasks[number].description);
        printLineWithNewLine();
    }

    public static void main(String[] args) {

        printLogo();
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
            }
            else if (line.equals("list")) {
                printList(tasks, count);
            }
            else if (line.startsWith("done")) {
                markTask(tasks, count, line);
            } else {
                addTask(tasks, count, line);
                count++;
            }
        }
    }
}
