package duke.task;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.ui.Ui;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static duke.ui.Ui.*;

public class TaskList {
    private static final int DONE_LENGTH = 5;
    private static final int TODO_LENGTH = 5;
    private static final int DELETE_LENGTH = 7;
    private static final int FIND_LENGTH = 5;
    private static ArrayList<Task> tasks;
    public static int tasksCount =0;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    public static ArrayList<Task> getTasks(){
        return tasks;
    }

    public static Task get(int i){
        return tasks.get(i);
    }


    public static void markTaskAsDone(String input) {
        try {
            int newNum = Integer.parseInt(input.substring(DONE_LENGTH));
            if (newNum > tasks.size()|| newNum < 0) {
                throw new DukeException();
            }
            System.out.println("Nice! I've marked this task as done:");
            tasks.get(newNum - 1).markAsDone();
            System.out.println(tasks.get(newNum - 1).toString());
        } catch (DukeException e) {
            System.out.println("☹ OOPS!!! The task number is invalid!");
        }

    }
    public static void deleteTask(String input){
        try {
            int newNum = Integer.parseInt(input.substring(DELETE_LENGTH));
            if (newNum > tasks.size() || newNum < 0) {
                throw new ArrayIndexOutOfBoundsException();
            }

            System.out.println("Noted. I've removed this task:" + "\n"+ tasks.get(newNum-1).toString());
            tasks.remove(newNum-1);
            tasksCount--;
            printTotalTasks();

        }catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! The task number is invalid!");
            showLine();
        }
    }
    public static void addTodo(String newTask) {
        try {
            newTask = newTask.substring(TODO_LENGTH);
            System.out.println("Got it. I've added this task: ");
            Todo todo = new Todo(newTask);
            tasks.add(todo);
            tasksCount++;
            System.out.println(todo.toString());
            printTotalTasks();
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
            showLine();
        }
    }
    public static void addDeadline(String input) {
        String[] split = input.split(" /by");
        System.out.println("Got it. I've added this task: ");
        Deadline deadline = new Deadline(split[0], split[1]);
        tasks.add(deadline);
        tasksCount++;
        System.out.println(deadline.toString());
        printTotalTasks();

    }
    public static void printTotalTasks(){
        System.out.println("Now you have "+ tasksCount+ " tasks in the list.");
    }
    public static void addEvent(String input) {
        String[] split = input.split(" /at");
        System.out.println("Got it. I've added this task: ");
        Event event = new Event(split[0], split[1]);
        tasks.add(event);
        tasksCount++;
        System.out.println(event.toString());
        printTotalTasks();
    }
    public static void find(String input){
        input = input.substring(FIND_LENGTH);
        ArrayList<Task> foundTasks = new ArrayList<Task>();
        int foundCount =0;
        System.out.println("Here are the matching tasks in your list: ");
        for(Task t:tasks){
            if(t.description.contains(input)){
                foundTasks.add(t);
                System.out.println((foundCount+1)+"."+t.toString());
                foundCount++;
            }

        }



    }



}
