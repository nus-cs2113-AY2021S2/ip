package duke.util;

import duke.task.Task;

import java.util.Formatter;
import java.util.Scanner;

public class Util {
    public static boolean isEmpty(String s){
        return s.equals("");
    }

    public static boolean isOneWord(String userInput) {
        return !userInput.contains(" ");
    }

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

    public static String extractTaskName(String s){
        // If s is empty
        if (isEmpty(s)){
            return "";
        }

        String[] splitArray = s.split("/");

        return splitArray[0];
    }

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
