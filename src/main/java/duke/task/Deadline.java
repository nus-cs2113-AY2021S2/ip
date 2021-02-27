package duke.task;

import duke.date.Date;
import duke.exceptions.EmptyInputException;
import duke.exceptions.IncompleteInputException;
import duke.exceptions.InvalidDateInputException;

public class Deadline extends Task{
    private static final String ALPHABET_D = "D";
    private String byTime;

    public Deadline(String d, String by){
        super(d);
        this.byTime = by;
    }

    @Override
    public String getDate() {
        return this.byTime;
    }

    @Override
    public String getTaskType(){
        return ALPHABET_D;
    }

    @Override
    public void printTaskInformation(){
        super.printTaskInformation();
        this.printTime();
    }

    public void printTime(){
        System.out.print(" (by:" + this.byTime + ")");
    }

    @Override
    public void printInputErrorMessage(String userInput) {
        super.printInputErrorMessage(userInput);
        System.out.println("  deadline [deadline name] /by [MM-DD-YYYY]\n"
                + "    e.g. deadline return book /by 03-28-2021");
    }

    @Override
    public void printInvalidDateInputMessage(String userInput) {
        super.printInvalidDateInputMessage(userInput);
        System.out.println("  deadline [deadline name] /by [MM-DD-YYYY]\n"
                + "    e.g. deadline return book /by 03-28-2021");
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
