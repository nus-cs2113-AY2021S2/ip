import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        DukeCommandHandler.greeting();
        Scanner in = new Scanner(System.in);
        String input;

        while (in.hasNext()) {
            input = in.nextLine();
            if (input.equals("list")) {
                DukeCommandHandler.listTask();
            } else if (input.contains("done")) {
                DukeCommandHandler.markTaskDone(input);
            } else if (input.contains("add")) {
                DukeCommandHandler.addTask(input);
            } else if (input.contains("todo")) {
                DukeCommandHandler.addToDo(input);
            } else if (input.contains("deadline")) {
                DukeCommandHandler.addDeadline(input);
            } else if (input.contains("event")) {
                DukeCommandHandler.addEvent(input);
            } else if (input.equals("bye")) {
                DukeCommandHandler.exitDuke();
                break;
            } else {
                DukeException.invalidCommand();
            }
        }
        in.close();

    }
}
