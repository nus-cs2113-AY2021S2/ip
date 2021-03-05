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
        Task newTask = new Task(inputDetails, userCommand);
        return newTask;
    }

    public static Task processUserRequest(String userCommand, String inputDetails) throws TodoException {
        if (inputDetails.equalsIgnoreCase("filler") & userCommand.equalsIgnoreCase("todo")) {
            throw new TodoException();
        }
        DukeUI.printLine();
        DukeUI.printLine();
        Task newTask = new Task(inputDetails, userCommand);
        return newTask;
    }
}
