public class Deadline extends Task{

    protected String by;

    public Deadline(String inputJob, String by) {
        super(inputJob);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }


    @Override
    public String addLabel(String s) {
        String label = "[D]";
        label += s;
        return label;
    }

    @Override
    public String addEnd(String s){
        String end = " (" + "by: " + this.by + ")";
        return s.concat(end);
    }
}
