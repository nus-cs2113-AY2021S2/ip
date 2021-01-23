import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        TaskList taskList = new TaskList();
        Output.greet();
        while (true){
            Scanner scan = new Scanner(System.in);
            String userInput = scan.nextLine();
            Output.printLine();
            if (userInput.equals("bye")) {
                break;
            }
            else if (userInput.equals("list")) {
                taskList.printTasks();
            }
            else {
                Task newTask = new Task(userInput);
                taskList.addTask(newTask);
            }
            Output.printLine();
        }
        Output.exit();
    }
}