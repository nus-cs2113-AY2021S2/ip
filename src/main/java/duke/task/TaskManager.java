package duke.task;

import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidCommandException;
import duke.exception.LoadDataException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;
import java.util.Scanner;

public class TaskManager {

    private final ArrayList<Task> tasks = new ArrayList<>();

    public String getData() {
        StringBuilder data = new StringBuilder();
        for (Task task : tasks) {
            data.append(task.getData()).append(System.lineSeparator());
        }
        return data.toString();
    }

    public String listTask() {
        StringBuilder feedback = new StringBuilder();

        feedback.append("Here are the tasks in your list:").append(System.lineSeparator());
        for (int i = 0; i < tasks.size() - 1; ++i) {
            feedback.append(String.format("%d: %s", (i + 1), tasks.get(i))).append(System.lineSeparator());
        }
        if (tasks.size() > 0) {
            feedback.append(String.format("%d: %s", (tasks.size()), tasks.get(tasks.size() - 1)));
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
            tasks.add(new Todo(description));
            break;
        case "deadline":
        case "d": {
            String[] nameAndDate = parseDescription(description, " /by ");
            tasks.add(new Deadline(nameAndDate[0], nameAndDate[1]));
            break;
        }
        case "event":
        case "e": {
            String[] nameAndDate = parseDescription(description, " /at ");
            tasks.add(new Event(nameAndDate[0], nameAndDate[1]));
            break;
        }
        }

        return "Got it. I've added this task:" + System.lineSeparator()
                + tasks.get(tasks.size() - 1) + System.lineSeparator()
                + "Now you have " + tasks.size() + " tasks in the list.";
    }

    private boolean isValidTaskType(String taskType) {
        return taskType.equals("todo") || taskType.equals("deadline") || taskType.equals("event") ||
                taskType.equals("t") || taskType.equals("d") || taskType.equals("e");
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
            tasks.get(taskNum).setAsDone();
        } catch (IndexOutOfBoundsException e) {
            return "OOPS!!! Invalid task number. Try 1-" + tasks.size();
        }
        return "Nice! I've marked this task as done:" + System.lineSeparator()
                + tasks.get(taskNum);
    }

    public String deleteTask(int taskNumber) {
        String task = tasks.get(taskNumber).toString();
        try {
            tasks.remove(taskNumber);
        } catch (IndexOutOfBoundsException e) {
            return "OOPS!!! Invalid task number. Try 1-" + tasks.size();
        }
        return "Noted. I've removed this task:" + System.lineSeparator() +
                task + System.lineSeparator() +
                "Now you have " + tasks.size() + " tasks in the list.";
    }


    public void setData(Scanner scanner) {
        if(!scanner.hasNext()){
            return;
        }

        try {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] parsedLine = line.split(";", 3);
                char taskType = parsedLine[0].charAt(0);
                boolean taskIsDone = parsedLine[1].equals("1");
                String taskDescription = parsedLine[2];
                switch (taskType) {
                case 'T':
                    tasks.add(new Todo(taskDescription));
                    break;
                case 'D': {
                    String[] nameAndDate = taskDescription.split(";", 2);
                    tasks.add(new Deadline(nameAndDate[0], nameAndDate[1]));
                    break;
                }
                case 'E': {
                    String[] nameAndDate = taskDescription.split(";", 2);
                    tasks.add(new Event(nameAndDate[0], nameAndDate[1]));
                    break;
                }
                default:
                    throw new LoadDataException();
                }

                if(taskIsDone){
                    doneTask(tasks.size());
                }
            }
            System.out.println("Data loaded sucessfully...");
        } catch (Exception e) {
            tasks.clear();
            System.out.println("Error loading old data...");
            System.out.println("Data will be overridden...");
        }
    }

}
