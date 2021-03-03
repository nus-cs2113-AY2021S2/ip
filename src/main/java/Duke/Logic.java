package Duke;

import Duke.Tasks.Deadline;
import Duke.Tasks.Event;
import Duke.Tasks.Todo;

import java.util.Scanner;

public class Logic {
    public static void loop() {
        TaskList taskList = TextFile.loadTaskList();
        while (true) {
            Scanner scan = new Scanner(System.in);
            String command = scan.next();
            if (command.equals("bye")) {
                break;
            }
            switch (command) {
            case "list": {
                taskList.printTaskList();
                break;
            }
            case "done": {
                int taskNumber;
                try {
                    taskNumber = scan.nextInt();
                }
                catch(Exception e) {
                    Output.printDoneInputError();
                    break;
                }
                if (taskNumber > taskList.size() || taskNumber < 1) {
                    Output.printDoneRangeError();
                }
                else {
                    taskList.updateCompletion(taskNumber);
                }
                break;
            }
            case "delete": {
                int taskNumber;
                try {
                    taskNumber = scan.nextInt();
                }
                catch(Exception e) {
                    Output.printDoneInputError();
                    break;
                }
                if (taskNumber > taskList.size() || taskNumber < 1) {
                    Output.printDoneRangeError();
                }
                else {
                    taskList.deleteTask(taskNumber);
                }
                break;
            }
            case "todo": {
                String taskName = scan.nextLine();
                Todo newTask = new Todo(taskName.trim());
                taskList.addAndPrint(newTask);
                break;
            }
            case "deadline": {
                String taskName = "";
                String inputWord = scan.next();
                while (inputWord.charAt(0) != '/'){
                    taskName += inputWord;
                    taskName += " ";
                    inputWord = scan.next();
                }
                String endTime = scan.nextLine();
                Deadline newDeadline = new Deadline(taskName.trim(), endTime.trim());
                taskList.addAndPrint(newDeadline);
                break;
            }
            case "event": {
                String taskName = "";
                String inputWord = scan.next();
                while (inputWord.charAt(0) != '/'){
                    taskName += inputWord;
                    taskName += " ";
                    inputWord = scan.next();
                }
                String timePeriod = scan.nextLine();
                Event newEvent = new Event(taskName.trim(), timePeriod.trim());
                taskList.addAndPrint(newEvent);
                break;
            }
            case "save": {
                TextFile.saveData(taskList);
                Output.printSaved();
                break;
            }
            default: {
                Output.printUnknownCommandError();
            }
            }
        }
    }
}