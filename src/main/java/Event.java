public class Event extends Task {

    private String period;

    public Event(String nameInit, String timePeriod) {
        super(nameInit);
        this.period = timePeriod;
    }

    @Override
    public String toString() {
        String outputString = "[E]";
        if (isDone) {
            outputString += "[\u2713]";
        }
        else {
            outputString += "[\u2715]";
        }
        outputString = outputString + " " + name + " (at: " + period + ")";
        return outputString;
    }
}
