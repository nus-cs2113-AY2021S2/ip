package Duke.UI;

import Duke.Commands.PrintListCommand;
import Duke.Duke;

public class UI extends Duke{

    public static final String LINE = "____________________________________________________________";

    public UI(){
        greetings();
    }

    public static void greetings(){
        String LOGO = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println( LOGO + LINE + "\n" + " Hello! I'm Duke\n" + " Type 'help' to see what i could do for you\n" + LINE + "\n" );
    }

    public static void goodbye(){
        System.out.println(" Bye! Hope to see you again soon!\n" + LINE );
    }

    public static void helpMenu(){
        System.out.println("Here's what I could do for you ^_^\n"
                + "help: print help menu\n"
                + "list: see saved tasks in the list\n"
                + "todo: add todo task into the list\n"
                + "event: add event into the list\n"
                + "deadline: add deadline into the list\n"
                + "[tip: enter date as YYYY-MM-DD to help duke understand better!]\n"
                + "done: mark a task in the list as done\n"
                + "delete: delete a task from the list\n"
                + "date: search saved tasks on a specific day\n"
                + "find: search saved tasks by keywords\n"
                + "bye: exit Duke\n"
                );
    }
    public static void taskAddedText() {
        System.out.println(" Task added! ^_^");
        PrintListCommand.printList(taskCount, taskCount + 1);
    }
    public static void taskDeleted() {
        System.out.println(" Yay! This task is deleted!");
    }

    public static void taskDone() {
        System.out.println(" Yay! This task is done!");
    }

}
