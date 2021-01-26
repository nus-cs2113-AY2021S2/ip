import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        String command;
        String horizontalLine = "____________________________________________________________";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        Task[] tasks = new Task[100];
        int numberOfTasks = 0;

        System.out.println("Hello from\n" + logo);
        System.out.println(horizontalLine);
        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");
        System.out.println(horizontalLine);

        while (!(command = userInput.nextLine().trim()).equals("bye")) {
            if (command.equals("list")) {
                System.out.println(" Here are the tasks in your list:");
                for (int i = 0; i < numberOfTasks; i++) {
                    System.out.println(" " + (i + 1) + "." + tasks[i].getStatus()
                            + " " + tasks[i].getDescription());
                }
                System.out.println(horizontalLine);
            } else {
                String[] tokens = command.split(" ");
                if (tokens[0].equals("done")) {
                    int taskNumber = Integer.parseInt(tokens[1]);
                    Task task = tasks[taskNumber-1];
                    task.markAsDone();
                    System.out.println(horizontalLine + "\n Nice! I've marked this task as done:");
                    System.out.println("   [X] " + task.getDescription()  + "\n" + horizontalLine);
                } else {
                    System.out.println(" added: " + command + "\n" + horizontalLine);
                    Task task = new Task(command);
                    tasks[numberOfTasks] = task;
                    numberOfTasks++;
                }
            }
        }
        System.out.println(" Bye. Hope to see you again soon!\n" + horizontalLine);
    }
}
