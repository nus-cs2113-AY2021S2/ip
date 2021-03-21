package dukchess.dao;

import java.util.ArrayList;
import java.util.List;

import dukchess.entity.Deadline;
import dukchess.entity.Event;
import dukchess.entity.Task;
import dukchess.entity.Todo;
import dukchess.ui.Ui;

public class TaskList {
    private static ArrayList<Task> tasks = new ArrayList<>(); // static list of tasks that reside throughout the program

    private TaskList() { } // ensure TaskList can never be instantiated

    /**
     * Set the list of tasks initially
     * @param tasks - list of tasks
     */
    public static void setTaskList(ArrayList<Task> tasks) {
        TaskList.tasks = tasks;
    }

    /**
     * Gets the list of tasks in the program at the moment
     * @return a list of tasks
     */
    public static ArrayList<Task> getTasksList() {
        return tasks;
    }

    private static String setAddedTaskStatus(int taskId, boolean isDone) {
        int actualTaskId = taskId - 1;
        if (actualTaskId >= tasks.size() || actualTaskId < 0) {
            return String.format(
                    "I'm terribly sorry Sir/Madam/Other :(\n"
                            + "%d is not a valid task id for the current list of tasks.",
                    taskId);
        }
        Task selectedTask = tasks.get(actualTaskId);
        String originalTaskStatus = selectedTask.toString();
        selectedTask.setDone(isDone);
        return String.format("Setting to done, original task status: %s\n>>> New task status: %s",
                originalTaskStatus, selectedTask.toString());
    }

    /**
     * Set a task to done.
     * @param taskIdString - A task ID in integer form
     */
    public static void handleDoneTask(String taskIdString) {
        try {
            int taskId = Integer.parseInt(taskIdString);
            String taskStatusAdditionOutcome = setAddedTaskStatus(taskId, true);
            System.out.println(taskStatusAdditionOutcome);
        } catch (NumberFormatException e) {
            Ui.printErrorMessage("The task ID provided must be a string.");
        }
    }

    /**
     * Prints out all added tasks
     */
    public static void printAddedTasks() {
        if (tasks.size() == 0) {
            System.out.println("No previously added tasks to list Sir/Madam/Other :(");
            return;
        }
        System.out.println("Here are the tasks in your list, Sir/Madam/Other:");
        Ui.printListOfTasks(tasks);
    }

    private static String addTodo(String description) {
        Todo newTodo = new Todo(description);
        tasks.add(newTodo);
        return String.format("Gotcha, added this todo: %s", newTodo.toString());
    }

    /**
     * Performs input validation before adding a new to-do to the list of tasks.
     * @param todoDescription - the description for the to-do task
     */
    public static void handleAddTodo(String todoDescription) {
        if (todoDescription.length() == 0) {
            System.out.println("Oops, todo description cannot be empty :(");
            return;
        }
        String todoAdditionOutcome = addTodo(todoDescription);
        System.out.println(todoAdditionOutcome);
    }

    private static String addDeadline(String description, String by) {
        Deadline newDeadline = new Deadline(description, by);
        tasks.add(newDeadline);
        return String.format("Gotcha, added this deadline: %s", newDeadline.toString());
    }

    /**
     * Performs input validation before adding a new deadline to the list of tasks.
     * @param commandArgs - expects a two-length array, first element is deadline's description,
     *                    second element is deadline's due date
     */
    public static void handleAddDeadline(String commandArgs) {
        if (commandArgs.length() == 0) {
            Ui.printErrorMessage("Oops, deadline description cannot be empty :(");
            return;
        }
        String[] deadlineArgs = commandArgs.split(" /by ");
        if (deadlineArgs.length != 2) {
            Ui.printErrorMessage("Oops, deadline due date cannot be empty :(");
            return;
        }
        String deadlineAdditionOutcome = addDeadline(deadlineArgs[0], deadlineArgs[1]);
        System.out.println(deadlineAdditionOutcome);
    }

    private static String addEvent(String description, String at) {
        Event newEvent = new Event(description, at);
        tasks.add(newEvent);
        return String.format("Gotcha, added this event: %s", newEvent.toString());
    }

    /**
     * Performs input validation before adding a new event to the list of tasks.
     * @param commandArgs - expects a two-length array, first element is event's description,
     *                    second element is when the event is at
     */
    public static void handleAddEvent(String commandArgs) {
        if (commandArgs.length() == 0) {
            Ui.printErrorMessage("Oops, event description cannot be empty :(");
            return;
        }
        String[] eventArgs = commandArgs.split(" /at ");
        if (eventArgs.length != 2) {
            Ui.printErrorMessage("Oops, event time cannot be empty :(");
            return;
        }
        String eventAdditionOutcome = addEvent(eventArgs[0], eventArgs[1]);
        System.out.println(eventAdditionOutcome);
    }

    private static String deleteTask(int taskIdToDelete) {
        try {
            Task taskToDelete = tasks.get(taskIdToDelete - 1);
            tasks.remove(taskIdToDelete - 1);
            return String.format("Noted, I've removed this task:\n"
                    + "%s\n"
                    + "Now, you have %d tasks in the list.", taskToDelete.toString(), tasks.size());
        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            return String.format("There is no task with id %d at the moment.", taskIdToDelete);
        }
    }

    /**
     * Performs input validation before deleting a task from the list of tasks.
     * @param taskIdString - the task id to be deleted as a string
     */
    public static void handleDeleteTask(String taskIdString) {
        if (taskIdString.length() == 0) {
            Ui.printErrorMessage("You have to specify which task to delete!");
            return;
        }
        try {
            int taskIdToDelete = Integer.parseInt(taskIdString);
            String deletionOutcome = deleteTask(taskIdToDelete);
            System.out.println(deletionOutcome);
        } catch (NumberFormatException numberFormatException) {
            Ui.printErrorMessage("Invalid number passed to task deletion command.");
        }
    }

    /**
     * Performs input validation before finding a task from the list of tasks.
     * @param keyword - the keyword to search task list on
     */
    public static void handleFindTask(String keyword) {
        if (keyword.length() == 0) {
            Ui.printErrorMessage("You did not specify the keyword to find!");
            return;
        }
        List<Task> searchResults = findTask(keyword);
        if (searchResults.size() == 0) {
            Ui.printErrorMessage(String.format("Could not find any tasks matching \"%s\"", keyword));
            return;
        }
        Ui.printListOfTasks(searchResults);
    }

    private static List<Task> findTask(String keyword) {
        List<Task> searchResults = new ArrayList<>();
        for (Task task: tasks) {
            if (task.getTaskDescription().contains(keyword)) {
                searchResults.add(task);
            }
        };
        return searchResults;
    };

}
