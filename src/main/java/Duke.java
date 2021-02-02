import java.util.Scanner;

public class Duke {

    /** Displays messages */
    private static final String BORDER = "____________________________________________________________";
    private static final String NEWLINE = System.lineSeparator();
    private static final String DONE_STATUS = "X";

    public static void greet() {
        System.out.println(BORDER);
        help();
        System.out.println("Hello, I'm Panda!");
        System.out.println("What would you like to do today?");
        System.out.println(BORDER + NEWLINE);
    }

    public static void echo() {
        System.out.println(BORDER);
        System.out.println("New task added: ");
        System.out.print("\t");
        tasks[tasksCount-1].printTask();
        System.out.print("\n");
        System.out.println("You have " + tasksCount + " tasks in your list.");
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
        System.out.println("All valid commands:" + "\n");
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
        System.out.println("\t- marks existing task matching the specified index as completed in the list" + "\n");
        System.out.println("<> indicates an input field and | is a field separator");
        System.out.println(BORDER + NEWLINE);
    }


    /** Adds to or modifies the list */
    private static Task[] tasks = new Task[100]; //arr of 100 objects of class Task
    private static int tasksCount = 0;

    public static void addTodo(String description) {
        tasks[tasksCount] = new Task(description, tasksCount+1);
        tasks[tasksCount].setType("T");
        tasksCount++;
        echo();
    }

    public static void addDeadline(String description, String deadline) {
        tasks[tasksCount] = new Deadline(description, tasksCount+1, deadline);
        tasks[tasksCount].setType("D");
        tasksCount++;
        echo();
    }

    public static void addEvent(String description, String time) {
        tasks[tasksCount] = new Event(description, tasksCount+1, time);
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


    /** Prints the full list */
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


    /** Filters user input for valid commands and calls relevant methods */
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
            isValid = hasDeadlineOrEvent(" /by ", tokens);
            if (isValid) {
                int position = tokens[1].indexOf("/by");
                addDeadline(tokens[1].substring(0, position - 1), tokens[1].substring(position + 4));
            }
            break;
        case "event":
            isValid = hasDeadlineOrEvent(" /at ", tokens);
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
        if (tokens.length != 2) {
            return false;
        }
        try {
            Integer.parseInt(tokens[1]);
        } catch (NumberFormatException e) {
            return false;
        }
        int index = Integer.parseInt(tokens[1]);
        return index > 0 && index <= tasksCount;
    }

    public static boolean hasDeadlineOrEvent(String keyword, String[] tokens) {
        if (!tokens[1].contains(keyword)) {
            return false;
        }
        String[] words = tokens[1].split(keyword, 2);
        if (words.length < 2) {
            return false;
        }
        String newKeyword = keyword.trim();
        if (words[0].contains(newKeyword) || words[1].contains(newKeyword)) {
            return false;
        }
        return true;
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
