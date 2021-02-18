package Duke;

public class Deadline extends Task {
    protected String time;

    public Deadline(String description, String time) {
        super(description, time);
    }

    @Override
    public String getAlphabet() {
        return "D";
    }

    public String toString(){
        return this.description + " (by: " + this.time + ")" ;
    }
}
