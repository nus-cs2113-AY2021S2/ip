import java.util.Scanner;

public class Duke {

    public static Task[] tasks = new Task[100];
    public static int index = 0;

    public static void greet() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("---------------------------------------------------------");
        System.out.println(logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("---------------------------------------------------------");
    }

    public static void bidGoodbye() {
        System.out.println("---------------------------------------------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("---------------------------------------------------------");
    }

    /**
     * Lists the tasks in order
     */
    public static void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= index; i++){
            System.out.print(i + ".");
            System.out.println(tasks[i-1].toString());
        }
    }

    /**
     * Confirms task has been added
     *
     * @param task Task object that was added
     */
    public static void printAddTaskMessage(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + index + " tasks in the list");
    }

    public static void request() {
        String line;
        Scanner in = new Scanner(System.in);

        line = in.nextLine();
        while (!line.equals("bye")) {
            System.out.println("---------------------------------------------------------");
            if (line.equals("list")) {
                printList();
            } else if (line.startsWith("todo")) {
                int descriptionStart = 5;
                String description = line.substring(descriptionStart);
                tasks[index] = new Todo(description);
                index++;
                printAddTaskMessage(tasks[index - 1]);
            } else if (line.startsWith("deadline")) {
                int descriptionStart = 9;
                int descriptionEnd = line.indexOf("/by") - 1;
                String description = line.substring(descriptionStart, descriptionEnd);
                int byStart = line.indexOf("/by") + 4;
                String by = line.substring(byStart);
                tasks[index] = new Deadline(description, by);
                index++;
                printAddTaskMessage(tasks[index - 1]);
            } else if (line.startsWith("event")) {
                int descriptionStart = 6;
                int descriptionEnd = line.indexOf("/at") - 1;
                String description = line.substring(descriptionStart, descriptionEnd);
                int atStart = line.indexOf("/at") + 4;
                String at = line.substring(atStart);
                tasks[index] = new Event(description, at);
                index++;
                printAddTaskMessage(tasks[index - 1]);
            } else if (line.startsWith("done")) {
                int itemNum = Integer.parseInt(line.substring(5));
                tasks[itemNum - 1].setAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tasks[itemNum-1].toString());
            } else {
                System.out.println("Invalid input");
            }
            System.out.println("---------------------------------------------------------");
            line = in.nextLine();
        }
        bidGoodbye();
    }

    public static void main(String[] args) {
        greet();
        request();
    }
}
