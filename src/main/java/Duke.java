import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    
    public static void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Handles commands from user
     */
    public static void handleCommand() {
        String input;
        Scanner in = new Scanner(System.in);
        boolean shouldExit = false;
        int taskCounter = 0;
        Task[] taskList = new Task[100];
        while (!shouldExit) {
            input = in.nextLine();
            if (input.equals("bye")) {
                printHorizontalLine();
                System.out.println("Bye. Hope to see you again soon!");
                printHorizontalLine();
                shouldExit = true;
            } else if (input.equals("list")) {
                printHorizontalLine();
                System.out.println("Here are the tasks in your list:");
                Task[] outputTaskList = Arrays.copyOf(taskList, taskCounter);
                int descriptionCounter = 1;
                for (Task task: outputTaskList) {
                    String description = task.getDescription();
                    System.out.println(descriptionCounter + ".[" + task.getStatusIcon() + "] " + description);
                    ++descriptionCounter;
                }
                printHorizontalLine();
            } else if (input.contains("done")) {
                printHorizontalLine();
                int index = Integer.parseInt(input.substring(5)) - 1; // minus 1 to adhere to array indexing
                taskList[index].markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  [" + taskList[index].getStatusIcon() + "] " + taskList[index].getDescription());
                printHorizontalLine();
            } else {
                printHorizontalLine();
                System.out.println("added: " + input);
                Task task = new Task(input);
                taskList[taskCounter] = task;
                ++taskCounter;
                printHorizontalLine();
            }
        }
    }
    public static void main(String[] args) {
        // logo/loading
        printHorizontalLine();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printHorizontalLine();

        // greeting
        String greeting = "Hello! I'm Duke\n" + "What can I do for you?";
        System.out.println(greeting);
        printHorizontalLine();

        handleCommand(); // handle input received
    }
}
