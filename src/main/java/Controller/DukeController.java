package controller;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Controller class that contains all methods involving adding, deleting, marking as done, find, save and load.
 */
public class DukeController {
    private Parser parse = new Parser();
    public DukeController() {};

    /**
     * printTask prints the task description as well as the total number of tasks.
     * @param tasks task list.
     * @param count task specified index.
     */
    public void printTask(ArrayList<Task> tasks, int count) {
        System.out.println("--------------------------------------------");
        System.out.println("Got it. I've added this task: ");
        System.out.println(tasks.get(count).getPrintedLine());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("--------------------------------------------");
    }

    /**
     * Prints all the tasks in the task list.
     * @param tasks task list.
     */
    public void printList(ArrayList<Task> tasks) {
        int count = 1;
        System.out.println("--------------------------------------------");
        System.out.println("Here are the tasks in your list:");
        for (Task task: tasks) {
            System.out.println(count+ ". " + task.getPrintedLine());
            count++;
        }
        System.out.println("--------------------------------------------");
    }

    /**
     * Mark a task as done base on index.
     * @param tasks task list.
     * @param input string input by user.
     */
    public void printDone(ArrayList<Task> tasks, String input) {
        int numerate;
        numerate = parse.charNumber(input);
        try {
            if (numerate > tasks.size() || numerate == 0) {
                throw new NullPointerException("There is no such task in your list");
            }
            System.out.println("--------------------------------------------");
            tasks.get(numerate-1).markAsDone();
            System.out.println(tasks.get(numerate-1).getPrintedLine());
            System.out.println("--------------------------------------------");
        }
        catch (NullPointerException e) {
            System.out.println(e.getMessage());
            System.out.println("--------------------------------------------");
        }
    }

    /**
     * Adds a todo task in the list and prints the task description out.
     * @param tasks task list.
     * @param input String input by user.
     * @param strings Substrings of string input by user.
     * @param count1 Specified Index.
     * @return True if error is found.
     */
    public boolean printTodo(ArrayList<Task> tasks, String input, String[] strings, int count1) {
        boolean foundError = false;
        try{
            if (input.trim().equals("todo") ) {
                throw new DukeException("todo");
            }
            Task task = new ToDo(strings[1]);
            tasks.add(task);
            printTask(tasks, count1);
            return foundError;
        }
        catch(DukeException e) {
            System.out.println(e.getMessage());
            System.out.println("--------------------------------------------");
            return foundError = true;
        }
    }

    /**
     * Adds a Deadline task in the list and prints the task description out.
     * @param tasks task list.
     * @param input String input by user.
     * @param strings Substrings of string input by user.
     * @param count1 Specified Index.
     * @return True if error is found.
     */
    public boolean printDeadline(ArrayList<Task> tasks, String input, String[] strings, int count1) {
        boolean foundError = false;
        try{
            if (input.trim().equals("deadline")) {
                throw new DukeException("deadline");
            }
            Task task = new Deadline(strings[1], strings[2]);
            tasks.add(task);
            printTask(tasks, count1);
            return foundError;
        }
        catch (NullPointerException e) {
            System.out.println("Please key in using this format: deadline <description> /by <date and time>");
            System.out.println("--------------------------------------------");
            return foundError = true;
        }
        catch (DukeException e) {
            System.out.println(e.getMessage());
            System.out.println("--------------------------------------------");
            return foundError = true;
        }
        catch (DateTimeParseException e) {
            System.out.println("Please key input within specified format yyyy-MM-dd HH:mm");
            System.out.println("--------------------------------------------");
            return foundError = true;
        }
    }

    /**
     * Adds a Event task in the list and prints the task description out.
     * @param tasks task list.
     * @param input String input by user.
     * @param strings Substrings of string input by user.
     * @param count1 Specified Index.
     * @return True if error is found.
     */
    public boolean printEvent(ArrayList<Task> tasks, String input, String[] strings, int count1) {
        boolean foundError = false;
        try{
            if (input.trim().equals("event")) {
                throw new DukeException("event");
            }
            Task task = new Event(strings[1], strings[2]);
            tasks.add(task);
            printTask(tasks, count1);
            return foundError;
        }
        catch (NullPointerException e) {
            System.out.println("Please key in using this format: event <description> /at <date and time>");
            System.out.println("--------------------------------------------");
            return foundError = true;
        }
        catch (DukeException e) {
            System.out.println(e.getMessage());
            System.out.println("--------------------------------------------");
            return foundError = true;
        }
        catch (DateTimeParseException e) {
            System.out.println("Please key input within specified format yyyy-MM-dd HH:mm");
            System.out.println("--------------------------------------------");
            return foundError = true;
        }
    }

    /**
     * Print error message if user did not call any method.
     * @param input String input by user.
     */
    public void printUnidentified(String input) {
        try {
            if (!input.contains("deadline") || !input.contains("event") || !input.contains("todo")) {
                throw new DukeException();
            }
        }
        catch (DukeException e) {
            System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
            System.out.println("--------------------------------------------");
        }
    }

    /**
     * Delete task base on index.
     * @param tasks Task list.
     * @param input String input by user.
     */
    public void deleteTask(ArrayList<Task> tasks, String input){
        int numerate;
        numerate = parse.charNumber(input);
        try {
            if (numerate > tasks.size() || numerate == 0) {
                throw new NullPointerException("There is no such task in your list");
            }
            System.out.println("--------------------------------------------");
            System.out.println("Noted. I've removed this task: ");
            System.out.println(tasks.get(numerate-1).getPrintedLine());
            tasks.remove(numerate-1);
            System.out.println("Now you have " + tasks.size() + " tasks input the list.");
            System.out.println("--------------------------------------------");

        }
        catch (NullPointerException e) {
            System.out.println(e.getMessage());
            System.out.println("--------------------------------------------");
        }
    }

    /**
     * Find all task that is due by a specific date.
     * @param tasks Task list.
     * @param date Date input by user.
     */
    public void findbyDate(ArrayList<Task> tasks, LocalDate date) {
        boolean found = false;
        for (Task task : tasks) {
            if (task instanceof Deadline && ((Deadline) task).getDatetime().toLocalDate().isEqual(date)) {
                found = true;
                System.out.println(((Deadline) task).getPrintedLine());
            }
            else if (task instanceof Event && ((Event) task).getDatetime().toLocalDate().isEqual(date)) {
                found = true;
                System.out.println(((Event) task).getPrintedLine());
            }
        }
        System.out.println("--------------------------------------------");
        if (!found) {
            System.out.println("No activity on that date specified");
            System.out.println("--------------------------------------------");
        }
    }

    /**
     * Find all task by searching for a keyword.
     * @param tasks Task list.
     * @param strings String input by user.
     */
    public void findbyDescription(ArrayList<Task> tasks, String[] strings) {
        boolean found = false;
        for (Task task : tasks) {
            if (task.getDescription().contains(strings[1])) {
                System.out.println(task.getPrintedLine());
                found = true;
            }
        }
        System.out.println("--------------------------------------------");

        if (!found) {
            System.out.println("No activity found!");
            System.out.println("--------------------------------------------");
        }
    }
}
