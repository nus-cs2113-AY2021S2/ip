package Controller;
import Tasks.*;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.util.ArrayList;

public class DukeController {
    private Parser parse = new Parser();
    public DukeController() {};

    public void printTask(ArrayList<Task> tasks, int count) {
        System.out.println("--------------------------------------------");
        System.out.println("Got it. I've added this task: ");
        System.out.println(tasks.get(count).getPrintedLine());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("--------------------------------------------");
    }

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

    public void printDone(ArrayList<Task> tasks, String in) {
        int numerate;
        numerate = parse.charNumber(in);
        try {
            if (numerate > tasks.size() || numerate == 0) {
                throw new NullPointerException("There is no such event in your list");
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

    public boolean printTodo(ArrayList<Task> tasks, String in, String[] strings, int count1) {
        boolean Error = false;
        try{
            if (in.trim().equals("todo") ) {
                throw new DukeException("todo");
            }
            Task task = new ToDo(strings[0]);
            tasks.add(task);
            printTask(tasks, count1);
            return Error;
        }
        catch(DukeException e) {
            System.out.println(e.getMessage());
            System.out.println("--------------------------------------------");
            return Error = true;
        }
    }

    public boolean printDeadline(ArrayList<Task> tasks, String in, String[] strings, int count1) {
        boolean Error = false;
        try{
            if (in.trim().equals("deadline")) {
                throw new DukeException("deadline");
            }
            Task task = new Deadline(strings[0], strings[1]);
            tasks.add(task);
            printTask(tasks, count1);
            return Error;
        }
        catch (DukeException e) {
            System.out.println(e.getMessage());
            System.out.println("--------------------------------------------");
            return Error = true;
        }
        catch (DateTimeParseException e) {
            System.out.println("Please key in within specified format yyyy-MM-dd HH:mm");
            System.out.println("--------------------------------------------");
            return Error = true;
        }
    }

    public boolean printEvent(ArrayList<Task> tasks, String in, String[] strings, int count1) {
        boolean Error = false;
        try{
            if (in.trim().equals("event")) {
                throw new DukeException("event");
            }
            Task task = new Event(strings[0], strings[1]);
            tasks.add(task);
            printTask(tasks, count1);
            return Error;
        }
        catch (DukeException e) {
            System.out.println(e.getMessage());
            System.out.println("--------------------------------------------");
            return Error = true;
        }
        catch (DateTimeParseException e) {
            System.out.println("Please key in within specified format yyyy-MM-dd HH:mm");
            System.out.println("--------------------------------------------");
            return Error = true;
        }
    }

    public void printDK(String in) {
        try {
            if (!in.contains("deadline") || !in.contains("event") || !in.contains("todo")) {
                throw new DukeException();
            }
        }
        catch (DukeException e) {
            System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
            System.out.println("--------------------------------------------");
        }
    }

    public void deleteTask(ArrayList<Task> tasks, String in){
        int numerate;
        numerate = parse.charNumber(in);
        try {
            if (numerate > tasks.size() || numerate == 0) {
                throw new NullPointerException("There is no such event in your list");
            }
            System.out.println("--------------------------------------------");
            System.out.println("Noted. I've removed this task: ");
            System.out.println(tasks.get(numerate-1).getPrintedLine());
            tasks.remove(numerate-1);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            System.out.println("--------------------------------------------");

        }
        catch (NullPointerException e) {
            System.out.println(e.getMessage());
            System.out.println("--------------------------------------------");
        }
    }

    public void findbyDate(ArrayList<Task> tasks, LocalDate date) {
        boolean found = false;
        for (Task task : tasks) {
            if (task instanceof Deadline) {

                if (((Deadline) task).getDatetime().toLocalDate().isEqual(date)) {
                    found = true;
                    System.out.println(((Deadline) task).getPrintedLine());
                }
            }
            else if (task instanceof Event) {
                if (((Event) task).getDatetime().toLocalDate().isEqual(date)) {
                    found = true;
                    System.out.println(((Event) task).getPrintedLine());
                }
            }
        }
        System.out.println("--------------------------------------------");
        if (!found) {
            System.out.println("No activity on that date specified");
            System.out.println("--------------------------------------------");
        }
    }
}
