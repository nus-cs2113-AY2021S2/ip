public class DoneCommand implements Command {
    public DoneCommand(String input) {
    }

    public void execute(String input) {
        String[] command = input.trim().split(" ");
        int num = 0;
        try {
            num = Integer.parseInt(command[1]);
            TaskManager.tasks[num - 1].setAsDone();
            System.out.println("Nice! I've marked this task as done: ");
            System.out.println(TaskManager.tasks[num - 1]);
        } catch (NumberFormatException e) { //to check if the word after done is a number
            System.out.println("Error: input is not a number.");
        } catch (NullPointerException e) { //to check if the number after done is a valid number for a task
            System.out.println("Error: input is invalid");
        }
        end();
    }

    public static void end() {
        System.out.println("____________________________________________________________" + System.lineSeparator());
    }
}
