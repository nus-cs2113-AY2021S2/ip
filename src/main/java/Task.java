public class Task {
    protected String description;
    protected boolean resolved;

    public Task(){
        this.description = "";
        this.resolved = false;
    }

    public Task(String desc){
        this.description = desc;
        this.resolved = false;
    }

    public void resolve(){
        this.resolved = true;
    }

}
