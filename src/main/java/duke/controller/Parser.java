package duke.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    /*Methods to process the input of the user.
If input is not valid, returns string nonsense.
The type of task is indicated by the first substring and the task description is found after the first blank space.
If a forward slash present,  ths substring is split into the task and the end date which is indicated by the slash.
Exception handling includes cases where no tasks are specified, invalid inputs, no date or time being specified (for event and deadline)*/

    public String sortTask(String input) {
        if (input.equalsIgnoreCase("list")) {
            return "list";
        } else if (input.equalsIgnoreCase("bye")) {
            return "bye";
        } else if (input.contains("done")) {
            return "done";
        } else if (input.contains("delete")) {
            return "delete";
        } else if (input.contains("save")) {
            return "save";
        } else if (input.contains("find")) {
            String keyword = extractKeyword(input);
            return keyword;
        }
        else if (input.contains("todo") || input.contains("deadline") || input.contains("event")) {
            String extractedTask = extractTask(input);
            return extractedTask;
        } else {
            return "nonsense";
        }
    }

    public String extractTask(String input) {
        String task;
        int indexOfSpace = input.indexOf(" ");
        if (indexOfSpace == -1) {
            return "retry";
        } else {
            String subString = input.substring(indexOfSpace + 1);
            if (subString.contains("/")) {
                int indexOfSlash = subString.indexOf("/");
                task = subString.substring(0, indexOfSlash - 1);
            } else {
                task = subString;
            }
            return task;
        }
    }

    public String sortDate(String input) {
        if (input.equalsIgnoreCase("list")) {
            return "list";
        } else if (input.equalsIgnoreCase("bye")) {
            return "bye";
        } else if (input.contains("done")) {
            return "done";
        } else if (input.contains("todo")) {
            return "todo";
        } else if (input.contains("delete")) {
            return "delete";
        } else if (input.contains("save")) {
            return "save";
        } else if (input.contains("deadline") || input.contains("event")) {
            String extractedDate = extractDate(input);
            return extractedDate;
        } else {
            return "nonsense";
        }
    }

    public String extractDate(String input) {
        String date;
        int indexOfSpace = input.indexOf(" ");
        String subString = input.substring(indexOfSpace + 1);
        if (subString.contains("/")) {
            int indexOfSlash = subString.indexOf("/");
            String subStringDate = subString.substring(indexOfSlash);
            int indexNext = subStringDate.indexOf(" ");
            if (indexNext == -1) {
                return "missing";
            } else {
                date = subStringDate.substring(indexNext + 1);
                return date;
            }
        } else {
            return "missing";
        }
    }

    public LocalDate processString(String dateTime) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate datetime = LocalDate.parse(dateTime, formatter);
        return datetime;
    }

    public String extractKeyword(String input) {
        String keyword;
        int indexOfSpace = input.indexOf(" ");
        if (indexOfSpace == -1) {
            return "Please enter a keyword homie!";
        } else {
            keyword = input.substring(indexOfSpace + 1);
            return keyword;
        }
    }
}

