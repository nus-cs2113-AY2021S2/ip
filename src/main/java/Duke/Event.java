package Duke;

public class Event extends Task {

    public Event(String description, String time) {
        super(description, time);
    }

    @Override
    public String getAlphabet() {
        return "E";
    }

    public String toString(){
        return this.description + " (at: " + this.time + ")" ;
    }
}
