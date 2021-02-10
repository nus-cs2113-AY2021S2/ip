import java.util.Locale;
import java.util.Scanner;

public class Duke {
    private static final String LOGO = " ____        _\n"
            + "|  _ \\ _   _| | _____\n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String LINE_SEPERATOR = "    ____________________________________________________________";

    public static void main(String[] args) {
        System.out.println("    Hello from\n" + LOGO);
        System.out.println(LINE_SEPERATOR +
                "\n    Hello! I'm Duke\n" +
                "    What can I do for you?\n" +
                LINE_SEPERATOR);
        Scanner sc = new Scanner(System.in);
        TaskList tasks = new TaskList();
        String userInput = sc.nextLine();
        while (!userInput.equals("bye")) {
            try {
                if (userInput.equals("list")) {
                    tasks.printTaskList();
                    userInput = sc.nextLine();
                    continue;
                }
                String[] inputParts = userInput.trim().split("\\s+",2);
                switch (inputParts[0]){
                case "done":
                    tasks.setTaskDone(inputParts[1]);
                    break;
                case "todo":
                    ToDo newTodo = new ToDo(inputParts[1],false);
                    tasks.addTask(newTodo);
                    break;
                case "deadline":
                    String[] deadlineNameDoby = inputParts[1].split("/by");
                    String deadlineName = deadlineNameDoby[0].trim();
                    String deadlineDoby = deadlineNameDoby[1].trim();
                    Deadline newDeadline = new Deadline(deadlineName,false,deadlineDoby);
                    tasks.addTask(newDeadline);
                    break;
                case "event":
                    String[] eventNameDoby = inputParts[1].split("/at");
                    String eventName = eventNameDoby[0].trim();
                    String eventDoby = eventNameDoby[1].trim();
                    Event newEvent = new Event(eventName,false,eventDoby);
                    tasks.addTask(newEvent);
                    break;
                default:
                    System.out.println(LINE_SEPERATOR + "\n    Invalid input format.");
                    System.out.println("    Input format:\n    todo todo name\n    deadline deadline name /by time of deadline" +
                            "\n    event event name /at time of the event\n" + LINE_SEPERATOR);
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println(LINE_SEPERATOR + "\n    Invalid input format.");
                System.out.println("    Input format for done:done + event index    eg. done 1\n" + LINE_SEPERATOR);
            } catch (TaskAlreadyDoneException e) {
                System.out.println(LINE_SEPERATOR + "\n    The task is done already.\n" + LINE_SEPERATOR);
            } catch (IndexOutOfBoundsException e) {
                System.out.println(LINE_SEPERATOR + "\n    The task index is out of bound.\n" + LINE_SEPERATOR);
            }
            userInput = sc.nextLine();
        }
        System.out.print(LINE_SEPERATOR +
                "\n    Bye. Hope to see you again soon!" +
                "\n" + LINE_SEPERATOR);
    }
}
