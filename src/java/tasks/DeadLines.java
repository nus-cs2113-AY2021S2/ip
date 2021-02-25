package tasks;

import tasks.Task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DeadLines extends Task {

    private String by;

    public DeadLines(String task, String by) {
        super(task);
        this.by = by;
    }

    @Override
    public void printStatus() {
        System.out.print("[D]");
        super.printStatus();
        try {
            String words[] = by.split(" ");
            String date = words[0];
            String time = words[1];
            LocalDate d1 = LocalDate.parse(date);
            System.out.println("(by: " + d1.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ", " + time + ")");
        } catch(DateTimeParseException e){
            System.out.println("(by: "+by+")");
        }
    }
}
