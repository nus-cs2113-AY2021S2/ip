import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public Command addTask(String description, Command command) {
        Task newTask;
        String[] stringArray;
        try {
            validateDescription(description, command);
        } catch (EmptyDescriptionException e) {
            System.out.println("The description cannot be empty!");
            return Command.ERROR;
        }
        switch (command) {
        case TODO:
            newTask = new ToDo(description);
            break;
        case EVENT:
            stringArray = description.split("/at");
            newTask = new Event(stringArray[0], stringArray[1].trim());
            break;
        case DEADLINE:
            stringArray = description.split("/by");
            newTask = new Deadline(stringArray[0], stringArray[1].trim());
            break;
        default:
            newTask = new Task(description);
            break;
        }
        tasks.add(newTask);
        return command;
    }

    public void validateDescription(String description, Command command) throws EmptyDescriptionException {
        if (description.equals("")) {
            throw new EmptyDescriptionException();
        }
        switch (command) {
        case DONE:
            if (description.replace("done ", "").equals("")) {
                throw new EmptyDescriptionException();
            }
            break;
        case TODO:
            break;
        case DEADLINE:
            break;
        case EVENT:
            break;
        }
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
    public String getLastTask() {
        return tasks.get(tasks.size() - 1).toString();
    }
}
