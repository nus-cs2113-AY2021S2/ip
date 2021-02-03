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

        System.out.println("\t------------------------------------------");
        System.out.println("\tHello there! I'm Duke.");
        System.out.println("\tWhat can I help you with?");
        System.out.println("\t__________________________________________\n");

        ArrayList<Task> tasks = new ArrayList<Task>();

        boolean runLoop = true;
        while (runLoop) {
            String userInput = sc.nextLine().trim();

            if (userInput.equals("list")) {
                // displays the list of tasks
                System.out.println("\t------------------------------------------");
                System.out.println("\tHere are the tasks in your list: ");
                int i = 1;
                for (Task task : tasks) {
                    System.out.println("\t" + i++ + ".[" + task.getStatusIcon() + "] " + task.getDescription());
                }
                System.out.println("\t__________________________________________\n");
            } else if (userInput.startsWith("done") && userInput.split(" ").length == 2) {
                // sets a task as done
                System.out.println("\t------------------------------------------");
                System.out.println("\tGreat job! I've marked this task as done: ");
                int taskIndex = Integer.parseInt(userInput.split(" ")[1]);
                Task task = tasks.get(taskIndex - 1);
                task.markAsDone();
                System.out.println("\t[" + task.getStatusIcon() + "] " + task.getDescription());
                System.out.println("\t__________________________________________\n");
            } else if (userInput.equals("bye")) {
                // exits the program
                System.out.println("\t------------------------------------------");
                System.out.println("\tSee you soon! Goodbye! ^.^");
                System.out.println("\t__________________________________________");
                runLoop = false;
            } else {
                // adds a task to the list of tasks
                System.out.println("\t------------------------------------------");
                System.out.println("\tadded: " + userInput);
                Task task = new Task(userInput);
                tasks.add(task);
                System.out.println("\t__________________________________________\n");
            }
        }
    }
}
