public class Deadline extends Task{
    protected String dueDate;

    public Deadline(String task, String dueDate) {
        super(task);
        this.dueDate = dueDate;
    }

    public String getDateTime(){
        return this.dueDate;
    }

    public String toBaseString(){
        return super.toString();
    }

    @Override
    public String toString() {
        return "Deadline : " + toBaseString() + " || Due by: " + this.getDateTime();
    }
}
