import java.util.Scanner;

public class Duke {
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

    private static String[] tasks = new String[100];
    private static int tasksCount = 0;

    public static void addToList(String newItem) {
        Task obj = new Task(newItem, tasksCount+1);
        tasks[tasksCount] = obj.getItem();
        tasksCount++;
    }

    public static void printList() {
        System.out.println(border);
        for (int i=0; i<tasksCount; i++) {
            System.out.println("\t" + tasks[i]);
        }
        System.out.println(border + newline);
    }

    public static void main(String[] args) {
        greet();

        Scanner in = new Scanner(System.in);
        String cmd = in.nextLine();
        while (!cmd.equals("bye")) {
            if (cmd.equals("list")) {
                printList();
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
