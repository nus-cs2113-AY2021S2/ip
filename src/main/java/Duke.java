import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<Task>();

        System.out.println("~____________________________________________________________~");
        System.out.println("What's up! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("~____________________________________________________________~\n");

        boolean isRunning = true;
        while (isRunning) {
            String phrase = sc.nextLine();

            if (phrase.equals("bye")) {
                // Exits program
                isRunning = false;
            } else if (phrase.equals("list")) {
                // List all tasks
                System.out.println("~____________________________________________________________~");
                System.out.println("Here are the tasks in your list:");
                int i = 0;
                for (Task task : tasks) {
                    System.out.println(++i + ". [" + task.getStatusIcon() + "] " + task.getDescription());
                };
                System.out.println("~____________________________________________________________~\n");
            } else if (phrase.startsWith("done ")) {
                // Set a task as done
                int index = phrase.charAt(phrase.length()-1) - '0';

                System.out.println("~____________________________________________________________~");
                System.out.println("Nice! I've marked this task as done:");
                Task task = tasks.get(index - 1);
                task.markAsDone();
                System.out.println("[" + task.getStatusIcon() + "] " + task.getDescription());
                System.out.println("~____________________________________________________________~\n");
            } else {
                // Add a task
                System.out.println("~____________________________________________________________~");
                Task task = new Task(phrase);
                System.out.println("added: " + phrase);
                tasks.add(task);
                System.out.println("~____________________________________________________________~\n");
            }
        }
        System.out.println("\n~____________________________________________________________~");
        System.out.println("Alright cheers mate!");
        System.out.println("~____________________________________________________________~");
    }
}
