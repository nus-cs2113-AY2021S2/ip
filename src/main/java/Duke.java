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

        ArrayList<Task> tasks = new ArrayList<>();
        boolean runLoop = true;

        while (runLoop) {
            String userInput = sc.nextLine().trim();

            if (userInput.equalsIgnoreCase("bye")) {
                exitProgram();
                runLoop = false;
            } else if (userInput.equalsIgnoreCase("list")) {
                listTasks(tasks);
            } else if (userInput.split(" ")[0].equals("done")) {
                markTask(userInput, tasks);
            } else {
                addTask(userInput, tasks);
            }
        }
    }

    public static void exitProgram() {
        // exits the program
        System.out.println("\t------------------------------------------");
        System.out.println("\tSee you soon! Goodbye! ^.^");
        System.out.println("\t__________________________________________\n");
    }

    public static void listTasks(ArrayList<Task> tasks) {
        System.out.println("\t------------------------------------------");
        if (tasks.size() == 0) {
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
    }

    public static void markTask(String userInput, ArrayList<Task> tasks) {
        // check if task exists
        System.out.println("\t------------------------------------------");
        try {
            int taskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
            if (taskIndex > tasks.size()-1) {
                System.out.println("\tTask " + taskIndex + 1 + " does not exist! Please try again.");
            }
            else {
                // sets a task as done
                Task task = tasks.get(taskIndex);
                task.markAsDone();
                System.out.println("\tGreat job! I've marked this task as done: ");
                System.out.println("\t" + task.toString());
            }
        }
        catch (NumberFormatException e) {
            System.out.println("\tOOPS!! To mark task, you have to enter an integer following the work done " +
                    "such as in this example: 'done 3'.");
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("\tOOPS!! Invalid task input!");
        }
        System.out.println("\t__________________________________________\n");
    }

    public static void addTask(String userInput, ArrayList<Task> tasks) throws StringIndexOutOfBoundsException {
        // adds a task to the list
        System.out.println("\t------------------------------------------");
        switch(userInput.split(" ")[0]) {
        case "todo":
            // adds to-do tasks
            try {
                Task todo = new Todo(userInput.substring(5));
                tasks.add(todo);
                System.out.println("\tGot it. I've added this task: ");
                System.out.println("\t" + todo.toString());
                System.out.println("\tNow you have " + tasks.size() + " tasks in the list.");
            }
            catch (StringIndexOutOfBoundsException e) {
                System.out.println("\tOOPS!!! The description of a todo cannot be empty!");
            }
            break;
        case "deadline":
            // adds tasks with deadline
            try {
                Task deadline = new Deadline(userInput.substring(9, userInput.indexOf("/by")),
                        userInput.substring(userInput.indexOf("/by")+4));
                tasks.add(deadline);
                System.out.println("\tGot it. I've added this task: ");
                System.out.println("\t" + deadline.toString());
                System.out.println("\tNow you have " + tasks.size() + " tasks in the list.");
            }
            catch (StringIndexOutOfBoundsException e) {
                System.out.println("\tOOPS!!! The description of a deadline is required in the following format " +
                                ": 'deadline CS2113 Project /by Thursday 9pm'.");
            }
            break;
        case "event":
            // adds event tasks
            try {
                Task event = new Event(userInput.substring(6, userInput.indexOf("/at")),
                        userInput.substring(userInput.indexOf("/at")+4));
                tasks.add(event);
                System.out.println("\tGot it. I've added this task: ");
                System.out.println("\t" + event.toString());
                System.out.println("\tNow you have " + tasks.size() + " tasks in the list.");
            }
            catch (StringIndexOutOfBoundsException e) {
            System.out.println("\tOOPS!!! The description of an event is required in the following format " +
                    ": 'event CS2113 Meeting /at Monday 1pm'.");
            }
            break;
        default:
            // invalid command
            System.out.println("\tOOPS! I'm sorry, but I don't know what that means! :-(");
        }S
        System.out.println("\t__________________________________________\n");
    }
}
