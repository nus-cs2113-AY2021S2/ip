package duke;

import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.Locale;
import java.util.Scanner;

public class TaskManager {

    private Task[] tasks = new Task[100];
    int taskCount = 0;

    public String getData() {
        StringBuilder data = new StringBuilder();
        for (int i = 0; i < taskCount; i++) {
            data.append(tasks[i].getData()).append(System.lineSeparator());
        }
        return data.toString();
    }

    public String listTask() {
        StringBuilder feedback = new StringBuilder();

        feedback.append("Here are the tasks in your list:" + System.lineSeparator());
        for (int i = 0; i < taskCount - 1; ++i) {
            feedback.append(String.format("%d: %s", (i + 1), tasks[i])).append(System.lineSeparator());
        }
        if (taskCount > 0) {
            feedback.append(String.format("%d: %s", (taskCount), tasks[taskCount - 1]));
        } else {
            feedback.append("Your task list is empty... ):");
        }

        return feedback.toString();
    }

    public String addTask(String taskType, String description) {
        try {
            checkValidInput(taskType, description);
        } catch (InvalidCommandException e) {
            return "OOPS!!! I'm sorry, but I don't know what that means :(";
        } catch (EmptyDescriptionException e) {
            return "OOPS!!! The description of a " + taskType + " cannot be empty.";
        }

        switch (taskType) {
        case "todo":
        case "t":
            tasks[taskCount] = new Todo(description);
            break;
        case "deadline":
        case "d": {
            String[] nameAndDate = parseDescription(description, " /by ");
            tasks[taskCount] = new Deadline(nameAndDate[0], nameAndDate[1]);
            break;
        }
        case "event":
        case "e": {
            String[] nameAndDate = parseDescription(description, " /at ");
            tasks[taskCount] = new Event(nameAndDate[0], nameAndDate[1]);
            break;
        }
        }

        ++taskCount;

        return "Got it. I've added this task:" + System.lineSeparator()
                + tasks[taskCount - 1] + System.lineSeparator()
                + "Now you have " + taskCount + " tasks in the list.";
    }

    private boolean isValidTaskType(String taskType) {
        if (taskType.equals("todo") || taskType.equals("deadline") || taskType.equals("event") ||
                taskType.equals("t") || taskType.equals("d") || taskType.equals("e")) {
            return true;
        }
        return false;
    }

    private void checkValidInput(String taskType, String description) throws InvalidCommandException, EmptyDescriptionException {
        if (!isValidTaskType(taskType)) {
            throw new InvalidCommandException();
        }
        if (description.isEmpty()) {
            throw new EmptyDescriptionException();
        }
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
            tasks[taskNum].setAsDone();
        } catch (IndexOutOfBoundsException e) {
            return "OOPS!!! Invalid task number. Try 1-" + taskCount;
        }
        return "Nice! I've marked this task as done:" + System.lineSeparator()
                + tasks[taskNum];
    }


    public void setData(Scanner scanner) {
        try {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] tokens = line.split(";", 3);
                String taskType = tokens[0].toLowerCase();
                boolean done = tokens[1] == "1";
                String taskName = tokens[2];

                addTask(taskType, taskName);
                if (done) {
                    doneTask(taskCount - 1);
                }
            }
        } catch (Exception e) {
            System.out.println("Error loading old data...");
            System.out.println("Data will be overridden...");
        }
    }
}
