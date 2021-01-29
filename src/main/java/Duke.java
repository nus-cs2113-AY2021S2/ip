import java.util.Scanner;

public class Duke {
    private static final String DIVIDER = "____________________________________________________________";
    private static final Task[] STORED_TASKS = new Task[100];
    private static int storedTasksCount = 0;

    public static void main(String[] args) {
        String command;
        Scanner scanner = new Scanner(System.in);

        welcomeMsg();
        do {
            command = scanner.nextLine();
            handleCommand(command);
        } while(!command.equals("bye"));

        scanner.close();
    }

    public static void welcomeMsg() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(DIVIDER);
        System.out.println(logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(DIVIDER);
    }

    public static void handleCommand(String command) {
        if(command.equals("list")) {
            displayStoredTasks();
        } else if (command.startsWith("done")) {
            markTaskAsDone(command);
        } else if (command.equals("bye")) {
            exitMsg();
        } else {
            storeTask(command);
        }
    }

    public static void displayStoredTasks() {
        System.out.println(DIVIDER);
        System.out.println("Here are the tasks in your list:");
        for(int i=0; i<storedTasksCount; i++) {
            Task currentTask = STORED_TASKS[i];
            System.out.printf("%d.[%s] %s\n", (i+1), currentTask.getStatusIcon(), currentTask.getDescription());
        }
        System.out.println(DIVIDER);
    }

    public static void markTaskAsDone(String command) {
        int indexOfTaskToMark = Integer.parseInt(command.substring(4).strip())-1;
        Task taskToMark = STORED_TASKS[indexOfTaskToMark];
        taskToMark.markAsDone();
        System.out.println(DIVIDER);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[X] " + taskToMark.getDescription());
        System.out.println(DIVIDER);
    }

    public static void storeTask(String description) {
        STORED_TASKS[storedTasksCount] = new Task(description);
        storedTasksCount++;
        System.out.println(DIVIDER);
        System.out.println("added: " + description);
        System.out.println(DIVIDER);
    }

    public static void exitMsg() {
        System.out.println(DIVIDER);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(DIVIDER);
    }
}
