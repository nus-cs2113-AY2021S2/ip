public class Deadline extends Tasks{
    protected String at;
    public Deadline(String description, String taskType,String at){
        super(description,taskType);
        this.at = at;
        super.extraDescription = "( at: " + at+ " )";
    }
}