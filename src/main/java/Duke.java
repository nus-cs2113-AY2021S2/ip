import java.util.Scanner;

public class Duke {

    static String LOGO = "                o                     o\n" +
            "                oooo               oooo\n" +
            "                ooooooo         ooooooo\n" +
            "                   ooooooo   ooooooo\n" +
            "                      ooooooooooo\n" +
            "                         ooooo\n" +
            "  ooooooooooooooooooooooooooooooooooooooooooooooooooo\n" +
            "  ooooooooooooooooooooooooooooooooooooooooooooooooooo\n" +
            "\n" +
            "     ooo    oooooooooo           oooooooooo\n" +
            "   ooo   oooooooooooooooo     oooooooooooooooo\n" +
            "        ooooo        ooooo   ooooo        ooooo\n" +
            "       oooo            ooooooooo            oooo\n" +
            "        ooooo        ooooo   ooooo        ooooo\n" +
            "         oooooooooooooooo     oooooooooooooooo \n" +
            "            oooooooooo           oooooooooo\n";

    static String LINE = "────────────────────────────────────────────────────────────";
    static String HELLO_MESSAGE = LOGO + "\n" + LINE + "\n"
            + "8K: Hi there! 8K here.\n"
            + "    How can I help you?\n"
            + "    (Enter \"help\" to view all commands.)\n" + LINE;
    static String BYE_MESSAGE = LINE + "\n"
            + "8K: Bye bye! Have a nice day.\n" + LINE;
    static String HELP_MESSAGE = LINE + "\n"
            + "8K: bye - Exit programme.\n"
            + "    help - Show list of commands.\n"
            + "    list - Show list of saved values.\n"
            + "    todo <name> - Creates new todo task.\n"
            + "    event <name> /at <info> - Creates new event.\n"
            + "    deadline <name> /by <date> - Creates task with deadline.\n"
            + "    done n - Mark nth item as done.\n"
            + "    undo n - Mark nth item as not done.\n" + LINE;
    static String MARK_DONE_MESSAGE = "Marked as done:\n";
    static String MARK_UNDONE_MESSAGE = "Marked as undone:\n";
    static String ERROR_MESSAGE = LINE + "\n"
            + "8K: Sorry. I do not understand.\n" + LINE;
    static String LIST_FULL_MESSAGE = LINE + "\n"
            + "8K: List is full." + "\n" + LINE;
    static String EMPTY_LIST_MESSAGE = "<< List is empty >>\n" + LINE;


    static int MAX_SIZE = 100;
    static Task[] tasks = new Task[MAX_SIZE];
    static int taskCount = 0;
    static boolean endProgram = false;

    static int LENGTH_OF_EVENT_WORD = 5;
    static int LENGTH_OF_DEADLINE_WORD = 8;
    static int LENGTH_OF_TODO_WORD = 4;


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        System.out.println(HELLO_MESSAGE);
        do {
            input = scanner.nextLine().trim();
            processInput(input);
        } while (!endProgram);
    }


    /**
     * Processes input.
     *
     * @param input Input value by user.
     */
    private static void processInput(String input) {
        if (input.equals("bye")) {
            //End programme
            System.out.println(BYE_MESSAGE);
            endProgram = true;
        } else if (input.equals("list")) {
            //Show saved list
            printList();
        } else if (input.equals("help")) {
            //Show commands
            System.out.println(HELP_MESSAGE);
        } else if (input.startsWith("done ")) {
            //Mark item as done
            markAsDone(input);
        } else if (input.startsWith("undo ")) {
            //Mark item as undone
            markAsUndone(input);
        } else if (taskCount >= MAX_SIZE) {
            //Array full
            System.out.println(LIST_FULL_MESSAGE);
        } else if (input.startsWith("todo ")) {
            //Add new todotask
            addToDo(input);
        } else if (input.startsWith("event ") && input.contains(" /at ")) {
            //Add new event
            addEvent(input);
        } else if (input.startsWith("deadline ") && input.contains(" /by ")) {
            //Add new deadline
            addDeadline(input);
        } else {
            //Unrecognized
            System.out.println(ERROR_MESSAGE);
        }
    }


    /**
     * Prints list of task name.
     */
    private static void printList() {
        System.out.println(LINE);
        if (taskCount == 0) {
            System.out.println(EMPTY_LIST_MESSAGE);
            return;
        }
        for (int i = 0; i < taskCount; i++) {
            System.out.print((i + 1) + ".");
            tasks[i].printStatus();
            System.out.println();
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
                System.out.print(MARK_DONE_MESSAGE);
                tasks[position].printStatus();
                System.out.println();
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
                System.out.print(MARK_UNDONE_MESSAGE);
                tasks[position].printStatus();
                System.out.println();
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
     * Creates new todotask.
     *
     * @param input Input value by user.
     */
    public static void addToDo(String input) {
        input = input.substring(LENGTH_OF_TODO_WORD + 1);
        tasks[taskCount] = new ToDoTask(input);
        taskCount++;
        printAddedContent(input);
    }


    /**
     * Creates new eventtask.
     *
     * @param input Input value by user.
     */
    public static void addEvent(String input) {
        String[] inputSplit = input.substring(LENGTH_OF_EVENT_WORD + 1).split(" /at ");
        if (inputSplit.length < 2) {
            System.out.println(ERROR_MESSAGE);
            return;
        }
        tasks[taskCount] = new EventTask(inputSplit[0], inputSplit[1]);
        taskCount++;
        printAddedContent(inputSplit[0]);
    }


    /**
     * Creates new deadlinetask.
     *
     * @param input Input value by user.
     */
    public static void addDeadline(String input) {
        String[] inputSplit = input.substring(LENGTH_OF_DEADLINE_WORD + 1).split(" /by ");
        if (inputSplit.length < 2) {
            System.out.println(ERROR_MESSAGE);
            return;
        }
        tasks[taskCount] = new DeadlineTask(inputSplit[0], inputSplit[1]);
        taskCount++;
        printAddedContent(inputSplit[0]);
    }


    /**
     * Prints added message.
     *
     * @param input Input value by user.
     */
    public static void printAddedContent(String input) {
        System.out.println(LINE);
        System.out.println("8K: Added \"" + input + "\" to list.");
        System.out.println(LINE);
    }

}
