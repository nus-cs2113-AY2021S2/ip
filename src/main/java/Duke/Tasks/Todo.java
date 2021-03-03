package Duke.Tasks;

import java.time.LocalDate;

public class Todo extends Task {

    /**
     * constructor for the Todo task
     * @param nameInit name of the task
     */
    public Todo(String nameInit) {
        super(nameInit);
    }

    /**
     *
     * @return the task formatted nicely in a string for output purposes
     */
    @Override
    public String toString() {
        String outputString = "[T]";
        if (isDone) {
            outputString += "[\u2713]";
        }
        else {
            outputString += "[\u2715]";
        }
        outputString = outputString + " " + name;
        return outputString;
    }

    /**
     *
     * @return the string to be saved by the program representing the different attributes of the task
     */
    public String toStringSave() {
        String saveString = "T | ";
        if (isDone) {
            saveString += "1 | ";
        }
        else {
            saveString += "0 | ";
        }
        saveString = saveString + name;
        return saveString;
    }

    /**
     *
     * @return the date of the task, which is null as Todo objects does not have a date attribute
     */
    public LocalDate getDate() {
        return null;
    }

}
