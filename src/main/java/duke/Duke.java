package duke;

import duke.exceptions.DukeException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    /** Constants used for displaying messages */
    private static final String BORDER = "____________________________________________________________";
    private static final String NEWLINE = System.lineSeparator();
    public static final String LOGO = """
                                                          ,::::,
                                              ,,,,:::::::':::::::
                            ,::::.     ..:::~~           \\::::::
                           ::::::::::~''      ':       __     ':.
                           ::::/          __    .o.. : u ::     ':.
                             ::,        :: u :.' '. ' ':::'     '::    ,, .::,,
                              `::       ':::' /.  : .\\         .::   ::::::::::
                              ::.               '':'            ::'  :::,'''.:::'
                              `::                  :          ,::'  ,:::',,,':::'
                               `::,                 .     ..::::,, ::::::::::::'
                                  '::::;,,...       :..::::~     ':::::::::::'
                                  :::::::::::::::::'~   .         ''::::::'
                                  ::::::::::::::::::.      .         `::::'
                                  ::::::::::::::::::::,     .          ::
                                   ::::::::::::::::::::      :         ::
                                     ::::::::::::::::'      .       .::'
                                    :: '::::::::::::'       .     .:':
                                     ::    ~~::::''        . ,,,:::::::::::::
                                      :::,,,,,,,,,,,....::::::::::::::::::::::
                                      :::::::::::::::::: :::::::::::::::::::::
                                     .::::::::::::::::::,  :::::::::::::::::''
                                    .:::::::::::::::::::::,  :::::::::::::'
                                    `::::::::::::::::::::::' """;


    /** Constants used to set status of a task */
    private static final String DEFAULT_STATUS = " ";
    private static final String DONE_STATUS = "X";


    /** List of tasks being maintained and number of tasks it has */
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static int tasksCount = 0;


    /** Invalid input messages */
    private static final String DEFAULT_INVALID_MESSAGE
            = "I'm sorry, I don't quite understand :( Could you try again?";
    private static final String MISSING_FIELDS_MESSAGE
            = "I think you missed some fields! Try again?";
    private static final String INVALID_INDEX_MESSAGE
            = "Squeal! Second field must be a number.";
    private static final String OUTSIDE_RANGE_INDEX_MESSAGE
            = "Squeal? There is no task in the list with index ";
    private static final String INVALID_TASK_MESSAGE
            = "Squeal... Are you sure that is a task?";


    /** Methods that display messages */
    public static void greet() {
        String greetString = BORDER + NEWLINE
                + LOGO + NEWLINE
                + "Hello, I'm Panda!" + NEWLINE
                + "What would you like to do today?" + NEWLINE
                + "Tip: use \"help\" to view all valid commands" + NEWLINE
                + BORDER + NEWLINE + NEWLINE;
        System.out.print(greetString);
    }

    public static void goodbye() {
        String goodbyeString = BORDER + NEWLINE
                + "Alright, goodbye!" + NEWLINE
                + BORDER + NEWLINE;
        System.out.print(goodbyeString);
    }

    public static void printInvalidInputMessage(String message) {
        String openString = BORDER + NEWLINE;
        String closeString = NEWLINE + BORDER + NEWLINE + NEWLINE;
        if (message != null) {
            System.out.print(openString + message + closeString);
            return;
        }
        System.out.print(openString + DEFAULT_INVALID_MESSAGE + closeString);
    }

    public static void help() {
        String helpString = BORDER + NEWLINE
                + "HELP PAGE" + NEWLINE
                + "This is the list of all valid commands:" + NEWLINE + NEWLINE
                + "\thelp" + NEWLINE
                + "\t - displays all valid commands" + NEWLINE + NEWLINE
                +"\tbye" + NEWLINE
                + "\t- stops the task manager" + NEWLINE + NEWLINE
                + "\tlist" + NEWLINE
                + "\t- displays all tasks in the list" + NEWLINE + NEWLINE
                + "\ttodo     | <task>" + NEWLINE
                + "\t- adds specified task to the list" + NEWLINE + NEWLINE
                + "\tdeadline | <task>  | /by | <deadline>" + NEWLINE
                + "\t- adds specified task and deadline and to the list" + NEWLINE + NEWLINE
                + "\tevent    | <task>  | /at | <timing>" + NEWLINE
                + "\t- adds specified task and timing to the list" + NEWLINE + NEWLINE
                + "\tdone     | <index>" + NEWLINE
                + "\t- marks existing task matching the specified index"
                + "as completed in the list" + NEWLINE  + NEWLINE
                + "<> indicates an input field and | is a field separator." + NEWLINE
                + BORDER + NEWLINE + NEWLINE;
        System.out.print(helpString);
    }


    /** Methods that print part of or full list */
    public static void echo() {
        System.out.print(BORDER + NEWLINE);
        System.out.print("New task added: " + NEWLINE);
        System.out.print("\t");
        tasks.get(tasksCount-1).printTask();
        printNumberOfTasks();
    }

    public static void printNumberOfTasks() {
        System.out.print(NEWLINE + "There ");
        System.out.print(tasksCount > 1 ? "are " : "is ");
        System.out.print(tasksCount);
        System.out.print(tasksCount > 1 ? " tasks" : " task");
        System.out.print(" in your list." + NEWLINE);
        System.out.print(BORDER + NEWLINE + NEWLINE);
    }

    public static void printList() {
        System.out.print(BORDER + NEWLINE);
        System.out.print("Here are the tasks in your list:" + NEWLINE);
        for (int i=0; i<tasksCount; i++) {
            System.out.print("\t" + (i+1) + ". ");
            tasks.get(i).printTask();
            System.out.print(NEWLINE);
        }
        System.out.print(BORDER + NEWLINE + NEWLINE);
    }


    /** Methods that modify the list */
    public static void addTodo(String description) {
        tasks.add(new Task(description));
        tasks.get(tasksCount).setStatus(DEFAULT_STATUS);
        tasks.get(tasksCount).setType("T");
        tasksCount++;
        echo();
    }

    public static void addDeadline(String task, String deadline) {
        tasks.add(new Deadline(task, deadline));
        tasks.get(tasksCount).setStatus(DEFAULT_STATUS);
        tasks.get(tasksCount).setType("D");
        tasksCount++;
        echo();
    }

    public static void addEvent(String task, String time) {
        tasks.add(new Event(task, time));
        tasks.get(tasksCount).setStatus(DEFAULT_STATUS);
        tasks.get(tasksCount).setType("E");
        tasksCount++;
        echo();
    }

    public static void markInList(int index) {
        tasks.get(index-1).setStatus(DONE_STATUS);
        System.out.print(BORDER + NEWLINE);
        System.out.print("Nice! This task is now done:" + NEWLINE);
        System.out.print("\t");
        tasks.get(index-1).printTask();
        printNumberOfTasks();
    }

    public static void deleteTask(int index) {
        System.out.print(BORDER + NEWLINE);
        System.out.print("This task has been removed:" + NEWLINE);
        System.out.print("\t");
        tasks.get(index-1).printTask();
        tasks.remove(index-1);
        tasksCount--;
        printNumberOfTasks();
    }

    
    /** Methods that check if user inputs are valid commands */
    public static void processInput(String userInput) {
        if (userInput.equals("list")) {
            printList();
            return;
        } else if (userInput.equals("help")) {
            help();
            return;
        }
        userInput = userInput.trim();
        String[] tokens = userInput.split(" ", 2);
        switch(tokens[0]) {
        case "todo":
            try {
                String taskDescription = getTodoDescription(tokens[1]);
                addTodo(taskDescription);
            } catch (ArrayIndexOutOfBoundsException e) {
                printInvalidInputMessage(MISSING_FIELDS_MESSAGE);
            } catch (DukeException e) {
                printInvalidInputMessage(INVALID_TASK_MESSAGE);
            }
            break;
        case "deadline":
            try {
                String[] words = getDeadlineOrEventDescription(" /by ", tokens[1]);
                addDeadline(words[0], words[1]);
            } catch (ArrayIndexOutOfBoundsException | DukeException e) {
                printInvalidInputMessage(MISSING_FIELDS_MESSAGE);
            }
            break;
        case "event":
            try {
                String[] words = getDeadlineOrEventDescription(" /at ", tokens[1]);
                addEvent(words[0], words[1]);
            } catch (ArrayIndexOutOfBoundsException | DukeException e) {
                printInvalidInputMessage(MISSING_FIELDS_MESSAGE);
            }
            break;
        case "done":
            try {
                int index = getTaskIndex(tokens[1]);
                markInList(index);
            } catch (ArrayIndexOutOfBoundsException e) {
                printInvalidInputMessage(MISSING_FIELDS_MESSAGE);
            } catch (NumberFormatException e) {
                printInvalidInputMessage(INVALID_INDEX_MESSAGE);
            } catch (DukeException e) {
                printInvalidInputMessage(OUTSIDE_RANGE_INDEX_MESSAGE + tokens[1]);
            }
            break;
        case "delete":
            try {
                int index = getTaskIndex(tokens[1]);
                deleteTask(index);
            } catch (ArrayIndexOutOfBoundsException e) {
                printInvalidInputMessage(MISSING_FIELDS_MESSAGE);
            } catch (NumberFormatException e) {
                printInvalidInputMessage(INVALID_INDEX_MESSAGE);
            } catch (DukeException e) {
                printInvalidInputMessage(OUTSIDE_RANGE_INDEX_MESSAGE + tokens[1]);
            }
            break;
        default:
            printInvalidInputMessage(null);
            break;
        }
    }

    public static int getTaskIndex(String description) throws DukeException {
        int index = Integer.parseInt(description);
        boolean isPossibleIndex = index > 0;
        boolean isValidIndex = index <= tasksCount;
        if (!isPossibleIndex || !isValidIndex) {
            throw new DukeException();
        }
        return index;
    }

    public static String getTodoDescription(String description) throws DukeException {
        try {
            /* task description contains only numbers */
            Long.parseLong(description);
            throw new DukeException();
        } catch (NumberFormatException e) {
            return description;
        }
    }

    public static String[] getDeadlineOrEventDescription(String keyword, String sentence) throws DukeException {
        String[] words = sentence.split(keyword, 2);
        if (words.length < 2) {
            throw new DukeException();
        }
        return words;
    }


    /** Main method */
    public static void main(String[] args) {
        greet();
        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine();
        while (!userInput.equals("bye")) {
            processInput(userInput);
            userInput = in.nextLine();
        }
        goodbye();
    }
}
