package parser;

import exceptions.DukeException;

import java.util.Arrays;
import java.util.List;

public class Parser {


    public static final String border = "    ――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――";
    public static final List<String> commands = Arrays.asList("event", "deadline", "todo", "list", "done", "delete", "bye","find");

    public String processUserCommand(String userCommand) {
        if (commands.contains(userCommand)) {
            switch (userCommand) {
            case "event":
                return "event";
            case "deadline":
                return "deadline";
            case "todo":
                return "todo";
            case "list":
                return "list";
            case "done":
                return "done";
            case "bye":
                return "bye";
            case "delete":
                return "delete";
            case "find":
                return "find";
            default:
                return "invalid_user_input";
            }
        } else {
            return "invalid_user_input";
        }
    }

    public String processTodo(String[] userInput) {
        String description = "";
        try {
            int wordsCount = userInput.length;
            if (userInput.length == 1) {
                throw new DukeException("missing_parameter");
            }
            for (String word : userInput) {
                wordsCount--;
                if (word.equals("todo")) {
                    continue;
                } else if (wordsCount == 0) {
                    description = description + word;
                } else {
                    description = description + word + " ";
                }
            }
        } catch (DukeException e) {
            System.out.println(border);
            System.out.println("    OOPS!! The description of a todo cannot be empty.");
            System.out.println(border);
        }
        return description;
    }

    public String[] processEvent(String[] userInput) {
        String[] information = new String[2];
        information[0] = "";
        information[1] = "";
        try {
            boolean atFlag = false;
            int wordsCount = userInput.length;
            if (wordsCount == 1) {
                throw new DukeException("missing_parameter");
            }
            for (String word : userInput) {
                wordsCount--;
                if (word.equals("/at")) {
                    atFlag = true;
                    continue;
                }
                if (word.equals("event")) {
                    continue;
                }
                if (atFlag && (wordsCount == 0)) {
                    information[1] = information[1] + word;
                } else if (atFlag) {
                    information[1] = information[1] + word + " ";
                } else {
                    information[0] = information[0] + word + " ";
                }
            }
        } catch (DukeException e) {
            System.out.println(border);
            System.out.println("    OOPS!! The description of the deadline cannot be empty.");
            System.out.println(border);
        }
        return information;
    }

    public String[] processDeadline(String[] userInput) {
        String[] information = new String[2];
        information[0] = "";
        information[1] = "";
        try {
            boolean byFlag = false;
            int wordsCount = userInput.length;
            if (wordsCount == 1) {
                throw new DukeException("missing_parameter");
            }
            for (String word : userInput) {
                wordsCount--;
                if (word.equals("/by")) {
                    byFlag = true;
                    continue;
                }
                if (word.equals("deadline")) {
                    continue;
                }
                if (byFlag && (wordsCount == 0)) {
                    information[1] = information[1] + word;
                } else if (byFlag) {
                    information[1] = information[1] + word + " ";
                } else {
                    information[0] = information[0] + word + " ";
                }
            }
        } catch (DukeException e) {
            System.out.println(border);
            System.out.println("    OOPS!! The description of the deadline cannot be empty.");
            System.out.println(border);
        }
        return information;
    }

}
