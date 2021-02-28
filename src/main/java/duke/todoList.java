
package duke;

import duke.improvedTask;
import duke.listTypes;


import java.util.ArrayList;


public class todoList {

    private ArrayList<improvedTask> taskList = new ArrayList<>();
    private int totalTasks;
    private int leftoverTasks;

    public todoList(){
        this.totalTasks = 0;
        this.leftoverTasks = 0;
    }


    public String addTask(String desc, listTypes type, String date){ // adds deadline or event type task and returns confirmation
        taskList.add (new improvedTask(desc, type, date));
        totalTasks++;
        leftoverTasks++;
        return "Task: [" + desc + "] has been added."
        + "\nYou now have " + this.tasksLeft() + " task(s) left undone in the list!";
    }
    public String addTask(String desc, listTypes type){ // adds todo type task and returns confirmation
        taskList.add(new improvedTask(desc, type));
        totalTasks++;
        leftoverTasks++;
        return "Task: [" + desc + "] has been added."
                + "\nYou now have " + this.tasksLeft() + " task(s) left undone in the list!";
    }

    /**Method to add task entry to the list
     * @param newTask task entry to be added to the list
     */
    public String addTask(improvedTask newTask){ // adds todo type task and returns confirmation
        taskList.add(newTask);
        totalTasks++;
        leftoverTasks++;
        return "Task: [" + newTask.description + "] has been added."
                + "\nYou now have " + this.tasksLeft() + " task(s) left undone in the list!";
    }
    /**Method to list all task entries currently in task list*/
    public String listItems(){ // lists all current tasks
        String output = "";
        if(this.totalTasks == 0){
            return "Great! There are no tasks to show!";
        }
        for(int i = 0; i < totalTasks; i++){
            if(i < totalTasks - 1) {
                output += Integer.toString(i + 1)
                        + ". " + taskList.get(i).displayType()
                        + " " + taskList.get(i).displayResolved()
                        + " " + taskList.get(i).displayDescription()
                        + " " + taskList.get(i).displayDate() + "\n";
            }else{
                output += Integer.toString(i + 1)
                        + ". " + taskList.get(i).displayType()
                        + " " + taskList.get(i).displayResolved()
                        + " " + taskList.get(i).displayDescription()
                        + " " + taskList.get(i).displayDate();
            }
        }
        return output;
    }
    /**Method to set task in the list as resolved
     * @param num the number of the task entry to be set as resolved
     */
    public String resolveTask(String num){ // resolves task and returns confirmation if task is not resolved
        Integer taskNum = Integer.parseInt(num) - 1;
        if (taskList.get(taskNum).isResolved()){
            return "Task is already done!";
        }
        taskList.get(taskNum).resolve();
        this.leftoverTasks--;
        return "Great! Task ["+ taskList.get(taskNum).displayDescription() + "] has been marked as done!"
                + "\nYou now have " + this.tasksLeft() + " task(s) left undone in the list!";

    }
    /**Method to return number of tasks not done
     */
    public int tasksLeft(){ // returns number of undone tasks
        return this.leftoverTasks;
    }
    /**Method to return total number of tasks
     */
    public int tasksTotal(){ // returns total tasks in list
        return this.totalTasks;
    }
    /**Method to delete the specified task
     * @param num the number of the task to delete
     */
    public String deleteTask(String num){ // delete the specified task
        Integer taskNum = Integer.parseInt(num) - 1;
        improvedTask temp = taskList.get(taskNum);
        taskList.remove(temp);
        totalTasks--;
        if(!temp.isResolved()) {
            leftoverTasks--;
        }
        String out = "Noted. I've removed this task: \n     "
                   + temp.displayType()
                   + " " + temp.displayResolved()
                   + " " + temp.displayDescription()
                   + " " + temp.displayDate() + "\n"
                   + "Now you have " + totalTasks + " task(s) total left in the list!";
        return out;
    }

    /**Method to get all current tasks in the task list and format it in a way that can be parsed by updateFile
     */
    public String tasksUpdate(){
        String out = "";
        for (int i = 0; i < totalTasks; i++){
            if (taskList.get(i).displayType().equals("[T]")) {
                out += taskList.get(i).displayType().substring(1, 2) + "/"
                        + taskList.get(i).displayResolved().substring(1, 2) + "/"
                        + taskList.get(i).displayDescription() + "/\n";
            }else{
                out += taskList.get(i).displayType().substring(1, 2) + "/"
                        + taskList.get(i).displayResolved().substring(1, 2) + "/"
                        + taskList.get(i).displayDescription() + "/"
                        + taskList.get(i).displayDate().substring(5, taskList.get(i).displayDate().indexOf(")")) + "\n";
            }
        }
        return out;
    }
    /**Method to get all current tasks in the task list with the specified keyword and print it.
     * @param keyword the specified keyword
     */
    public ArrayList<improvedTask> findTasksWithKeyword(String keyword){
        ArrayList<improvedTask> tasksWithKeyword = new ArrayList<>();
        for (improvedTask task : taskList) {
            if(task.description.contains(keyword)){
                tasksWithKeyword.add(task);
            }
        }
        return tasksWithKeyword;
    }
}
