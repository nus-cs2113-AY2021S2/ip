package duke.task;

import duke.parser.Parser;

public class Event extends Task {
    private String period;

    public Event(String listContent, String period) {
        super(listContent);
        this.taskType = TaskType.EVENT;
        this.period = period;
    }

    public static void isEventCommandValid(String userInput) throws Exception {
        if (!userInput.contains("/at")) {
            throw new Exception("Invalid event command. Check 'help'.\n");
        }
    }

    public static String[] getEventTaskContent(String userInput) throws Exception {
        String[] words = userInput.split(" ");
        int indexOfAtWord = 0;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals("/at")) {
                indexOfAtWord = i;
                break;
            }
        }
        return Parser.parseTaskContentAndTime(words, indexOfAtWord);
    }

    public String getPeriod() {
        return period;
    }

    public String getTimeLimitString() {
        return "(at: " + period + ")";
    }
}
