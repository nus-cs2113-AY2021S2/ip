import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.time.LocalDate;
public class TaskList {
    public ArrayList<Task> tasks;


    public TaskList(ArrayList<Task> tasks){
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = null;
    }


    public int getSize(){
        return tasks.size();
    }


    public void addTask(Task task, Ui ui){
        tasks.add(task);
        ui.addResponse("Got it. I've added this task:" + "\n" + " " + task.toString() + "\n"
                + "Now you have " + getSize() + " tasks in the list");
    }


    public void deleteTask(int taskIndex, Ui ui){
        Task deleted = tasks.get(taskIndex - 1);
        tasks.remove(taskIndex - 1);
        ui.addResponse("Noted. I've removed this task: " + "\n" +
                deleted.toString() + "\n" + "Now you have " + getSize() + " tasks in the list.");
    }


    public void doneTask(int taskIndex, Ui ui){
        tasks.get(taskIndex - 1).markAsDone();
        ui.addResponse("Congrats! You are done with task " + taskIndex);
    }


    public void showTaskList(Ui ui){
        String listStrings = "";
        for (int i = 0; i < getSize(); i++) {
            Integer listNum = i + 1;
            listStrings += listNum.toString() + "." + tasks.get(i).toString() + "\n";
        }
        ui.addResponse("Here are the tasks in your list:" + "\n" + listStrings);
    }

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