package duke.task;
import duke.exception.DukeException;

import java.util.ArrayList;

import static duke.ui.Ui.showLine;

/**
 * Contains the task list and has operations to add/delete tasks in list.
 */
public class TaskList {
    private static final int DONE_LENGTH = 5;
    private static final int TODO_LENGTH = 5;
    private static final int DELETE_LENGTH = 7;
    private static final int FIND_LENGTH = 5;
    private static ArrayList<Task> tasks;
    public static int tasksCount =0;

    /**
     * @param tasks is the arraylist of tasks.
     * constructor for TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * @return arraylist of tasks.
     */
    public static ArrayList<Task> getTasks(){
        return tasks;
    }

    /**
     * @param i index of tasks.
     * @return returns the task at the given index.
     */
    public static Task get(int i){
        return tasks.get(i);
    }


    /**
     * @param input user's input.
     * marks task as done.
     * if task number is invalid, error message is printed.
     */
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

    /**
     * @param input user's input.
     * deletes tasks.
     * if task number is invalid, an error message is printed.
     */
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

    /**
     * @param newTask user's input.
     *  adds a todo task.
     *  if description is empty, error message is printed.
     */
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

    /**
     * @param input user's input.
     * adds a deadline.
     */
    public static void addDeadline(String input){
        try{
            String[] split = input.split(" /by");
            Deadline deadline = new Deadline(split[0], split[1]);
            System.out.println("Got it. I've added this task: ");
            tasks.add(deadline);
            tasksCount++;
            System.out.println(deadline.toString());
            printTotalTasks();

        } catch(ArrayIndexOutOfBoundsException e){
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * prints a message that describes the number of tasks in the list.
     */
    public static void printTotalTasks(){
        System.out.println("Now you have "+ tasksCount+ " tasks in the list.");
    }

    /**
     * @param input user's input.
     * adds an event.
     */
    public static void addEvent(String input) {
        try{
            String[] split = input.split(" /at");
            Event event = new Event(split[0], split[1]);
            System.out.println("Got it. I've added this task: ");
            tasks.add(event);
            tasksCount++;
            System.out.println(event.toString());
            printTotalTasks();
        } catch(ArrayIndexOutOfBoundsException e){
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

    }

    /**
     * @param input user's input.
     * finds if the description entered by the user is already in database.
     */
    public static void find(String input){
        input = input.substring(FIND_LENGTH);
        ArrayList<Task> foundTasks = new ArrayList<Task>();
        System.out.println("Here are the matching tasks in your list: ");
        int foundCount =0;
        for(Task t:tasks){
            if(t.description.contains(input)){
                foundTasks.add(t);
                System.out.println((foundCount+1)+"."+t.toString());
                foundCount++;
            }
        }
    }
}
