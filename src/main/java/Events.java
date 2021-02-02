public class Events extends Task{

    private String by;

    public Events(String task,String by){
        super(task);
        this.by=by;
    }

    public void printStatus() {
        System.out.print("[E]");
        super.printStatus();
        System.out.println("(at: "+by+")");
    }
}
