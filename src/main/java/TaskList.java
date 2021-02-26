import Task.*;

import java.util.ArrayList;

public class TaskList {

    public static void showDone() {
        System.out.println("____________________________________________________________\n");
        System.out.println("Nice! I've marked this task as done:\n ");
        System.out.println("____________________________________________________________\n");
    }
    public static void showAddTasks(String string, int size){
        System.out.println("____________________________________________________________\n");
        System.out.println("Got it. I've added this task:\n");
        System.out.println(string + "\n");
        System.out.println("Now you have " + size + " tasks in the list.\n");
        System.out.println("____________________________________________________________\n");
    }

    public static void RemoveTasks(ArrayList<Task> Tasks,String line){
        System.out.println("____________________________________________________________\n");
        String[] parts = line.split(" ");
        int i=Parser.parserToInteger(parts[1].trim());
        System.out.println("Noted. I've removed this task: \n");
        System.out.println(Tasks.get(i-1).toString() + "\n");
        Tasks.remove(i-1);
        System.out.println("Now you have " + Tasks.size() + " tasks in the list.\n");
        System.out.println("____________________________________________________________\n");
    }

    public static void showList(ArrayList<Task> Tasks){
        System.out.println("____________________________________________________________\n");
        System.out.println("Here are the tasks in your list:\n");
        for (int i = 0; i < Tasks.size(); i++){
            System.out.println(i+1 + ". " + Tasks.get(i).toString() + "\n");
        }
        System.out.println("____________________________________________________________\n");
    }
    public static void findTasks(ArrayList<Task> Tasks, String line){
        System.out.println("____________________________________________________________\n");
        System.out.println("Here are the matching tasks in your list:\n");
        for (int i = 0; i < Tasks.size(); i++){
            if(Tasks.get(i).getDescription().contains(line))
            System.out.println(i+1 + ". " + Tasks.get(i).toString() + "\n");
        }
        System.out.println("____________________________________________________________\n");
    }
}
