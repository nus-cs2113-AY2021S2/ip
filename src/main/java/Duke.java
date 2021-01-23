import java.util.Scanner;

public class Duke {

    static String LINE = "────────────────────────────────────────────────────────────";
    static String HELLO_MESSAGE = LINE + "\n"
            + "8K: Hi there! 8K here.\n"
            + "    How can I help you?\n" + LINE;
    static String BYE_MESSAGE = LINE + "\n"
            + "8K: Bye bye! Have a nice day.\n" + LINE;
    static String HELP_MESSAGE = LINE + "\n"
            + "8K: bye - Exit programme.\n"
            + "    done n - Mark nth item as done.\n"
            + "    help - Show list of commands.\n"
            + "    list - Show list of saved values.\n"
            + "    undo n - Mark nth item as not done.\n" + LINE;
    static String MARK_DONE_MESSAGE = "Marked as done:\n" + "[X] ";
    static String MARK_UNDONE_MESSAGE = "Marked as undone:\n" + "[ ] ";
    static String ERROR_MESSAGE = LINE + "\n"
            + "8K: Error. I do not understand.\n" + LINE;
    static String LIST_FULL_MESSAGE = LINE + "\n"
            + "8K: List is full." + "\n" + LINE;


    static int MAX_SIZE = 100;
    static Task[] tasks = new Task[MAX_SIZE];
    static int taskCount = 0;


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        System.out.println(HELLO_MESSAGE);
        do {
            input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("bye")) {
                //End programme
                System.out.println(BYE_MESSAGE);
                break;
            } else if (input.equalsIgnoreCase("list")) {
                //Show saved list
                printList();
            } else if (input.equalsIgnoreCase("help")) {
                //Show commands
                System.out.println(HELP_MESSAGE);
            } else if (input.equalsIgnoreCase("done")|| input.toLowerCase().startsWith("done ")) {
                //Mark item as done
                markAsDone(input);
            } else if (input.equalsIgnoreCase("undo")|| input.toLowerCase().startsWith("undo ")) {
                //Mark item as undone
                markAsUndone(input);
            } else if (taskCount >= MAX_SIZE) {
                //Array full
                System.out.println(LIST_FULL_MESSAGE);
            } else {
                //Add new task
                addTask(input);
            }
        } while (true);
    }


    /**
     * Prints list of task name.
     */
    private static void printList() {
        System.out.println(LINE);
        for (int i = 0; i < taskCount; i++) {
            System.out.print((i + 1) + ".");
            if (tasks[i].getDone()) {
                System.out.print("[X]");
            } else {
                System.out.print("[ ]");
            }
            System.out.println(" " + tasks[i].getName());
        }
        System.out.println(LINE);
    }


    /**
     * Marks specified task as done.
     * If position is invalid, print error message.
     *
     * @param input Input value by user.
     */
    private static void markAsDone(String input) {
        try {
            int position = Integer.parseInt(input.split(" ")[1]) - 1;
            if (position < taskCount) {
                tasks[position].setDone(true);
                System.out.println(LINE);
                System.out.print(MARK_DONE_MESSAGE + tasks[position].getName() + "\n");
                System.out.println(LINE);
            } else {
                //Out of bounds
                System.out.println(ERROR_MESSAGE);
            }
        } catch (Exception e) {
            System.out.println(ERROR_MESSAGE);
        }
    }


    /**
     * Marks specified task as undone.
     * If position is invalid, print error message.
     *
     * @param input Input value by user.
     */
    private static void markAsUndone(String input) {
        try {
            int position = Integer.parseInt(input.split(" ")[1]) - 1;
            if (position < taskCount) {
                tasks[position].setDone(false);
                System.out.println(LINE);
                System.out.print(MARK_UNDONE_MESSAGE + tasks[position].getName() + "\n");
                System.out.println(LINE);
            } else {
                //Out of bounds
                System.out.println(ERROR_MESSAGE);
            }
        } catch (Exception e) {
            System.out.println(ERROR_MESSAGE);
        }
    }


    /**
     * Creates new task with input as name.
     *
     * @param input Input value by user.
     */
    public static void addTask(String input) {
        tasks[taskCount] = new Task(input);
        taskCount++;
        System.out.println(LINE);
        System.out.println("8K: Added \"" + input + "\" to list.");
        System.out.println(LINE);
    }
}
