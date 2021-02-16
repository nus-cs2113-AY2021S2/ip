package duke.task;

import duke.Duke;

import java.util.ArrayList;

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

    public ArrayList<Task> getTasks() {
        return Tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        Tasks = tasks;
    }

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
        System.out.println(Duke.DIVIDER);
        printList();
        System.out.println(Duke.DIVIDER_LINE_ONLY);
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
                String timeString = currentItem.getTimeLimitString();
                System.out.println((i + 1) + ". "
                        + "[" + convertTaskType(currentItem.getTaskType()) + "] "
                        + "[" + (currentItem.isDone() ? "☑️" : "✖️") + "] "
                        + currentItem.getTaskContent()
                        + (currentItem.getTaskType() == TaskType.TODO ? "" : " ")
                        + timeString
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
     * Converts the enum type to string.
     *
     * @param type the type of the task.
     * @return the type of the task in string.
     */
    private String convertTaskType(TaskType type) {
        switch (type) {
        case TODO:
            return "T";
        case EVENT:
            return "E";
        case DEADLINE:
            return "D";
        default:
            return "Unknown task type";
        }
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
        System.out.println(Duke.DIVIDER
                + "A new to-do task is added:\n"
                + "Task content  :" + newTask.getTaskContent() + "\n"
                + getNumOfTasksString() + "\n"
                + Duke.DIVIDER_LINE_ONLY
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
        Deadline newTask = new Deadline(content, deadline);
        Tasks.add(newTask);
        numOfTasks++;
        System.out.println(Duke.DIVIDER
                + "A new deadline task is added:\n"
                + "Task content  :" + newTask.getTaskContent() + "\n"
                + "Task deadline :" + newTask.getDeadline() + "\n"
                + getNumOfTasksString() + "\n"
                + Duke.DIVIDER_LINE_ONLY
        );
    }

    /**
     * Adds a new event task to the current task list.
     * The content of the new list will be printed.
     *
     * @param content the content of the new event task object.
     * @param period  the period of the new event task object.
     */
    public void addEventTask(String content, String period) {
        Event newTask = new Event(content, period);
        Tasks.add(newTask);
        numOfTasks++;
        System.out.println(Duke.DIVIDER
                + "A new event task is added:\n"
                + "Task content  :" + newTask.getTaskContent() + "\n"
                + "Task period   :" + newTask.getPeriod() + "\n"
                + getNumOfTasksString() + "\n"
                + Duke.DIVIDER_LINE_ONLY
        );
    }

    /**
     * Updates the content of a given task object.
     *
     * @param itemIndex  the index of the task object in the array list.
     * @param newContent the new content to be updated.
     */
    public void updateTaskContent(int itemIndex, String newContent) {
        Task itemToUpdate = Tasks.get(itemIndex);
        itemToUpdate.setTaskContent(newContent);
    }

    /**
     * Removes a given task object from the current task list.
     *
     * @param itemIndex the index of the task object in the array list.
     */
    public void removeTaskFromList(int itemIndex) {
        String itemContent = Tasks.get(itemIndex).getTaskContent();
        Tasks.remove(itemIndex);
        numOfTasks--;
        System.out.println(Duke.DIVIDER
                + "A task object is removed: "
                + itemContent + "\n"
                + Duke.DIVIDER_LINE_ONLY
        );
    }

    /**
     * Prints the task object with content and status.
     *
     * @param itemIndex the index of the task object in the array list.
     */
    public void printTask(int itemIndex) {
        Task currentItem = Tasks.get(itemIndex);
        boolean isItemDone = currentItem.isDone();
        System.out.println("Item content: " + currentItem.getTaskContent());
        System.out.println("Item status: " + (isItemDone ? "Done" : "Undone"));
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
        System.out.println(Duke.DIVIDER_LINE_ONLY);
    }

    /**
     * Prints the updated status of a Task object.
     *
     * @param isDone      The status of the Task object.
     * @param currentTask The current Task object whose status is updated.
     */
    private void printUpdatedTaskStatus(boolean isDone, Task currentTask) {
        System.out.println(Duke.DIVIDER
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
