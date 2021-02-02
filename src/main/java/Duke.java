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

  private static void setAddedTaskStatus(int taskId, boolean isDone) {
    int actualTaskId = taskId - 1;
    if (actualTaskId >= addedTasks.size() || actualTaskId < 0) {
      System.out.printf(
          "I'm terribly sorry Sir/Madam/Other :(\n"
              + "%d is not a valid task id for the current list of tasks.\n",
          taskId);
      return;
    }
    Task selectedTask = addedTasks.get(actualTaskId);
    System.out.printf("Setting to done, original task status: %s\n", selectedTask.toString());
    selectedTask.setDone(isDone);
    System.out.printf(">>> New task status: %s\n", selectedTask.toString());
  }

  private static void addTodo(String description) {
    Todo newTodo = new Todo(description);
    addedTasks.add(newTodo);
    System.out.printf("Gotcha, added this todo: %s\n", newTodo.toString());
  }

  private static void addDeadline(String description, String by) {
    Deadline newDeadline = new Deadline(description, by);
    addedTasks.add(newDeadline);
    System.out.printf("Gotcha, added this deadline: %s\n", newDeadline.toString());
  }

  private static void addEvent(String description, String at) {
    Event newEvent = new Event(description, at);
    addedTasks.add(newEvent);
    System.out.printf("Gotcha, added this event: %s\n", newEvent.toString());
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
            setAddedTaskStatus(taskId, true);
            break;
          case "todo":
            addTodo(commandArgs);
            break;
          case "deadline":
            String[] deadlineArgs = commandArgs.split(" /by ");
            if (deadlineArgs.length == 2) {
              addDeadline(deadlineArgs[0], deadlineArgs[1]);
            }
            break;
          case "event":
            String[] eventArgs = commandArgs.split(" /at ");
            if (eventArgs.length == 2) {
              addEvent(eventArgs[0], eventArgs[1]);
            }
            break;
        }
      }
      System.out.println("What other tasks do you want to do?");
      commandString = scanner.nextLine().toLowerCase(Locale.ROOT).trim();
    }
  }
}
