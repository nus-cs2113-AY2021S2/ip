package duke.controller;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.toDo;

/**
 * Task list is a controller class that contains all methods such as
 * save, load, print, adding, deleting, mark as done and find by keyword or date.
 */
public class TaskList {

    /**
     * Creates task list object.
     */
    public TaskList() {}

    /**
     * Prints the task list containing task type, check box to be marked as done and the task description.
     * @param tasks Array list of tasks.
     */
    public void printTaskList(ArrayList<Task> tasks) {
        int number = 1;
        System.out.println("Here are the tings in yo list: ");
        for (Task task : tasks) {
            System.out.println(number + ". " + task.printDescription());
            number++;
        }
    }

    /**
     * Marks the task as done based on the user specified number.
     * If the user does not enter a task number, an error message will be displayed.
     * If the user keys in a task number not in the list index, an error message will be displayed.
     * @param tasks Array list of tasks.
     * @param input User input.
     */
    public void showDone(ArrayList<Task> tasks, String input) {
        int indexSpace = input.indexOf(" ");
        if (indexSpace == -1) {
            System.out.println("I see you forget how to type numbers yea... you donut! Type in your task number man!");
        } else {
            String numberString = input.substring(indexSpace + 1);
            int taskNumber = Integer.parseInt(numberString);
            try {
                if (taskNumber > tasks.size() || taskNumber == 0) {
                    throw new NullPointerException("Are you blind? There is no such task number you fool!");
                }
                tasks.get(taskNumber - 1).markAsDone();
                System.out.println("Awwww yeah! I've marked this task as done... brrrrrap brrrrrap: ");
                System.out.println(tasks.get(taskNumber - 1).printDescription());
            } catch (NullPointerException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Deletes task based on the task number that the user has indicated.
     * If the user does not enter a task number, an error message will be displayed.
     * If the user keys in a task number not in the list index, an error message will be displayed.
     * @param tasks Array list of tasks.
     * @param input User input.
     */
    public void deleteTask(ArrayList<Task> tasks, String input) {
        int indexSpace = input.indexOf(" ");
        if (indexSpace == -1) {
            System.out.println("I see you forget how to type numbers yea... you donut! Type in your task number man!");
        } else {
            String numberString = input.substring(indexSpace + 1);
            int taskNumber = Integer.parseInt(numberString);
            try {
                if (taskNumber > tasks.size() || taskNumber == 0) {
                    throw new NullPointerException("Are you blind? There is no such task number you fool!");
                }
                System.out.println("Awwww yeah! I've deleted this task like a beast: ");
                System.out.println(tasks.get(taskNumber - 1).printDescription());
                tasks.remove(taskNumber - 1);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } catch (NullPointerException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Adds a todo task into the list.
     * Displays a confirmation message with the task details and current number of tasks in the list.
     * @param tasks Array list of tasks.
     * @param description Task description.
     */
    public void printToDo(ArrayList<Task> tasks, String description) {
        Task todo = new toDo(description);
        tasks.add(todo);
        System.out.println("Ayy I got you my brother. I've added this ting: ");
        System.out.println(todo.printDescription());
        System.out.println("Dayuum son! You have " + tasks.size() + " mad tings in the list.");
    }

    /**
     * Adds a deadline task into the list.
     * Displays a confirmation message with the task details and current number of tasks in the list.
     * @param tasks Array list of tasks.
     * @param description Task description.
     * @param date Date and time in which task is due.
     */
    public void printDeadline(ArrayList<Task> tasks, String description, String date) {
        try {
            Task deadline = new Deadline(description, date);
            tasks.add(deadline);
            System.out.println("Ayy I got you my brother. I've added this ting: ");
            System.out.println(deadline.printDescription());
            System.out.println("Jeeeeeeez! You have " + tasks.size() + " mad tings in the list.");
        } catch (DateTimeParseException e) {
            System.out.println("Oi you gotta key input in specified format dd-MM-yyyy HH:mm");
        }
    }

    /**
     * Adds an event task into the list.
     * Displays a confirmation message with the task details and current number of tasks in the list.
     * @param tasks Array list of tasks.
     * @param description Task description.
     * @param date Date and time in which task is due.
     */
    public void printEvent(ArrayList<Task> tasks, String description, String date) {
        try {
            Task event = new Event(description, date);
            tasks.add(event);
            System.out.println("Ayy I got you my brother. I've added this ting: ");
            System.out.println(event.printDescription());
            System.out.println("I feer! You have " + tasks.size() + " mad tings in the list.");
        } catch (DateTimeParseException e) {
            System.out.println("Oi you gotta key input in specified format dd-MM-yyyy HH:mm!");
        }
    }

    /**
     * Finds and display tasks containing the keyword indicated by user.
     * If the keyword not found in any tasks, returns an empty search.
     * @param tasks Array list of tasks.
     * @param keyword Keyword used for search.
     */
    public void findTask(ArrayList<Task> tasks, String keyword) {
        System.out.print("Here are the search results:\n");
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                System.out.println(task.printDescription());
            }
        }
    }

    /**
     * Finds and display tasks containing the date indicated by user.
     * If the date is not found in any tasks, returns an empty search.
     * @param tasks Array list of tasks.
     * @param date Date used for search.
     */
    public void findByDate(ArrayList<Task> tasks, LocalDate date) {
        System.out.print("Here are the search results:\n");
        for (Task task : tasks) {
            if (task instanceof Deadline && ((Deadline) task).getDateTime().toLocalDate().isEqual(date)) {
                System.out.println(task.printDescription());
            } else if (task instanceof Event && ((Event) task).getDateTime().toLocalDate().isEqual(date)) {
                System.out.println(task.printDescription());
            }
        }
    }
}