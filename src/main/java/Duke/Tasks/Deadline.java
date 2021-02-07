package Duke.Tasks;

public class Deadline extends Task {

    private String endTime;

    public Deadline(String nameInit, String time) {
        super(nameInit);
        this.endTime = time;
    }

    @Override
    public String toString() {
        String outputString = "[D]";
        if (isDone) {
            outputString += "[\u2713]";
        }
        else {
            outputString += "[\u2715]";
        }
        outputString = outputString + " " + name + " (by: " + endTime + ")";
        return outputString;
    }

}
