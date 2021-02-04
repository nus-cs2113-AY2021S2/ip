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
                int taskIndex = phrase.charAt(phrase.length()-1) - '0';

                System.out.println("~____________________________________________________________~");
                System.out.println("Nice! I've marked this task as done:");
                Task task = tasks.get(taskIndex - 1);
                task.markAsDone();
                System.out.println("[" + task.getStatusIcon() + "] " + task.getDescription());
                System.out.println("~____________________________________________________________~\n");
            } else if (phrase.startsWith("todo ")) {
                // Add a To-Do
                System.out.println("~____________________________________________________________~");
                Todo todo = new Todo(phrase);
                System.out.println("Got it! I've added this task:");
                tasks.add(todo);
                System.out.println(todo);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                System.out.println("~____________________________________________________________~\n");
            } else if (phrase.startsWith("deadline ")) {
                // Add a Deadline
                System.out.println("~____________________________________________________________~");
                Deadline deadline = new Deadline(phrase, null); // TODO:Add deadline param
                System.out.println("Got it! I've added this task:");
                tasks.add(deadline);
                System.out.println(deadline);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                System.out.println("~____________________________________________________________~\n");
            } else if (phrase.startsWith("event ")) {
                // Add a Deadline
                System.out.println("~____________________________________________________________~");
                Event event = new Event(phrase, null); // TODO:Add event time param
                System.out.println("Got it! I've added this task:");
                tasks.add(event);
                System.out.println(event);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                System.out.println("~____________________________________________________________~\n");
            } else {
                // TODO: Error Handling
            }
        }
        System.out.println("\n~____________________________________________________________~");
        System.out.println("Alright cheers mate!");
        System.out.println("~____________________________________________________________~");
    }
}
