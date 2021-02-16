package list;

public class Deadline extends TaskList {
    private static final String TASK_TITLE = "D";
    protected String by;


    public Deadline(String line) {
        this.isDone = false;
        this.description = getDescription(line);
        this.by = getBy(line);
        printAddedTask();
    }

    private String getDescription(String line) {
        String[] description = line.split("/", 2);
        return description[0].trim();
    }

    public String getTaskBy() {
        return this.by;
    }

    public String getBy(String line) {
        String[] lineWords = line.split("/", 2);
        String by;
        try {
            by = lineWords[1];
            if (by.equals("")) {
                by = "No Deadline!! Hehe! :)";
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            by = "No Deadline!! Hehe! :)";
        }
        return by.trim();
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
        System.out.println("Aight Crewmate!! I've got a new deadline for you!!! (¬‿¬): " + getTaskDescription());
    }

    @Override
    public String getTaskToPrintInFile() {
        return ("deadline " + this.description + "/" + this.by + System.lineSeparator() + this.isDone
                + System.lineSeparator());
    }
}
