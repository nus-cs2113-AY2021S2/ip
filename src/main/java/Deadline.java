public class Deadline extends Task{

    protected String by;

    public Deadline() {
        super();
        this.by = null;
    }

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
    public void printTask() {
        String doneBox = "[X] ";
        String emptyBox = "[ ] ";

        String output = this.isDone ? doneBox : emptyBox;
        output += this.job;

        output = addLabel(output);

        output += " (" + "by:" + this.by + ")";

        System.out.println(output);

    }

    @Override
    public String addLabel(String s) {
        String label = "[D]";
        label += s;
        return label;
    }
}
