import java.util.Arrays;
import java.util.Scanner;

public class Duke {

    public static Task[] tasks = new Task[100];
    public static int taskCount = 0;

    public static void printStartUpMessage() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public static void printExitMessage() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public static void run(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                return;
            } else if (input.equals("list")) {
                printTaskList();
            } else if (input.contains("done")) {
                markTaskDone(input);
            } else {
                addNewTask(input);
            }
        }
    }

    public static void markTaskDone(String input) {
        String[] parts = input.split(" ");
        int taskIndex = Integer.parseInt(parts[1]) - 1;
        tasks[taskIndex].markDone();
        System.out.println("____________________________________________________________");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks[taskIndex]);
        System.out.println("____________________________________________________________");
    }

    public static void addNewTask(String input) {
        Task taskToAdd = processTaskToAdd(input);
        if (taskToAdd != null) {
            tasks[taskCount] = taskToAdd;
            taskCount++;
            System.out.println("____________________________________________________________");
            System.out.println("Got it. I've added this task:");
            System.out.println(taskToAdd);
            System.out.println("Now you have " + taskCount + " tasks in the list.");
            System.out.println("____________________________________________________________");
        }
    }

    public static Task processTaskToAdd(String input) {
        if (input.contains("todo")) {
            return processToDo(input);
        } else if (input.contains("deadline")) {
            return processDeadline(input);
        } else if (input.contains("event")) {
            return processEvent(input);
        } else {
            return null;
        }

    }

    private static Event processEvent(String input) {
        String substr = input.substring(5);
        String[] parts = substr.split("/at");
        String description = parts[0].trim();
        String at = parts[1].trim();
        return new Event(description, at);
    }

    private static Deadline processDeadline(String input) {
        String substr = input.substring(8);
        String[] parts = substr.split("/by");
        String description = parts[0].trim();
        String by = parts[1].trim();
        return new Deadline(description, by);
    }

    private static ToDo processToDo(String input) {
        String substr = input.substring(4);
        return new ToDo(substr.trim());
    }

    public static void printTaskList() {
        Task[] taskList = Arrays.copyOf(tasks, taskCount);
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.length; i++) {
            System.out.printf("%d. %s%n", i + 1, taskList[i]);
        }
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printStartUpMessage();
        run(scanner);
        printExitMessage();
    }
}
