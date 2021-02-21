package duke;

public class Deadline extends Task {
    private String deadline;
    private String keyWordBeforeDeadline;

    public Deadline(String description) {
        super(description);
        preprocessInput(description);
    }

    private void preprocessInput(String input){
        int indexOfBackslash = input.indexOf("/");
        this.description = input.substring(0,indexOfBackslash);
        String stringAfterBackslash = input.substring(indexOfBackslash+1);
        int indexFirstSpaceAfterBackslash = stringAfterBackslash.indexOf(" ");
        this.keyWordBeforeDeadline =
                stringAfterBackslash.substring(0, indexFirstSpaceAfterBackslash);
        this.deadline = stringAfterBackslash.substring(indexFirstSpaceAfterBackslash + 1);
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getKeyWordBeforeDeadline() {
        return keyWordBeforeDeadline;
    }

    public void setKeyWordBeforeDeadline(String keyWordBeforeDeadline) {
        this.keyWordBeforeDeadline = keyWordBeforeDeadline;
    }

    @Override
    public void printTask() {
        System.out.printf("[D][%s] %s (%s: %s)\n",
                super.getStatusIcon(), super.getDescription().substring(9),
                getKeyWordBeforeDeadline(), getDeadline());
    }
}
