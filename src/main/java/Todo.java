public class Todo extends Task {
    public static String[] parseTaskContent(String userInput) {
        String[] parseResult = new String[1];
        parseResult[0] = userInput.substring(5).trim();
        return parseResult;
    }

    public Todo(String content) {
        super(content);
        this.taskType = TaskType.TODO;
    }

    public String getTimeLimitString() {
        return "";
    }
}
