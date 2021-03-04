package duke;

import java.util.ArrayList;

/**
 * Represents a task manager where all the tasks are stored in
 * this class
 * <p>
 * User can add, delete, print tasks etc here
 */
public class TaskList {
    private static ArrayList<Task> taskList = new ArrayList<>();
    private static int taskCount = 0;

    /**
     * Adds tasks(todo, event or deadline) to an ArrayList called taskList
     *
     * @param task Task to be added
     */
    public static void add(Task task) {
        taskList.add(task);
        taskCount++;
    }


    /**
     * Delete and print out the specific task in taskList
     *
     * @param taskIndex index of the specific task to be deleted
     */
    public static void delete(int taskIndex) {
        Task t = taskList.get(taskIndex - 1);
        taskList.remove(taskIndex - 1);
        taskCount--;
        Ui.printDivider();
        System.out.println("Noted. I've removed this task:");
        System.out.print("[" + t.getAlphabet() + "]");
        System.out.println("[" + t.getStatusIcon() + "] " + t.toString());
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        Ui.printDivider();
    }

    public static int getTaskCount() {
        return taskCount;
    }

    public static Task getTask(int taskIndex) {
        return taskList.get(taskIndex - 1);
    }

    //prints tasks when user typed "list"
    public static void printTasks() {
        Ui.printDivider();
        if (taskCount == 0) {
            System.out.println("You do not have any tasks at the moment! :)");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskCount; ++i) {
                System.out.print(i + 1 + ". [" + taskList.get(i).getAlphabet() + "]");
                System.out.println("[" + taskList.get(i).getStatusIcon() + "] " + taskList.get(i).toString());
            }
        }
        Ui.printDivider();
    }

    /**
     * Prints all tasks in taskList that contains a specific keyword
     * in its description
     *
     * @param keyword Keyword to be searched in all the tasks in taskList
     */
    public static void printKeywordTasks(String keyword) {
        ArrayList<Task> keywordTaskList = new ArrayList<>();
        for (Task t : taskList) {
            if (t.description.toLowerCase().contains(keyword.toLowerCase())) {
                keywordTaskList.add(t);
            }
        }

        if (keywordTaskList.isEmpty()) {
            Ui.printDivider();
            System.out.println("There are no matching tasks in your list.");
            Ui.printDivider();
            return;
        }

        int i = 0;
        Ui.printDivider();
        System.out.println("Here are the matching tasks in your list:");
        for (Task t : keywordTaskList) {
            System.out.print(i + 1 + ". [" + t.getAlphabet() + "]");
            System.out.println("[" + t.getStatusIcon() + "] " + t.toString());
            i++;
        }
        Ui.printDivider();
    }

}
