public class Event extends Task {
    protected String at;

    public Event(String inputJob, String at){
        super(inputJob);
        this.at = at;
    }

    public String getAt() {
        return at;
    }

    public void setAt(String at) {
        this.at = at;
    }


    @Override
    public String addLabel(String s){
        String label = "[E]";
        label += s;
        return label;
    }

    @Override
    public String addEnd(String s){
        String end = " (" + "at: " + this.at + ")";
        return s.concat(end);
    }

}
