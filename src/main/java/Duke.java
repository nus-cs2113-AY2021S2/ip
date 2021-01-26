import java.util.Scanner;

public class Duke {
    public static String line = "\t________________________________________";
    public static String[] tasks = new String[100];
    public static int currentTask = 0;

    public static void takeInput() {
        while (true) {
            Scanner scannerObject = new Scanner(System.in);
            String command = scannerObject.nextLine();
            if (command.equals("bye")) {
                bye();
                break;
            } else if (command.equals("list")) {
                list();
            } else {
                add(command);
            }
        }
    }

    public static void bye() {
        System.out.println(line);
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println(line);
        System.out.println();
    }

    public static void list() {
        System.out.println(line);
        for (int i=0; i<currentTask; i++) {
            System.out.println("\t" + Integer.toString(i+1) + ". " + tasks[i]);
        }
        System.out.println(line);
        System.out.println();
    }

    public static void add(String task) {
        tasks[currentTask] = task;
        currentTask++;
        System.out.println(line);
        System.out.println("\tadded: " + task);
        System.out.println(line);
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println(line);
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        System.out.println(line);
        System.out.println();
        takeInput();
    }
}
