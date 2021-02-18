import java.util.Arrays;

public class TaskManager {
    static Task[] tasks = new Task[100];
    static int count = 0;

    public TaskManager() {
    }

    public static int getCount() {
        return count;
    }

    public static Task[] getTasks() {
        return tasks;
    }

    public static void setTasks(Task[] tasks) {
        TaskManager.tasks = tasks;
    }

    public static void setCount(int count) {
        TaskManager.count = count;
    }

    public static void printList() {
        MessagePrinter.printBorder();
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < count; i++) {
            System.out.print("     " + (i + 1));
            System.out.println(". " + tasks[i]);
        }
        MessagePrinter.printBorder();
    }

    public static void displayTaskDone(int index) throws DukeException {
        if (tasks[index - 1].isDone()) {
            throw new DukeException();
        } else {
            tasks[index - 1].markAsDone();
            MessagePrinter.printBorder();
            System.out.println("      Nice! I've marked this task as done: ");
            System.out.print("       ");
            System.out.println(tasks[index - 1]);
            MessagePrinter.printBorder();
        }
    }

    public static void addNewTask(String[] partOfCommand, String fullCommand) {

        MessagePrinter.printBorder();
        switch (partOfCommand[0]) {
        case ("todo"):
            try {
                Task a = new Todo(fullCommand.substring(5));
                addTaskToArray(a);
                System.out.println("     Now you have " + count + " tasks in the list.");
            } catch (IndexOutOfBoundsException oob) {
                System.out.println("     ☹ OOPS!!! The description of a todo cannot be empty.");
            }
            break;
        case ("deadline"):
            try {
                Task b = new Deadline(fullCommand.substring(9, fullCommand.indexOf("/by")), fullCommand.substring(fullCommand.indexOf("/by") + 4));
                addTaskToArray(b);
                System.out.println("     Now you have " + count + " tasks in the list.");
            } catch (IndexOutOfBoundsException oob) {
                System.out.println("     ☹ OOPS!!! The description of a deadline cannot be empty.");
            }
            break;
        case ("event"):
            try {
                Task c = new Event(fullCommand.substring(6, fullCommand.indexOf("/at")), fullCommand.substring(fullCommand.indexOf("/at") + 4));
                addTaskToArray(c);
                System.out.println("     Now you have " + count + " tasks in the list.");
            } catch (IndexOutOfBoundsException oob) {
                System.out.println("     ☹ OOPS!!! The description of a event cannot be empty.");
            }
            break;
        default:
            System.out.println("      ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            break;
        }
        MessagePrinter.printBorder();
    }

    private static void addTaskToArray(Task a) {
        System.out.println("     Got it. I've added this task: ");
        tasks[count] = a;
        count++;
        System.out.println("      " + a);
    }

}
