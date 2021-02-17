package duke.controller;

import duke.exceptions.DukeException;
import duke.exceptions.ErrorHandler;
import duke.tasks.*;

import java.io.*;
import java.util.ArrayList;


public class TaskController {

    static ArrayList<Task> tasks = new ArrayList<> ();
    static int tasksCount = 0 ;

    static final int TODOLENGTH = 4;
    static final int EVENTLENGTH = 5;
    static final int DEADLINELENGTH = 8;
    static final int DELETELENGTH = 6;

    public static Task processFile(String line) {
        Task t = null;
        int idx;
        String description, at , by;

        if (line.charAt(1) == 'T') {
            t = new Todo(line.substring(7));
        } else if (line.charAt(1) == 'E') {
            idx = line.indexOf('(');
            description = line.substring(7, idx);
            at = line.substring(idx+3, line.length()-1);
            t = new Event(description, at);
        } else if (line.charAt(1) == 'D') {
            idx = line.indexOf('(');
            description = line.substring(7, idx);
            by = line.substring(idx+3, line.length()-1);
            t = new Deadline(description, by);
        }
        // check if task isDone
        if (line.contains("\u2713")) {
            t.markAsDone();
        }
        return t;
    }
    public static void readFile() {
        try {
            FileReader reader = new FileReader("Duke.txt");
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Task t = processFile(line);
                tasks.add(t);
                tasksCount++;
            }

        } catch (FileNotFoundException e) {
            // do nothing start with an empty file (empty taskArray)
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveFile() {
        try {
            FileWriter writer = new FileWriter("Duke.txt", false);
            for (Task t : tasks) {
                String task = t.toString() + "\n";
                writer.write(task);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
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
