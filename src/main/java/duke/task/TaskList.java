package duke.task;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static java.util.stream.Collectors.toList;

/**
 * Class that stores all the tasks in a list.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Default constructor. Creates a new arraylist of task.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor. Loads an existing arraylist of task.
     * @param tasks an arraylist of task
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * @return array list of tasks
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Gets a task by their task number
     * @param taskIndex the task number from 1 to maximum number of tasks
     * @return the task associated with the task number
     */
    public Task getTaskByIndex(int taskIndex) {
        return tasks.get(taskIndex);
    }

    /**
     * @param task task to be added
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes task from the task list by their task number.
     * @param taskIndex the task number of the task to be deleted
     * @return the deleted task
     */
    public Task deleteTaskByIndex(int taskIndex) {
        return tasks.remove(taskIndex);
    }

    /**
     *
     * @param task the task to get the task number from
     * @return task number from 1 to maximum number of tasks
     */
    public int getTaskNumber(Task task) {
        return tasks.indexOf(task) + 1;
    }

    /**
     * Takes in a keyword and returns an array list of tasks that match the keyword.
     * @param keyword the string to be used for matching
     * @return an array list of tasks
     */
    public ArrayList<Task> findTasksByKeyword(String keyword) {
        ArrayList<Task> matches = (ArrayList<Task>) getTasks().stream()
                .filter((t) -> t.getDescription().contains(keyword))
                .collect(toList());
        return matches;
    }

    /**
     * Takes in a date and returns an array list of tasks to be completed on the specified date.
     * @param date the LocalDateTime object with date to be used for matching
     * @return an array list of tasks
     */
    public ArrayList<Task> findTasksByDate(LocalDateTime date) {
        ArrayList<Task> matches = new ArrayList<>();
        // Add matching deadlines
        matches.addAll(getTasks().stream()
                .filter(t -> t instanceof Deadline)
                .map(t -> (Deadline) t)
                .filter(d -> d.getDeadline().toLocalDate().equals(date.toLocalDate()))
                .collect(toList()));
        // Add matching events
        matches.addAll(getTasks().stream()
                .filter(t -> t instanceof Event)
                .map(t -> (Event) t)
                .filter(e -> e.getDatetime().toLocalDate().equals(date.toLocalDate()))
                .collect(toList()));
        return matches;
    }
}
