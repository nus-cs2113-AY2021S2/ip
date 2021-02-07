public class Deadline extends Task {
    protected String dueDate;

    public Deadline(String task, String dueDate) {
        super(task);
        this.dueDate = dueDate;
    }

    public String getDateTime(){
        return this.dueDate;
    }

    public String toBaseString(){
        return super.toString();
    }

    @Override
    public String toString() {
        return "Deadline : " + toBaseString() + " || Due by: " + this.getDateTime();
    }

    // Exceptions

    public static void checkDeadlineInput(String[] taskDetails) throws MissingDueDateException, MissingTaskDescriptionException {
        if (taskDetails[0] == null){
            throw new MissingTaskDescriptionException();
        }
        if (taskDetails[1] == null) {
            throw new MissingDueDateException();
        }

    }
}
