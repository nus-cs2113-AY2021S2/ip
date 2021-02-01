import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        String command;
        String HORIZONTAL_LINE = "____________________________________________________________";
        String LOGO = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        Task[] tasks = new Task[100];
        Task task;
        ToDo toDo;
        String[] splitCommand;
        String[] date;
        int numberOfTasks = 0;

        System.out.println("Hello from\n" + LOGO);
        System.out.println(HORIZONTAL_LINE);
        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");
        System.out.println(HORIZONTAL_LINE);

        while (!(command = userInput.nextLine().trim()).equals("bye")) {
            if (command.equals("list")) {
                System.out.println(" Here are the tasks in your list:");
                for (int i = 0; i < numberOfTasks; i++) {
                    System.out.println(" " + (i + 1) + "." + tasks[i].toString());
                }
                System.out.println(HORIZONTAL_LINE);
            } else {
                String[] tokens = command.split(" ");
                switch (tokens[0]) {
                case "done":
                    int taskNumber = Integer.parseInt(tokens[1]);
                    task= tasks[taskNumber-1];
                    task.markAsDone();
                    System.out.println(HORIZONTAL_LINE + "\n Nice! I've marked this task as done:");
                    System.out.println("   " + task.toString()  + "\n" + HORIZONTAL_LINE);
                    break;
                case "deadline":
                    splitCommand = command.split(" ", 2);
                    date = splitCommand[1].split("/by", 2);
                    Deadline deadline = new Deadline(date[0].trim(), date[1].trim());
                    tasks[numberOfTasks] = deadline;
                    numberOfTasks++;
                    System.out.println(" Alright, I've added this task:\n   " + deadline.toString() + "\n"
                            + " Now you have " + numberOfTasks + " tasks in your list.\n" + HORIZONTAL_LINE);
                    break;
                case "event":
                    splitCommand = command.split(" ", 2);
                    date = splitCommand[1].split("/at", 2);
                    Event event = new Event(date[0].trim(), date[1].trim());
                    tasks[numberOfTasks] = event;
                    numberOfTasks++;
                    System.out.println(" Alright, I've added this task:\n   " + event.toString() + "\n"
                            + " Now you have " + numberOfTasks + " tasks in your list.\n" + HORIZONTAL_LINE);
                    break;
                case "todo":
                    toDo = new ToDo(command.substring(5, command.length()));
                    tasks[numberOfTasks] = toDo;
                    numberOfTasks++;
                    System.out.println(" Alright, I've added this task:\n   " + toDo.toString() + "\n"
                            + " Now you have " + numberOfTasks + " tasks in your list.\n" + HORIZONTAL_LINE);
                    break;
                default:
                    toDo = new ToDo(command);
                    tasks[numberOfTasks] = toDo;
                    numberOfTasks++;
                    System.out.println(" Alright, I've added this task:\n   " + toDo.toString() + "\n"
                            + " Now you have " + numberOfTasks + " tasks in your list.\n" + HORIZONTAL_LINE);
                    break;
                }
            }
        }
        System.out.println(" Bye. Hope to see you again soon!\n" + HORIZONTAL_LINE);
    }
}
