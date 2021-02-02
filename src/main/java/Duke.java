import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        /**String logo = " ____        _        \n"
         + "|  _ \\ _   _| | _____ \n"
         + "| | | | | | | |/ / _ \\\n"
         + "| |_| | |_| |   <  __/\n"
         + "|____/ \\__,_|_|\\_\\___|\n";
         System.out.println("Hello from\n" + logo);
         **/
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?\n");

        String input;
        Task[] tasks = new Task[100];
        int numTasks = 0;
        Scanner in = new Scanner(System.in);
        input = in.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i <= numTasks; i++) {
                    System.out.println(i + "." + tasks[i].toString());
                }

            } else if (input.length() > 4 && input.substring(0, 4).equals("done")) {
                int completedTaskIndex = Integer.parseInt(input.substring(5, input.length()));

                tasks[completedTaskIndex].markAsDone();

                System.out.println("Nice! I've marked this task as done: ");
                System.out.println("[" + tasks[completedTaskIndex].getStatusIcon() + "] " + tasks[completedTaskIndex].getDescription() + "\n");

            } else {
                if (input.length() > 4 && input.substring(0, 4).equals("todo")) {
                    numTasks++;
                    ToDo newTask = new ToDo(input.substring(5, input.length()));
                    System.out.println(newTask);
                    tasks[numTasks] = newTask;
                    System.out.println("Now you have " + numTasks + " tasks in the list.");
                }
                else if (input.length() > 5 && input.substring(0, 5).equals("event")) {
                    int timePosition = input.indexOf('/');
                    String time = input.substring(timePosition+3, input.length());
                    numTasks++;
                    Event newTask = new Event(input.substring(6, timePosition), time);
                    System.out.println(newTask);
                    tasks[numTasks] = newTask;
                    System.out.println("Now you have " + numTasks + " tasks in the list.");
                }
                else if (input.length() > 8 && input.substring(0, 8).equals("deadline")) {
                    int timePosition = input.indexOf('/');
                    String time = input.substring(timePosition+3, input.length());
                    numTasks++;
                    Deadline newTask = new Deadline(input.substring(9, timePosition), time);
                    System.out.println(newTask);
                    tasks[numTasks] = newTask;
                    System.out.println("Now you have " + numTasks + " tasks in the list.");
                }
                else {
                    numTasks++;
                    Task newTask = new Task(input);
                    System.out.println(newTask);
                    tasks[numTasks] = newTask;
                    System.out.println("Now you have " + numTasks + " tasks in the list.");
                }

            }
            System.out.println();
            input = in.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!\n");

    }
}
