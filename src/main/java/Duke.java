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
        } else if (command.startsWith("todo")) {
            storeTodoTask(command);
        } else if (command.startsWith("deadline")) {
            storeDeadlineTask(command);
        } else if (command.startsWith("event")) {
            storeEventTask(command);
        } else {
            storeTask(command);
        }
    }

    public static void storeTodoTask(String command) {
        String description = command.substring(5).strip();
        Todo todoTask = new Todo(description);
        STORED_TASKS[storedTasksCount] = todoTask;
        storedTasksCount++;
        printStoredSuccessMsg(todoTask);
    }

    public static void storeDeadlineTask(String command) {
        String request = command.substring(9).strip();
        int indexOfBy  = request.indexOf("/by");
        String description = request.substring(0, indexOfBy).strip();
        String by = request.substring(indexOfBy+4).strip();
        Deadline deadlineTask = new Deadline(description, by);
        STORED_TASKS[storedTasksCount] = deadlineTask;
        storedTasksCount++;
        printStoredSuccessMsg(deadlineTask);
    }

    public static void storeEventTask(String command) {
        String request = command.substring(6).strip();
        int indexOfAt  = request.indexOf("/at");
        String description = request.substring(0, indexOfAt).strip();
        String at = request.substring(indexOfAt+4).strip();
        Event eventTask = new Event(description, at);
        STORED_TASKS[storedTasksCount] = eventTask;
        storedTasksCount++;
        printStoredSuccessMsg(eventTask);
    }

    public static void printStoredSuccessMsg(Task justStoredTask) {
        System.out.println(DIVIDER);
        System.out.println("Got it. I've added this task:");
        System.out.println(justStoredTask);
        System.out.println("Now you have " + storedTasksCount + " tasks in the list.");
        System.out.println(DIVIDER);
    }

    public static void storeTask(String description) {
        STORED_TASKS[storedTasksCount] = new Task(description);
        storedTasksCount++;
        System.out.println(DIVIDER);
        System.out.println("added: " + description);
        System.out.println("Now you have " + storedTasksCount + " tasks in the list.");
        System.out.println(DIVIDER);
    }

    //Tasks added by storeTask() will not have a category flag.
    public static void displayStoredTasks() {
        System.out.println(DIVIDER);
        System.out.println("Here are the tasks in your list:");
        for(int i=0; i<storedTasksCount; i++) {
            Task currentTask = STORED_TASKS[i];
            System.out.printf("%d.%s\n", (i+1), currentTask);
        }
        System.out.println(DIVIDER);
    }

    public static void markTaskAsDone(String command) {
        int indexOfTaskToMark = Integer.parseInt(command.substring(5).strip())-1;
        Task taskToMark = STORED_TASKS[indexOfTaskToMark];
        taskToMark.markAsDone();
        System.out.println(DIVIDER);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[X] " + taskToMark.getDescription());
        System.out.println(DIVIDER);
    }

    public static void exitMsg() {
        System.out.println(DIVIDER);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(DIVIDER);
    }
}
