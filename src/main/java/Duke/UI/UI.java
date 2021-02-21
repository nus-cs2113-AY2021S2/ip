package Duke.UI;

import Duke.Duke;

public class UI extends Duke{

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
                + "add: add tasks into the list [tip: enter date as YYYY-MM-DD to help duke understand better!]\n"
                + "done: mark a task in the list as done\n"
                + "delete: delete a task from the list\n"
                + "bye: exit Duke\n"
                );
    }
}
