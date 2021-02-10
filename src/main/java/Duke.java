import java.util.Scanner;

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
    private static Task[] tasks = new Task[100];
    private static int tasksCount = 0;


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
        if (message == null) {
            String invalidInputString = "I'm sorry, I don't quite understand. Can you try again?";
            System.out.print(openString + invalidInputString + closeString);
        }
        else {
            System.out.print(openString + message + closeString);
        }

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
        tasks[tasksCount-1].printTask();
        System.out.print(NEWLINE);
        System.out.print("There ");
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
            System.out.print("\t" + tasks[i].getIndex() + ". ");
            tasks[i].printTask();
            System.out.print(NEWLINE);
        }
        System.out.print(BORDER + NEWLINE + NEWLINE);
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
        System.out.print(BORDER + NEWLINE);
        System.out.print("Nice! This task is now done:" + NEWLINE);
        System.out.print("\t");
        tasks[id-1].printTask();
        System.out.print(NEWLINE + BORDER + NEWLINE + NEWLINE);
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
        if (tokens.length < 2) {
            String invalidMessage = "Squealll! Not enough or invalid fields entered :(";
            printInvalidInputMessage(invalidMessage);
            return;
        }
        switch(tokens[0]) {
        case "done":
            try {
                int index = getTaskIndex(tokens);
                boolean isPossibleIndex = index > 0;
                boolean isValidIndex = index <= tasksCount;
                if (isPossibleIndex && isValidIndex) {
                    markInList(index);
                }
                else {
                    String invalidMessage = "Squeal! There is no task with number "
                            + index + "in the list";
                    printInvalidInputMessage(invalidMessage);
                    return;
                }
            } catch (DukeException e) {
                String invalidMessage = "Squeal! Second field must be a number.";
                printInvalidInputMessage(invalidMessage);
            }
            break;
        case "todo":
            addTodo(tokens[1]);
            break;
        case "deadline":
            try {
                int position = getPositionOfKeyword(" /by ", tokens);
                addDeadline(tokens[1].substring(0, position - 1), tokens[1].substring(position + 4));
            } catch (DukeException e) {
                printInvalidInputMessage(null); //to make more detailed (no/repeated keyword/no description)
            }
            break;
        case "event":
            try {
                int position = getPositionOfKeyword(" /at ", tokens);
                addEvent(tokens[1].substring(0, position - 1), tokens[1].substring(position + 4));
            } catch (DukeException e) {
                printInvalidInputMessage(null);
            }
            break;
        default:
            printInvalidInputMessage(null);
            break;
        }
    }

    public static int getTaskIndex(String[] tokens) throws DukeException {
        try {
            Integer.parseInt(tokens[1]);
            return Integer.parseInt(tokens[1]);
        } catch (NumberFormatException e) {
            throw new DukeException();
        }
    }

    public static int getPositionOfKeyword(String keyword, String[] tokens) throws DukeException {
        if (!tokens[1].contains(keyword)) {
            throw new DukeException();
        }
        String[] words = tokens[1].split(keyword, 2);
        if (words.length < 2) {
            throw new DukeException();
        }
        String actualKeyword = keyword.trim();
        boolean isInvalidTask = words[0].contains(actualKeyword);
        boolean isInvalidDeadlineOrTiming = words[1].contains(actualKeyword);
        if (isInvalidTask || isInvalidDeadlineOrTiming) {
            throw new DukeException();
        }
        return tokens[1].indexOf(actualKeyword);
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
