package duke;

import java.util.Scanner;

//deals with interactions from User:
public class Ui {

    public static String line = "____________________________________________________________";
    public static Scanner in = new Scanner(System.in);

    //deal with new user input:
    public Ui() {

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

    /**
     * Prints Line for Start and End
     * of current response to User
     */
    public static void showLine() {
        System.out.println(line);
    }

    /**
     * takes in next line of User Command
     * @return
     */
    public static String readCommand() {
        String input = in.nextLine(); //take in User's current input
        return input;
    }

    /**
     * Shows Loading Error associated with IOException in main Duke Class
     */
    public static void showLoadingError() {
        System.out.println("Loading Error :((");
    }

    public void showNullPointerError() {
        System.out.println("Null Pointer Error :((");
    }

}
