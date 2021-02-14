package list;

public class Event extends Deadline {
    private static final String TASK_TITLE = "E";

    public Event(String line) {
        super(line);
    }

    @Override
    public String getBy(String line) {
        String[] lineWords = line.split("/", 2);
        String by;
        try {
            by = lineWords[1];

        } catch (ArrayIndexOutOfBoundsException e) {
            by = "No Event time!! Noice!!! :)";
        }

        return by;

    }

    @Override
    public void printTask() {
        String phrase = "[" + TASK_TITLE + "]" + "[" + getStatusIcon() + "]"
                + getTaskDescription() + "(by: "
                + getTaskBy() + ")";
        System.out.println(phrase);
    }

    @Override
    public void printAddedTask() {
        System.out.println("Aight Crewmate!! I've got a new event for you!!! (¬‿¬): " + getTaskDescription());
    }
    public String getTaskToPrintInFile() {
        return ("event " + this.description + "/" + this.by);
    }
}

