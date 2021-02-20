package duke;

import exceptions.DukeException;
import exceptions.ErrorHandler;
import tasks.Task;


import java.lang.reflect.Array;
import java.util.ArrayList;

public class TaskList {

    private static ArrayList<Task> tasks;
    private static int tasksCount;

    static final int DONELENGTH = 4;
    static final int DELETELENGTH = 6;
    static final int FINDLENGTH = 4;


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


    public static void addTask(String userInput) {
        try {
            Task t;
            if (userInput.toLowerCase().startsWith("deadline")){
                t = Parser.processAddDeadline(userInput);
            }
            else if (userInput.toLowerCase().startsWith("event")){
                t = Parser.processAddEvent(userInput);
            }
            else if (userInput.toLowerCase().startsWith("todo")){
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
            int idx = Integer.parseInt(String.valueOf(task.charAt(DONELENGTH+1)));
            Task taskDone = tasks.get(idx-1);
            taskDone.markAsDone();
            Ui.printMarkTaskDoneMessage(taskDone);
        } catch (NumberFormatException e) {
            System.out.println("OOPS!!! Please enter an integer after 'done'.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("OOPS!!! Please choose a valid task index.");
        }

    }

    public static void deleteTask(String task) {
        try {
            int idx = Integer.parseInt(String.valueOf(task.charAt(DELETELENGTH+1)));
            Task taskDeleted = tasks.get(idx-1);
            tasks.remove(idx-1);
            tasksCount--;
            Ui.printDeleteTaskMessage(taskDeleted, tasksCount);
        } catch (NumberFormatException e) {
            System.out.println("OOPS!!! Please enter an integer after 'delete'.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("OOPS!!! Please choose a valid task index.");
        }
    }

    public static ArrayList<Task> findTask(String task) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        String searchDescription = task.substring(FINDLENGTH+1).trim();
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
