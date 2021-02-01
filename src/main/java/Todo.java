public class Todo extends Task {
    /**
     * Parses the content of a Task object from user input and return it in an array.
     *
     * @param userInput User's keyboard input in String.
     * @return An array that stores the content of the to-do Task.
     */
    public static String[] parseTaskContent(String userInput) {
        String[] parseResult = new String[1];
        parseResult[0] = userInput.substring(5).trim();
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
