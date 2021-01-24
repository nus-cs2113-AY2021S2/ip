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
        String[] taskList = new String[100];
        while (!shouldExit) {
            input = in.nextLine();
            if (input.equals("bye")) {
                System.out.println(HORIZONTAL_LINE);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(HORIZONTAL_LINE);
                shouldExit = true;
            } else if (input.equals("list")) {
                System.out.println(HORIZONTAL_LINE);
                String[] outputTaskList = Arrays.copyOf(taskList, taskCounter);
                int listCounter = 1;
                for (String task: outputTaskList) {
                    System.out.println(listCounter + ". " + task);
                    ++listCounter;
                }
                System.out.println(HORIZONTAL_LINE);
            } else {
                System.out.println(HORIZONTAL_LINE);
                System.out.println("added: " + input);
                taskList[taskCounter] = input;
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
