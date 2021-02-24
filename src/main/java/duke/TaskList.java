package duke;

import exceptions.DukeException;
import exceptions.ErrorHandler;
import tasks.Task;
import tasks.TaskType;

import java.util.ArrayList;

/**
 * Contains the task list and tracks the number of tasks in the list.
 * Deals with tasks-related operations, such as add tasks, delete tasks, find tasks and marking a task done.
 */
public class TaskList {

    private static ArrayList<Task> tasks;
    private static int tasksCount;

    /**
     * Constructor for TaskList
     * Initialises 'tasks' arraylist and the number of tasks.
     * If 'tasks' is null, create an empty 'tasks' arraylist and set number of tasks to 0.
     * @param tasks ArrayList containing all the tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        if (tasks == null) {
            tasks = new ArrayList<>();
            tasksCount =0;
        }
        this.tasks = tasks;
        this.tasksCount = tasks.size();
    }

    /**
     * Return 'tasks' arraylist.
     * @return tasks ArrayList containing all the tasks
     */
    public static ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Add new task (todo/ event/ deadline) into 'tasks' arraylist
     * If description of task is empty, print error message indicating that description of task cannot be empty.
     * If user command does not contain ('todo'/ 'event'/ 'deadline'), print error message indicating invalid command.
     * @param userInput User's description of task to be added
     */
    public static void addTask(String userInput) {
        try {
            Task t;
            if (userInput.toLowerCase().startsWith(TaskType.DEADLINE.toString().toLowerCase())){
                t = Parser.processAddDeadline(userInput);
            }
            else if (userInput.toLowerCase().startsWith(TaskType.EVENT.toString().toLowerCase())){
                t = Parser.processAddEvent(userInput);
            }
            else if (userInput.toLowerCase().startsWith(TaskType.TODO.toString().toLowerCase())){
                t = Parser.processAddTodo(userInput);
            }
            else {
                ErrorHandler.printErrorMsgInvalidInput();
                return;
            }
            tasks.add(t);
            Ui.printAddTaskMessage(tasks, tasksCount);
            tasksCount++;
        } catch (DukeException e) {
            e.getErrorTaskCannotBeEmpty();
        }
    }

    /**
     * Mark the task, as specified by the user, as done.
     * If no index is indicated, print error message indicating invalid index.
     * @param task User's input
     */
    public static void markTaskDone(String task) {
        try {
            int idx = Parser.processTaskDone(task);
            Task taskDone = tasks.get(idx-1);
            taskDone.markAsDone();
            Ui.printMarkTaskDoneMessage(taskDone);
        } catch (IndexOutOfBoundsException e) {
            ErrorHandler.printErrorMsgIndexCannotBeEmpty();
        }
    }

    /**
     * Delete the task, as specified by the user, from the arraylist.
     * If no index is indicated, print error message indicating invalid index.
     * @param task User's input
     */
    public static void deleteTask(String task) {
        try {
            int idx = Parser.processDeleteTask(task);
            Task taskDeleted = tasks.get(idx-1);
            tasks.remove(idx-1);
            tasksCount--;
            Ui.printDeleteTaskMessage(taskDeleted, tasksCount);
        } catch (IndexOutOfBoundsException e) {
            ErrorHandler.printErrorMsgIndexCannotBeEmpty();
        }

    }

    /**
     * Returns tasks which contains substring that matches user's indicated search description
     * If no match is found, return nothing.
     * @param task ArrayList containing all the tasks
     * @return
     */
    public static ArrayList<Task> findTask(String task) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        String searchDescription = Parser.processFindTask(task);
        for (Task t: tasks) {
            if (t.getDescription().contains(searchDescription)){
                matchingTasks.add(t);
            }
        }
        return matchingTasks;
    }

}
