package duke.Controller;

public class Parser {

    /*Methods to process the input of the user.
If input is not valid, returns string nonsense.
The type of task is indicated by the first substring and the task description is found after the first blank space.
If a forward slash present,  ths substring is split into the task and the end date which is indicated by the slash.
Exception handling includes cases where no tasks are specified, invalid inputs, no date or time being specified (for event and deadline)*/

    public String extractTask(String input) {
        String Task;
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
        }
        else if (input.contains("todo") || input.contains("deadline") || input.contains("event")) {
            int indexOfSpace = input.indexOf(" ");
            if (indexOfSpace == -1) {
                return "retry";
            } else {
                String subString = input.substring(indexOfSpace + 1);
                if (subString.contains("/")) {
                    int indexOfSlash = subString.indexOf("/");
                    Task = subString.substring(0, indexOfSlash - 1);
                } else {
                    Task = subString;
                }
                return Task;
            }
        } else {
            return "nonsense";
        }
    }

    public String extractDate(String input) {
        String Date = null;
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
            int indexOfSpace = input.indexOf(" ");
            String subString = input.substring(indexOfSpace + 1);
            if (subString.contains("/")) {
                int indexOfSlash = subString.indexOf("/");
                String subStringDate = subString.substring(indexOfSlash);
                int indexNext = subStringDate.indexOf(" ");
                if (indexNext == -1) {
                    return "missing";
                } else {
                    Date = subStringDate.substring(indexNext + 1);
                    return Date;
                }
            } else {
                return "missing";
            }
        } else {
            return "nonsense";
        }
    }
}

