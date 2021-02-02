import java.util.Scanner;

public class Duke {

    /** Constants used for displaying messages */
    private static final String BORDER = "____________________________________________________________";
    private static final String NEWLINE = System.lineSeparator();
    public static final String LOGO =
                      "                                      ,::::," + "\n"
                    + "                          ,,,,:::::::':::::::" + "\n"
                    + "        ,::::.     ..:::~~           \\::::::" + "\n"
                    + "       ::::::::::~''      ':       __     ':." + "\n"
                    + "       ::::/          __    .o.. : u ::     ':." + "\n"
                    + "         ::,        :: u :.' '. ' ':::'     '::    ,, .::,," + "\n"
                    + "          `::       ':::' /.  : .\\         .::   ::::::::::" + "\n"
                    + "          ::.               '':'            ::'  :::,'''.:::'" + "\n"
                    + "          `::                  :          ,::'  ,:::',,,':::'" + "\n"
                    + "           `::,                 .     ..::::,, ::::::::::::'" + "\n"
                    + "              '::::++,....      :..::::~     ':::::::::::'" + "\n"
                    + "              :::::::::::::::::'~   .         ''::::::'" + "\n"
                    + "              ::::::::::::::::::.      .         `::::'" + "\n"
                    + "              ::::::::::::::::::::,     .          ::" + "\n"
                    + "               ::::::::::::::::::::      :         ::" + "\n"
                    + "                 ::::::::::::::::'      .       .::'" + "\n"
                    + "                :: '::::::::::::'       .     .:':" + "\n"
                    + "                 ::    ~~::::''        . ,,,:::::::::::::" + "\n"
                    + "                  :::,,,,,,,,,,,....::::::::::::::::::::::" + "\n"
                    + "                  :::::::::::::::::: :::::::::::::::::::::" + "\n"
                    + "                 .::::::::::::::::::,  :::::::::::::::::''" + "\n"
                    + "                .:::::::::::::::::::::,  :::::::::::::'" + "\n"
                    + "                `::::::::::::::::::::::'" + "\n";


    /** Constants used to set status of a task */
    private static final String DEFAULT_STATUS = " ";
    private static final String DONE_STATUS = "X";


    /** List of tasks being maintained and number of tasks it has */
    private static Task[] tasks = new Task[100];
    private static int tasksCount = 0;


    /** Methods that display messages */
    public static void greet() {
        System.out.println(BORDER);
        System.out.println(LOGO);
        System.out.println("Hello, I'm Panda!");
        System.out.println("What would you like to do today?");
        System.out.println("Tip: use \"help\" to view all valid commands");
        System.out.println(BORDER + NEWLINE);
    }

    public static void goodbye() {
        System.out.println(BORDER);
        System.out.println("Alright, goodbye!");
        System.out.println(BORDER);
    }

    public static void printInvalidInputMessage() {
        System.out.println(BORDER);
        System.out.println("I'm sorry, I don't quite understand. Can you try again?");
        System.out.println(BORDER + NEWLINE);
    }

    public static void help() {
        System.out.println(BORDER);
        System.out.println("HELP PAGE");
        System.out.println("This is the list of all valid commands:" + "\n");
        System.out.println("\thelp");
        System.out.println("\t - displays all valid commands" + "\n");
        System.out.println("\tbye");
        System.out.println("\t- stops the task manager" + "\n");
        System.out.println("\tlist");
        System.out.println("\t- displays all tasks in the list" + "\n");
        System.out.println("\ttodo     | <task>");
        System.out.println("\t- adds specified task to the list" + "\n");
        System.out.println("\tdeadline | <task>  | /by | <deadline>");
        System.out.println("\t- adds specified task and deadline and to the list" + "\n");
        System.out.println("\tevent    | <task>  | /at | <timing>");
        System.out.println("\t- adds specified task and timing to the list" + "\n");
        System.out.println("\tdone     | <index>");
        System.out.println("\t- marks existing task matching the specified index"
                + "as completed in the list" + "\n");
        System.out.println("<> indicates an input field and | is a field separator.");
        System.out.println(BORDER + NEWLINE);
    }

    /** Methods that print part of or full list */
    public static void echo() {
        System.out.println(BORDER);
        System.out.println("New task added: ");
        System.out.print("\t");
        tasks[tasksCount-1].printTask();
        System.out.print("\n");
//        System.out.println("There are " + tasksCount + " tasks in your list.");
        System.out.print("There ");
        System.out.print(tasksCount > 1 ? "are " : "is ");
        System.out.print(tasksCount);
        System.out.print(tasksCount > 1 ? " tasks" : " task");
        System.out.println(" in your list.");
        System.out.println(BORDER + NEWLINE);
    }

    public static void printList() {
        System.out.println(BORDER);
        System.out.println("Here are the tasks in your list:");
        for (int i=0; i<tasksCount; i++) {
            System.out.print("\t" + tasks[i].getIndex() + ". ");
            tasks[i].printTask();
            System.out.print("\n");
        }
        System.out.println(BORDER + NEWLINE);
    }


    /** Methods that add or modify a task in the list */
    public static void addTodo(String description) {
        tasks[tasksCount] = new Task(description, tasksCount+1);
        tasks[tasksCount].setStatus(DEFAULT_STATUS);
        tasks[tasksCount].setType("T");
        tasksCount++;
        echo();
    }

    public static void addDeadline(String description, String deadline) {
        tasks[tasksCount] = new Deadline(description, tasksCount+1, deadline);
        tasks[tasksCount].setStatus(DEFAULT_STATUS);
        tasks[tasksCount].setType("D");
        tasksCount++;
        echo();
    }

    public static void addEvent(String description, String time) {
        tasks[tasksCount] = new Event(description, tasksCount+1, time);
        tasks[tasksCount].setStatus(DEFAULT_STATUS);
        tasks[tasksCount].setType("E");
        tasksCount++;
        echo();
    }

    public static void markInList(int id) {
        tasks[id-1].setStatus(DONE_STATUS);
        System.out.println(BORDER);
        System.out.println("Nice! This task is now done:");
        System.out.print("\t");
        tasks[id-1].printTask();
        System.out.print("\n");
        System.out.println(BORDER + NEWLINE);
    }


    /** Methods that check if user inputs are valid commands */
    public static void processInput(String userInput) {
        userInput = userInput.trim();
        String[] tokens = userInput.split(" ", 2);
        boolean isValid = true;
        if (tokens.length < 2) {
            printInvalidInputMessage();
            return;
        }
        switch(tokens[0]) {
        case "done":
            isValid = hasIndex(tokens);
            if (isValid) {
                markInList(Integer.parseInt(tokens[1]));
            }
            break;
        case "todo":
            addTodo(tokens[1]);
            break;
        case "deadline":
            isValid = hasDeadlineOrTiming(" /by ", tokens);
            if (isValid) {
                int position = tokens[1].indexOf("/by");
                addDeadline(tokens[1].substring(0, position - 1), tokens[1].substring(position + 4));
            }
            break;
        case "event":
            isValid = hasDeadlineOrTiming(" /at ", tokens);
            if (isValid) {
                int position = tokens[1].indexOf("/at");
                addEvent(tokens[1].substring(0, position - 1), tokens[1].substring(position + 4));
            }
            break;
        default:
            printInvalidInputMessage();
            break;
        }
        if (!isValid) {
            printInvalidInputMessage();
        }
    }

    public static boolean hasIndex(String[] tokens) {
        try {
            Integer.parseInt(tokens[1]);
        } catch (NumberFormatException e) {
            return false;
        }
        int index = Integer.parseInt(tokens[1]);
        boolean isPossibleIndex = index > 0;
        boolean isValidIndex = index <= tasksCount;
        return isPossibleIndex && isValidIndex;
    }

    public static boolean hasDeadlineOrTiming(String keyword, String[] tokens) {
        if (!tokens[1].contains(keyword)) {
            return false;
        }
        String[] words = tokens[1].split(keyword, 2);
        if (words.length < 2) {
            return false;
        }
        String repeatedKeyword = keyword.trim();
        boolean isInvalidTask = words[0].contains(repeatedKeyword);
        boolean isInvalidDeadlineOrTiming = words[1].contains(repeatedKeyword);
        return !isInvalidTask && !isInvalidDeadlineOrTiming;
    }


    /** Main method */
    public static void main(String[] args) {
        greet();
        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine();
        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                printList();
            } else if (userInput.equals("help")) {
                help();
            } else {
                processInput(userInput);
            }
            userInput = in.nextLine();
        }
        goodbye();
    }
}
