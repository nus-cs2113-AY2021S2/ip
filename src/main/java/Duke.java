import java.util.Locale;
import java.util.Scanner;

public class Duke {
    static Task[] tasks = new Task[100];
    static int taskCount = 0;

    private static void showDivider() {
        System.out.println("-----------------------------------------------------");
    }

    private static void showGreeting() {
        showDivider();
        System.out.println("Hello! I'm Duke :)");
        System.out.println("What can I do for you?");
        showDivider();
    }

    private static void showExit() {
        System.out.println("Bye. Hope to see you again soon!");
        showDivider();
    }

    private static void addTask(Task task) {
        tasks[taskCount] = task;
        taskCount++;
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    private static void showTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println(i + 1 + ". " + tasks[i]);
        }
    }

    private static void markAsDone(int index) {
        Task task = tasks[index - 1];
        task.markAsDone(true);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
    }

    private static void parseCommand(String input) {
        if (input.toLowerCase().startsWith("list")) {
            showTasks();
        } else if (input.toLowerCase().startsWith("done")) {
            markAsDone(Integer.parseInt(input.split(" ")[1]));
        } else if (input.toLowerCase().startsWith("todo")) {
            String description = input.substring(5);
            Task todoTask = new Todo(description);
            addTask(todoTask);
        } else if (input.toLowerCase().startsWith("event")) {
            String[] eventSplit = input.split("/at ");
            String description = eventSplit[0].substring(6);
            String eventTime = eventSplit[1];
            Task eventTask = new Event(description, eventTime);
            addTask(eventTask);
        } else {
            System.out.println("Invalid input. Please try again with valid command.");
        }
    }

    public static void main(String[] args) {
        showGreeting();

        while (true) {
            Scanner line = new Scanner(System.in);
            String input = line.nextLine();
            showDivider();
            if (input.toLowerCase().equals("bye")) {
                break;
            }
            parseCommand(input);
            showDivider();
        }

        showExit();
    }
}
