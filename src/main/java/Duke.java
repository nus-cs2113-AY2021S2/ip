import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        printLogo();

        Scanner sc = new Scanner(System.in);
        String phrase;
        ArrayList<Task> tasks = new ArrayList<Task>();

        printDivider();
        System.out.println("What's up! I'm Duke");
        System.out.println("What can I do for you?");
        printEndDivider();

        do {
            phrase = sc.nextLine();
            printDivider();
            inputCommand(phrase, tasks);
            printEndDivider();
        } while (!phrase.equals("bye"));
    }

    public static void inputCommand(String phrase, ArrayList<Task> tasks) {
        String[] subStrings = phrase.split(" ");
        int dividerPosition = phrase.indexOf("/");

        switch (subStrings[0]) {
        case ("bye"):
            // Exits program
            System.out.println("Alright cheers mate!");
            break;
        case ("list"):
            // List all tasks
            System.out.println("Here are the tasks in your list:");
            int i = 0;
            for (Task task : tasks) {
                System.out.println(++i + ". " + task.toString());
            }
            break;
        case ("done"):
            // Set a task as done
            try {
                int taskIndex = phrase.charAt(phrase.length() - 1) - '0';

                Task task = tasks.get(taskIndex - 1);
                task.markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[" + task.getStatusIcon() + "] " + task.getDescription());
            } catch (NumberFormatException e) {
                System.out.println("\uD83D\uDE2D Please input a valid number!");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("\uD83D\uDE2D Sorry task index is out of range!");
            }
            break;
        case ("todo"):
            // Add a To-Do
            try {
                phrase = phrase.substring(5);
                Todo todo = new Todo(phrase);
                System.out.println("Got it! I've added this task:");
                tasks.add(todo);
                System.out.println(todo);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("\uD83D\uDE2D Description of To-Do cannot be empty!");
            }
            break;
        case ("deadline"):
            try {
                // Add a Deadline
                String by = phrase.substring(dividerPosition + 4);
                phrase = phrase.substring(9, dividerPosition);
                Deadline deadline = new Deadline(phrase, by);
                System.out.println("Got it! I've added this task:");
                tasks.add(deadline);
                System.out.println(deadline);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("\uD83D\uDE2D Please enter deadline in the format: 'deadline <name> /by <day> <time>'");
            }
            break;
        case ("event"):
            // Add an Event
            try {
                String eventTime = phrase.substring(dividerPosition + 4);
                phrase = phrase.substring(6, dividerPosition);
                Event event = new Event(phrase, eventTime);
                System.out.println("Got it! I've added this task:");
                tasks.add(event);
                System.out.println(event);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("\uD83D\uDE2D Please enter event in the format: 'event <name> /at <day> <time>'");
            }
            break;
        default:
            // Invalid Task
            System.out.println("\uD83D\uDE2D Sorry mate I do not understand your request. Please specify task");
            break;
        }
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
