public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone(){
        isDone = true;
    }
    public String getTask(){
        return description;
    }

    @Override
    public String toString(){
        if(isDone){
            return "[X] " + description;
        }
        else{
            return "[ ] " + description;
        }
    }

}
