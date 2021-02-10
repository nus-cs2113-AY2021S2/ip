package duke.items;

import duke.exceptions.InvalidIndexExceptions;

public class Task {
    private static int numOfTasks = 0;
    protected String description;
    protected boolean isDone;
    protected static Task[] list = new Task[100];

    public static void printList(){
        for (int i =0; i<numOfTasks;i++) {
            System.out.print(i+1 + ".");
            list[i].print();
        }
    }
    public static int getNumOfTasks() {
        return numOfTasks;
    }
    public static void addTask(Task task) {
        list[numOfTasks] = task;
        numOfTasks++;
    }
    public static void setDone(int index) throws InvalidIndexExceptions{
        if (index >= numOfTasks){
            throw new InvalidIndexExceptions();
        }
        list[index].isDone = true;
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("[\u2713] " + list[index].getDescription());
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

}