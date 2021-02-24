package duke.taskList;

import duke.task.*;
import duke.ui.UI;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Represents a list that stores tasks.
 */
public class TaskList {
    /**
     * Total number of task object in current list.
     */
    private int numOfTasks = 0;
    /**
     * The collection of task object in current list.
     */
    private ArrayList<Task> Tasks;

    /**
     * Initializes a to-do-list object with an empty array list that stores task item.
     */
    public TaskList() {
        this.Tasks = new ArrayList<>();
    }

    /**
     * Returns the total number of task object.
     *
     * @return the total number of task object.
     */
    public int getNumOfTasks() {
        return numOfTasks;
    }

    /**
     * Gets all tasks in an ArrayList.
     *
     * @return an ArrayList that stores all task objects.
     */
    public ArrayList<Task> getTasks() {
        return Tasks;
    }

    /**
     * Sets tasks in an ArrayList to the current task list.
     *
     * @param tasks an ArrayList that stores all task objects to be set.
     */
    public void setTasks(ArrayList<Task> tasks) {
        Tasks = tasks;
    }

    /**
     * Sets the total number of task objects in a taskList object.
     *
     * @param numOfTasks the total number of task objects.
     */
    public void setNumOfTasks(int numOfTasks) {
        this.numOfTasks = numOfTasks;
    }

    /**
     * Returns a string message about the number of task objects in the list.
     *
     * @return a string of the number of task objects in the list.
     */
    private String getNumOfTasksString() {
        int numOfTasks = getNumOfTasks();
        return "Now you have " + numOfTasks + (numOfTasks == 1 ? " task" : " tasks") + " in the list.";
    }

    /**
     * Prints the current list with dividers.
     */
    public void printCurrentList() {
        System.out.println(UI.DIVIDER);
        printList();
        System.out.println(UI.DIVIDER_LINE_ONLY);
    }

    /**
     * Prints the current list (without dividers).
     */
    private void printList() {
        int listSize = Tasks.size();
        System.out.println("This is your current task list:");
        if (listSize == 0) {
            printListEmptyWarning();
        } else {
            for (int i = 0; i < listSize; i++) {
                Task currentItem = Tasks.get(i);
                String timeLimitFormatted = UI.getTimeLimitFormatted(currentItem);
                System.out.println((i + 1) + ". "
                        + "[" + UI.convertTaskType(currentItem.getTaskType()) + "] "
                        + "[" + (currentItem.isDone() ? "☑️" : "✖️") + "] "
                        + currentItem.getTaskContent()
                        + (currentItem.getTaskType() == TaskType.TODO ? "" : " ")
                        + timeLimitFormatted
                );
            }
        }
    }


    /**
     * Prints warning message if the list is empty.
     */
    private void printListEmptyWarning() {
        System.out.println(" - The list is empty!");
        System.out.println(" - You can try add task object by input what\n"
                + "   you want to do.\n"
                + " - check 'help' for more."
        );
    }


    /**
     * Adds a new to-do task to the current task list.
     * The content of the new list will be printed.
     *
     * @param content the content of the new task object.
     */
    public void addTodoTask(String content) {
        Todo newTask = new Todo(content);
        Tasks.add(newTask);
        numOfTasks++;
        System.out.println(UI.DIVIDER
                + "A new to-do task is added:\n"
                + "Task content  :" + newTask.getTaskContent() + "\n"
                + getNumOfTasksString() + "\n"
                + UI.DIVIDER_LINE_ONLY
        );
    }

    /**
     * Adds a new deadline task to the current task list.
     * The content of the new list will be printed.
     *
     * @param content  the content of the new deadline task object.
     * @param deadline the deadline of the new deadline task object.
     */
    public void addDeadlineTask(String content, String deadline) {
        LocalDate timeLimit = getTimeLimitInDate(deadline);
        Deadline newTask = new Deadline(content, timeLimit);
        Tasks.add(newTask);
        numOfTasks++;
        System.out.println(UI.DIVIDER
                + "A new deadline task is added:\n"
                + "Task content  :" + newTask.getTaskContent() + "\n"
                + "Task deadline :" + newTask.getDeadline() + "\n"
                + getNumOfTasksString() + "\n"
                + UI.DIVIDER_LINE_ONLY
        );
    }

    private LocalDate getTimeLimitInDate(String timeLimit) throws DateTimeParseException {
        return LocalDate.parse(timeLimit);
    }

    /**
     * Adds a new event task to the current task list.
     * The content of the new list will be printed.
     *
     * @param content the content of the new event task object.
     * @param period  the period of the new event task object.
     */
    public void addEventTask(String content, String period) {
        LocalDate timeLimit = getTimeLimitInDate(period);
        Event newTask = new Event(content, timeLimit);
        Tasks.add(newTask);
        numOfTasks++;
        System.out.println(UI.DIVIDER
                + "A new event task is added:\n"
                + "Task content  :" + newTask.getTaskContent() + "\n"
                + "Task period   :" + newTask.getEventTime() + "\n"
                + getNumOfTasksString() + "\n"
                + UI.DIVIDER_LINE_ONLY
        );
    }

    /**
     * Removes a given task object from the current task list.
     *
     * @param itemIndex the index of the task object in the array list.
     */
    public void removeTaskFromList(int itemIndex) {
        Task currentItem = Tasks.get(itemIndex);
        String timeString = currentItem.getTimeLimitString();
        Tasks.remove(itemIndex);
        numOfTasks--;
        System.out.println(UI.DIVIDER
                + "The task below is successfully removed from your task list:)\n\t"
                + "[" + UI.convertTaskType(currentItem.getTaskType()) + "] "
                + "[" + (currentItem.isDone() ? "☑️" : "✖️") + "] "
                + currentItem.getTaskContent()
                + (currentItem.getTaskType() == TaskType.TODO ? "" : " ")
                + timeString + "\n"
                + getNumOfTasksString() + "\n"
                + UI.DIVIDER_LINE_ONLY
        );
    }

    /**
     * Updates the status of a given task object.
     *
     * @param itemIndex the index of the task object in the array list.
     * @param isDone    the new status of the task object.
     */
    public void updateTaskStatus(int itemIndex, boolean isDone) {
        Task currentItem = Tasks.get(itemIndex);
        currentItem.setDone(isDone);
        printUpdatedTaskStatus(isDone, currentItem);
        printList();
        System.out.println(UI.DIVIDER_LINE_ONLY);
    }

    /**
     * Prints the updated status of a Task object.
     *
     * @param isDone      The status of the Task object.
     * @param currentTask The current Task object whose status is updated.
     */
    private void printUpdatedTaskStatus(boolean isDone, Task currentTask) {
        System.out.println(UI.DIVIDER
                + "The task object: \n"
                + " | " + currentTask.getTaskContent() + " |\n"
                + "is marked as "
                + (isDone ? "done" : "undone") + ".\n"
        );
    }

    /**
     * Checks if the index of a given task is in the range of the list;
     *
     * @param index the index of the task object.
     * @return true if in range, false otherwise.
     */
    public boolean isIndexInRange(int index) {
        return !(index < 0 || index >= numOfTasks);
    }

}
