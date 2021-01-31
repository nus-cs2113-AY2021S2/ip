public class Deadline extends Task {
    private String deadline;

    public Deadline(String content, String deadline) {
        super(content);
        this.taskType = TaskType.DEADLINE;
        this.deadline = deadline;
    }

    public static boolean isCommandValid(String userInput) {
        return userInput.contains("/by");
    }

    public static String[] parseTaskContent(String userInput) {
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

    public static String[] parseContentAndTime(String[] words, int byIndex) {

        StringBuilder contentBuilder = new StringBuilder();
        for (int j = 1; j < byIndex; j++) {
            String contentWord = words[j] + " ";
            contentBuilder.append(contentWord);
        }
        String content = contentBuilder.toString();

        StringBuilder deadlineBuilder = new StringBuilder();
        for (int k = (byIndex + 1); k < words.length; k++) {
            String deadlineWord = words[k] + " ";
            deadlineBuilder.append(deadlineWord);
        }
        String deadline = deadlineBuilder.toString();

        String[] parseResult = new String[2];
        parseResult[0] = content.trim();
        parseResult[1] = deadline.trim();
        return parseResult;
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
