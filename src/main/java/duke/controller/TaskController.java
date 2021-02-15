package duke.controller;

import duke.exceptions.DukeException;
import duke.exceptions.ErrorHandler;
import duke.tasks.*;
import java.util.ArrayList;

public class TaskController {

    static ArrayList<Task> tasks = new ArrayList<> ();
    static int tasksCount = 0 ;

    static final int TODOLENGTH = 4;
    static final int EVENTLENGTH = 5;
    static final int DEADLINELENGTH = 8;
    static final int DELETELENGTH = 6;


    public static void printTaskList() {
        System.out.println("Here are the tasks in your list: ");
        for (int i=0; i<tasks.size(); i++) {
            System.out.println("    " + (i+1) + ". " + tasks.get(i));
        }
    }

    public static void addDeadline(String userInput) throws DukeException {
        if (userInput.length() == DEADLINELENGTH ) {
            throw new DukeException(TaskType.DEADLINE);
        }
        String description, by;
        userInput = userInput.substring(DEADLINELENGTH+1).trim();
        if (userInput.contains("/")){
            int idx = userInput.indexOf('/');
            description =userInput.substring(0, idx);
            by = userInput.substring(idx+3).trim();
        }
        else {
            description =userInput;
            by = "";
        }

        Deadline d = new Deadline(description, by);
        tasks.add(d);
    }

    public static void addEvent(String userInput) throws DukeException {
        if (userInput.length() == EVENTLENGTH) {
            throw new DukeException(TaskType.EVENT);
        }
        String description, at;
        userInput = userInput.substring(EVENTLENGTH+1).trim();
        if (userInput.contains("/")){
            int idx = userInput.indexOf('/');
            description =userInput.substring(0, idx);
            at = userInput.substring(idx+3).trim();
        }
        else {
            description =userInput;
            at = "";
        }

        Event e = new Event(description, at);
        tasks.add(e);
    }

    public static void addTodo(String userInput) throws DukeException {
        if (userInput.length() == TODOLENGTH) {
            throw new DukeException(TaskType.TODO);
        }
        String description = userInput.substring(TODOLENGTH+1);
        Todo t = new Todo(description);
        tasks.add(t);
    }


    public static void addTask(String userInput) {
        try {
            if (userInput.toLowerCase().startsWith("deadline")){
                addDeadline(userInput);
            }
            else if (userInput.toLowerCase().startsWith("event")){
                addEvent(userInput);
            }
            else if (userInput.toLowerCase().startsWith("todo")){
                addTodo(userInput);
            }
            else {
                ErrorHandler.printErrorMsgInvalidInput();
                return;
            }
            System.out.println("    Got it. I've added this task: \n      " + tasks.get(tasksCount));
            tasksCount++;
            System.out.println("    Now you have " + tasksCount + " tasks in the list.");
        } catch (DukeException e) {
            e.printErrorTaskCannotBeEmpty();
        }
    }

    public static void markTaskDone(String task) {
        try {
            int idx = Integer.parseInt(String.valueOf(task.charAt(TODOLENGTH+1)));
            tasks.get(idx-1).markAsDone();
            System.out.println("------------------------------------------");
            System.out.println("    Nice! I've marked this task as done: ");
            System.out.println("    " + tasks.get(idx-1));
        } catch (NumberFormatException e) {
            System.out.println("OOPS!!! Please enter an integer after 'done'.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("OOPS!!! Please choose a valid task index.");
        }

    }

    public static void deleteTask(String task) {
        try {
            int idx = Integer.parseInt(String.valueOf(task.charAt(DELETELENGTH+1)));
            String taskDescription = tasks.get(idx-1).toString();
            tasks.remove(idx-1);
            tasksCount--;
            System.out.println("------------------------------------------");
            System.out.println("    Noted. I've removed this task: ");
            System.out.println("    " + taskDescription);
            System.out.println("    Now you have " + tasksCount + " tasks in the list.");
        } catch (NumberFormatException e) {
            System.out.println("OOPS!!! Please enter an integer after 'done'.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("OOPS!!! Please choose a valid task index.");
        }
    }

}
