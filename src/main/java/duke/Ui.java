package duke;

import java.util.Scanner;

/**
 * <code>Ui</code> object associated with methods that deals with interactions from User.
 */
public class Ui {

    public static String line = "____________________________________________________________";
    public static Scanner in = new Scanner(System.in);

    //deal with new user input:
    public Ui() {

    }

    /**
     * Prints some lines to welcome the user.
     */
    public static void saysHiToUser() {
        //Greeting:
        System.out.println(line);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(line);
    }

    /**
     * Prints some lines when user exits.
     */
    public static void saysByeToUser() {
        //exits with "bye":
        System.out.println("Bye. Hope to see you again soon!"); //exits
    }

    /**
     * Prints a line to mark the start and end of the current response to user.
     */
    public static void showLine() {
        System.out.println(line);
    }

    /**
     * Takes in next line of the user command and stores it to 'input'.
     * @return input
     */
    public static String readCommand() {
        String input = in.nextLine(); //take in User's current input
        return input;
    }

    /**
     * Shows error when creating new file with given filepath.
     * Associated with IOException thrown in Storage and caught in Duke.
     */
    public static void showCreateFileError() {
        System.out.println("Error creating new file. :((");
    }

    /**
     * Shows error when there is nothing returned from the storage.load() method.
     * Associated with NullPointerException thrown in Storage and caught in Duke.
     */
    public void showNullPointerError() {
        System.out.println("Null Pointer Error :((");
    }

}
