import errors.*;
import tasks.*;

import java.util.*;

public class Duke {

    //Strings
    private static final String LINE = "───────────────────────────────────────────────────────────────────────────";
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
    private static final String MESSAGE_WELCOME = LOGO + "\n" + LINE + "\n"
            + "8K: Hi there! 8K here.\n"
            + "    How can I help you?\n"
            + "    (Enter \"help\" to view all commands.)\n" + LINE;
    private static final String MESSAGE_BYE = LINE + "\n"
            + "8K: Bye bye! Have a nice day.\n" + LINE;
    private static final String MESSAGE_HELP = LINE + "\n"
            + "8K: bye - Exit programme.\n"
            + "    help - Show list of commands.\n"
            + "    list - Show list of saved values.\n"
            + "    todo <name> - Creates new todo task.\n"
            + "    event <name> /at <info> - Creates new event.\n"
            + "    deadline <name> /by <date> - Creates new deadline.\n"
            + "    done n - Mark nth item as done.\n"
            + "    undo n - Mark nth item as not done.\n" + LINE;
    private static final String MESSAGE_MARK_DONE =  LINE + "\nMarked as done:";
    private static final String MESSAGE_MARK_UNDONE = LINE + "\nMarked as undone:";
    private static final String MESSAGE_DELETED = LINE + "\nDeleted:";


    //Errors
    private static final String MESSAGE_UNRECOGNIZED_COMMAND = LINE + "\n"
            + "8K: Sorry. I do not understand.\n" + LINE;
    private static final String MESSAGE_LIST_FULL = LINE + "\n"
            + "8K: List is full." + "\n" + LINE;
    private static final String MESSAGE_EMPTY_LIST = "<< List is empty >>\n" + LINE;
    private static final String MESSAGE_MISSING_AT_KEYWORD = LINE + "\n"
            + "8K: Please follow the format \"event <name> /at <info>\" to create event.\n" + LINE;
    private static final String MESSAGE_MISSING_BY_KEYWORD = LINE + "\n"
            + "8K: Please follow the format \"deadline <name> /by <date>\" to create deadline.\n" + LINE;


    //Constants
    private static final int MAX_SIZE = 100;
    private static final int LENGTH_OF_WORD_EVENT = 5;
    private static final int LENGTH_OF_WORD_DEADLINE = 8;
    private static final int LENGTH_OF_WORD_TODO = 4;


    //Variables & arrays
    private static final ArrayList<Task> tasks = new ArrayList<>();
    private static int taskCount = 0;
    private static boolean endProgramNow = false;


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        System.out.println(MESSAGE_WELCOME);
        do {
            input = scanner.nextLine().trim();
            processInput(input);
        } while (!endProgramNow);
    }


    /**
     * Processes input.
     *
     * @param input Input value by user.
     */
    private static void processInput(String input) {
        if (input.equals("bye")) {
            //Ends program
            endProgram();
        } else if (input.equals("list")) {
            //Shows saved tasks
            printList();
        } else if (input.equals("help")) {
            //Shows all commands
            System.out.println(MESSAGE_HELP);
        } else if (input.startsWith("done ")) {
            //Marks item as done
            setDoneStatus(input, true);
        } else if (input.startsWith("undo ")) {
            //Marks item as undone
            setDoneStatus(input,false);
        } else if (input.startsWith("todo ")) {
            //Adds new ToDoTask
            addToDo(input);
        } else if (input.startsWith("event ")) {
            //Adds new EventTask
            addEvent(input);
        } else if (input.startsWith("deadline ")) {
            //Adds new DeadlineTask
            addDeadline(input);
        } else if (input.startsWith("delete ")) {
            //Delete specified tasks
            deleteTasks(input);
        } else {
            //Prints error (unrecognized command)
            System.out.println(MESSAGE_UNRECOGNIZED_COMMAND);
        }
    }


    /**
     * Ends while-loop to exit program.
     */
    private static void endProgram() {
        System.out.println(MESSAGE_BYE);
        endProgramNow = true;
    }


    /**
     * Prints list of task name.
     */
    private static void printList() {
        System.out.println(LINE);
        if (taskCount == 0) {
            System.out.println(MESSAGE_EMPTY_LIST);
            return;
        }
        for (int i = 0; i < taskCount; i++) {
            System.out.print((i + 1) + ".");
            tasks.get(i).printStatus();
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
            if (position >= taskCount || position < 0) {
                //Out of bounds
                throw new IndexOutOfBoundsException();
            }
            tasks.get(position).setDone(isDone);
            if (isDone) {
                System.out.println(MESSAGE_MARK_DONE);
            } else {
                System.out.println(MESSAGE_MARK_UNDONE);
            }
            tasks.get(position).printStatus();
            System.out.println("\n" + LINE);
        } catch (Exception e) {
            System.out.println(MESSAGE_UNRECOGNIZED_COMMAND);
        }
    }


    /**
     * Creates new ToDoTask.
     *
     * @param input Input value by user.
     */
    public static void addToDo(String input) {
        try {
            if (taskCount >= MAX_SIZE) {
                //Array full
                throw new ListFullException();
            }
            input = input.substring(LENGTH_OF_WORD_TODO + 1);
            tasks.add(new ToDoTask(input));
            taskCount++;
            printAddedContent(input);
        } catch (ListFullException e) {
            System.out.println(MESSAGE_LIST_FULL);
        }
    }


    /**
     * Creates new EventTask.
     * Prints error message if invalid.
     *
     * @param input Input value by user.
     */
    public static void addEvent(String input) {
        try {
            if (!input.contains(" /at ")) {
                throw new MissingKeywordException();
            }
            String[] inputSplit = input.substring(LENGTH_OF_WORD_EVENT + 1).split(" /at ");
            if (taskCount >= MAX_SIZE) {
                //Array full
                throw new ListFullException();
            } else if (inputSplit.length < 2) {
                //Invalid input
                throw new DescriptionSplitException();
            }
            tasks.add(new EventTask(inputSplit[0], inputSplit[1]));
            taskCount++;
            printAddedContent(inputSplit[0]);
        } catch (ListFullException e) {
            System.out.println(MESSAGE_LIST_FULL);
        } catch (MissingKeywordException e) {
            System.out.println(MESSAGE_MISSING_AT_KEYWORD);
        } catch (Exception e) {
            System.out.println(MESSAGE_UNRECOGNIZED_COMMAND);
        }
    }


    /**
     * Creates new DeadlineTask.
     * Prints error message if invalid.
     *
     * @param input Input value by user.
     */
    public static void addDeadline(String input) {
        try {
            if (!input.contains(" /by ")) {
                throw new MissingKeywordException();
            }
            String[] inputSplit = input.substring(LENGTH_OF_WORD_DEADLINE + 1).split(" /by ");
            if (taskCount >= MAX_SIZE) {
                //Array full
                throw new ListFullException();
            } else if (inputSplit.length < 2) {
                //Invalid input
                throw new DescriptionSplitException();
            }
            tasks.add(new DeadlineTask(inputSplit[0], inputSplit[1]));
            taskCount++;
            printAddedContent(inputSplit[0]);
        } catch (ListFullException e) {
            System.out.println(MESSAGE_LIST_FULL);
        } catch (MissingKeywordException e) {
            System.out.println(MESSAGE_MISSING_BY_KEYWORD);
        } catch (Exception e) {
            System.out.println(MESSAGE_UNRECOGNIZED_COMMAND);
        }
    }

    /**
     * Deletes multiple tasks from list.
     *
     * @param input Input value by user.
     */
    public static void deleteTasks(String input) {
        try {
            String[] indices = input.split(" ");
            if (indices.length < 2) {
                throw new DescriptionSplitException();
            }
            TreeSet<Integer> validIndices = new TreeSet<>();
            for (int i = 1; i < indices.length; i++) {
                int index = processIndex(indices[i]);
                if (index != -1) {
                    validIndices.add(index);
                }
            }
            if (validIndices.size() < 1) {
                throw new IndexOutOfBoundsException();
            }
            NavigableSet<Integer> sortedIndices = validIndices.descendingSet();
            System.out.println(MESSAGE_DELETED);
            for (int index : sortedIndices) {
                tasks.get(index).printStatus();
                System.out.println();
                tasks.remove(index);
                taskCount--;
            }
            System.out.println(LINE);
        } catch (Exception e) {
            System.out.println(MESSAGE_UNRECOGNIZED_COMMAND);
        }
    }

    /**
     * Processes index task from list. Returns -1 if invalid.
     *
     * @param stringIndex Index of task to remove.
     */
    public static int processIndex(String stringIndex) {
        try {
            int intIndex = Integer.parseInt(stringIndex) - 1;
            if (intIndex >= taskCount || intIndex < 0) {
                //Out of bounds
                throw new IndexOutOfBoundsException();
            }
            return intIndex;
        } catch (Exception e) {
            return -1;
        }
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
