public class ToDoTask extends Task {

    public ToDoTask(String name) {
        super(name);
    }

    /**
     * Prints task status.
     */
    @Override
    public void printStatus() {
        System.out.print("[T]");
        super.printStatus();
    }
}
