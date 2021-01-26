import java.util.Scanner;

public class Duke {

    // Messages from chatbot
    private static final String BORDER = "____________________________________________________________";
    private static final String NEWLINE = System.lineSeparator();

    public static void greet() {
        System.out.println(BORDER);
        System.out.println("Hello, I'm Panda!");
        System.out.println("What would you like to do today?");
        System.out.println(BORDER + NEWLINE);
    }

    public static void echo(String description) {
        System.out.println(BORDER);
        System.out.println("\t" + "added: " + description);
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

    // Actions that can be done
    private static Task[] tasks = new Task[100]; //arr of 100 objects of class Task
    private static int tasksCount = 0;
    private static final String DEFAULT_STATUS = " ";
    private static final String DONE_STATUS = "X";

    public static void addToList(String description) {
        tasks[tasksCount] = new Task(description, tasksCount+1, DEFAULT_STATUS);
        tasksCount++;
    }

    public static void markInList(int id) {
        tasks[id-1].setStatus(DONE_STATUS);
        System.out.println(BORDER);
        System.out.println("\t" + "Nice! I've marked this task as done:");
        System.out.println("\t" + "[" + DONE_STATUS + "] " + tasks[id-1].getItem());
        System.out.println(BORDER + NEWLINE);
    }

    public static void printList() {
        System.out.println(BORDER);
        System.out.println("Here are the tasks in your list:");
        for (int i=0; i<tasksCount; i++) {
            System.out.println("\t" + tasks[i].getIndex() + ". "
                    + "[" + tasks[i].getStatus() + "] "
                    + tasks[i].getItem());
        }
        System.out.println(BORDER + NEWLINE);
    }

    // Input validation
    // This method checks for incomplete or invalid done command, or non-existent item in the list
    public static boolean isNumeric(String[] tokens) {
        if (tokens.length != 2) {
            printInvalidInputMessage();
            return false;
        }
        try {
            Integer.parseInt(tokens[1]);
        } catch (NumberFormatException e) {
            printInvalidInputMessage();
            return false;
        }
        int index = Integer.parseInt(tokens[1]);
        if (index <=0 || index > tasksCount) {
            printInvalidInputMessage();
            return false;
        }
        return true;
    }

    //Main method
    public static void main(String[] args) {
        greet();

        Scanner in = new Scanner(System.in);
        String description = in.nextLine();
        while (!description.equals("bye")) {
            if (description.equals("list")) {
                printList();
            } else if (description.contains("done")) {
                String[] tokens = description.split(" ", 0);
                boolean isValid = isNumeric(tokens);
                if (isValid) {
                    markInList(Integer.parseInt(tokens[1]));
                }
            } else {
                echo(description);
                addToList(description);
            }
            description = in.nextLine();
        }

        goodbye();
    }
}
