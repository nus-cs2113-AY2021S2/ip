package duke;

import java.util.ArrayList;

public class Messages {

    protected static String LINE = "____________________________________________________________";

    public static void welcomeMessage(){
        String logo = " _                          \n"
                + "| |                         \n"
                + "| |     _   _   ___   _ __  \n"
                + "| |    | | | | / _ \\ | '_ \\ \n"
                + "| |____| |_| || (_) || | | |\n"
                + "\\_____/ \\__, | \\___/ |_| |_|\n"
                + "         __/ |              \n"
                + "        |___/              \n";
        System.out.println("Hello from\n" + logo);

        System.out.println();
        System.out.println(LINE);
        System.out.println("Hello! I'm Lyon");
        System.out.println("What can I do for you?");
    }

    public static void badUserInputMessage(){
        System.out.println(LINE);
        System.out.println("I'm sorry, your input does not comply with the available features I have.");
        System.out.println("Kindly try again!");
    }

    public static void markTaskDoneMessage(ArrayList<Task> tasks, int index){
        System.out.println(LINE);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(index).toString());
        System.out.println(LINE);
    }

    public static void deleteTaskMessage(ArrayList<Task> tasks, int index){
        System.out.println(LINE);
        System.out.println("Noted! I have removed this task:");
        System.out.println(tasks.get(index).toString());
        System.out.println(LINE);
    }

    public static void newItemMessage(ArrayList<Task> tasks, Task newItem){
        System.out.println(LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println(newItem.toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(LINE);
    }

    public static void goodbyeMessage(){
        System.out.println(LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    public static void noFindResults(String term){
        System.out.println(LINE);
        System.out.println("No results found for search term: " + term);
    }
}
