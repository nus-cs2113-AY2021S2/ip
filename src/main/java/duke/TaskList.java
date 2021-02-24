package duke;

import java.util.ArrayList;

/**
 * A class containing the methods necessary to perform task-related functions in the Duke program
 * and contains the objects for storing the task information
 */
public class TaskList {
    public static ArrayList<Task> tasks = new ArrayList<>();
    public static int maxTaskIndex = 0;

    /**
     * Prints tasks that match the keyword that the user wishes to search for
     * Along with their Task index in the Task List
     *
     * @param input the user input from Duke
     */
    static void findTasks(String input) {
        String keyword = Parser.getTaskToFind(input);
        int currentTaskIndex;
        for (int i = 0; i < maxTaskIndex; i++) {
            currentTaskIndex = i + 1;
            if (tasks.get(i).description.contains(keyword)) {
                System.out.println(currentTaskIndex + "." + tasks.get(i).toString());
            }
        }
    }

    /**
     * Prints all tasks in the Task List
     * Along with their Task index
     */
    static void enumerateTasks() {
        int currentTaskIndex;
        for (int i = 0; i < maxTaskIndex; i++) {
            currentTaskIndex = i + 1;
            System.out.println(currentTaskIndex + "." + tasks.get(i).toString());
        }
    }

    /**
     * Deletes a task from the Task List
     * by obtaining their Task index from the input
     *
     * @param input the user input from Duke
     */
    static void deleteTask(String input) {
        int taskNumberToDelete = (Integer.parseInt(input.substring(Parser.DELETE_START).strip()) - 1);
        Ui.deletedTaskMessage(taskNumberToDelete);
        tasks.remove(taskNumberToDelete);
    }

    /**
     * Marks a task from the Task List as Done
     * by obtaining their Task index from the input
     * and prints out the Completed Task Message from Ui.
     *
     * @param input the user input from Duke
     */
    static void markTaskAsDone(String input) {
        int completedTaskIndex = Parser.getCompletedTaskIndex(input);
        tasks.get(completedTaskIndex).markAsDone();
        Ui.completedTaskMessage(completedTaskIndex);
    }

    /**
     * Adds a new Deadline to the Task List
     * Parses the deadline time by searching for 'by' in the input string
     * and adds both the description of the task and its corresponding deadline time
     * to the Task List by constructing a new Deadline object.
     *
     * @param input the user input from Duke
     */
    public static void addNewDeadline(String input) {
        tasks.add(new Deadline(input.substring(Parser.DEADLINE_START, Parser.getTimePosition(input)), Parser.getTime(input)));
    }

    /**
     * Adds a new Event to the Task List
     * Parses the event duration by searching for 'at' in the input string
     * and adds both the description of the task and its corresponding event time
     * to the Task List by constructing a new Event object.
     *
     * @param input the user input from Duke
     */
    public static void addNewEvent(String input) {
        tasks.add(new Event(input.substring(Parser.EVENT_START, Parser.getTimePosition(input)), Parser.getTime(input)));
    }

    /**
     * Adds a new Todo to the Task List
     * There is no time duration involved for a todo object
     * and adds the description of the task
     * to the Task List by constructing a new Todo object.
     *
     * @param input the user input from Duke
     */
    public static void addNewTodo(String input) {
        tasks.add(new ToDo(input.substring(Parser.TODO_START)));
    }

    public static void incrementTasks() {
        maxTaskIndex++;
    }

    static void decrementTasks() {
        maxTaskIndex--;
    }
}
