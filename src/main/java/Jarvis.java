import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;

public class Jarvis {
    private final Scanner in = new Scanner(System.in);    // create Scanner object
    private final String DIVIDER = "------------------------------------------------";
    ArrayList<Task> tasks = new ArrayList<>();   // create ArrayList to store all the tasks

    public Jarvis() {}

    // initialisation of JARVIS (just for fun)
    public void startJarvis() throws InterruptedException {
        System.out.println(DIVIDER);
        System.out.println("Importing all preferences from home interface.");
        int DELAY = 500;    // 0.5 seconds delay
        TimeUnit.MILLISECONDS.sleep(DELAY);
        System.out.println("Systems are now fully operational.");
        TimeUnit.MILLISECONDS.sleep(DELAY);
        System.out.print("Initialising");
        TimeUnit.MILLISECONDS.sleep(DELAY);
        for (int i = 0; i <= 2; i++) {
            System.out.print(".");
            TimeUnit.MILLISECONDS.sleep(DELAY);
        }
        System.out.println();
        System.out.println(DIVIDER);
        System.out.println("\tHello, sir. J.A.R.V.I.S at your service.");
        System.out.println(DIVIDER);
    }

    // print these if task is successfully added in the ArrayList
    public void addSuccess(Task task) {
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t" + task.toString());
        System.out.println("\tNow you have " + tasks.size() + " tasks in the list.");
        System.out.println(DIVIDER);
    }

    // JARVIS stops running
    public void exitJarvis() {
        System.out.println("\tGoodbye, sir.");
        System.out.println(DIVIDER);
    }

    // adds Todo task to the ArrayList
    public void runTodo(String command) {
        String description = command.replaceFirst("todo ", "");
        Task todo = new Todo(description);
        tasks.add(todo);
        addSuccess(todo);
    }

    // adds Deadline task to the ArrayList
    public void runDeadline(String command) {
        String task = command.replaceFirst("deadline ", "");
        String[] details = task.split("/by", 2);
        String description = details[0];
        String by = details[1];
        Task deadline = new Deadline(description, by);
        tasks.add(deadline);
        addSuccess(deadline);
    }

    // adds Event task to the ArrayList
    public void runEvent(String command) {
        String task = command.replaceFirst("event ", "");
        String[] details = task.split("/at", 2);
        String description = details[0];
        String at = details[1];
        Task event = new Event(description, at);
        tasks.add(event);
        addSuccess(event);
    }

    // prints out the entire list of tasks
    public void runList() {
        if (tasks.size() != 0) {
            System.out.println("\tHere are the tasks in your list, sir:");
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                System.out.println(String.format("\t\t%d. ", i + 1) + task.toString());
            }
        } else {    // if there is no task in the ArrayList
            System.out.println("\tYou do not have any pending task, sir.");
        }
        System.out.println(DIVIDER);
    }

    // marks the task as done if the task exist
    public void runDone(String command) {
        String description = command.replaceFirst("done ", "");
        int taskNumber = Integer.parseInt(description.substring(0, 1));
        if (taskNumber <= tasks.size()) {
            Task task = tasks.get(taskNumber - 1);
            task.setTaskStatus(true);
            System.out.println("\tWell done, sir! I've marked this task as done:");
            System.out.println("\t\t" + task.toString());
        } else {    // if the task is not in the ArrayList
            System.out.println("\tSorry, sir.");
            System.out.println("\tI am unable to retrieve the information from the database.");
            System.out.println("\tWould you like to add the task instead, sir?");
        }
        System.out.println(DIVIDER);
    }

    // Echo back to user
    public void runEcho(String command) {
        System.out.println("\t" + command);
        System.out.println(DIVIDER);
    }

    // JARVIS main task manager
    public void performTask() {
        while (true) {
            String command = in.nextLine();
            if (command.startsWith("bye")) {    // bye
                exitJarvis();
                return;
            } else if (command.startsWith("todo")) {     // todo
                runTodo(command);
            } else if (command.startsWith("deadline")) {    // deadline
                runDeadline(command);
            } else if (command.startsWith("event")) {    // event
                runEvent(command);
            } else if (command.startsWith("list")) {    // list
                runList();
            } else if (command.startsWith("done")) {    // done
                runDone(command);
            } else {    // echo
                runEcho(command);
            }
        }
    }
}
