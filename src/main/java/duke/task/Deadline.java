package duke.task;

import duke.parser.Parser;

public class Deadline extends Task {
    private String deadline;

    public Deadline(String content, String deadline) {
        super(content);
        this.taskType = TaskType.DEADLINE;
        this.deadline = deadline;
    }

    public static void isDeadlineCommandValid(String userInput) throws Exception {
        if (!userInput.contains("/by")) {
            throw new Exception("Invalid deadline command. Check 'help'.\n");
        }
    }

    public static String[] getDeadlineTaskContent(String userInput) throws Exception {
        String[] words = userInput.split(" ");
        int indexOfByWord = 0;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals("/by")) {
                indexOfByWord = i;
                break;
            }
        }
        return Parser.parseTaskContentAndTime(words, indexOfByWord);
    }


    public String getDeadline() {
        return deadline;
    }

    public String getTimeLimitString() {
        return "(by: " + deadline + ")";
    }

}
