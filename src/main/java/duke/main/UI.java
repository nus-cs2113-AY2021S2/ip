package duke.main;

import duke.items.Task;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class in charge of UI printing
 */
public class UI {
    private static final String DEADLINE_TEMPLATE = "deadline <task> /by <YYYY-MM-DD>";
    private static final String EVENT_TEMPLATE = "event <task> /at <YYYY-MM-DD>";
    private static final String TODO_TEMPLATE = "todo read book";
    private static final String DATE_TEMPLATE = "<YYYY-MM-DD>";
    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Printing the output when using the list function
     */
    public static void listPreamble() {
        if (Task.getNumOfTasks() != 0){
            System.out.println("Here are the tasks in your list:");
        }
    }

    /**
     * Printing the output when using the find function
     */
    public static void findPreamble() {
        if (Task.getNumOfTasks() != 0){
            System.out.println("Here are the matching tasks in your list:");
        }
        else {
            System.out.println("There are no matching task in your list:");
        }
    }

    /**
     * Prints the updated number of tasks in list
     */
    public static void listUpdate() {
        if (Task.getNumOfTasks() == 0){
            System.out.println("There are no tasks in your list");
        }
        else {
            System.out.println("Now you have " + Task.getNumOfTasks() + " tasks in the list.");
        }

    }

    /**
     * Prints the welcome message.
     */
    public static void welcomeMessage(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm duke.main.Duke\nWhat can I do for you?");
    }

    /**
     * Error message for invalid commands
     */
    public static void InvalidCommandErrorMessage(){
        System.out.println(" ☹ OOPS!!! Invalid Command :-(");
    }

    /**
     * Error message for too little input parameters
     */
    public static void InvalidParameterLengthErrorMessage(String line){
        System.out.println(" ☹ OOPS!!! Insufficient number of inputs for command: " + line.split(" ")[0]);
        System.out.println(" Example inputs:");
        switch (line.split(" ")[0]){

        case ("deadline"):
            System.out.println(" \t " + DEADLINE_TEMPLATE);
            break;
        case ("event"):
            System.out.println(" \t " + EVENT_TEMPLATE);
            break;
        case ("todo"):
            System.out.println(" \t " + TODO_TEMPLATE);
            break;
        }
    }

    /**
     * Error message for invalid date format
     */
    public static void InvalidDateErrorMessage(String line){
        String[] arr = line.split(" ");
        System.out.println(" ☹ OOPS!!! Invalid format for date: " + arr[arr.length-1]);
        System.out.println(" Input format:");
        System.out.println(" \t " + DATE_TEMPLATE);
    }

    /**
     * Error message when the /at indicator is not present when event command is used
     */
    public static void EventParameterErrorMessage(){
        System.out.println(" ☹ OOPS!!! For \"event\" command please input \"/at\"");
    }

    /**
     * Error message when the /by indicator is not present when deadline command is used
     */
    public static void DeadlineParameterErrorMessage(){
        System.out.println(" ☹ OOPS!!! For \"event\" command please input \"/by\"");
    }

    /**
     * Error message when the the integer parameter is out of range
     */
    public static void InvalidIndexErrorMessage(){
        System.out.println(" ☹ OOPS!!! Index is out of range");
    }

    /**
     * Prints the goodbye message
     */
    public static void byeMessage(){
        System.out.println("Bye. Hope to see you again soon!");
        UI.printLine();
    }

    /**
     * Returns a String in the formatted version of the date input
     *
     * @param date date in the YYYY-MM-DD format
     * @return String in the MMM-DD-YYYY date format
     */
    public static String convertDateToStringFormat(String date){
        LocalDate myDateObj = LocalDate.parse(date);
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("MMM-dd-yyyy ");
        return myDateObj.format(myFormatObj);
    }

    /**
     * Returns a LocalDate object based on the String input
     *
     * @param input date in the MMM-DD-YYYY format
     * @return LocalDate object
     */
    public static LocalDate stringPatternToDate(String input) throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM-d-yyyy ");
        LocalDate localDate = LocalDate.parse(input, formatter);
        return localDate;
    }

}
