package Duke.Tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    LocalDate deadline;

    public Deadline(String nameInit, LocalDate deadline) {
        super(nameInit);
        this.deadline = deadline;
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
        DateTimeFormatter formatObject = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String formattedDeadline = deadline.format(formatObject);
        outputString = outputString + " " + name + " (by: " + formattedDeadline + ")";
        return outputString;
    }

    public String toStringSave() {
        String saveString = "D | ";
        if (isDone) {
            saveString += "1 | ";
        }
        else {
            saveString += "0 | ";
        }
        saveString = saveString + name + " | " + deadline;
        return saveString;
    }

    public LocalDate getDate() {
        return deadline;
    }

}
