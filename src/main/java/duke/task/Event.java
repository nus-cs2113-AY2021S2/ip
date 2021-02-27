package duke.task;

import duke.date.Date;
import duke.exceptions.EmptyInputException;
import duke.exceptions.IncompleteInputException;
import duke.exceptions.InvalidDateInputException;

public class Event extends Task{
    private static final String ALPHABET_E = "E";
    private String atTime;

    public Event(String d, String at){
        super(d);
        this.atTime = at;
    }

    @Override
    public String getDate() {
        return this.atTime;
    }

    @Override
    public String getTaskType(){
        return ALPHABET_E;
    }

    @Override
    public void printTaskInformation(){
        super.printTaskInformation();
        this.printTime();
    }

    public void printTime(){
        System.out.print(" (at:" + this.atTime + ")");
    }

    @Override
    public void printInputErrorMessage(String userInput) {
        super.printInputErrorMessage(userInput);
        System.out.println("  event [event name] /at [MM-DD-YYYY]\n"
                + "    e.g. event project meeting /at 03-28-2021");
    }

    @Override
    public void printInvalidDateInputMessage(String userInput) {
        super.printInvalidDateInputMessage(userInput);
        System.out.println("  event [event name] /at [MM-DD-YYYY]\n"
                + "    e.g. event project meeting /at 03-28-2021");
        System.out.println("Please check if date is:");
        System.out.println("1. Out of range.");
        System.out.println("2. Not a future date.");
        System.out.println("3. In incorrect format.");
    }

    @Override
    public void addTask() throws EmptyInputException, IncompleteInputException, InvalidDateInputException {
        // Check if date field is valid
        if (Date.isIncompleteDate(this)){
            throw new IncompleteInputException();
        }
        if (Date.isInvalidDate(this)){
            throw new InvalidDateInputException();
        }
        super.addTask();
    }
}
