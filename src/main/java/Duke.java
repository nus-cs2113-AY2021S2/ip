import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {

        Scanner userInput = new Scanner(System.in);

        printStartingMessage();

        Task[] tasks = new Task[100];
        Task task;
        ToDo toDo;
        String[] splitCommand;
        String[] date;
        String command;
        int numberOfTasks = 0;

        while (!(command = userInput.nextLine().trim()).equals("bye")) {

            if (command.equals("list")) {
                printListMessage(tasks, numberOfTasks);
                printHorizontalLine();
                continue;
            }

            String[] tokens = command.split(" ");

            switch (tokens[0]) {
            case "done":
                int taskNumber = Integer.parseInt(tokens[1]);
                task = tasks[taskNumber-1];
                task.markAsDone();
                printHorizontalLine();
                printDoneMessage(task);
                printHorizontalLine();
                break;
            case "deadline":
                splitCommand = command.split(" ", 2);
                date = splitCommand[1].split("/by", 2);
                Deadline deadline = new Deadline(date[0].trim(), date[1].trim());
                tasks[numberOfTasks] = deadline;
                printDeadlineMessage(deadline, ++numberOfTasks);
                printHorizontalLine();
                break;
            case "event":
                splitCommand = command.split(" ", 2);
                date = splitCommand[1].split("/at", 2);
                Event event = new Event(date[0].trim(), date[1].trim());
                tasks[numberOfTasks] = event;
                printEventMessage(event, ++numberOfTasks);
                printHorizontalLine();
                break;
            default:
                toDo = new ToDo(command);
                tasks[numberOfTasks] = toDo;
                printToDoMessage(toDo, ++numberOfTasks);
                printHorizontalLine();
                break;
            }
        }
        printByeMessage();
    }

    public static void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    public static void printStartingMessage() {
        System.out.println("Hello from");
        System.out.println(" ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n");
        printHorizontalLine();
        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");
        printHorizontalLine();
    }

    public static void printListMessage(Task[] tasks, int numberOfTasks) {
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < numberOfTasks; i++) {
            System.out.println(" " + (i + 1) + "." + tasks[i].toString());
        }
    }

    public static void printDoneMessage(Task task) {
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + task.toString());
    }

    public static void printDeadlineMessage(Deadline deadline, int numberOfTasks) {
        System.out.println(" Alright, I've added this task:\n   " + deadline.toString() + "\n"
                + " Now you have " + numberOfTasks + " tasks in your list.");
    }

    public static void printEventMessage(Event event, int numberOfTasks) {
        System.out.println(" Alright, I've added this task:\n   " + event.toString() + "\n"
                + " Now you have " + numberOfTasks + " tasks in your list.");
    }

    public static void printToDoMessage(ToDo toDo, int numberOfTasks) {
        System.out.println(" Alright, I've added this task:\n   " + toDo.toString() + "\n"
                + " Now you have " + numberOfTasks + " tasks in your list.");
    }

    public static void printByeMessage() {
        System.out.println(" Bye. Hope to see you again soon!");
        printHorizontalLine();
    }
}
