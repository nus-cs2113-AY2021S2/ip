package Duke;

public class Deadline extends Task {
    protected String time;

    public Deadline(String description, String time) {
        super(description, time);
    }

    public String getAlphabet() {
        return "D";
    }

}
