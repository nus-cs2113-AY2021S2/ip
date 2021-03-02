import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static int count = 0;

    public TaskList() {
    }

    public static int getCount() {
        return count;
    }

    public static ArrayList<Task> getTasks() {
        return tasks;
    }

    public static void setTasks(ArrayList<Task> tasks) {
        TaskList.tasks = tasks;
    }

    public static void setCount(int count) {
        TaskList.count = count;
    }

    public static void printList() {
        Ui.printBorder();
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < count; i++) {
            System.out.print("     " + (i + 1));
            System.out.println(". " + tasks.get(i));
        }
        Ui.printBorder();
    }

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

    public static void deleteTask(int index) {
        Task temp = tasks.get(index - 1);
        tasks.remove(index - 1);
        Ui.printBorder();
        System.out.println("      Noted. I've removed this task:  ");
        System.out.print("       ");
        System.out.println(temp);
        Ui.printBorder();
        count--;
    }

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

    public static void addEvent(String[] command) {
        String[] newCommand = command[1].split(" /at ", 2);
        try {
            Task c = new Event(newCommand[0], newCommand[1]);
            addTaskToArrayList(c);
            System.out.println("     Now you have " + count + " tasks in the list.");
        } catch (IndexOutOfBoundsException oob) {
            System.out.println("     ☹ OOPS!!! The description of a event cannot be empty.");
        }
        Ui.printBorder();
    }

    public static void addDeadline(String[] command) {
        String[] newCommand = command[1].split(" /by ", 2);
        try {
            Task c = new Deadline(newCommand[0], newCommand[1]);
            addTaskToArrayList(c);
            System.out.println("     Now you have " + count + " tasks in the list.");
        } catch (IndexOutOfBoundsException oob) {
            System.out.println("     ☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        Ui.printBorder();
    }

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
