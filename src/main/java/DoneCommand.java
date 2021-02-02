public class DoneCommand implements Command {
    public DoneCommand(String input) {
    }

    public void execute(String input) {
        String[] command = input.trim().split(" ");
        int num = Integer.parseInt(command[1]);
        TaskManager.tasks[num - 1].setAsDone();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(TaskManager.tasks[num - 1]);
        end();
    }

    public static void end() {
        System.out.println("____________________________________________________________" + System.lineSeparator());
    }
}
