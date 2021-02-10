public class Todo extends Task {
    public static String[] parseTaskContent(String userInput) throws Exception {
        String[] parseResult = new String[1];
        String contentStr = userInput.substring(5).trim();
        if (contentStr.length() < 1) {
            throw new Exception("Invalid todo command! Check 'help'.\n");
        }
        parseResult[0] = contentStr;
        return parseResult;
    }

    /**
     * Initializes a To-do type of Task object.
     *
     * @param content The content of the To-do Task.
     */
    public Todo(String content) {
        super(content);
        this.taskType = TaskType.TODO;
    }

    /**
     * Gets the time limit of the task, if applicable.
     *
     * @return "".
     */
    public String getTimeLimitString() {
        return "";
    }
}
