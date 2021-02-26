package duke.task;

import duke.ui.Ui;

import java.util.ArrayList;
import java.util.StringJoiner;

public class TaskList {
    protected static ArrayList<Task> taskArray = new ArrayList<>();

    public static int returnTaskCount() {
        return taskArray.size();
    }

    public static String returnTaskType(int index) {
        return taskArray.get(index).getTaskType();
    }

    public static String returnTaskItem(int index) {
        return taskArray.get(index).getTaskItem();
    }

    public static String returnStatusIcon(int index) {
        return taskArray.get(index).getStatusIcon();
    }

    public static String returnFileFormat(int index) {
        return taskArray.get(index).getFileFormat();
    }

    public static void printList() {
        Ui.printTaskHeader();
        for (Task t : taskArray) {
            int index = taskArray.indexOf(t);
            Ui.printTaskIndex(index);
            System.out.print("[" + returnTaskType(index) + "]");
            Ui.printCompletionStatus(index);
            System.out.print(" " + returnTaskItem(index));
            System.out.println();
        }
        System.out.println();
    }

    public static void markDone(int index) {
        taskArray.get(index).isDone = true;
    }

    public static void deleteTask(int index) {
        Ui.printDeletePrompt(index);
        taskArray.remove(index);
    }

    public static int addToTaskList(Task task) {
        taskArray.add(task);
        return taskArray.indexOf(task);
    }

    public static String convertToFileInput() {
        StringJoiner string = new StringJoiner("\n");

        for (Task t : taskArray) {
            int index = taskArray.indexOf(t);
            string.add(getFileInput(index));
        }

        return string.toString();
    }

    public static String getFileInput(int index) {
        return returnTaskType(index) + " , " + returnStatusIcon(index) + " , " + returnFileFormat(index);
    }
}
