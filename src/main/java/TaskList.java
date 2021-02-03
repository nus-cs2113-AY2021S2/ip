import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void addTask(String description, Duke.inputCommand command) {
        Task newTask;

        switch (command) {
        case TODO:
            newTask = new ToDo(description);
            break;
        case EVENT:
            newTask = new Event(description);
            break;
        case DEADLINE:
            newTask = new Deadline(description);
            break;
        default:
            newTask = new Task(description);
            break;
        }
        tasks.add(newTask);
    }

    @Override
    public String toString() {
        StringBuilder outputString = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            outputString.append(i + 1);
            outputString.append(".");
            outputString.append(tasks.get(i).toString());
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
        return tasks.get(taskNum).toString();
    }

    // Gets the Last task of tasks array
    public String getTask() {
        return tasks.get(tasks.size() - 1).toString();
    }
}
