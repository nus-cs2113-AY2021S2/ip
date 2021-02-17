package Duke;

public class Event extends Task {
    protected String time;

    public Event(String description, String time)  {
        super(description, time);
    }

    @Override
    public String getAlphabet(){
        return "E";
    }
}
