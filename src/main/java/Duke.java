import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        printLogo();

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<Task>();

        printDivider();
        System.out.println("What's up! I'm Duke");
        System.out.println("What can I do for you?");
        printEndDivider();

        boolean isRunning = true;
        while (isRunning) {
            String phrase = sc.nextLine();

            if (phrase.equals("bye")) {
                // Exits program
                isRunning = false;
            } else if (phrase.equals("list")) {
                // List all tasks
                printDivider();
                System.out.println("Here are the tasks in your list:");
                int i = 0;
                for (Task task : tasks) {
                    System.out.println(++i + ". " + task.toString());
                };
                printEndDivider();
            } else if (phrase.startsWith("done ")) {
                // Set a task as done
                int taskIndex = phrase.charAt(phrase.length()-1) - '0';

                if (taskIndex > tasks.size()) {
                    printDivider();
                    System.out.println("Oops task " + taskIndex + " does not exist! Try again mate!");
                    printEndDivider();
                } else {
                    printDivider();
                    System.out.println("Nice! I've marked this task as done:");
                    Task task = tasks.get(taskIndex - 1);
                    task.markAsDone();
                    System.out.println("[" + task.getStatusIcon() + "] " + task.getDescription());
                    printEndDivider();
                }
            } else if (phrase.startsWith("todo ")) {
                // Add a To-Do
                printDivider();
                phrase = phrase.substring(5);
                Todo todo = new Todo(phrase);
                System.out.println("Got it! I've added this task:");
                tasks.add(todo);
                System.out.println(todo);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                printEndDivider();
            } else if (phrase.startsWith("deadline ")) {
                // Add a Deadline
                printDivider();
                int dividerPosition = phrase.indexOf("/");
                String by = phrase.substring(dividerPosition+4);
                phrase = phrase.substring(9, dividerPosition);
                Deadline deadline = new Deadline(phrase, by);
                System.out.println("Got it! I've added this task:");
                tasks.add(deadline);
                System.out.println(deadline);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                printEndDivider();
            } else if (phrase.startsWith("event ")) {
                // Add an Event
                printDivider();
                int dividerPosition = phrase.indexOf("/");
                String eventTime = phrase.substring(dividerPosition+4);
                phrase = phrase.substring(6, dividerPosition);
                Event event = new Event(phrase, eventTime);
                System.out.println("Got it! I've added this task:");
                tasks.add(event);
                System.out.println(event);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                printEndDivider();
            } else {
                // Invalid Task
                printDivider();
                System.out.println("Sorry mate I do not understand your request. Please specify task :)");
                printEndDivider();
            }
        }
        // Exits Program
        printEndDivider();
        System.out.println("Alright cheers mate!");
        printDivider();
    }

    private static void printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    private static void printDivider() {
        System.out.println("~____________________________________________________________~");
    }

    private static void printEndDivider() {
        System.out.println("~____________________________________________________________~\n");
    }

}
