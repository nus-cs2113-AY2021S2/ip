import java.util.Scanner;

public class Duke {
    public static String line = "\t________________________________________";
    public static Task[] tasks = new Task[100];
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
            } else if (command.startsWith("done")) {
                done(command.split(" ")[1]);
            } else {
                add(command);
            }
        }
    }

    public static void bye() {
        System.out.println(line);
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println(line + "\n");
    }

    public static void list() {
        System.out.println(line);
        System.out.println("\tHere are the tasks in your list:");
        for (int i=0; i<currentTask; i++) {
            System.out.println("\t" + Integer.toString(i+1) + ".[" + tasks[i].getStatusIcon() + "] " + tasks[i].getDescription());
        }
        System.out.println(line + "\n");
    }

    public static void add(String task) {
        Task t = new Task(task);
        tasks[currentTask] = t;
        currentTask++;
        System.out.println(line);
        System.out.println("\tadded: " + task);
        System.out.println(line + "\n");
    }

    public static void done(String item) {
        int i = Integer.parseInt(item)-1;
        if (i >= currentTask || i < 0) {
            System.out.println(line);
            System.out.println("\tItem does not exist!");
            System.out.println(line + "\n");
        } else {
            tasks[i].markAsDone();
            System.out.println(line);
            System.out.println("\tNice! I've marked this task as done:");
            System.out.println("\t  [" + tasks[i].getStatusIcon() + "] " + tasks[i].getDescription());
            System.out.println(line + "\n");
        }
    }

    public static void main(String[] args) {
        System.out.println(line);
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        System.out.println(line + "\n");
        takeInput();
    }
}
