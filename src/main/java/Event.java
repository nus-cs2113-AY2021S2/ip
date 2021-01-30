public class Event extends Task{
    private static final String ALPHABET_E = "E";
    private String at;

    public Event(String d, String at){
        super(d);
        this.at = at;
    }

    @Override
    public String getTaskType(){
        return ALPHABET_E;
    }

    @Override
    public void printTaskInformation(){
        super.printTaskInformation();
        this.printTime();
    }

    public void printTime(){
        System.out.print(" (at:" + this.at + ")");
    }

}
