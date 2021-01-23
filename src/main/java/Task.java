import java.util.LinkedHashMap;
import java.util.Map;

public class Task {

    protected LinkedHashMap<String,Boolean> taskList;

    public Task(){
        this.taskList = new LinkedHashMap<String,Boolean>();
    }

    public void addTask(String description){
        this.taskList.put(description,false);
    }

    public void showList() {
        if (this.taskList.size() == 0) {
            System.out.println("Your list is empty.");
        }
        else {
            System.out.println("Here are the tasks in your list:");
            int i = 1;
            while (i<=this.taskList.size()){
                for (Map.Entry<String, Boolean> taskList : this.taskList.entrySet()) {
                    if (taskList.getValue()) {
                        System.out.println(i + ". " + "[\u2713] " + taskList.getKey());
                    }
                    else {
                        System.out.println(i + ". " + "[\u0020] " + taskList.getKey());
                    }
                    i++;
                }
            }
        }
    }

    public void markAsDone(int number) {
        int i = 1;
        if (number > taskList.size()) {
            System.out.println("task does not exist");
        }
        else {
            while (i <= this.taskList.size()) {
                for (Map.Entry<String, Boolean> taskList : this.taskList.entrySet()) {
                    if (i == number) {
                        this.taskList.put(taskList.getKey(), true);
                        System.out.println("Nice! I've marked this task as done: ");
                        System.out.println("[\u2713] " + taskList.getKey());
                    }
                    i++;
                }
            }
        }
    }
}
