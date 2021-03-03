package Duke.Tasks;

import java.time.LocalDate;

public class Todo extends Task {

    public Todo(String nameInit) {
        super(nameInit);
    }

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

    public LocalDate getDate() {
        return null;
    }

}
