package Duke.Tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    /**
     * Deadline of the event
     */
    LocalDate deadline;

    /**
     * Constructor for a deadline class
     * @param nameInit
     * @param deadline
     */
    public Deadline(String nameInit, LocalDate deadline) {
        super(nameInit);
        this.deadline = deadline;
    }

    /**
     *
     * @return the string to be saved by the program representing the different attributes of the task
     */
    @Override
    public String toString() {
        String outputString = "[D]";
        if (isDone) {
            outputString += "[Y]";
        }
        else {
            outputString += "[N]";
        }
        DateTimeFormatter formatObject = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String formattedDeadline = deadline.format(formatObject);
        outputString = outputString + " " + name + " (by: " + formattedDeadline + ")";
        return outputString;
    }

    /**
     *
     * @return the string to be saved by the program representing the different attributes of the task
     */
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

    /**
     *
     * @return the date of the deadline
     */
    public LocalDate getDate() {
        return deadline;
    }

}
