import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws duke.exception.DukeException {
        final String LOGO = "\n" +
                " .----------------.  .----------------. \n" +
                "| .--------------. || .--------------. |\n" +
                "| |    ______    | || |        __    | |\n" +
                "| |  .' ____ '.  | || |    _  / /    | |\n" +
                "| |  | (____) |  | || |   (_)/ /     | |\n" +
                "| |  '_.____. |  | || |     / / _    | |\n" +
                "| |  | \\____| |  | || |    / / (_)   | |\n" +
                "| |   \\______,'  | || |   /_/        | |\n" +
                "| |              | || |              | |\n" +
                "| '--------------' || '--------------' |\n" +
                " '----------------'  '----------------' ";
        final String DECO_LINE = "____________________________________________________________";
        final String HELLO_MESSAGE = " Hello! I'm 9%.";
        final String ASK_MESSAGE = " What can I do for you? (todo/deadline/event)";
        final String BYE_MESSAGE = " Bye. Hope to see you again soon!";
        final String ERROR_MESSAGE = " Sorry, I can't recognize your input.";
        final String ADD_TASK = " Got it. I've added this task:";
        final String FILE = "./ip/src/main/java/db/tasks.txt";

        Storage storage = new Storage(FILE);
        ArrayList<Task> inputs = new ArrayList<>(storage.loadTasks());

        System.out.println(LOGO);
        System.out.println(DECO_LINE);
        System.out.println(HELLO_MESSAGE);
        System.out.println(ASK_MESSAGE);
        System.out.println(DECO_LINE);

        Scanner readinput = new Scanner(System.in);
        String input = readinput.nextLine();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.println(DECO_LINE);
                for (int i = 0; i < inputs.size(); i++) {
                    System.out.println(" " + (i + 1) + ". " + inputs.get(i).toString());
                }
                System.out.println(DECO_LINE);
            } else if (input.split(" ")[0].equals("done")) {
                System.out.println(DECO_LINE);
                try {
                    int ind = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (ind < inputs.size() && ind >= 0) {
                        if (inputs.get(ind).getIsDone()) {
                            System.out.println(" Task is already marked done!");
                        } else {
                            inputs.get(ind).markAsDone();
                            System.out.println(" " + inputs.get(ind).toString());
                        }
                    } else {
                        System.out.println(" Index is out of boundary!");
                    }
                } catch (NumberFormatException nfe) {
                    System.out.println(DECO_LINE);
                    System.out.println(" Format is wrong!");
                    System.out.println(DECO_LINE);
                }
                System.out.println(DECO_LINE);
            } else if (input.split(" ")[0].equals("remove")) {
                System.out.println(DECO_LINE);
                try {
                    int ind = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (ind < inputs.size() && ind >= 0) {
                        inputs.remove(ind);
                        System.out.println(" Task is removed!");
                    } else {
                        System.out.println(" Index is out of boundary!");
                    }
                } catch (NumberFormatException nfe) {
                    System.out.println(DECO_LINE);
                    System.out.println(" Format is wrong!");
                    System.out.println(DECO_LINE);
                }
                System.out.println(DECO_LINE);
            } else if (input.split(" ")[0].equals("todo")) {
                String task = input.split(" ", 2)[1];
                inputs.add(new Todo(task, false));
                System.out.println(DECO_LINE);
                System.out.println(ADD_TASK);
                System.out.println("   [T][ ] " + task);
                System.out.println(" Now you have " + inputs.size() + " tasks in the list.");
                System.out.println(DECO_LINE);
            } else if (input.split(" ")[0].equals("deadline")) {
                try {
                    String task = input.split(" /by ")[0].split(" ", 2)[1];
                    String deadline = input.split(" /by ")[1];
                    inputs.add(new Deadline(task, false, deadline));
                    System.out.println(DECO_LINE);
                    System.out.println(ADD_TASK);
                    System.out.println("   [D][ ] " + task + " (by: " + deadline + ")");
                    System.out.println(" Now you have " + inputs.size() + " tasks in the list.");
                    System.out.println(DECO_LINE);
                } catch (Exception nfe) {
                    System.out.println(DECO_LINE);
                    System.out.println("Format is wrong! You should write \"deadline <event> /by <deadline>\"");
                    System.out.println(DECO_LINE);
                }
            } else if (input.split(" ")[0].equals("event")) {
                try {
                    String task = input.split(" /at ")[0].split(" ", 2)[1];
                    String schedule = input.split(" /at ")[1];
                    inputs.add(new Event(task, false, schedule));
                    System.out.println(DECO_LINE);
                    System.out.println(ADD_TASK);
                    System.out.println("   [E][ ] " + task + " (at: " + schedule + ")");
                    System.out.println(" Now you have " + inputs.size() + " tasks in the list.");
                    System.out.println(DECO_LINE);
                } catch (Exception nfe) {
                    System.out.println(DECO_LINE);
                    System.out.println("Format is wrong! You should write \"event <event> /at <schedule>\"");
                    System.out.println(DECO_LINE);
                }
            } else {
                System.out.println(DECO_LINE);
                System.out.println(ERROR_MESSAGE);
                System.out.println(DECO_LINE);
            }
            input = readinput.nextLine();
        }

        storage.saveTasks(inputs);
        System.out.println(DECO_LINE);
        System.out.println(BYE_MESSAGE);
        System.out.println(DECO_LINE);
    }
}
