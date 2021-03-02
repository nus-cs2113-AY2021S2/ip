package duke.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Parser class that processes the input of the user.
 */
public class Parser {

    /**
     * Sorts input of the user to perform the specific commands.
     * @param input User input.
     * @return Command strings (list, done, bye, delete, save, date), and keyword and task processed from the input.
     */
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
        } else if (input.contains("date")) {
            return "date";
        } else if (input.contains("todo") || input.contains("deadline") || input.contains("event")) {
            String extractedTask = extractTask(input);
            return extractedTask;
        } else {
            return "nonsense";
        }
    }

    /**
     * Extracts the task from the user input.
     * @param input User input.
     * @return Task extracted from input.
     */
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

    /**
     * Sorts input from the user to obtain the date and time.
     * @param input User input.
     * @return Command strings (list, done, bye, delete, save, todo), and date and time processed from the input.
     */
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
            String extractedDateTime = extractDateTime(input);
            return extractedDateTime;
        } else {
            return "nonsense";
        }
    }

    /**
     * Extracts date from the user input for search by date.
     * @param dateTime Date which the task is due as a string.
     * @return Date in which task is due.
     */
    public String extractDate(String dateTime) {
        int indexOfSpace = dateTime.indexOf(" ");
        String date = dateTime.substring(indexOfSpace+1);
        return date;
    }

    /**
     * Extracts date and time from user input.
     * If no date and time present, returns a "missing" statement that will prompt an error message in the user interface.
     * @param input User input.
     * @return Date and time in which task is due.
     */
    public String extractDateTime(String input) {
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

    /**
     * Processes the date and time of input into DateTime format.
     * @param dateTime Date and time of task.
     * @return Date and time in DateTime format.
     * @throws DateTimeParseException
     */
    public LocalDate processSearch(String dateTime) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate datetime = LocalDate.parse(dateTime, formatter);
        return datetime;
    }

    /**
     * Extracts keyword from user input for search task by keyword.
     * If no keyword present, returns a "retry" statement that will prompt an error message in the user interface.
     * @param input User input.
     * @return Keyword.
     */
    public String extractKeyword(String input) {
        String keyword;
        int indexOfSpace = input.indexOf(" ");
        if (indexOfSpace == -1) {
            return "retry";
        } else {
            keyword = input.substring(indexOfSpace + 1);
            return keyword;
        }
    }
}

