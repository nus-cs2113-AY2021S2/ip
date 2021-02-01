public class Event extends Task{
    private String duration;
    private String keyWordBeforeDuration;

    public Event(String description, String duration, String keyWordBeforeDuration) {
        super(description);
        this.duration = duration;
        this.keyWordBeforeDuration = keyWordBeforeDuration;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getKeyWordBeforeDuration() {
        return keyWordBeforeDuration;
    }

    public void setKeyWordBeforeDuration(String keyWordBeforeDuration) {
        this.keyWordBeforeDuration = keyWordBeforeDuration;
    }

    @Override
    public void printTask() {
        System.out.printf("[E][%s] %s (%s: %s)\n",
                super.getStatusIcon(), super.getDescription(),
                getKeyWordBeforeDuration(), getDuration());
    }
}
