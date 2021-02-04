import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Duke {

    public static final int MAX_TASK = 100;

    public static final Scanner SCANNER = new Scanner(System.in);

    static Task[] taskList = new Task[MAX_TASK];

    static int listCount = 0;

    public static void main(String[] args) {
        greet();
        while (true) {
            String userInput = SCANNER.nextLine();
            executeCommand(userInput);
        }
    }

    private static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        lineBreak();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        lineBreak();
    }

    public static void lineBreak() {
        final String HORIZONTAL_LINE = "____________________________________________________________";
        System.out.println(HORIZONTAL_LINE);
    }

    public static void echoCommand(String command) {
        lineBreak();
        switch (command) {
        case "list":
            System.out.println("Here are the tasks in your list:");
            break;
        case "done":
            System.out.println("Nice! I've marked this task as done:");
            break;
        case "todo":
            //Fallthrough
        case "deadline":
            //Fallthrough
        case "event":
            System.out.println("Got it. I've added this task:");
            break;
        case "bye":
            System.out.println("Bye. Hope to see you again soon!");
            break;
        }
    }

    public static void executeCommand(String userInput) {
        String[] words = userInput.split(" ");
        String command = words[0];
        int dividerPosition;
        echoCommand(command);
        switch (command) {
        case "list":
            for (int i = 0; i < listCount; i++) {
                Task currentTask = taskList[i];
                System.out.println(i + 1 + "." + currentTask.toString());
            }
            break;
        case "done":
            Task currentTask = taskList[Integer.parseInt(words[1]) - 1];
            currentTask.markAsDone();
            System.out.println("  " + currentTask.toString());
            break;
        case "todo":
            taskList[listCount++] = new Todo(userInput.substring(5));
            System.out.println("  " + taskList[listCount - 1].toString());
            System.out.println("Now you have " + listCount + " tasks in the list.");
            break;
        case "deadline":
            dividerPosition = userInput.indexOf("/by");
            String by = userInput.substring(dividerPosition + 4);
            taskList[listCount++] = new Deadline(userInput.substring(9, dividerPosition - 1), by);
            System.out.println("  " + taskList[listCount - 1].toString());
            System.out.println("Now you have " + listCount + " tasks in the list.");
            break;
        case "event":
            dividerPosition = userInput.indexOf("/at");
            String at = userInput.substring(dividerPosition + 4);
            taskList[listCount++] = new Event(userInput.substring(6, dividerPosition - 1), at);
            System.out.println("  " + taskList[listCount - 1].toString());
            System.out.println("Now you have " + listCount + " tasks in the list.");
            break;
        case "bye":
            exitProgram();
            //Fallthrough
        }
        lineBreak();
    }

    public static void exitProgram() {
        lineBreak();
        System.exit(0);
    }
}
