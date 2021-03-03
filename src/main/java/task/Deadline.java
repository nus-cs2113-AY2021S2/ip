package task;

import java.time.LocalDate;
import java.time.LocalTime;

public class Deadline extends Task {
    protected LocalDate date;
    protected LocalTime time;

    public Deadline(String description, LocalDate date, LocalTime time) {
        super(description);
        this.date = date;
        this.time = time;
    }

    public LocalDate getDate(){
        return date;
    }
    public LocalTime getTime(){
        return time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date + " " + time + ")";
    }

}

