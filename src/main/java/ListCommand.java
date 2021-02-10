public class ListCommand extends Command{
    private final int counter;
    ListCommand(Tasks[] tasks, int counter) {
        super(tasks);
        this.counter = counter;
    }

    void printList(){
        displayLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i< this.counter; i++) {
            System.out.println(i+1+" .[ " + tasks[i].displayTaskType()+ " ]" + " [" +
                    tasks[i].setDisplay()+ "] " + tasks[i].getDescription() + tasks[i].extraDescription);
        }
        displayLine();
    }
}
