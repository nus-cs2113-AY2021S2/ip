public class Deadline extends Task {
    private String deadline;

    public Deadline(String content, String deadline) {
        super(content);
        this.taskType = TaskType.DEADLINE;
        this.deadline = deadline;
    }

    public static void isCommandValid(String userInput) throws Exception {
        if (!userInput.contains("/by")) {
            throw new Exception("Invalid deadline command. Check 'help'.\n");
        }
    }

    public static String[] parseTaskContent(String userInput) throws Exception {
        String[] words = userInput.split(" ");
        int byIndex = 0;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals("/by")) {
                byIndex = i;
                break;
            }
        }

        return parseContentAndTime(words, byIndex);
    }

    public static String[] parseContentAndTime(String[] words, int byIndex) throws Exception {
        String content = getTaskContentString(words, byIndex);
        String deadline = getTaskDeadlineString(words, byIndex);
        String[] parseResult = new String[2];
        parseResult[0] = content;
        parseResult[1] = deadline;
        return parseResult;
    }

    private static String getTaskDeadlineString(String[] words, int byIndex) throws Exception {
        StringBuilder deadlineBuilder = new StringBuilder();
        for (int k = (byIndex + 1); k < words.length; k++) {
            String deadlineWord = words[k] + " ";
            deadlineBuilder.append(deadlineWord);
        }
        String deadline = deadlineBuilder.toString().trim();
        if (deadline.length() < 1) {
            throw new Exception("Time limit must be input! Check 'help'.\n");
        }
        return deadline;
    }

    private static String getTaskContentString(String[] words, int byIndex) throws Exception {
        StringBuilder contentBuilder = new StringBuilder();
        for (int j = 1; j < byIndex; j++) {
            String contentWord = words[j] + " ";
            contentBuilder.append(contentWord);
        }
        String contentStr = contentBuilder.toString().trim();
        if (contentStr.length() < 1) {
            throw new Exception("Task content must be input! Check 'help'.\n");
        }
        return contentStr;
    }


    public String getDeadline() {
        return deadline;
    }

    public String getTimeLimitString() {
        return "(by: " + deadline + ")";
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }
}
