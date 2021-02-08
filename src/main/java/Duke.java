import java.util.Scanner;
public class Duke {
    public static Task[] tasks = new Task[100];
    public static int size = 0;

    // mark and display task that is completed
    public static void taskCompleted(String command) {
        // remove done from string
        command = command.replace("done", " ");
        command = command.strip();
        // convert string 2 into int 2
        int count = Integer.parseInt(command);
        --count; // array starts from 0
        tasks[count].markAsDone();
        printDash();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(tasks[count].toString());
        printDash();
    }

    public static void main(String[] args) {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String dash = "-";

        // print welcome message
        System.out.println("Hello from\n" + logo);
        System.out.println(dash.repeat(50));
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        System.out.println(dash.repeat(50));

        Scanner myObj = new Scanner(System.in);
        String command = myObj.nextLine();

        while (!command.equals("bye")) {
            // prints a checklist
            if (command.equals("list")) {
                printList();
            }
            // marks current task as completed
            else if (command.contains("done")) {
                taskCompleted(command);
            }
            // adds tasks into list
            else {
                addTasks(command);
            }
            command = myObj.nextLine();
        }
        // exit program when input=bye
        System.out.println(dash.repeat(50));
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(dash.repeat(50));

    }
    public static void addTasks(String description){
        System.out.println("\tGot it. I've added this task: ");
        if(description.contains("todo")){
            description = description.replace("todo", "");
            description = description.strip();
            runTodo(description);
        }
        else if(description.contains("deadline")){
            description = description.replace("deadline", "");
            description = description.strip();
            runDeadline(description);
        }
        else if(description.contains("event")){
            description = description.replace("event", "");
            description = description.strip();
            runEvent(description);
        }
        System.out.println(tasks[size++].toString());
        System.out.println("\tNow you have " + size + " tasks in the list");

    }
    // print list as a checklist when command=list
    public static void printList() {
        printDash();
        if(size != 0) {
            System.out.println("Here are the tasks in your list: ");
            for (int i = 1; i <= size; ++i) {
                System.out.println(i + ". " + tasks[i-1].toString());
            }
        }
        else {
            System.out.println("You do not have any pending task.");
        }
        printDash();
    }
    public static void runDeadline(String description){
        String[] details = description.split(" /by");
        tasks[size] = new Deadline(details[0], details[1]);
    }
    public static void runTodo(String description) {
        tasks[size] = new Todo(description);
    }
    public static void runEvent(String description){
        String[] details = description.split(" /at");
        tasks[size] = new Event(details[0], details[1]);
    }
    public static void printDash() {
        System.out.println("-".repeat(50));
    }

}
