package ui;

import tasklist.TaskList;
import commands.Task;
import java.util.ArrayList;

public class Ui {

    public static final String border = "    -------------------------------------------------------------------------------------------------------------------------------";

    public void printWelcomeMessage(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(" ");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
    }

    public void printList(){
        System.out.println(border);
        System.out.println("    Here are the tasks in your list:");
    }
    public void printBorder(){
        System.out.println(" ");
        System.out.println(border);
    }
    public void printByeMessage(){
        System.out.println(border);
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println(" ");
        System.out.println(border);
    }
    public void printAddTaskMessage(TaskList tasks){
        System.out.println(border);
        System.out.println("    Got it. I've added this task:");
        int taskCount = tasks.getTaskCount();
        System.out.println("      " + tasks.getMostRecentTaskAdded());
        tasks.incrementTaskCount();
        System.out.println("    Now you have " + tasks.getTaskCount() + " tasks in the list.");
        System.out.println(" ");
        System.out.println(border);
    }

}
