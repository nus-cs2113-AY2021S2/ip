package duke.task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public int getListSize() {
        return this.taskList.size();
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    public Task getTask(int index) {
        return this.taskList.get(index);
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }
    
    public void deleteTask(int index) {
        this.taskList.remove(index);
    }

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
            taskDetails = getTaskDetails(task, taskDetails);
            listOfTaskDetails.add(taskDetails);
        }
        return listOfTaskDetails;
    }

    public String getTaskDetails(Task task, String taskDetails) {
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
