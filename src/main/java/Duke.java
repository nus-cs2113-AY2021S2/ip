import java.util.Locale;
import java.util.Scanner;

public class Duke {
    static int CAPACITY = 100;
    static Task[] tasks = new Task[CAPACITY];
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
        showAddTaskMessage(task);
    }

    private static void showAddTaskMessage(Task task) {
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
        showMarkAsDoneMessage(task);
    }

    private static void showMarkAsDoneMessage(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
    }

    private static void parseCommand(String input) {
        String lowercaseInput = input.toLowerCase();
        int firstSpacePosition = input.indexOf(" ");
        String parameter = input.substring(firstSpacePosition + 1);

        if (lowercaseInput.startsWith("list")) {
            showTasks();
        } else if (lowercaseInput.startsWith("done")) {
            markAsDone(Integer.parseInt(parameter));
        } else if (lowercaseInput.startsWith("todo")) {
            String description = parameter;
            Task todoTask = new Todo(description);
            addTask(todoTask);
        } else if (lowercaseInput.startsWith("event")) {
            String[] eventSplit = parameter.split("/at ");
            String description = eventSplit[0];
            String eventTime = eventSplit[1];
            Task eventTask = new Event(description, eventTime);
            addTask(eventTask);
        } else if (lowercaseInput.startsWith("deadline")) {
            String[] deadlineSplit = parameter.split("/by ");
            String description = deadlineSplit[0];
            String deadlineTime = deadlineSplit[1];
            Task deadlineTask = new Deadline(description, deadlineTime);
            addTask(deadlineTask);
        }else {
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
