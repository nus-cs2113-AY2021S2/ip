package duke.taskclass;
public class Deadlines extends Task{

    protected String by;

    public Deadlines(String description, String by){
        super(description);
        this.by = by;
    }

    public String getBy(){
        return by;
    }

    @Override
    public String toString(){
        return ("[D]" + super.toString() + " (by: "+by+")");
    }
}
