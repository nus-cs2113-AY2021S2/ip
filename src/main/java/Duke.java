import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        TaskList taskList = new TaskList();
        Output.printGreet();
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
            else if (userInput.startsWith("done")) {
                String[] splitInput = userInput.split(" ");
                int taskNumber = Integer.parseInt(splitInput[1]);
                taskList.updateCompletion(taskNumber);
            }
            else {
                Task newTask = new Task(userInput);
                taskList.addTask(newTask);
            }
            Output.printLine();
        }
        Output.printExit();
    }
}