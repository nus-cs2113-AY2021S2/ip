public class DoneCommand extends Command{
    private final int n;

    DoneCommand(Tasks[] tasks, int n) {
        super(tasks);
        this.n = n;
    }

    public void markDone() {
        displayLine();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("[" +tasks[n-1].displayTaskType()+ "] [\u2718] " + tasks[n-1].getDescription());
        displayLine();
        tasks[n-1].setDone();
    }
}
