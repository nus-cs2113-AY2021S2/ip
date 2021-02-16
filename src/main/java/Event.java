public class Event extends Task{
    private String date;

    public Event(String desc, String date) {
        super(desc);
        this.date = date;
    }

    public Event(String desc, boolean isDone, String date) {
        super(desc, isDone);
        this.date = date;
    }

    @Override
    public String getDate() {
        return date;
    }

    public String toString() {
        return " [E]" + super.toString() + " (at: " + date + ")";
    }
}
