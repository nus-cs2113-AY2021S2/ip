package Duke;

public class DukeParser {
    public static String[] splitInputIntoString(String userInput) {
        String[] listOfInputs = userInput.split(" ", 2);
        if (listOfInputs.length == 1) {
            listOfInputs = new String[]{userInput, "filler"};
        }
        return listOfInputs;
    }

    public static Task processSavedData(String userCommand, String inputDetails) {
        return new Task(inputDetails, userCommand);
    }

    public static Task processUserRequest(String userCommand, String inputDetails) throws TodoException {
        if (inputDetails.equalsIgnoreCase("filler") & userCommand.equalsIgnoreCase("todo")) {
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

    private static void isValidInput(String userCommand) throws InvalidCommandException {
        var isValid = userCommand.equalsIgnoreCase("todo") | userCommand.equalsIgnoreCase("deadline") | userCommand.equalsIgnoreCase("event");
        if (!isValid) {
            throw new InvalidCommandException();
        }
    }
}
