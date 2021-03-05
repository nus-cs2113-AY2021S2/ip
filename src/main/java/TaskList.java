import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TaskList {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static int count = 0;

    /**
     * Constructor of TaskList
     */
    public TaskList() {
    }

    /**
     * Returns the number of tasks in ArrayList
     *
     * @return number of tasks
     */
    public static int getCount() {
        return count;
    }

    /**
     * Returns all the tasks in ArrayList
     *
     * @return ArrayList of tasks
     */
    public static ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Sets the tasks in ArrayList
     *
     * @param tasks makes up the array list of task.
     */
    public static void setTasks(ArrayList<Task> tasks) {
        TaskList.tasks = tasks;
    }

    /**
     * Sets the tasks in ArrayList
     *
     * @param count is now the number of tasks in array list.
     */
    public static void setCount(int count) {
        TaskList.count = count;
    }

    /**
     * Prints out all the tasks in the array list.
     * Each task printed out is numbered.
     */
    public static void printList() {
        Ui.printBorder();
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < count; i++) {
            System.out.print("     " + (i + 1));
            System.out.println(". " + tasks.get(i));
        }
        Ui.printBorder();
    }

    /**
     * Prints out message that the task has been marked done.
     * Print out the task as well.
     *
     * @param index indicates the task number which the user has marked done.
     */
    public static void displayTaskDone(int index) throws DukeException {
        if ((tasks.get(index - 1)).isDone()) {
            throw new DukeException();
        } else {
            (tasks.get(index - 1)).markAsDone();
            Ui.printBorder();
            System.out.println("      Nice! I've marked this task as done: ");
            System.out.print("       ");
            System.out.println((tasks.get(index - 1)));
            Ui.printBorder();
        }
    }

    /**
     * Prints out message that the task has been removed from the list.
     * Removes the task from the list.
     *
     * @param index indicates the task number which the user has deleted.
     */
    public static void deleteTask(int index) {
        Ui.printBorder();
        try {
            Task temp = tasks.get(index - 1);
            tasks.remove(index - 1);
            System.out.println("      Noted. I've removed this task:  ");
            System.out.print("       ");
            System.out.println(temp);
            count--;
        } catch (IndexOutOfBoundsException oob) {
            System.out.println("     ☹ OOPS!!! That task number is invalid.");
        }
        Ui.printBorder();
    }

    /**
     * Adds todo task to array list
     * Updates total number of tasks.
     *
     * @param command consists of task description to be added to array list.
     */
    public static void addToDo(String[] command) {
        try {
            Task a = new Todo(command[1]);
            addTaskToArrayList(a);
            System.out.println("     Now you have " + count + " tasks in the list.");
        } catch (IndexOutOfBoundsException oob) {
            System.out.println("     ☹ OOPS!!! The description of a todo cannot be empty.");
        }
        Ui.printBorder();
    }

    public static String formatDateTime(String Command) {
        String[] newCommand = Command.split(" ", 2);
        try {
            LocalDate taskDate = LocalDate.parse(newCommand[0]);
            String formattedDate = taskDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));

            LocalTime taskTime = LocalTime.parse(newCommand[1]);
            String formattedTime = taskTime.format(DateTimeFormatter.ofPattern("hh:mm a"));

            return formattedDate + ", " + formattedTime;
        } catch (DateTimeParseException | ArrayIndexOutOfBoundsException dpe) {
            return Command;
        }
    }

    /**
     * Adds event task to array list
     * Updates total number of tasks.
     *
     * @param command consists of task description to be added to array list.
     */
    public static void addEvent(String[] command) {
        try {
            String[] newCommand = command[1].split(" /at ", 2);
            newCommand[1] = formatDateTime(newCommand[1]);
            Task c = new Event(newCommand[0], newCommand[1]);
            addTaskToArrayList(c);
            System.out.println("     Now you have " + count + " tasks in the list.");
        } catch (ArrayIndexOutOfBoundsException oob) {
            Ui.printBorder();
            System.out.println("     ☹ OOPS!!! The description of a event cannot be empty.");
        }
        Ui.printBorder();
    }

    /**
     * Adds deadline task to array list
     * Updates total number of tasks.
     *
     * @param command consists of task description to be added to array list.
     */
    public static void addDeadline(String[] command) {
        try {
            String[] newCommand = command[1].split(" /by ", 2);
            newCommand[1] = formatDateTime(newCommand[1]);
            Task c = new Deadline(newCommand[0], newCommand[1]);
            addTaskToArrayList(c);
            System.out.println("     Now you have " + count + " tasks in the list.");
        } catch (IndexOutOfBoundsException oob) {
            Ui.printBorder();
            System.out.println("     ☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        Ui.printBorder();
    }

    /**
     * Adds the task to array list.
     * Updates count.
     *
     * @param a indicates task to be added.
     */
    private static void addTaskToArrayList(Task a) {
        Ui.printBorder();
        System.out.println("     Got it. I've added this task: ");
        tasks.add(a);
        count++;
        System.out.println("      " + a);
    }

    public static void searchArrayList(String keyword) {
        Ui.printBorder();
        System.out.println("     Here are the matching tasks in your list:");
        boolean hasTask = false;
        for (Task task : tasks) {
            if (!task.getDescription().contains(keyword)) {
                continue;
            }
            printFoundTaskMessage(task);
            hasTask = true;
        }
        if (!hasTask) {
            System.out.println("     There are no tasks with that keyword!");
        }
        Ui.printBorder();
    }

    public static void printFoundTaskMessage(Task task) {
        System.out.println("     " + (tasks.indexOf(task) + 1) + "." + task);
    }

}
