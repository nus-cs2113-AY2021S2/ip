package tasks;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns every information about the task in correct format for storage
     *
     * @return String that has all the information about the task
     */
    @Override
    public String toSaveFormat() {
        if (isDone) {
            return "T " + "Y " + description + "\n";
        }
        else {
            return "T " + "N " + description + "\n";
        }
    }

    /**
     * Returns every information about the task in correct format for printing
     *
     * @return String that has all the information about the task
     */
    @Override
    public String toString() {
        return "[T]" + super.getStatus() + " " + super.getDescription();
    }
}
