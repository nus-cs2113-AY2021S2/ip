public class task {
    protected String description;
    protected boolean resolved;

    public task(){
        this.description = "";
        this.resolved = false;
    }

    public task(String desc){
        this.description = desc;
        this.resolved = false;
    }

    public void resolve(){
        this.resolved = true;
    }

}
