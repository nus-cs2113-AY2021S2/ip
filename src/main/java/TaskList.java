import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.time.LocalDate;

/**
 * list of task recorded
 */
public class TaskList {

    public ArrayList<Task> tasks;

    /**
     * TaskList Constructor
     * @param tasks the list of tasks in list
     */
    public TaskList(ArrayList<Task> tasks){
        this.tasks = tasks;
    }

    /**
     * gets the size of task list
     * @return the number of tasks in list
     */
    public int getSize(){
        return tasks.size();
    }

    /**
     * adds task to list
     * @param task the task to be added
     * @param ui ui of adding tasks
     */
    public void addTask(Task task, Ui ui){
        tasks.add(task);
        ui.addResponse("Got it. I've added this task:" + "\n" + " " + task.toString() + "\n"
                + "Now you have " + getSize() + " tasks in the list");
    }

    /**
     * deletes task from list
     * @param taskIndex index of task to be deleted
     * @param ui ui of deleting task
     */
    public void deleteTask(int taskIndex, Ui ui){
        Task deleted = tasks.get(taskIndex - 1);
        tasks.remove(taskIndex - 1);
        ui.addResponse("Noted. I've removed this task: " + "\n" +
                deleted.toString() + "\n" + "Now you have " + getSize() + " tasks in the list.");
    }

    /**
     * marking a task as done
     * @param taskIndex index of task to be mark as done
     * @param ui ui of marking task as done
     */
    public void doneTask(int taskIndex, Ui ui){
        tasks.get(taskIndex - 1).markAsDone();
        ui.addResponse("Congrats! You are done with task " + taskIndex);
    }

    /**
     * showing the tasks in the list
     * @param ui ui of showing the tasks in the list
     */
    public void showTaskList(Ui ui){
        String listStrings = "";
        for (int i = 0; i < getSize(); i++) {
            Integer listNum = i + 1;
            listStrings += listNum.toString() + "." + tasks.get(i).toString() + "\n";
        }
        ui.addResponse("Here are the tasks in your list:" + "\n" + listStrings);
    }

    /**
     * finding a task according to keyword
     * @param keyword the keyword to be searched
     * @param ui ui of finding according to keyword
     */
    public void findKeyword(String keyword, Ui ui){
        ArrayList<Task> keyTasks = new ArrayList<>();
        for(int i = 0; i < getSize(); i++){
            if(this.tasks.get(i).description.contains(keyword)){
                keyTasks.add(this.tasks.get(i));
            }
        }

        if(keyTasks.size() == 0){
            ui.addResponse("Sorry there is no matching task :-(");
        }

        String findList = "";
        for(int i = 0; i < keyTasks.size(); i++){
            Integer listNum = i + 1;
            findList += listNum.toString() + "." + keyTasks.get(i).toString() + "\n";
        }
        ui.addResponse("Here are the matching tasks in your list:\n" + findList);
    }

}