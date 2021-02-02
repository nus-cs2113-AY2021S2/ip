import java.util.Scanner;

public class Duke {

    public static final int DONE_START = 5;
    public static final int TODO_START = 5;
    public static final int EVENT_START = 6;
    public static final int DEADLINE_START = 9;

    public static Task[] tasks = new Task[100];
    public static int numTasks = 0;

    public static Scanner in = new Scanner(System.in);
    public static String input;

    public static void main(String[] args) {
        welcomeMessage();

        input = getString(in);

        while (!isBye()) {
            if (isList()) {
                executeCommand(input, Command.LIST);
            } else if (isDone()) {
                executeCommand(input, Command.DONE);
            } else if (isTodo()) {
                executeCommand(input, Command.TODO);
            } else if (isEvent()) {
                executeCommand(input, Command.EVENT);
            } else if (isDeadline()) {
                executeCommand(input, Command.DEADLINE);
            } else {
                executeCommand(input, Command.TASK);
            }

            System.out.println();
            input = getString(in);
        }


        exitMessage();

    }

    public static void executeCommand(String input, Command c) {
        switch (c) {
        case LIST:
            listBeginMessage();
            enumerateTasks();
            break;
        case DONE:
            markTaskAsDone(input);
            break;
        case TODO:
            incrementTasks();
            addNewTodo(input);
            confirmNewTask();
            break;
        case EVENT:
            incrementTasks();
            addNewEvent(input);
            confirmNewTask();
            break;
        case DEADLINE:
            incrementTasks();
            addNewDeadline(input);
            confirmNewTask();
            break;
        case TASK:
            incrementTasks();
            addNewTask(input);
            confirmNewTask();
            break;

        }
    }

    private static void markTaskAsDone(String input) {
        int completedTaskIndex = getCompletedTaskIndex(input);
        tasks[completedTaskIndex].markAsDone();
        completedTaskMessage(completedTaskIndex);
    }

    private static void addNewTask(String input) {
        tasks[numTasks] = new Task(input);
    }

    private static void addNewDeadline(String input) {
        tasks[numTasks] = new Deadline(input.substring(DEADLINE_START, getTimePosition(input)), getTime(input));
    }

    private static void addNewEvent(String input) {
        tasks[numTasks] = new Event(input.substring(EVENT_START, getTimePosition(input)), getTime(input));
    }

    private static void addNewTodo(String input) {
        tasks[numTasks] = new ToDo(input.substring(TODO_START, input.length()));
    }

    private static int getCompletedTaskIndex(String input) {
        int completedTaskIndex = Integer.parseInt(input.substring(DONE_START, input.length()));
        return completedTaskIndex;
    }

    private static void enumerateTasks() {
        for (int i = 1; i <= numTasks; i++) {
            System.out.println(i + "." + tasks[i].toString());
        }
    }

    private static void listBeginMessage() {
        System.out.println("Here are the tasks in your list:");
    }

    private static void completedTaskMessage(int completedTaskIndex) {
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("[" + tasks[completedTaskIndex].getStatusIcon() + "] " + tasks[completedTaskIndex].getDescription() + "\n");
    }

    private static void incrementTasks() {
        numTasks++;
    }

    private static int getTimePosition(String input) {
        return input.indexOf('/');
    }

    private static String getTime(String input) {
        int timePosition = getTimePosition(input);
        return input.substring(timePosition + 3, input.length());
    }

    private static void confirmNewTask() {
        System.out.println(tasks[numTasks]);
        System.out.println("Now you have " + numTasks + " tasks in the list.");
    }

    private static boolean isBye() {
        return input.equals("bye");
    }

    private static boolean isDeadline() {
        return input.length() > 8 && input.substring(0, 8).equals("deadline");
    }

    private static boolean isEvent() {
        return input.length() > 5 && input.substring(0, 5).equals("event");
    }

    private static boolean isTodo() {
        return input.length() > 4 && input.substring(0, 4).equals("todo");
    }

    private static boolean isList() {
        return input.equals("list");
    }

    private static boolean isDone() {
        return input.length() > 4 && input.substring(0, 4).equals("done");
    }

    private static String getString(Scanner in) {
        String input;
        input = in.nextLine();
        return input;
    }

    private static void welcomeMessage() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?\n");
    }

    private static void exitMessage() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }

}
