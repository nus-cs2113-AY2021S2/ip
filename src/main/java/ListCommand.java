public class ListCommand implements Command{

    public ListCommand(String input) {
    }

    public void execute(String input) {
        int count = 0;
        while (count < TaskStorer.numOfTasks) {
            System.out.println(count + 1 + ": " + TaskStorer.tasks[count]);
            count++;
        }
        end();
    }

    public static void end() {
        System.out.println("____________________________________________________________" + System.lineSeparator());
    }
}
