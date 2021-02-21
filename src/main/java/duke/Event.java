package duke;

public class Event extends Task {
    private String duration;
    private String keyWordBeforeDuration;

    public Event(String description) {
        super(description);
        preprocessInput(description);
    }

    private void preprocessInput(String input){
        int indexOfBackslash = input.indexOf("/");
        this.description = input.substring(0,indexOfBackslash-1);
        String stringAfterBackslash = input.substring(indexOfBackslash+1);
        int indexFirstSpaceAfterBackslash = stringAfterBackslash.indexOf(" ");
        this.keyWordBeforeDuration =
                stringAfterBackslash.substring(0, indexFirstSpaceAfterBackslash);
        this.duration = stringAfterBackslash.substring(indexFirstSpaceAfterBackslash + 1);
    }

    public String getDuration() {
        return duration;
    }

    public String getKeyWordBeforeDuration() {
        return keyWordBeforeDuration;
    }

    @Override
    public void printTask() {
        System.out.printf("[E][%s] %s  (%s: %s)\n",
                super.getStatusIcon(), super.getDescription().substring(6),
                getKeyWordBeforeDuration(), getDuration());
    }
}
