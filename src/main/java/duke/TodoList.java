
package duke;


import java.util.ArrayList;


public class TodoList {

    private ArrayList<ImprovedTask> taskList = new ArrayList<>();
    private int totalTasks;

    public TodoList() {
        this.totalTasks = 0;
    }


    /**
     * Method to add task entry to the list
     *
     * @param newTask task entry to be added to the list
     */
    public void addTask(ImprovedTask newTask) { // adds todo type task and returns confirmation
        taskList.add(newTask);
        totalTasks++;
    }

    /**
     * Method to list all task entries currently in task list
     */
    public String listItems() { // lists all current tasks
        String output = "";
        if (this.totalTasks == 0) {
            return "Great! There are no tasks to show!";
        }
        for (int i = 0; i < totalTasks; i++) {
            if (i < totalTasks - 1) {
                output += Integer.toString(i + 1)
                        + ". " + taskList.get(i).displayType()
                        + " " + taskList.get(i).displayResolved()
                        + " " + taskList.get(i).displayDescription()
                        + " " + taskList.get(i).displayDate() + "\n";
            } else {
                output += Integer.toString(i + 1)
                        + ". " + taskList.get(i).displayType()
                        + " " + taskList.get(i).displayResolved()
                        + " " + taskList.get(i).displayDescription()
                        + " " + taskList.get(i).displayDate();
            }
        }
        return output;
    }

    /**
     * Method to set task in the list as resolved
     *
     * @param num the number of the task entry to be set as resolved
     */
    public void resolveTask(String num) { // resolves task and returns confirmation if task is not resolved
        int taskNum = Integer.parseInt(num) - 1;
        taskList.get(taskNum).resolve();


    }


    /**
     * Method to return total number of tasks
     */
    public int tasksTotal() { // returns total tasks in list
        return this.totalTasks;
    }

    /**
     * Method to delete the specified task
     *
     * @param num the number of the task to delete
     */
    public void deleteTask(String num) { // delete the specified task
        int taskNum = Integer.parseInt(num) - 1;
        ImprovedTask temp = taskList.get(taskNum);
        taskList.remove(temp);
        totalTasks--;
    }

    /**
     * Method to get all current tasks in the task list and format it in a way that can be parsed by updateFile
     */
    public String tasksUpdate() {
        String out = "";
        for (int i = 0; i < totalTasks; i++) {
            if (taskList.get(i).displayType().equals("[T]")) {
                out += taskList.get(i).displayType().charAt(1) + "/"
                        + taskList.get(i).displayResolved().charAt(1) + "/"
                        + taskList.get(i).displayDescription() + "/\n";
            } else {
                out += taskList.get(i).displayType().charAt(1) + "/"
                        + taskList.get(i).displayResolved().charAt(1) + "/"
                        + taskList.get(i).displayDescription() + "/"
                        + taskList.get(i).displayDate().substring(5, taskList.get(i).displayDate().indexOf(")")) + "\n";
            }
        }
        return out;
    }

    /**
     * Method to get all current tasks in the task list with the specified keyword and print it.
     *
     * @param keyword the specified keyword
     */
    public ArrayList<ImprovedTask> findTasksWithKeyword(String keyword) {
        ArrayList<ImprovedTask> tasksWithKeyword = new ArrayList<>();
        for (ImprovedTask task : taskList) {
            if (task.description.contains(keyword)) {
                tasksWithKeyword.add(task);
            }
        }
        return tasksWithKeyword;
    }
}
