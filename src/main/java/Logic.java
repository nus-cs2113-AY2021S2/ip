import java.util.Scanner;

public class Logic {
    public static void loop() {
        TaskList taskList = new TaskList();
        while (true) {
            Scanner scan = new Scanner(System.in);
            String userInput = scan.nextLine();
            Scanner secondScan = new Scanner(userInput);
            String command = secondScan.next();
            if (command.equals("bye")) {
                break;
            }
            Output.printLine();
            switch (command) {
                case "list": {
                    taskList.printTasks();
                    break;
                }
                case "done": {
                    int taskNumber = secondScan.nextInt();
                    taskList.updateCompletion(taskNumber);
                    break;
                }
                case "todo": {
                    String taskName = secondScan.nextLine();
                    Todo newTask = new Todo(taskName.trim());
                    taskList.addTask(newTask);
                    break;
                }
                case "deadline": {
                    String taskName = "";
                    String inputWord = secondScan.next();
                    while (inputWord.charAt(0) != '/'){
                        taskName += inputWord;
                        taskName += " ";
                        inputWord = secondScan.next();
                    }
                    String endTime = secondScan.nextLine();
                    Deadline newDeadline = new Deadline(taskName.trim(), endTime.trim());
                    taskList.addTask(newDeadline);
                    break;
                }
                case "event": {
                    String taskName = "";
                    String inputWord = secondScan.next();
                    while (inputWord.charAt(0) != '/'){
                        taskName += inputWord;
                        taskName += " ";
                        inputWord = secondScan.next();
                    }
                    String timePeriod = secondScan.nextLine();
                    Event newEvent = new Event(taskName.trim(), timePeriod.trim());
                    taskList.addTask(newEvent);
                    break;
                }
            }
            Output.printLine();
        }
    }
}
