public class Deadline extends Task{
    private static final String ALPHABET_D = "D";
    private String by;

    public Deadline(String d, String by){
        super(d);
        this.by = by;
    }

    @Override
    public String getTaskType(){
        return ALPHABET_D;
    }

    @Override
    public void printTaskInformation(){
        super.printTaskInformation();
        this.printTime();
    }

    public void printTime(){
        System.out.print(" (by:" + this.by + ")");
    }

}
