public abstract class Task {
    protected String description;
    protected boolean isDone;



    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    public String getDescription(){
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : ""); //return X or empty symbols
    }
    public void markAsDone(){
        isDone = true;
    }
    public abstract String getType();
    public String getStatusNum() { return (isDone ? "1" : "0");}


    public String toString(){
        return "description: " + description;
    }
    public String toFileString(){
        return null;
    };

}
