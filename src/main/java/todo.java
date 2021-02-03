public class todo extends Task {

    protected String by;
    protected boolean isDeadline;

    public todo (String description) {
        super(description);
    }

    public String getType(){
        return "T";
    }
}