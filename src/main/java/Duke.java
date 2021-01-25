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
                    System.out.println(i + ".[" + tasks[i].getStatusIcon() + "] " + tasks[i].getDescription());
                }
                System.out.println();

            } else if (input.length() > 4 && input.substring(0, 4).equals("done")) {
                int completedTaskIndex = Integer.parseInt(input.substring(5, input.length()));

                tasks[completedTaskIndex].markAsDone();

                System.out.println("Nice! I've marked this task as done: ");
                System.out.println("[" + tasks[completedTaskIndex].getStatusIcon() + "] " + tasks[completedTaskIndex].getDescription() + "\n");

            } else {
                numTasks++;
                System.out.println("added: " + input + "\n");
                Task newTask = new Task(input);
                tasks[numTasks] = newTask;

            }

            input = in.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!\n");

    }
}
