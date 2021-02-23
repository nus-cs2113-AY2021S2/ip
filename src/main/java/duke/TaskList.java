package duke;

import exceptions.DukeException;
import exceptions.ErrorHandler;
import tasks.Task;
import tasks.TaskType;

import java.util.ArrayList;
import java.util.Locale;

public class TaskList {

    private static ArrayList<Task> tasks;
    private static int tasksCount;


    public TaskList(ArrayList<Task> tasks) {
        if (tasks == null) {
            tasks = new ArrayList<>();
            tasksCount =0;
        }
        this.tasks = tasks;
        this.tasksCount = tasks.size();
    }

    public static ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     *
     * @param userInput User command
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

    public static void addTaskFromData(Task t) {
        tasks.add(t);
    }

    public static void incrementTaskCount() {
        tasksCount++;
    }
}
