import java.util.Scanner;

public class Duke {
    private static final String DIVIDER = "____________________________________________________________";
    private static final String[] STORED_TASKS = new String[100];
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
        switch(command) {
        case "list":
            displayStoredTasks();
            break;

        case "bye":
            exitMsg();
            break;

        default:
            storeTask(command);
        }
    }

    public static void displayStoredTasks() {
        System.out.println(DIVIDER);
        for(int i=0; i<storedTasksCount; i++) {
            System.out.printf("%d. %s\n", i+1, STORED_TASKS[i]);
        }
        System.out.println(DIVIDER);
    }

    public static void storeTask(String task) {
        STORED_TASKS[storedTasksCount] = task;
        storedTasksCount++;
        System.out.println(DIVIDER);
        System.out.println("added: " + task);
        System.out.println(DIVIDER);
    }

    public static void exitMsg() {
        System.out.println(DIVIDER);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(DIVIDER);
    }
}
