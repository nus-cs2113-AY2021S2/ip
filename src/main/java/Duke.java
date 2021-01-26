import java.util.Scanner;

public class Duke {

    // Messages from chatbot
    private static final String border = "____________________________________________________________";
    private static final String newline = System.lineSeparator();

    public static void greet() {
        System.out.println(border);
        System.out.println("Hello, I'm Panda!");
        System.out.println("What would you like to do today?");
        System.out.println(border + newline);
    }

    public static void echo(String command) {
        System.out.println(border);
        System.out.println("\t" + "added: " + command);
        System.out.println(border + newline);
    }

    public static void goodbye() {
        System.out.println(border);
        System.out.println("Alright, goodbye!");
        System.out.println(border);
    }

    public static void printInvalidInput() {
        System.out.println(border);
        System.out.println("I'm sorry, I don't quite understand. Can you try again?");
        System.out.println(border + newline);
    }

    // Actions that can be done
    private static Task[] tasks = new Task[100]; //arr of 100 objects of class Task
    private static int tasksCount = 0;
    private static final String defaultStatus = " ";
    private static final String doneStatus = "X";

    public static void addToList(String description) {
        tasks[tasksCount] = new Task(description, tasksCount+1, defaultStatus);
        tasksCount++;
    }

    public static void markInList(int id) {
        tasks[id-1].setStatus(doneStatus);
        System.out.println(border);
        System.out.println("\t" + "Nice! I've marked this task as done:");
        System.out.println("\t" + "[" + doneStatus + "] " + tasks[id-1].getItem());
        System.out.println(border + newline);
    }

    public static void printList() {
        System.out.println(border);
        System.out.println("Here are the tasks in your list:");
        for (int i=0; i<tasksCount; i++) {
            System.out.println("\t" + tasks[i].getIndex() + ". "
                    + "[" + tasks[i].getStatus() + "] "
                    + tasks[i].getItem());
        }
        System.out.println(border + newline);
    }

    // Input validation
    // This method checks for incomplete or invalid done command
    public static boolean isNumeric(String[] tokens) {
        if (tokens.length != 2) {
            printInvalidInput();
            return false;
        }
        try {
            Integer.parseInt(tokens[1]);
        }
        catch (NumberFormatException e) {
            printInvalidInput();
            return false;
        }
        int index = Integer.parseInt(tokens[1]);
        if (index <=0 || index > tasksCount) { //item does not exist in list
            printInvalidInput();
            return false;
        }
        return true;
    }

    //Main method
    public static void main(String[] args) {
        greet();

        Scanner in = new Scanner(System.in);
        String cmd = in.nextLine();
        while (!cmd.equals("bye")) {
            if (cmd.equals("list")) {
                printList();
            }
            else if (cmd.contains("done")) {
                String[] tokens = cmd.split(" ", 0);
                boolean isValid = isNumeric(tokens);
                if (isValid) {
                    markInList(Integer.parseInt(tokens[1]));
                }
            }
            else {
                echo(cmd);
                addToList(cmd);
            }
            cmd = in.nextLine();
        }

        goodbye();
    }
}
