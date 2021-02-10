public class Event extends Task {
    private String startTime, endTime, period;

    public Event(String content, String startTime, String endTime) {
        super(content);
        this.taskType = TaskType.EVENT;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Event(String listContent, String period) {
        super(listContent);
        this.taskType = TaskType.EVENT;
        this.period = period;
    }

    public static void isCommandValid(String userInput) throws Exception {
        if (!userInput.contains("/at")) {
            throw new Exception("Invalid event command. Check 'help'.\n");
        }
    }

    public static String[] parseTaskContent(String userInput) throws Exception {
        String[] words = userInput.split(" ");
        int atIndex = 0;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals("/at")) {
                atIndex = i;
                break;
            }
        }

        return Deadline.parseContentAndTime(words, atIndex);
    }

    private boolean checkValidPeriod(String startTime, String endTime) {
        // WIP: return startTime < endTime;
        return true;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getPeriod() {
        return period;
    }

    public String getTimeLimitString() {
        return "(at: " + period + ")";
    }

    public void setPeriod(String period) {
        this.period = period;
    }
}
