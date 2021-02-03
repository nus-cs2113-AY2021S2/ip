import java.util.Scanner;

public class Duke {

    private static final int TODO_DONE_LENGTH = 5;
    private static final int DEADLINE_LENGTH = 9;
    private static final int EVENT_LENGTH = 6;
    private static final int BY_AT_LENGTH = 3;
    private static Task[] tasksList = new Task[100];
    private static int taskCounter = 0;

    public static void main(String[] args) {
        showWelcomeMessage();
        inputLoop();
    }

    private static void inputLoop() {
        Scanner in = new Scanner(System.in);
        String line;
        while (true) {
            line = in.nextLine();
            if (line.equals("bye")) {
                showExitMessage();
                break;
            } else if (line.equals("list")) {
                printTasksList();
                continue;
            } else if (line.startsWith("done")) {
                int index = Integer.parseInt(line.substring(TODO_DONE_LENGTH)) - 1;
                tasksList[index].markAsDone();
                printDoneTask(index);
                continue;
            } else if (line.startsWith("todo")) {
                String description = line.substring(TODO_DONE_LENGTH);
                Task newTask = new ToDo(description);
                tasksList[taskCounter] = newTask;
            } else if (line.startsWith("deadline")) {
                int index = line.indexOf("/");
                String description = line.substring(DEADLINE_LENGTH, index - 1);
                String by = line.substring(index + BY_AT_LENGTH).trim();
                Task newTask = new Deadline(description, by);
                tasksList[taskCounter] = newTask;
            } else if (line.startsWith("event")) {
                int index = line.indexOf("/");
                String description = line.substring(EVENT_LENGTH, index - 1);
                String at = line.substring(index + BY_AT_LENGTH).trim();
                Task newTask = new Event(description, at);
                tasksList[taskCounter] = newTask;
            }
            taskCounter++;
            printNewTask();
        }
    }

    private static void printDoneTask(int index) {
        printLine();
        System.out.println("\tNice! I've marked this task as done:");
        System.out.print("\t" + "  ");
        System.out.println(tasksList[index]);
        printLine();
    }

    private static void printTasksList() {
        printLine();
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 1; i <= taskCounter; i++) {
            System.out.print("\t" + i + ". ");
            System.out.println(tasksList[i-1]);
        }
        printLine();
    }

    private static void printNewTask() {
        printLine();
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + tasksList[taskCounter-1]);
        if (taskCounter == 1) {
            System.out.println("\tNow you have " + taskCounter + " task in the list.");
        } else {
            System.out.println("\tNow you have " + taskCounter + " tasks in the list.");
        }
        printLine();
    }

    private static void printLine() {
        System.out.println("\t---------------------------------------------------------------------");
    }

    private static void showWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printLine();
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        printLine();
    }

    private static void showExitMessage() {
        printLine();
        System.out.println("\tBye. Hope to see you again soon!");
        printLine();
    }
}
