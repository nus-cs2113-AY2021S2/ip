import java.util.Locale;
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

    public static void updateTask(int taskIndex) throws DukeException {
        if (taskIndex >= listCount) {
            throw new DukeException();
        }
        Task currentTask = taskList[taskIndex];
        if (currentTask.getDone() == true) {
            System.out.println("This task has already been completed:");
        } else {
            currentTask.setDone();
            System.out.println("Nice! I've marked this task as done:");
        }
        System.out.println("  " + currentTask.toString());
    }

    public static void addTask(DukeCommand taskType, String description) throws DukeException {
        int dividerPosition;
        switch (taskType) {
        case TODO:
            taskList[listCount++] = new Todo(description);
            break;
        case DEADLINE:
            if (!description.contains("/by")) {
                throw new DukeException();
            }
            dividerPosition = description.indexOf("/by");
            String by = description.substring(dividerPosition + 4);
            taskList[listCount++] = new Deadline(description.substring(0, dividerPosition - 1), by);
            break;
        case EVENT:
            if (!description.contains("/at")) {
                throw new DukeException();
            }
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
        DukeCommand commandWord = null;
        try {
            commandWord = DukeCommand.valueOf(words[0].toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException e) {
            lineBreak();
            System.out.println("I'm sorry, but I don't know what that means.");
            lineBreak();
            return;
        }
        lineBreak();
        switch (commandWord) {
        case LIST:
            listTasks();
            break;
        case DONE:
            try {
                updateTask(Integer.parseInt(words[1]) - 1);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Please enter the index of the task that is done.");
            } catch (NumberFormatException e) {
                System.out.println("You must enter a integer after done.");
            } catch (DukeException e) {
                System.out.println("Task " + words[1] + " does not exist.");
                System.out.println("There are " + listCount + " tasks in the list.");
            }
            break;
        case TODO:
            //Fallthrough
        case DEADLINE:
            //Fallthrough
        case EVENT:
            try {
                String commandDescription = words[1];
                addTask(commandWord, commandDescription);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("The description of " + commandWord + " cannot be empty.");
            } catch (DukeException e) {
                System.out.println("The format of " + commandWord + " is incorrect.");
            }
            break;
        case BYE:
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
