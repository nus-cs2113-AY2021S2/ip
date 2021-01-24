import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    
    public static String HORIZONTAL_LINE = "____________________________________________________________";

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
                System.out.println(HORIZONTAL_LINE);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(HORIZONTAL_LINE);
                shouldExit = true;
            } else if (input.equals("list")) {
                System.out.println(HORIZONTAL_LINE);
                System.out.println("Here are the tasks in your list:");
                Task[] outputTaskList = Arrays.copyOf(taskList, taskCounter);
                int descriptionCounter = 1;
                for (Task task: outputTaskList) {
                    String description = task.getDescription();
                    System.out.println(descriptionCounter + ".[" + task.getStatusIcon() + "] " + description);
                    ++descriptionCounter;
                }
                System.out.println(HORIZONTAL_LINE);
            } else if (input.contains("done")) {
                System.out.println(HORIZONTAL_LINE);
                int number = Integer.parseInt(input.substring(5)) - 1; // minus 1 to adhere to array indexing
                taskList[number].markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  [" + taskList[number].getStatusIcon() + "] " + taskList[number].getDescription());
                System.out.println(HORIZONTAL_LINE);
            } else {
                System.out.println(HORIZONTAL_LINE);
                System.out.println("added: " + input);
                Task task = new Task(input);
                taskList[taskCounter] = task;
                ++taskCounter;
                System.out.println(HORIZONTAL_LINE);
            }
        }
    }
    public static void main(String[] args) {
        // logo/loading
        System.out.println(HORIZONTAL_LINE);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(HORIZONTAL_LINE);

        // greeting
        String greeting = "Hello! I'm Duke\n" + "What can I do for you?";
        System.out.println(greeting);
        System.out.println(HORIZONTAL_LINE);

        handleCommand(); // handle input received
    }
}
