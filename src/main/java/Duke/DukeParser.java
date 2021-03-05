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
}
