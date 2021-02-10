package duke;

public class ToDos extends Task{


    public ToDos(String task){
        super(task);
    }

    @Override
    public void printStatus() {
        System.out.print("[T]");
        super.printStatus();
        System.out.print("\n");
    }
}
