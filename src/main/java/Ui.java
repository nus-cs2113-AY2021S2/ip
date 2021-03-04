import java.util.Scanner;
import java.util.ArrayList;

/**
 * Text user interface of the application.
 */
public class Ui {
    // Display messages
    private static final String line = "____________________________________________________________\n";
    public final static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public final static String greet = line +
            "Hello! I'm Duke\n" +
            line + "What can I do for you?\n" + line;
    public final static String exit = line +
            "Bye. Hope to see you again!\n" +
            line;

    public String readCommand() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    public void printGreeting() {
        System.out.print(logo);
        System.out.print(greet);
    }

    public void printByeMessage() {
        System.out.println(exit);
    }

    /**
     * Displays a pre-defined explanation for the error if {@code errorMessage} is
     * not null, and a default message if it is null.
     */
    public void printErrorMessage() {
        System.out.print(line);
        System.out.print("I don't quite understand." + "\n" + "Please enter the command again." + "\n");
        System.out.print(line);
    }

    public void printZeroTask() {
        System.out.print(line);
        System.out.print("You have zero task at the moment." + "\n");
        System.out.print(line);
    }

    public void printTask(Task a, Integer size) {
        System.out.print(line + "Got it. I've added this task:" + "\n" + a + "\n" + "Now you have " + size + " tasks in the list." + "\n" + line);
    }

    /**
     * Displays all tasks in a {@code TaskList} in their full form, indexed.
     * @param tasks list of tasks
     * being displayed (filtered or main list)
     */
    public void printList(ArrayList<Task> tasks) {
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println(i + ". " + tasks.get(i - 1).toString());
        }
        System.out.print(line);
    }

    /**
     * Prints the list of tasks that matches the find input.
     *
     * @param foundList the list of tasks that contains the finding keyword
     */
    public void printFoundList(ArrayList<Task> foundList) {
        System.out.print(line);
        System.out.println("Here are the matching tasks in your list: ");
        printList(foundList);
    }



    public void printNoSuchTask() {
        System.out.print(line);
        System.out.print("You have no such task." + "\n");
        System.out.print(line);
    }

    /**
     * Prints a message to confirm marking a task as done successfully by printing the formatted task
     * and the updated task status.
     *
     * @param tasks the task to be marked as done
     */
    public void printTaskAsDone(TaskList tasks, Integer taskIndex) {
        System.out.print(line);
        System.out.print("Nice! I've marked this task as done:" + "\n" + tasks.get(taskIndex) + "\n");
        System.out.print(line);
    }

    public void printDeleteTask(TaskList tasks, Integer taskIndex) {
        System.out.print(line);
        System.out.print("Noted. I've removed this task: " + "\n" + tasks.get(taskIndex) + "\n");

    }

    /**
     * Shows the number of items in {@code TaskList} when a {@code DoneCommand} or
     * {@code DeleteCommand} is executed, so that the user can keep track of
     * their tasks more easily.
     */
    public void printNumberOfTask(TaskList tasks){
        System.out.print("Now you have " + tasks.size() + " tasks in the list." + "\n");
        System.out.print(line);
    }


}
