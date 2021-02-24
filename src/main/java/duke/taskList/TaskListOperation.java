package duke.taskList;

import duke.parser.Parser;
import duke.storage.FileHandler;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.ui.UI;

import java.util.ArrayList;

public class TaskListOperation {
    /**
     * Marks a task with a given integer index as done, if the index is not out of range.
     *
     * @param taskList  The list that stores Task objects.
     * @param userInput User's keyboard input in String.
     */
    public static void handleDoneTask(TaskList taskList, String userInput) {
        try {
            String taskIndexString = Parser.getTaskIndexString(userInput);
            int itemIndex = Integer.parseInt(taskIndexString) - 1;
            if (isTaskIndexOutOfRange(taskList, itemIndex)) {
                return;
            }
            setTaskAsDone(taskList, itemIndex);
        } catch (Exception e) {
            UI.printInvalidIntegerWarning();
        }
    }

    /**
     * Sets the status of a Task object as done.
     *
     * @param taskList  The list that stores Task objects.
     * @param itemIndex The index of the task object in the task list.
     */
    private static void setTaskAsDone(TaskList taskList, int itemIndex) {
        taskList.updateTaskStatus(itemIndex, true);
    }

    /**
     * Returns true if the index of the task is out of range, false if in range.
     *
     * @param taskList  The list that stores Task objects.
     * @param itemIndex The index of the task object in the task list.
     * @return true if the index of the task is out of range.
     */
    private static boolean isTaskIndexOutOfRange(TaskList taskList, int itemIndex) {
        if (!taskList.isIndexInRange(itemIndex)) {
            System.out.println(UI.DIVIDER
                    + "The task index input is out of range!\n"
                    + UI.DIVIDER
                    + "Try again:"
            );
            return true;
        }
        return false;
    }

    /**
     * Adds an event type task into the taskList.
     *
     * @param taskList  The list that stores Task objects.
     * @param userInput User's keyboard input in String.
     */
    public static void addEventTaskToList(TaskList taskList, String userInput) {
        try {
            Event.isEventCommandValid(userInput);
            String[] eventTaskContent = Event.getEventTaskContent(userInput);
            String taskContent = eventTaskContent[0];
            String taskPeriod = eventTaskContent[1];
            taskList.addEventTask(taskContent, taskPeriod);
        } catch (Exception e) {
            UI.printErrorMessage(e);
        }
    }

    /**
     * Adds a deadline type task into the taskList.
     *
     * @param taskList  The list that stores Task objects.
     * @param userInput User's keyboard input in String.
     */
    public static void addDeadlineTaskToList(TaskList taskList, String userInput) {
        try {
            Deadline.isDeadlineCommandValid(userInput);
            String[] deadlineTaskContent = Deadline.getDeadlineTaskContent(userInput);
            String taskContent = deadlineTaskContent[0];
            String taskDeadline = deadlineTaskContent[1];
            taskList.addDeadlineTask(taskContent, taskDeadline);
        } catch (Exception e) {
            UI.printErrorMessage(e);
        }
    }

    /**
     * Adds a to-do type task into the taskList.
     *
     * @param taskList  The list that stores Task objects.
     * @param userInput User's keyboard input in String.
     */
    public static void addTodoTaskToList(TaskList taskList, String userInput) {
        try {
            String[] todoTaskContent = Todo.getTodoTaskContent(userInput);
            taskList.addTodoTask(todoTaskContent[0]);
        } catch (Exception e) {
            UI.printErrorMessage(e);
        }
    }

    public static void deleteTask(TaskList taskList, String userInput) {
        try {
            String taskIndexStr = Parser.getTaskIndexString(userInput);
            int taskIndex = Integer.parseInt(taskIndexStr) - 1;
            if (isTaskIndexOutOfRange(taskList, taskIndex)) {
                return;
            }
            taskList.removeTaskFromList(taskIndex);
        } catch (Exception e) {
            UI.printErrorMessage(e);
        }
    }

    public static void initializeTaskList(TaskList taskList) {
        ArrayList<Task> localTasks = FileHandler.readTasksFromFile();
        if (localTasks != null) {
            taskList.setTasks(localTasks);
            taskList.setNumOfTasks(localTasks.size());
        }
    }
}
