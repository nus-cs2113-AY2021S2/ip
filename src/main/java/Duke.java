import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class Duke {

    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        MainUI.showWelcomeScreen();
        while (true){
            String input = in.nextLine();
            if (input.equals("list")){
                TaskManager.printTaskList(TaskManager.removeNullFromList());
            }
            else if (input.equals("bye")) {
                MainUI.printExitMessage();
            }
            else if (input.startsWith("done")) {
                int taskNumberDone = Integer.parseInt(input.substring(5));
                TaskManager.markTaskAsDone(taskNumberDone);
            }
            else{
                TaskManager.addTask(input);
            }
        }
    }
}

