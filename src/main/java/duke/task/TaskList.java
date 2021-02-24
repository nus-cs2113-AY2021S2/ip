package duke.task;

import duke.exception.IllegalTaskCommandException;
import duke.ui.Ui;

import java.util.ArrayList;
import java.util.StringJoiner;

public class TaskList {
    protected static ArrayList<Task> taskArray = new ArrayList<>();

    public static void printList() {
        Ui.printTaskHeader();
        for(Task t: taskArray) {
            int index = taskArray.indexOf(t);
            printTaskIndex(index);
            printTaskType(index);
            printCompletionStatus(index);
            printTaskItem(index);
            System.out.println();
        }
        System.out.println();
    }

    public static void printTaskIndex(int taskIndex) {
        System.out.print("[" + (taskIndex + 1) + "]");
    }

    public static void printTaskCount() {
        String taskCountMessage = "There are now " + taskArray.size()
                + " mission objectives, Commander.";
        System.out.println(taskCountMessage);
    }

    public static void markDone(int index) {
        taskArray.get(index).isDone = true;
        Ui.printMarkDonePrompt(index);
    }

    public static void printDeletedTaskCount() {
        String taskCountMessage = "There are now " + (taskArray.size() - 1)
                + " mission objectives, Commander.";
        System.out.println(taskCountMessage);
    }

    /*
    public static void printDeletePrompt(int taskIndex) {
        String doneTextTop = "______________________________________________________\n"
                + "[Objective Successfully Removed]:\n";
        String doneTextBottom = "______________________________________________________\n";

        System.out.println(doneTextTop);
        taskArray.get(taskIndex).printTaskType();
        taskArray.get(taskIndex).printCompletionStatus();
        taskArray.get(taskIndex).printTaskItem();
        System.out.println("\n");
        printDeletedTaskCount();
        System.out.println(doneTextBottom);
    }
     */

    /*
    public static void deleteTask(String index) throws IllegalTaskCommandException {
        int indexOfTask;
        try {
            indexOfTask = Integer.parseInt(index) - 1;
        } catch (NumberFormatException e) {
            IllegalTaskCommandException.printErrorLogo();
            System.out.println("Only numbers are allowed commander!");
            return; // Ends the method here as invalid input is entered
        }

        if (indexOfTask < 0 || taskArray.size() == 0) {
            throw new IllegalTaskCommandException("There is no such task, Commander!");
        } else if (indexOfTask >= taskArray.size()) {
            throw new IllegalTaskCommandException("Unacceptable location Commander!");
        }

        printDeletePrompt(indexOfTask);
        taskArray.remove(indexOfTask);
    }
     */

    public static String convertToFileInput() {
        StringJoiner string = new StringJoiner("\n");

        for (Task t: taskArray) {
            string.add(t.getFileInput());
        }

        return string.toString();
    }

    public static void printCompletionStatus(int index) {
        System.out.print("[" + Task.getStatusIcon(index) + "]");
    }

    public static void printTaskType(int index) {
        System.out.print("[" + taskArray.get(index).getTaskType() + "]");
    }


    public static void addToTaskList(Task task) {
        taskArray.add(task);
        int index = taskArray.indexOf(task);
        Ui.echoInput(task, index);
    }

    public static void printTaskItem(int index) {
        String item = " " + taskArray.get(index).getTaskItem();
        System.out.print(item);
    }
}
