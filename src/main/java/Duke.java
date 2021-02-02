import entity.Deadline;
import entity.Event;
import entity.Task;
import entity.Todo;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Main application driver class.
 */
public class Duke {

  static List<Task> addedTasks = new ArrayList<Task>();

  private static void printAddedTasks() {
    if (addedTasks.size() == 0) {
      System.out.println("No previously added tasks to list Sir/Madam/Other :(");
      return;
    }
    System.out.println("Here are the tasks in your list, Sir/Madam/Other:");
    for (int i = 0; i < addedTasks.size(); i++) {
      Task addedTask = addedTasks.get(i);
      System.out.printf(">>> %d. %s\n", i + 1, addedTask.toString());
    }
  }

  private static String setAddedTaskStatus(int taskId, boolean isDone) {
    int actualTaskId = taskId - 1;
    if (actualTaskId >= addedTasks.size() || actualTaskId < 0) {
      return String.format(
          "I'm terribly sorry Sir/Madam/Other :(\n"
              + "%d is not a valid task id for the current list of tasks.",
          taskId);
    }
    Task selectedTask = addedTasks.get(actualTaskId);
    String originalTaskStatus = selectedTask.toString();
    selectedTask.setDone(isDone);
    return String.format("Setting to done, original task status: %s\n>>> New task status: %s",
        originalTaskStatus, selectedTask.toString());
  }

  private static String addTodo(String description) {
    Todo newTodo = new Todo(description);
    addedTasks.add(newTodo);
    return String.format("Gotcha, added this todo: %s", newTodo.toString());
  }

  private static String addDeadline(String description, String by) {
    Deadline newDeadline = new Deadline(description, by);
    addedTasks.add(newDeadline);
    return String.format("Gotcha, added this deadline: %s", newDeadline.toString());
  }

  private static String addEvent(String description, String at) {
    Event newEvent = new Event(description, at);
    addedTasks.add(newEvent);
    return String.format("Gotcha, added this event: %s", newEvent.toString());
  }

  /**
   * The main driver function for the application.
   *
   * @param args command line arguments
   */
  public static void main(String[] args) {
    String welcomeText = "Hello, I am a PC assistant\n"
        + "How do you want to set your tasks today?";
    System.out.println(welcomeText);
    Scanner scanner = new Scanner(System.in);
    String commandString = scanner.nextLine().toLowerCase(Locale.ROOT).trim();

    String nextCommand = "placeholder";
    while (!nextCommand.equals("bye")) {
      Pattern commandPattern = Pattern.compile("(\\w+)( .+)?");
      Matcher commandMatches = commandPattern.matcher(commandString);
      if (!commandMatches.matches()) {
        System.out.println("Oof, invalid command :(");
        System.out.println("What other tasks do you want to do?");
        commandString = scanner.nextLine().toLowerCase(Locale.ROOT).trim();
        continue;
      }
      nextCommand = commandMatches.group(1);
      String commandArgs = commandMatches.group(2);
      boolean noCommandArgs = commandArgs == null || commandArgs.length() == 0;
      if (noCommandArgs) {
        switch (nextCommand) {
          case "list":
            printAddedTasks();
            break;
          case "bye":
            String goodbyeText = "Goodbye, hope to see you soon!";
            System.out.println(goodbyeText);
            return;
        }
      } else {
        commandArgs = commandArgs.trim();
        switch (nextCommand) {
          case "done":
            int taskId = Integer.parseInt(commandArgs);
            String taskStatusAdditionOutcome = setAddedTaskStatus(taskId, true);
            System.out.println(taskStatusAdditionOutcome);
            break;
          case "todo":
            String todoAdditionOutcome = addTodo(commandArgs);
            System.out.println(todoAdditionOutcome);
            break;
          case "deadline":
            String[] deadlineArgs = commandArgs.split(" /by ");
            if (deadlineArgs.length != 2) {
              System.out.println("Not sure how to parse your request to add a deadline...");
              break;
            }
            String deadlineAdditionOutcome = addDeadline(deadlineArgs[0], deadlineArgs[1]);
            System.out.println(deadlineAdditionOutcome);
            break;
          case "event":
            String[] eventArgs = commandArgs.split(" /at ");
            if (eventArgs.length != 2) {
              System.out.println("Not sure how to parse your request to add an event...");
              break;
            }
            String eventAdditionOutcome = addEvent(eventArgs[0], eventArgs[1]);
            System.out.println(eventAdditionOutcome);
            break;
        }
      }
      System.out.println("What other tasks do you want to do?");
      commandString = scanner.nextLine().toLowerCase(Locale.ROOT).trim();
    }
  }
}
