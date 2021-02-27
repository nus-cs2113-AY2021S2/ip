package duke.util;

import duke.task.Task;

import java.util.Scanner;

public class Util {
    public static boolean isEmpty(String s){
        return s.equals("");
    }

    public static boolean isOneWord(String userInput) {
        return !userInput.contains(" ");
    }

    /**
     * Extracts and returns the date from the user input.
     *
     * Returns an empty string if s is incomplete or invalid.
     *
     * @param s User input with task type removed.
     * @return Date from user input.
     */
    public static String extractTime(String s){
        // If s is empty
        if (isEmpty(s)){
            return "";
        }

        String[] splitArray = s.split("/");

        // If there is no date field
        if (splitArray.length < 2){
            return "";
        }

        // If s is invalid input
        if (splitArray[1].trim().split(" ").length != 2){
            return "";
        }

        Scanner sc = new Scanner(splitArray[1]);
        sc.next();              // remove 'by' or 'at'
        return sc.nextLine();   // extract time/date
    }

    /**
     * Extracts and returns task name from the user input.
     *
     * Returns an empty string if s is incomplete.
     *
     * @param s User input with task type removed.
     * @return Task name from user input.
     */
    public static String extractTaskName(String s){
        // If s is empty
        if (isEmpty(s)){
            return "";
        }

        String[] splitArray = s.split("/");

        return splitArray[0];
    }

    /**
     * Returns the task index for done and delete methods.
     * If task index provided is incomplete or invalid,
     * different values will be returned.
     *
     * @param userInput Original user input.
     * @return Task index for done and delete methods, -1 if
     * parameter is insufficient, 0 if task index is not an
     * integer, is negative or is out of range.
     */
    public static String getTaskIndex(String userInput) {
        String[] userInputSplitted;
        String taskIndex;
        userInputSplitted = userInput.split(" ");
        if(userInputSplitted.length == 2) {
            taskIndex = userInputSplitted[1];
        } else {
            return "-1";
        }
        if(!isInteger(taskIndex)){
            return "0";
        }
        if(isNegative(taskIndex) || isOutOfRange(taskIndex)){
            return "0";
        }
        return taskIndex;
    }

    private static boolean isNegative(String taskIndex) {
        return Integer.parseInt(taskIndex) <= 0;
    }

    private static boolean isInteger(String taskIndex){
        try {
            Integer.parseInt(taskIndex);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private static boolean isOutOfRange(String taskIndex){
        return Task.getTaskCount() < Integer.parseInt(taskIndex);
    }

    public static boolean isBye(String taskType) {
        return taskType.equals("bye");
    }
}
