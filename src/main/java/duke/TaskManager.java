package duke;

import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;

public class TaskManager {

    private ArrayList<Task> tasks = new ArrayList<>();

    public String listTask() {
        StringBuilder feedback = new StringBuilder();

        feedback.append("Here are the tasks in your list:" + System.lineSeparator());
        for (int i = 0; i < tasks.size() - 1; ++i) {
            feedback.append(String.format("%d: %s", (i + 1), tasks.get(i))).append(System.lineSeparator());
        }
        feedback.append(String.format("%d: %s", (tasks.size()), tasks.get(tasks.size() - 1)));

        return feedback.toString();
    }

    public String addTask(String taskType, String description) throws InvalidCommandException, EmptyDescriptionException {

        if (!isValidTaskType(taskType)) {
            throw new InvalidCommandException();
        }
        if (description.isEmpty()) {
            throw new EmptyDescriptionException();
        }

        switch (taskType) {
        case "todo":
            tasks.add(new Todo(description));
            break;
        case "deadline": {
            String[] nameAndDate = parseDescription(description, " /by ");
            tasks.add(new Deadline(nameAndDate[0], nameAndDate[1]));
            break;
        }
        case "event": {
            String[] nameAndDate = parseDescription(description, " /at ");
            tasks.add(new Event(nameAndDate[0], nameAndDate[1]));
            break;
        }
        }

        return "Got it. I've added this task:" + System.lineSeparator()
                + tasks.get(tasks.size() - 1) + System.lineSeparator()
                + "Now you have " + tasks.size() + " tasks in the list.";
    }

    private boolean isValidTaskType (String taskType) {
        if (taskType.equals("todo") || taskType.equals("deadline") || taskType.equals("event")) {
            return true;
        }
        return false;
    }

    private String[] parseDescription(String description, String regex) {
        final String[] split = description.split(regex);
        if (split.length == 2) {
            return split;
        } else {
            return new String[]{split[0], ""};
        }
    }

    public String doneTask(int taskNum) {
        try {
            tasks.get(taskNum).setAsDone();
        } catch (IndexOutOfBoundsException e) {
            return "OOPS!!! Invalid task number. Try 1-" + tasks.size();
        }
        return "Nice! I've marked this task as done:" + System.lineSeparator()
                + tasks.get(taskNum);
    }
}
