public class Task {
    private String content;
    private boolean done;

    public Task(String content) {
        this.content = content;
        this.done = false;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDone(boolean done) {
        this.done = done;
    }


    public String getContent() {
        return content;
    }

    public boolean isDone() {
        return done;
    }


    public String getStatusIcon() {
        return (done ? "\u2718" : " "); //return tick or X symbols
    }

}
