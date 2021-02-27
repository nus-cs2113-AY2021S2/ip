package duke.parser;

import duke.util.Util;

import javax.lang.model.type.ArrayType;
import java.util.Scanner;
import java.util.ArrayList;

public class Parser {

    public Parser(){
    }

    public static ArrayList<String> parseInput(String userInput){
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
        }
        else{
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


    public static String parseTaskName(ArrayList<String> parsedList) {
        return parsedList.get(0).trim();
    }

    public static String parseTaskType(ArrayList<String> parsedList) {
        return parsedList.get(1).trim();
    }

    public static String[] parseCurrentDate(String date){
        String[] splitDate = date.trim().split("-");
        String[] arrangedSplitDate = new String[3];

        arrangedSplitDate[0] = splitDate[1];
        arrangedSplitDate[1] = splitDate[2];
        arrangedSplitDate[2] = splitDate[0];

        return arrangedSplitDate;
    }

    public static void emptyList(ArrayList<String> parsedList){
        for(String item : parsedList) {
            parsedList.remove(item);
        }
    }
}
