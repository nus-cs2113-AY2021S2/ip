package duke.task;

import duke.exception.InvalidDateTimeException;
import duke.exception.MissingDueDateException;
import duke.exception.MissingTaskDescriptionException;


import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Deadline extends Task {
    protected LocalDateTime dueDate;

    /**
     * Deadline constructor method
     * @param task is the task description
     * @param dueDate is he task due date - specific to deadlines
     */
    public Deadline(String task, String dueDate) {
        super(task);
        this.dueDate = parseDateTime(dueDate);
    }

    public LocalDateTime parseDateTime(String dueDate){
        DateTimeFormatter parseFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a");

        return LocalDateTime.parse(dueDate, parseFormatter);
    }

    public String writeDateTime() {
        DateTimeFormatter parseFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a");
        return parseFormatter.format(dueDate);
    }

    public String printDateTime() {
        DateTimeFormatter printFormatter = DateTimeFormatter.ofPattern("E, d MMM yyyy hh:mm a");
        return printFormatter.format(dueDate);
    }

    public String toBaseString(){
        return super.toString();
    }

    @Override
    public String toString() {
        return "Deadline : " + toBaseString() + " || Due by: " + this.printDateTime();
    }

    // Exceptions

    public static void checkDeadlineInput(String[] taskDetails) throws MissingDueDateException, MissingTaskDescriptionException, InvalidDateTimeException {
        try {
            DateTimeFormatter parseFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a");
            LocalDateTime.parse(taskDetails[1], parseFormatter);
        } catch (DateTimeException e) {
            throw new InvalidDateTimeException();
        }
        if (taskDetails[0] == null){
            throw new MissingTaskDescriptionException();
        }
        if (taskDetails[1] == null) {
            throw new MissingDueDateException();
        }


    }
}
