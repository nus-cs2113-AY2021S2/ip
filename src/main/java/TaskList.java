import duke.Task;

public class TaskList {
    /**
     * Function to delete certain task when user use the delete command
     * @param delete_index the task index that user entered to be deleted
     * @param index the maximum index number of the current task list
     * @param tasks the original task list that the user has inputted so far
     */
    protected static void removeTask(int delete_index, int index, Task[] tasks) {
        if (delete_index - 1 == index) {
        } else {
            for (int i = delete_index - 1; i <= index; i++) {
                tasks[i] = tasks[i + 1];
            }
        }
        index -= 1;
    }

    /**
     * Return message to inform the user that the task has been deleted
     * message include task description, task type and its by/at date time
     * and the remaining number of tasks in the list
     * @param index the maximum index number of the current task list
     * @param tasks the original task list that the user has inputted so far
     */
    protected static void removeTaskMessage(int index, Task tasks) {
        System.out.println("Noted. I've removed this task: ");
        System.out.println("  " + tasks.toString());
        System.out.println("Now you have " + (index - 1) + " tasks in the list.");
    }

    /**
     * Print message to inform the user that the task has been added
     * message include task description, task type and its by/at date time
     * and update the number of tasks in the list.
     * @param index the maximum index number of the current task list
     * @param tasks the original task list that the user has inputted so far
     * @return updated index to track the number of tasks in the task list
     */
    protected static int addTaskMessage(int index, Task tasks) {
        System.out.println("Got it. I've added this task: ");
        System.out.println(" " + tasks.toString());
        System.out.println("Now you have " + (index + 1) + " tasks in the list. ");
        index++;
        return index;
    }

    /**
     * Print message to inform the user that the task has been marked as done
     * @param index the index of the task to be marked as done
     * @param tasks the original task list that the user has inputted so far
     */
    protected static void markAsDone(int index, Task[] tasks) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks[index].toString());
    }

    /**
     * Print all tasks in the task list
     * @param index the maximum index number of the current task list
     * @param tasks the original task list that the user has inputted so far
     */
    protected static void showAllTasks(int index, Task[] tasks) {
        try {
            for (int i = 0; i < index; i++) {
                System.out.println((i + 1) + "." + tasks[i].toString());
            }
        } catch (NullPointerException e) {
            // do not show error
        }
    }

    /**
     * Print all the tasks that matched the keyword that user has entered
     * @param index the maximum index number of the current task list
     * @param keyword user input to be found in all tasks in the list
     * @param tasks the original task list that the use has inputted so far
     */
    protected static void findTasks(int index, String keyword, Task[] tasks) {
        System.out.println("Here are the matching tasks in your list: ");
        for (int i = 0; i < index; i++) {
            if (tasks[i].getDescription().contains(keyword)) {
                System.out.println((i + 1) + "." + tasks[i].toString());
            }
        }
    }
}
