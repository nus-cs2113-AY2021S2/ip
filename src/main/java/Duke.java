import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    static ArrayList<Task> tasks = new ArrayList<> ();
    static int tasksCount = 0 ;

    public static void printTaskList(ArrayList<Task> tasks) {
        System.out.println("Here are the tasks in your list: ");
        for (int i=0; i<tasks.size(); i++){
            System.out.println("    " + (i+1) + ". " + tasks.get(i));
        }
    }

    public static void addTask(String userInput) {
        String description = "";
        if (userInput.substring(0,8).equalsIgnoreCase("deadline")){
            String by = "";
            userInput = userInput.substring(9).trim();
            if (userInput.contains("/")){
                int idx = userInput.indexOf('/');
                description =userInput.substring(0, idx);
                by = userInput.substring(idx+3).trim();
            }
            else {
                description =userInput;
            }

            Deadline d = new Deadline(description, by);
            tasks.add(d);
        }
        else if (userInput.substring(0,5).equalsIgnoreCase("event")){
            String at = "";
            userInput = userInput.substring(6).trim();
            if (userInput.contains("/")){
                int idx = userInput.indexOf('/');
                description =userInput.substring(0, idx);
                at = userInput.substring(idx+3).trim();
            }
            else {
                description =userInput;
            }

            Event e = new Event(description, at);
            tasks.add(e);
        }
        else if (userInput.substring(0,4).equalsIgnoreCase("todo")){
            description = userInput.substring(5);
            Todo t = new Todo(description);
            tasks.add(t);
        }
        System.out.println("    Got it. I've added this task: \n      " + tasks.get(tasksCount));
        tasksCount++;
        System.out.println("    Now you have " + tasksCount + " tasks in the list.");

    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("------------------------------------------");

        boolean toContinueAddingTask = true;
        Scanner sc = new Scanner(System.in);
        String task = sc.nextLine();

        while (toContinueAddingTask) {
            if (task.equalsIgnoreCase("bye")) {
                toContinueAddingTask = false;
                break;
            }
            else if (task.equalsIgnoreCase("list")) {
                printTaskList(tasks);
                System.out.println("------------------------------------------");
            }
            else if (task.contains("done")){
                int idx = Integer.parseInt(String.valueOf(task.charAt(5)));
                tasks.get(idx-1).markAsDone();
                System.out.println("------------------------------------------");
                System.out.println("    Nice! I've marked this task as done: ");
                System.out.println("    " + tasks.get(idx-1));
            }
            else {
                try {
                    addTask(task);
                }
                catch (Exception e) {
                    System.out.println("    Invalid input! To add task: Please start with 'todo'/ 'deadline' / 'event'. ");
                    System.out.println("                   To view the list of tasks: Enter 'list'. ");
                    System.out.println("                   To end the application: Enter 'bye'. ");
                }
            }
            task = sc.nextLine().trim();
        }
        System.out.println("------------------------------------------");
        System.out.println("    Bye. Hope to see you again soon!");
    }
}
