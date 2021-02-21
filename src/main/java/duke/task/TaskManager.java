package duke.task;

import duke.exception.EmptyDescriptionException;
import duke.parser.CommandParser;

import java.util.ArrayList;

public class TaskManager {

    private ArrayList<Task> tasks = new ArrayList<>();
    private CommandParser parser = new CommandParser();

    public ArrayList<Task> getData() {
        return tasks;
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


    public void setData(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public String addTodo(String description) throws EmptyDescriptionException {

        if(description.isEmpty()){
            throw new EmptyDescriptionException();
        }

        Todo todo = new Todo(description);
        tasks.add(todo);

        return addTaskSuccessMessage(todo);
    }

    public String addDeadline(String description) throws EmptyDescriptionException {

        if(description.isEmpty()){
            throw new EmptyDescriptionException();
        }

        String[] parsedDescription = parser.parseDeadline(description);
        Deadline deadline = new Deadline(parsedDescription[0], parsedDescription[1]);
        tasks.add(deadline);

        return addTaskSuccessMessage(deadline);
    }

    public String addEvent(String description) throws EmptyDescriptionException {

        if(description.isEmpty()){
            throw new EmptyDescriptionException();
        }

        String[] parsedDescription = parser.parseEvent(description);
        Event event = new Event(parsedDescription[0], parsedDescription[1]);
        tasks.add(event);

        return addTaskSuccessMessage(event);
    }

    private String addTaskSuccessMessage(Task task) {
        return "Got it. I've added this task:" + System.lineSeparator()
                + task + System.lineSeparator()
                + "Now you have " + tasks.size() + " tasks in the list.";
    }
}
