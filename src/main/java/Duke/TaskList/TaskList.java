package Duke.TaskList;

import Duke.Constant.SpaceAndLine;
import Duke.Task.Task;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * TaskList contains the list of tasks and provides relevant methods to alter them.
 */
public class TaskList {
    private static ArrayList<Task> tasks = new ArrayList<Task>();
    private static ArrayList<Task> matchedTasks = new ArrayList<Task>();
    private static ArrayList<Task> sameDateTasks = new ArrayList<Task>();

    public TaskList() {

    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Gets the the list of tasks.
     *
     * @return the list of tasks
     */
    public static ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Gets the the matched tasks after a find command.
     *
     * @return the list of matched tasks
     */
    public static ArrayList<Task> getMatchedTasks() {
        return matchedTasks;
    }

    /**
     * Gets a task specified by the index received.
     *
     * @return the task specified by the index
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Adds a task to the task list.
     *
     * @param task the task to be added
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Gets number of task(s) there are in the list.
     *
     * @return number of task(s)
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Marks a task as done.
     *
     * @param index index of the task to be marked as done
     */
    public void setTaskToBeDone(int index) {
        tasks.get(index).setAsDone();
    }

    /**
     * Removes a task indicated by its index.
     *
     * @param index index of the task to be deleted
     */
    public void deleteTask(int index) {
        tasks.remove(index);
    }

    /**
     * Helps a ui to display all the task(s) in the list.
     */
    public static void displayList() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(SpaceAndLine.SHORT_SPACE + (i + 1) + "." + tasks.get(i));
        }
    }

    /**
     * Formats all tasks to be stored in Duke.txt
     *
     * @return content to be stored in Duke.txt
     */
    public String toPrintedFormat() {
        String text = "";
        for (int i = 0; i < tasks.size(); i++) {
            text = text + getTask(i).toPrintedFormat() + "\n";
        }
        return text;
    }

    /**
     * Finds all task(s) containing the keyword given
     * and add it / them to the matched tasks
     *
     * @param keyword keyword given to find all tasks containing it
     */
    public void findKeyword(String keyword) {
        for (int i = 0; i < tasks.size(); i++) {
            String description = getTask(i).getDescription();
            if (keywordMatched(keyword, description)) {
                matchedTasks.add(getTask(i));
            }
        }
    }

    /**
     * Decides whether a specific description contains the keyword
     *
     * @param keyword keyword given to find all tasks containing it
     * @param description description of a particular task
     * @return whether a specific description contains the keyword
     */
    private boolean keywordMatched(String keyword, String description) {
        if (description.contains(keyword)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Helps a ui to display all the matched task(s) in the list.
     */
    public void DisplayMatchedTasks() {
        for (int i = 0; i < matchedTasks.size(); i++) {
            System.out.println(SpaceAndLine.SHORT_SPACE + (i + 1) + "." + matchedTasks.get(i));
        }
    }

    /**
     * Finds all task(s) containing the date given
     * and adds it / them to the tasks of the same date
     *
     * @param date date given to find all tasks which occur on the same date
     */
    public void findDate(LocalDate date) {
        for (int i = 0; i < tasks.size(); i++) {
            String dateString = getTask(i).getDate();
            LocalDate convertedDate = LocalDate.parse(dateString);
            if (date.equals(convertedDate)) {
                sameDateTasks.add(getTask(i));
            }
        }
    }

    /**
     * Helps a ui to display all the task(s)
     * occurring on a particular date in the list.
     */
    public void DisplaySameDateTasks() {
        for (int i = 0; i < sameDateTasks.size(); i++) {
            System.out.println(SpaceAndLine.SHORT_SPACE + (i + 1) + "." + sameDateTasks.get(i));
        }
    }
}
