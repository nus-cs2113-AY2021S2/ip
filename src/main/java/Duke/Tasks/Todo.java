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
            outputString += "[Y]";
        }
        else {
            outputString += "[N]";
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
     * Todo tasks does not have a date, thus null is returned.
     * @return null
     */
    public LocalDate getDate() {
        return null;
    }

}
