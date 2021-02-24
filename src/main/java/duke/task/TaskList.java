package duke.task;

import java.util.ArrayList;

/**
 * Represents a collection of all <code>Task</code> created by the user.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<Task>();
    }

    public  int getListSize() {
        return taskList.size();
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Returns the task at a particular index.
     * 
     * @param index index to get task from
     * @return task at index
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }

    public void addTask(Task task) {
        taskList.add(task);
    }
    
    public void deleteTask(int index) {
        taskList.remove(index);
    }

    /**
     * Returns a list of task details of all tasks in the task list.
     * Details in each task are delimited with '~'.
     * 
     * @return ArrayList<String> list of task details
     */
    public ArrayList<String> getListOfTaskDetails() {
        ArrayList<String> listOfTaskDetails = new ArrayList<String>();
        for (Task task : getTaskList()) {
            if (task == null) {
                break;
            }
            String taskType = task.getClass().getSimpleName();
            String isDone = String.valueOf(task.isDone());
            String taskDescription = task.getDescription();
            String taskDetails = taskType + "~" + isDone + "~" + taskDescription;
            taskDetails = addTaskTimingsToDetails(task, taskDetails);
            listOfTaskDetails.add(taskDetails);
        }
        return listOfTaskDetails;
    }

    /**
     * Returns the task details of a task.
     * Adds the due dates of deadline and event tasks to their taskDetails string.
     * Details in each task are delimited with '~'.
     * 
     * @param task a <code>Task</code> object
     * @param taskDetails task details containing task type, done status, and description
     * @return updated task details containing task type, done status, description, and due date
     */
    public String addTaskTimingsToDetails(Task task, String taskDetails) {
        if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            String by = deadline.getBy();
            taskDetails += "~" + by;
        }
        if (task instanceof Event) {
            Event event = (Event) task;
            String at = event.getAt();
            taskDetails += "~" + at;
        }
        return taskDetails;
    }

}
