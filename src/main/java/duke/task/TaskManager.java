package duke.task;

import duke.exception.EmptyDescriptionException;
import duke.parser.CommandParser;

import java.util.ArrayList;

/**
 * Stores and handles the list of tasks in the program
 */
public class TaskManager {

    private ArrayList<Task> tasks;
    private CommandParser parser = new CommandParser();

    public ArrayList<Task> getData() {
        return tasks;
    }

    /**
     * Gets all the tasks' description.
     * @return tasks' information
     */
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

    /**
     * Marks the task as done in the task list.
     * @param taskNum 1-based index of the task that has been done.
     * @return Feedback of whether the process has been successful.
     */
    public String doneTask(int taskNum) {
        try {
            tasks.get(taskNum).setAsDone();
        } catch (IndexOutOfBoundsException e) {
            return "OOPS!!! Invalid task number. Try 1-" + tasks.size();
        }
        return "Nice! I've marked this task as done:" + System.lineSeparator()
                + tasks.get(taskNum);
    }

    /**
     * Removes the task from the task list.
     * @param taskNumber 1-based index of the task to be removed.
     * @return Feedback of whether the process has been successful.
     */
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

    /**
     * Loads the tasks into the task list.
     * @param tasks The task list to be loaded.
     */
    public void setData(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Add todo task into the task list.
     * @param description Name of the todo task.
     * @return Feedback of whether the process has been successful.
     * @throws EmptyDescriptionException On missing todo description.
     */
    public String addTodo(String description) throws EmptyDescriptionException {

        if(description.isEmpty()){
            throw new EmptyDescriptionException();
        }

        Todo todo = new Todo(description);
        tasks.add(todo);

        return addTaskSuccessMessage(todo);
    }

    /**
     * Add deadline task into the task list.
     * @param description Name of the deadline task.
     * @return Feedback of whether the process has been successful.
     * @throws EmptyDescriptionException On missing deadline description.
     */
    public String addDeadline(String description) throws EmptyDescriptionException {

        if(description.isEmpty()){
            throw new EmptyDescriptionException();
        }

        String[] parsedDescription = parser.parseDeadline(description);
        Deadline deadline = new Deadline(parsedDescription[0], parsedDescription[1]);
        tasks.add(deadline);

        return addTaskSuccessMessage(deadline);
    }

    /**
     * Add event task into the task list.
     * @param description Name of the event task.
     * @return Feedback of whether the process has been successful.
     * @throws EmptyDescriptionException On missing event description.
     */
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
