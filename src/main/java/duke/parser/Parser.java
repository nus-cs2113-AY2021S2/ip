package duke.parser;

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

    public static String[] parseTaskContentAndTime(String[] words, int indexOfTimeLimitIdentifier) throws Exception {
        String content = getTaskContentString(words, indexOfTimeLimitIdentifier);
        String timeLimit = getTaskTimeLimitString(words, indexOfTimeLimitIdentifier);
        String[] parseResult = new String[2];
        parseResult[0] = content;
        parseResult[1] = timeLimit;
        return parseResult;
    }

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

    private static String getTaskContentString(String[] words, int byIndex) throws Exception {
        StringBuilder contentBuilder = new StringBuilder();
        for (int i = 1; i < byIndex; i++) {
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
