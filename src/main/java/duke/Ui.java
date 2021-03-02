package duke;

import java.util.Scanner;

//deals with interactions from User:
public class Ui {

    public static String line = "____________________________________________________________";
    public static Scanner in = new Scanner(System.in);

    //deal with new user input:
    public Ui(){

    }

    /**
     * Prints some lines to welcome the user:
     */
    public static void saysHiToUser() {
        //Greeting:
        System.out.println(line);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(line);
    }

    /**
     * Prints some lines when user exits:
     */
    public static void saysByeToUser() {
        //exits with "bye":
        System.out.println("Bye. Hope to see you again soon!"); //exits
    }

    public static void showLine() {
        System.out.println(line);
    }

    public static String readCommand() {
        String input = in.nextLine(); //take in User's current input
        return input;
    }

    public static void showLoadingError() {
        System.out.println("Loading Error :((");
    }

}
