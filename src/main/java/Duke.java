import java.util.Scanner;

public class Duke {

    public static final int MAX_TASK = 100;

    static Task[] taskList = new Task[MAX_TASK];

    static int listCount = 0;

    public static final Scanner SCANNER = new Scanner(System.in);

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

    public static void listTasks() {
        if (listCount == 0) {
            System.out.println("There are no tasks in your list");
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < listCount; i++) {
            Task currentTask = taskList[i];
            System.out.println(i + 1 + "." + currentTask.toString());
        }
    }

    public static void updateTask(int taskIndex) {
        Task currentTask = taskList[taskIndex];
        if (currentTask.getDone() == true) {
            System.out.println("This task has already been completed:");
        } else {
            currentTask.setDone();
            System.out.println("Nice! I've marked this task as done:");
        }
        System.out.println("  " + currentTask.toString());
    }

    public static void addTask(String taskType, String description) {
        int dividerPosition;
        switch (taskType) {
        case "todo":
            taskList[listCount++] = new Todo(description);
            break;
        case "deadline":
            dividerPosition = description.indexOf("/by");
            String by = description.substring(dividerPosition + 4);
            taskList[listCount++] = new Deadline(description.substring(0, dividerPosition - 1), by);
            break;
        case "event":
            dividerPosition = description.indexOf("/at");
            String at = description.substring(dividerPosition + 4);
            taskList[listCount++] = new Event(description.substring(0, dividerPosition - 1), at);
            break;
        }
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + taskList[listCount - 1].toString());
        System.out.println("Now you have " + listCount + " tasks in the list.");
    }

    public static void executeCommand(String userInput) {
        String[] words = userInput.split(" ", 2);
        String commandWord = words[0];
        lineBreak();
        switch (commandWord) {
        case "list":
            listTasks();
            break;
        case "done":
            updateTask(Integer.parseInt(words[1]) - 1);
            break;
        case "todo":
            //Fallthrough
        case "deadline":
            //Fallthrough
        case "event":
            try {
                String commandDescription = words[1];
                addTask(commandWord, commandDescription);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("OOPS!!! The description of a " + commandWord + " cannot be empty.");
            }
            break;
        case "bye":
            exitProgram();
            //Fallthrough
        }
        lineBreak();
    }

    public static void exitProgram() {
        System.out.println("Bye. Hope to see you again soon!");
        lineBreak();
        System.exit(0);
    }
}
