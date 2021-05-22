package duke.parser;

import duke.util.Util;

import javax.lang.model.type.ArrayType;
import java.util.Scanner;
import java.util.ArrayList;

public class Parser {

    /**
     * Returns an array list containing task type
     * and task name.
     * The task name is stored in index = 0.
     * The task type is stored in index = 1.
     * The task name is "" if the a one-word-command
     * is parsed.
     *
     * @param userInput Original user input.
     * @return An array list containing task type and task name.
     */
    public static ArrayList<String> parseInput(String userInput) {
        String taskType;
        String taskName;
        Scanner splitInputScanner;
        ArrayList<String> parsedList = new ArrayList<>();
        // Separate taskType and taskName(may contain
        // dates/time) if applicable
        userInput = userInput.trim();   // removes leading and trailing spaces
        if(Util.isOneWord(userInput)) {
            taskType = userInput;
            taskName = "";
        } else {
            splitInputScanner = new Scanner(userInput);
            // Extract task type
            taskType = splitInputScanner.next().toLowerCase();
            // Extract task description
            taskName = splitInputScanner.nextLine();
        }
        parsedList.add(taskName);
        parsedList.add(taskType);
        return parsedList;
    }

    /**
     * Returns task name.
     * The task name is stored in index = 0
     * of the parsedList.
     *
     * @param parsedList Array list containing task name and task type.
     * @return Task name.
     */
    public static String parseTaskName(ArrayList<String> parsedList) {
        return parsedList.get(0).trim();
    }

    /**
     * Returns task type.
     * The task type is stored in index = 1
     * of the parsedList.
     *
     * @param parsedList Array list containing task name and task type.
     * @return Task type.
     */
    public static String parseTaskType(ArrayList<String> parsedList) {
        return parsedList.get(1).trim();
    }

    /**
     * Returns an array of String containing the
     * current month, day and year.
     * The current date format parsed in is
     * YYYY-MM-DD.
     * The month is stored in index = 0.
     * The day is stored in index = 1.
     * The year is stored in index = 2.
     *
     * @param date Current date
     * @return An array of String containing the current
     * month, day and year.
     */
    public static String[] parseCurrentDate(String date) {
        String[] splitDate = date.trim().split("-");
        String[] arrangedSplitDate = new String[3];

        arrangedSplitDate[0] = splitDate[1];
        arrangedSplitDate[1] = splitDate[2];
        arrangedSplitDate[2] = splitDate[0];

        return arrangedSplitDate;
    }

    /**
     * Removes all contents of the array list of strings.
     * This method empties the array list so that it can
     * be reused for parsing purposes.
     *
     * @param parsedList Array list to be emptied.
     */
    public static void emptyList(ArrayList<String> parsedList) {
        for(String item : parsedList) {
            parsedList.remove(item);
        }
    }
}
