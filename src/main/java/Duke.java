import java.util.Scanner;

public class Duke {
    static String[] tasks = new String[100];
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

    private static void addTask(String task) {
        tasks[taskCount] = task;
        taskCount++;

        System.out.println("added: " + task);
    }

    private static void showTasks() {
        for (int i = 0; i < taskCount; i++) {
            System.out.println(i + 1 + ". " + tasks[i]);
        }
    }

    private static void parseCommand(String input) {
        if (input.toLowerCase().startsWith("list")) {
            showTasks();
        } else {
            addTask(input);
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
