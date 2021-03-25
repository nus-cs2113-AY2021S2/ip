package duke;

import java.util.ArrayList;

/**
 * Stores the list of tasks in an ArrayList.
 * Performs operations on a list of task which includes
 * delete, done, add tasks, i.e.
 * todo, event, deadline, which signifies type of tasks to the list.
 */

public class TaskList {

    private static ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * List all the tasks saved into duke.txt through
     * the operations available,
     * i.e. todo, event, deadline, done, delete.
     */
    public static void listTasks() {
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.print(i + 1 + ".");
            System.out.println(tasks.get(i).toString());
        }
    }

    /**
     * Mark the task with a cross given index of the task in the list.
     *
     * @param userInput
     */
    public static void markTaskDone(String userInput) {
        int processedInput;
        processedInput = Integer.parseInt(userInput.replaceAll("[^0-9]", "")) - 1;
        tasks.get(processedInput).setDone();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(tasks.get(processedInput));
    }

    /**
     * Add ToDo task into the bottom of the list.
     *
     * @param userInput
     */
    public static void addTodoTask(String userInput) {
        System.out.println("Got it. I've added this task: ");
        Task newTask = new Todo(userInput.substring(5));
        tasks.add(newTask);
        System.out.println("  " + newTask.toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Add Deadline task into the bottom of the list.
     *
     * @param by The date/time inputted by the user for the Deadline task.
     * @param processedDeadlineInput The description of Deadline task.
     */
    public static void addDeadlineTask(String by, String processedDeadlineInput) {
        Task newTask = new Deadline(processedDeadlineInput, by);
        tasks.add(newTask);
        System.out.println(newTask.toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Add Event task into the bottom of the list.
     *
     * @param at The venue/date inputted by the user for Event task.
     * @param processedEventInput The description of Event task.
     */
    public static void addEventTask(String at, String processedEventInput) {
        Task newTask = new Event(processedEventInput, at);
        tasks.add(newTask);
        System.out.println(newTask.toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Find and print the list of tasks available in the tasks list
     * by finding words in each task object description.
     *
     * @param targetInput Keyword specified by the user.
     */
    public static void findTask(String targetInput) {
        int count = 0;
        for(Task t : tasks) {
            String[] mySplit = t.toString().split(" ");
            for(String s: mySplit) {
                if(s.equals(targetInput)) {
                    count += 1;
                    System.out.println(count + ". " + t);
                }
            }
        }

    }

    /**
     * Gets the description of all the tasks inputted by user
     * since the start of the program.
     *
     * @param i Index to loop through in list of tasks to write to file.
     * @return Description of each task.
     */
    public String getDescriptionAtIndex(int i) {
        return tasks.get(i).getDescription();
    }

    public int size() {
        return tasks.size();
    }

    /**
     * Add new tasks when reading duke.txt file
     * at the start of the program.
     *
     * @param newTask
     */
    public void addTask(Task newTask) {
        tasks.add(newTask);
    }

    public void removeTask(int i) {
        tasks.remove(i);
    }

    /**
     * Get task that user wants to delete.
     *
     * @param processedInput Index retrieved to delete a task.
     * @return task to be deleted by the user.
     */
    public String getProcessedInputAtIndex(int processedInput) {
        return String.valueOf(tasks.get(processedInput));
    }
}
