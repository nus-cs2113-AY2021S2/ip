package duke.parser;

/**
 * This class contains methods that help convert raw user input into a usable format.
 */
public class Parser {
    /**
     * Gets command from user input.
     *
     * @param userInput user's input.
     * @return command.
     */
    public static String getCommand(String userInput) {
        String[] inputWords = userInput.split(" ");
        return inputWords[0];
    }

    /**
     * Returns the task index in integer.
     *
     * @param userInput User's keyboard input in String.
     * @return an integer task index.
     */
    public static String getTaskIndexString(String userInput) {
        return userInput.split(" ")[1];
    }

    /**
     * Returns the keywords from the user input.
     *
     * @param userInput User's keyboard input in String.
     * @return a string of keyword for searching tasks.
     */
    public static String getSearchKeyword(String userInput) {
        return userInput.split(" ")[1];
    }

    /**
     * Parses the content and time limit of a task from user input.
     *
     * @param words                      an String array obtained by splitting the User's keyboard input.
     * @param indexOfTimeLimitIdentifier the index of the time limit identifier, such as "/by" and "/at"
     * @return an array of size 2. the first slot stores the task content, the second stores the time limit.
     * @throws Exception error occurred when parsing the content and/or time limit.
     */
    public static String[] parseTaskContentAndTime(String[] words, int indexOfTimeLimitIdentifier) throws Exception {
        String content = getTaskContentString(words, indexOfTimeLimitIdentifier);
        String timeLimit = getTaskTimeLimitString(words, indexOfTimeLimitIdentifier);
        String[] parseResult = new String[2];
        parseResult[0] = content;
        parseResult[1] = timeLimit;
        return parseResult;
    }

    /**
     * Parses the time limit of a task from user input and convert it to a usable string.
     *
     * @param words                      an String array obtained by splitting the User's keyboard input.
     * @param indexOfTimeLimitIdentifier the index of the time limit identifier, such as "/by" and "/at"
     * @return the processed time limit in String.
     * @throws Exception error occurred when parsing the time limit.
     */
    private static String getTaskTimeLimitString(String[] words, int indexOfTimeLimitIdentifier) throws Exception {
        StringBuilder timeLimitBuilder = new StringBuilder();
        for (int i = (indexOfTimeLimitIdentifier + 1); i < words.length; i++) {
            String timeLimitWord = words[i] + " ";
            timeLimitBuilder.append(timeLimitWord);
        }
        String timeLimitString = timeLimitBuilder.toString().trim();
        if (timeLimitString.length() < 1) {
            throw new Exception("Time limit must be input! Check 'help'.\n");
        }
        return timeLimitString;
    }

    /**
     * Parses the content of a task from user input and convert it to a usable string.
     *
     * @param words                      an String array obtained by splitting the User's keyboard input.
     * @param indexOfTimeLimitIdentifier the index of the time limit identifier, such as "/by" and "/at"
     * @return the processed task content in String.
     * @throws Exception error occurred when parsing the task content.
     */
    private static String getTaskContentString(String[] words, int indexOfTimeLimitIdentifier) throws Exception {
        StringBuilder contentBuilder = new StringBuilder();
        for (int i = 1; i < indexOfTimeLimitIdentifier; i++) {
            String contentWord = words[i] + " ";
            contentBuilder.append(contentWord);
        }
        String contentStr = contentBuilder.toString().trim();
        if (contentStr.length() < 1) {
            throw new Exception("Task content must be input! Check 'help'.\n");
        }
        return contentStr;
    }
}
