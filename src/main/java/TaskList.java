import java.util.Scanner;
public class TaskList {
    public static String[] tasks;
    public static int index = 0;
    protected boolean isDone;

    public TaskList(){
        tasks = new String[100];
        this.isDone = false;
        index = 0;
    }

    public static void addTask(String task){
        System.out.println("Added: " + task.trim());
        tasks[index] = task;
        index++;
    }
    public static void showTaskList() {
        System.out.print("Here are the tasks in your list: \n");
        for (int i=0; i<index; i++) {
            System.out.println((i+1) + "." + tasks[i]);
        }
    }
        public static void toDo (String task){
            System.out.println("Got it. I've added this task:");
            System.out.println("\t[T][ ]" + task);
            tasks[index] = "[T][ ]" + task;
            System.out.println("Now you have " + (index+1) + " tasks in the list.");
            index++;
        }
        public static void deadline(String task, String by){
            System.out.println("Got it. I've added this task:");
            System.out.println("\t[D][ ] " + task + " (by:" + by + ")");
            tasks[index] = "[D][ ] " + task + " (by:" + by + ")";
            System.out.println("Now you have " + (index+1) + " tasks in the list.");
            index++;
        }
        public static void event(String task, String at){
            System.out.println("Got it. I've added this task:");
            System.out.println("\t[E][ ] " + task + " (at:" + at + ")");
            tasks[index] = "[E][ ] " + task + " (at:" + at + ")";
            System.out.println("Now you have " + (index+1) + " tasks in the list.");
            index++;
        }
        public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
        //return tick or X symbols
        }

        public void markAsDone(int c, String description, String type) {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(type + "[" + getStatusIcon() + "]" + description);
        tasks[c-1] = tasks[c-1] = type + "[✓]" + description;
        }
    }


