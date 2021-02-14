import java.util.Scanner;
public class Duke {

    public static Task[] tasks = new Task[100];
    public static int size = 0;

    public static void main(String[] args) {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        // print welcome message
        System.out.println("Hello from\n" + logo);
        System.out.println("-".repeat(50));
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        printDash();

        Scanner myObj = new Scanner(System.in);
        String command = myObj.nextLine();

        while (!command.equals("bye")) {
            // prints a checklist
            if (command.equals("list")) {
                printList();

                // marks current task as completed
            } else if (command.contains("done")) {
                taskCompleted(command);

                // add tasks to list
            } else {
                addTasks(command);
            }
            command = myObj.nextLine();
        }
        // exit program
        printDash();
        System.out.println("Bye. Hope to see you again soon!");
        printDash();
    }

    public static void addTasks(String description) {
        printDash();
        if (description.contains("todo")) {
            try {
                description = description.substring(5);
                runTodo(description);
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("\tOOPS!!! The description of a todo cannot be empty.");
                printDash();
                return;
            }
        } else if (description.contains("deadline")) {
            try {
                description = description.substring(9);
                runDeadline(description);
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("\tOOPS!!! The description of a deadline cannot be empty.");
                printDash();
                return;
            }
        } else if (description.contains("event")) {
            try {
                description = description.substring(6);
                runEvent(description);
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("\tOOPS!!! The description of a event cannot be empty");
                printDash();
                return;
            }
        } else {
            System.out.println("\tOOPS!!! I'm sorry, but I don't know what that means :-(");
            printDash();
            return;
        }
        System.out.println("\tGot it. I've added this task: ");
        System.out.println(tasks[size++].toString());
        System.out.println("\tNow you have " + size + " tasks in the list");
        printDash();
    }

    // mark and display task that is completed
    public static void taskCompleted(String command) {
        int count = 0;
        // remove done from string
        command = command.replace("done", " ");
        command = command.strip();
        printDash();
        try {
            count = Integer.parseInt(command); // convert string 2 into int 2
        } catch (NumberFormatException e) {
            System.out.println("\tOOPS!!! Please indicate task number");
            printDash();
            return;
        }
        try {
            --count; // array starts from 0
            tasks[count].markAsDone();
            System.out.println("\tNice! I've marked this task as done: ");
            System.out.println(tasks[count].toString()); 
        } catch (NullPointerException e) {
            System.out.println("\tOOPS!!! Please enter valid task number");
            printDash();
            return;
        }
        printDash();
    }

    // print list as a checklist when command=list
    public static void printList() {
        printDash();
        if (size != 0) {
            System.out.println("\tHere are the tasks in your list: ");
            for (int i = 1; i <= size; ++i) {
                System.out.println("\t" + i + "." + tasks[i - 1].toString());
            }
        } else {
            System.out.println("\tYou do not have any pending task.");
        }
        printDash();
    }

    public static void runDeadline(String description) {
        String[] details = description.split(" /by");
        tasks[size] = new Deadline(details[0], details[1]);
    }

    public static void runTodo(String description) {
        tasks[size] = new Todo(description);
    }

    public static void runEvent(String description) {
        String[] details = description.split(" /at");
        tasks[size] = new Event(details[0], details[1]);
    }

    public static void printDash() {
        System.out.println("-".repeat(80));
    }

}
