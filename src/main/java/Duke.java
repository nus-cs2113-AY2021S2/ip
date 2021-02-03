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
        int taskCount = 0;
        boolean runLoop = true;

        while (runLoop) {

            String userInput = sc.nextLine().trim();

            if (userInput.equals("bye")) {
                // exits the program
                System.out.println("\t------------------------------------------");
                System.out.println("\tSee you soon! Goodbye! ^.^");
                System.out.println("\t__________________________________________\n");
                runLoop = false;
            } else if (userInput.equals("list")) {
                System.out.println("\t------------------------------------------");
                if (taskCount == 0) {
                    // if list is empty
                    System.out.println("\tYour list is empty!");
                }
                else {
                    // displays the list of tasks
                    System.out.println("\tHere are the tasks in your list: ");
                    int i = 1;
                    for (Task task : tasks) {
                        System.out.println("\t" + i++ + "." + task.toString());
                    }
                }
                System.out.println("\t__________________________________________\n");
            } else if (userInput.split(" ")[0].equals("done")) {
                // check if task exists
                System.out.println("\t------------------------------------------");
                // check if
                int taskIndex = Integer.parseInt(userInput.split(" ")[1]);
                if (taskIndex > taskCount) {
                    System.out.println("\tTask " + taskIndex + " does not exist! Please try again.");
                }
                else {
                    // sets a task as done
                    System.out.println("\tGreat job! I've marked this task as done: ");
                    Task task = tasks.get(taskIndex - 1);
                    task.markAsDone();
                    System.out.println("\t" + task.toString());

                }
                System.out.println("\t__________________________________________\n");
            } else {
                // adds a task to the list
                System.out.println("\t------------------------------------------");
                switch(userInput.split(" ")[0]) {
                case "todo":
                    // adds todo tasks
                    Task todo = new Todo(userInput.substring(5));
                    tasks.add(todo);
                    System.out.println("\tGot it. I've added this task: ");
                    System.out.println("\t" + todo.toString());
                    taskCount++;
                    break;
                case "deadline":
                    // adds tasks with deadline
                    Task deadline = new Deadline(userInput.substring(9, userInput.indexOf("/by")),
                            userInput.substring(userInput.indexOf("/by")+4));
                    tasks.add(deadline);
                    System.out.println("\tGot it. I've added this task: ");
                    System.out.println("\t" + deadline.toString());
                    taskCount++;
                    break;
                case "event":
                    // adds event tasks
                    Task event = new Event(userInput.substring(6, userInput.indexOf("/at")),
                            userInput.substring(userInput.indexOf("/at")+4));
                    tasks.add(event);
                    System.out.println("\tGot it. I've added this task: ");
                    System.out.println("\t" + event.toString());
                    taskCount++;
                    break;
                default:
                    // invalid task type
                    System.out.println("\tInvalid task entered. Please specify task type!");
                }
                System.out.println("\tNow you have " + taskCount + " tasks in the list.");
                System.out.println("\t__________________________________________\n");
            }
        }
    }
}
