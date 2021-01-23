import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> listOfTask = new ArrayList<Task>();

    public void addTask(Task inputTask) {
        listOfTask.add(inputTask);
        System.out.print("added: ");
        System.out.println(inputTask.name);
    }

    public void printTasks() {
        for (int i = 1; i <= listOfTask.size(); i++) {
            System.out.print(i);
            System.out.print(". ");
            System.out.println(listOfTask.get(i - 1).name);
        }
    }

}