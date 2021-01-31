public class Event extends Task {
    private static final String TASK_SYMBOL_EVENT = "E";

    public Event(String descrption) {

    }

    @Override
    public String getTaskSymbol() {
        return TASK_SYMBOL_EVENT;
    }
}
