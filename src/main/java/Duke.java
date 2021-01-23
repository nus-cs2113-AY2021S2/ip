import entity.Task;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 * Main application driver class.
 */
public class Duke {

  static List<Task> addedTasks = new ArrayList<Task>();

  private static void printAddedTasks() {
    if (addedTasks.size() == 0) {
      System.out.println("No previously added tasks to list sir/madam/other :(");
      return;
    }
    for (int i = 0; i < addedTasks.size(); i++) {
      Task addedTask = addedTasks.get(i);
      String checkMark = addedTask.isDone() ? "X" : " ";
      System.out.printf("%d.[%s] %s\n", i + 1, checkMark, addedTask.getTaskDescription());
    }
  }

  private static void setAddedTaskStatus(int taskId, boolean isDone) {
    taskId = taskId - 1;
    if (taskId >= addedTasks.size() || taskId < 0) {
      System.out.printf(
          "I'm terribly sorry Sir/Madam/Other, %d"
              + " is not a valid task id for the current list of tasks.\n",
          taskId);
      return;
    }
    addedTasks.get(taskId).setDone(isDone);
    System.out.printf("[X] %s\n", addedTasks.get(taskId).getTaskDescription());
  }

  /**
   * The main driver function for the application.
   *
   * @param args command line arguments
   */
  public static void main(String[] args) {
    String welcomeText = "Hello, my name is Apu\n"
        + "What can I get you?\n";
    System.out.println(welcomeText);
    Scanner scanner = new Scanner(System.in);
    String nextCommand = scanner.nextLine().toLowerCase(Locale.ROOT).trim();
    while (!nextCommand.equals("bye")) {
      if (nextCommand.equals("list")) {
        printAddedTasks();
      } else if (nextCommand.matches("done \\d")) {
        String[] commandTokens = nextCommand.split(" ");
        int taskId = Integer.parseInt(commandTokens[1]);
        setAddedTaskStatus(taskId, true);
      } else {
        addedTasks.add(new Task(nextCommand));
        System.out.println(String.format("Added: %s", nextCommand));
      }
      System.out.println("What else can I get you?");
      nextCommand = scanner.nextLine().toLowerCase(Locale.ROOT).trim();
    }
    String goodbyeText = "Goodbye, please come again!";
    System.out.println(goodbyeText);
  }
}
