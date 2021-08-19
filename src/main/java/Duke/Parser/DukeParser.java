package Duke.Parser;

import Duke.Task.Task;
import Duke.UI.DukeUI;

public class DukeParser {

    /**
     * Returns a list of event type and event description.
     * If there is no event description, "filler" is used temporarily.
     *
     * @param userInput userInput from Scanner
     * @return listOfInputs
     */
    public static String[] splitInputIntoString(String userInput) {
        userInput = userInput.trim();
        String[] listOfInputs = userInput.split(" ", 2);
        if (listOfInputs.length == 1) {
            listOfInputs = new String[]{userInput, "filler"};
        }
        return listOfInputs;
    }

    /**
     * Takes in userCommand and inputDetails parsed from stored text file and return a Task object.
     *
     * @param userCommand Type of event
     * @param inputDetails Event description
     * @return Task Object
     */
    public static Task processSavedData(String userCommand, String inputDetails) {
        return new Task(inputDetails, userCommand);
    }

    /**
     * Takes in userCommand and inputDetails from DukeUI and returns a task object.
     *
     * @param userCommand Type of event
     * @param inputDetails Event Description
     * @return Task Object
     * @throws TodoException if there is no task description
     */
    public static Task processUserRequest(String userCommand, String inputDetails) throws TodoException {
        if (inputDetails.equalsIgnoreCase("filler") & userCommand.equalsIgnoreCase("todo")) {
            throw new TodoException();
        }
        if (inputDetails.equalsIgnoreCase("filler") & userCommand.equalsIgnoreCase("deadline")) {
            throw new TodoException();
        }
        if (inputDetails.equalsIgnoreCase("filler") & userCommand.equalsIgnoreCase("event")) {
            throw new TodoException();
        }
        DukeUI.printLine();
        DukeUI.printLine();
        return new Task(inputDetails, userCommand);
    }

    public static boolean isValidCommand(String userCommand) {
        boolean isValid = true;
        try {
            isValidInput(userCommand);
        } catch (InvalidCommandException e) {
            DukeUI.printLine();
            DukeUI.print(e.sendErrorMessage());
            DukeUI.printLine();
            isValid = false;
        }
        return isValid;
    }

    /**
     * Check whether user has input any command not implemented for Duke.
     *
     * @param userCommand
     * @throws InvalidCommandException If the command is invalid
     */
    private static void isValidInput(String userCommand) throws InvalidCommandException {
        var isValid = userCommand.equalsIgnoreCase("todo") | userCommand.equalsIgnoreCase("deadline") | userCommand.equalsIgnoreCase("event");
        if (!isValid) {
            throw new InvalidCommandException();
        }
    }
}
