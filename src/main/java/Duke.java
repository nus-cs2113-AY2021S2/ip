import java.util.Scanner;

public class Duke {

    //Strings
    private static final String LINE = "────────────────────────────────────────────────────────────";
    private static final String LOGO = "                o                     o\n" +
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
            "         oooooooooooooooo     oooooooooooooooo\n" +
            "            oooooooooo           oooooooooo\n";
    private static final String HELLO_MESSAGE = LOGO + "\n" + LINE + "\n"
            + "8K: Hi there! 8K here.\n"
            + "    How can I help you?\n"
            + "    (Enter \"help\" to view all commands.)\n" + LINE;
    private static final String BYE_MESSAGE = LINE + "\n"
            + "8K: Bye bye! Have a nice day.\n" + LINE;
    private static final String HELP_MESSAGE = LINE + "\n"
            + "8K: bye - Exit programme.\n"
            + "    help - Show list of commands.\n"
            + "    list - Show list of saved values.\n"
            + "    todo <name> - Creates new todo task.\n"
            + "    event <name> /at <info> - Creates new event.\n"
            + "    deadline <name> /by <date> - Creates task with deadline.\n"
            + "    done n - Mark nth item as done.\n"
            + "    undo n - Mark nth item as not done.\n" + LINE;
    private static final String MARK_DONE_MESSAGE =  LINE + "\nMarked as done:\n";
    private static final String MARK_UNDONE_MESSAGE = LINE + "\nMarked as undone:\n";
    private static final String ERROR_MESSAGE = LINE + "\n"
            + "8K: Sorry. I do not understand.\n" + LINE;
    private static final String LIST_FULL_MESSAGE = LINE + "\n"
            + "8K: List is full." + "\n" + LINE;
    private static final String EMPTY_LIST_MESSAGE = "<< List is empty >>\n" + LINE;


    //Constants
    private static final int MAX_SIZE = 100;
    private static final int LENGTH_OF_EVENT_WORD = 5;
    private static final int LENGTH_OF_DEADLINE_WORD = 8;
    private static final int LENGTH_OF_TODO_WORD = 4;


    //Variables & arrays
    private static final Task[] tasks = new Task[MAX_SIZE];
    private static int taskCount = 0;
    private static boolean endProgram = false;


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
            //Ends program
            setEndProgram();
        } else if (input.equals("list")) {
            //Shows saved tasks
            printList();
        } else if (input.equals("help")) {
            //Shows all commands
            System.out.println(HELP_MESSAGE);
        } else if (input.startsWith("done ")) {
            //Marks item as done
            setDoneStatus(input, true);
        } else if (input.startsWith("undo ")) {
            //Marks item as undone
            setDoneStatus(input,false);
        } else if (input.startsWith("todo ")) {
            //Adds new ToDoTask
            addToDo(input);
        } else if (input.startsWith("event ") && input.contains(" /at ")) {
            //Adds new EventTask
            addEvent(input);
        } else if (input.startsWith("deadline ") && input.contains(" /by ")) {
            //Adds new DeadlineTask
            addDeadline(input);
        } else {
            //Prints error (unrecognized command)
            System.out.println(ERROR_MESSAGE);
        }
    }


    /**
     * Ends while-loop to exit program.
     */
    private static void setEndProgram() {
        System.out.println(BYE_MESSAGE);
        endProgram = true;
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
    private static void setDoneStatus(String input, Boolean isDone) {
        try {
            int position = Integer.parseInt(input.split(" ")[1]) - 1;
            if (position < taskCount) {
                tasks[position].setDone(isDone);
                if (isDone) {
                    System.out.print(MARK_DONE_MESSAGE);
                } else {
                    System.out.print(MARK_UNDONE_MESSAGE);
                }
                tasks[position].printStatus();
                System.out.println("\n" + LINE);
            } else {
                //Out of bounds
                System.out.println(ERROR_MESSAGE);
            }
        } catch (Exception e) {
            System.out.println(ERROR_MESSAGE);
        }
    }


    /**
     * Creates new ToDoTask.
     *
     * @param input Input value by user.
     */
    public static void addToDo(String input) {
        if (taskCount >= MAX_SIZE) {
            //Array full
            System.out.println(LIST_FULL_MESSAGE);
            return;
        }
        input = input.substring(LENGTH_OF_TODO_WORD + 1);
        tasks[taskCount] = new ToDoTask(input);
        taskCount++;
        printAddedContent(input);
    }


    /**
     * Creates new EventTask.
     * Prints error message if invalid.
     *
     * @param input Input value by user.
     */
    public static void addEvent(String input) {
        String[] inputSplit = input.substring(LENGTH_OF_EVENT_WORD + 1).split(" /at ");
        if (taskCount >= MAX_SIZE) {
            //Array full
            System.out.println(LIST_FULL_MESSAGE);
            return;
        }
        if (inputSplit.length < 2) {
            //Invalid input
            System.out.println(ERROR_MESSAGE);
            return;
        }
        tasks[taskCount] = new EventTask(inputSplit[0], inputSplit[1]);
        taskCount++;
        printAddedContent(inputSplit[0]);
    }


    /**
     * Creates new DeadlineTask.
     * Prints error message if invalid.
     *
     * @param input Input value by user.
     */
    public static void addDeadline(String input) {
        String[] inputSplit = input.substring(LENGTH_OF_DEADLINE_WORD + 1).split(" /by ");
        if (taskCount >= MAX_SIZE) {
            //Array full
            System.out.println(LIST_FULL_MESSAGE);
            return;
        }
        if (inputSplit.length < 2) {
            //Invalid input
            System.out.println(ERROR_MESSAGE);
            return;
        }
        tasks[taskCount] = new DeadlineTask(inputSplit[0], inputSplit[1]);
        taskCount++;
        printAddedContent(inputSplit[0]);
    }


    /**
     * Prints successfully added message.
     *
     * @param input Input value by user.
     */
    public static void printAddedContent(String input) {
        System.out.println(LINE);
        System.out.println("8K: Added \"" + input + "\" to list.");
        System.out.println(LINE);
    }

}
