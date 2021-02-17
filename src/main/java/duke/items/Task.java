package duke.items;

import java.util.ArrayList;
import duke.exceptions.InvalidIndexExceptions;

public class Task {
    private static int numOfTasks = 0;
    protected String description;
    protected boolean isDone;
    protected static ArrayList<Task> list = new ArrayList<Task>();


    public static Task[] getList(){
        return list;
    }
    public static int getNumOfTasks() {
        return numOfTasks;
    }

    public static void printList(){
        for (int i =0; i<numOfTasks;i++) {
            System.out.print(i+1 + ".");
            list.get(i).print();
        }
    }
    public static void addTask(Task task) {
        list.add(task);
        numOfTasks++;
    }
    public static void deleteTask(int index) {
        numOfTasks--;
        System.out.println("Noted. I've removed this task: ");
        System.out.print("  ");
        list.get(index).print();
        list.remove(index);
        System.out.println("Now you have " + numOfTasks + " tasks in the list.");

    }
    public static void setDone(int index) throws InvalidIndexExceptions{
        if (index >= numOfTasks){
            throw new InvalidIndexExceptions();
        }
        list.get(index).isDone = true;
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("[\u2713] " + list.get(index).getDescription());
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public void print(){
        if (this.isDone) {
            System.out.println("[T][\u2713] " + description );
        } else {
            System.out.println("[T][\u2718] " + description );
        }
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getType() {
        return "T";
    }
    public boolean isDone() {return this.isDone;}
    public void setDone() {this.isDone = true;};
}