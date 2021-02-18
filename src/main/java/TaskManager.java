import java.util.ArrayList;

public class TaskManager {
    private static ArrayList<Task> tasks = new ArrayList<>();
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
            System.out.println(". " + tasks.get(i));
        }
        MessagePrinter.printBorder();
    }

    public static void displayTaskDone(int index) throws DukeException {
        if ((tasks.get(index-1)).isDone()) {
            throw new DukeException();
        } else {
            (tasks.get(index-1)).markAsDone();
            MessagePrinter.printBorder();
            System.out.println("      Nice! I've marked this task as done: ");
            System.out.print("       ");
            System.out.println((tasks.get(index-1)));
            MessagePrinter.printBorder();
        }
    }

    public static void deleteTask(int index) {
            Task temp = tasks.get(index - 1);
            tasks.remove(index-1);
            MessagePrinter.printBorder();
            System.out.println("      Noted. I've removed this task:  ");
            System.out.print("       ");
            System.out.println(temp);
            MessagePrinter.printBorder();
            count--;
    }

    public static void addNewTask(String[] partOfCommand, String fullCommand) {

        MessagePrinter.printBorder();
        switch (partOfCommand[0]) {
        case ("todo"):
            try {
                Task a = new Todo(fullCommand.substring(5));
                addTaskToArrayList(a);
                System.out.println("     Now you have " + count + " tasks in the list.");
            } catch (IndexOutOfBoundsException oob) {
                System.out.println("     ☹ OOPS!!! The description of a todo cannot be empty.");
            }
            break;
        case ("deadline"):
            try {
                Task b = new Deadline(fullCommand.substring(9, fullCommand.indexOf("/by")), fullCommand.substring(fullCommand.indexOf("/by") + 4));
                addTaskToArrayList(b);
                System.out.println("     Now you have " + count + " tasks in the list.");
            } catch (IndexOutOfBoundsException oob) {
                System.out.println("     ☹ OOPS!!! The description of a deadline cannot be empty.");
            }
            break;
        case ("event"):
            try {
                Task c = new Event(fullCommand.substring(6, fullCommand.indexOf("/at")), fullCommand.substring(fullCommand.indexOf("/at") + 4));
                addTaskToArrayList(c);
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

    private static void addTaskToArrayList(Task a) {
        System.out.println("     Got it. I've added this task: ");
        tasks.add(a);
        count++;
        System.out.println("      " + a);
    }

}
