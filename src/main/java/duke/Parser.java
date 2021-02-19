package duke;

import java.util.ArrayList;
import java.util.List;

/**
 * Parses user input.
 */
public class Parser {

    /**
     * Used for initial separation of command word and args.
     * Parses user input into command for execution.
     *
     * @param fullCommand Full user input string.
     * @return List of command strings based on the user input.
     */
    public static List<String> parse(String fullCommand) {
        List<String> returnStr = new ArrayList<>();

        String[] words = fullCommand.split(" ", 2);
        String commandType = words[0];

        try {
            switch (commandType) {
            case "bye":
            case "list":
                returnStr.add(commandType);
                break;
            case "done":
            case "delete":
            case "find":
            case "todo":
                returnStr.add(commandType);
                String todoDetail = words[1];
                returnStr.add(todoDetail);
                break;
            case "deadline":
                returnStr.add(commandType);
                String ddlDetail = words[1];
                String[] infoD = ddlDetail.split(" /by ", 2);
                String descriptionD = infoD[0];
                String byTime = infoD[1];
                returnStr.add(descriptionD);
                returnStr.add(byTime);
                break;
            case "event":
                returnStr.add(commandType);
                String eventDetail = words[1];
                String[] infoE = eventDetail.split(" /at ", 2);
                String descriptionE = infoE[0];
                String atTime = infoE[1];
                returnStr.add(descriptionE);
                returnStr.add(atTime);
                break;
            default:
                returnStr.add(fullCommand);
                break;
            }
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
        return returnStr;
    }
}
