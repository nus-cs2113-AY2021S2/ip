import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void addTask(String description) {
        Task newTask = new Task(description);
        tasks.add(newTask);
    }

    @Override
    public String toString() {
        StringBuilder outputString = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            outputString.append(i + 1);
            outputString.append(".");
            outputString.append(tasks.get(i).getStatusIcon());
            outputString.append(tasks.get(i).getDescription());
            outputString.append(System.lineSeparator());
        }
        if (outputString.length() == 0) {
            return "List is empty!";
        }
        return outputString.toString().trim();
    }

    public void finishTask(int taskNum) {
        tasks.get(taskNum).setDone();
    }

    public String getTask(int taskNum) {
        return tasks.get(taskNum).getStatusIcon() + tasks.get(taskNum).getDescription();
    }
}
