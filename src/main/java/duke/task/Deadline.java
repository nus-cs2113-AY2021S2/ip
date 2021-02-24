package duke.task;

import duke.parser.Parser;

import java.time.LocalDate;

public class Deadline extends Task {
    private LocalDate deadline;

    public Deadline(String content, LocalDate deadline) {
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


    public LocalDate getDeadline() {
        return deadline;
    }

    public String getTimeLimitString() {
        return deadline.toString();
    }

}
