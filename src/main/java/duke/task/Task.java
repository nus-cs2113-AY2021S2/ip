package duke.task;

import duke.exception.IllegalTaskCommandException;

import java.util.ArrayList;
import java.util.StringJoiner;

public class Task {
    protected String description;
    protected boolean isDone;
    protected static ArrayList<Task> taskArray = new ArrayList<>();

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public static void printList() {
        printTaskHeader();
        for(Task t: taskArray) {
            printTaskIndex(taskArray.indexOf(t));
            t.printTaskType();
            t.printCompletionStatus();
            t.printTaskItem();
            System.out.println("");
        }
        System.out.println("");
    }

    private static void printTaskHeader() {
        String tasksTemplate
                = "******************************************************\n"
                + "*                                                    *\n"
                + "*          [Objectives]-[Missions]-[Tasks]           *\n"
                + "*                                                    *\n"
                + "******************************************************";
        System.out.println(tasksTemplate);
    }

    private static void printTaskIndex(int taskIndex) {
        System.out.print("[" + (taskIndex + 1) + "]");
    }

    private void printTaskType() {
        System.out.print("[" + getTaskType() + "]");
    }

    protected String getTaskType() {
        return null;
    }

    private void printCompletionStatus() {
        System.out.print("[" + getStatusIcon() + "]");
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    protected void printTaskItem() {
        String item = " " + this.description;
        System.out.print(item);
    }

    public void addToTaskList(String errand, String timestamp) {
        taskArray.add(this);
        echoInput(errand, timestamp);
    }

    public void echoInput(String errand, String timestamp) {
        String taskString;
        String echoTop = "______________________________________________________\n"
                + "[Orders received]:\n";
        String echoBottom = "______________________________________________________\n";

        if (timestamp == null) { // Todo tasks
            taskString = " " + errand + "\n";
        } else { // Deadlines and Events
            taskString = " " + errand + " (Timestamp: " + timestamp + ")" + "\n";
        }

        System.out.println(echoTop);
        printTaskType();
        printCompletionStatus();
        System.out.println(taskString);
        printTaskCount();
        System.out.println(echoBottom);
    }

    private static void printTaskCount() {
        String taskCountMessage = "There are now " + taskArray.size()
                + " mission objectives, Commander.";
        System.out.println(taskCountMessage);
    }

    public static void markDone(String index) throws IllegalTaskCommandException {
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

        taskArray.get(indexOfTask).isDone = true;
        printMarkDonePrompt(indexOfTask);
    }

    private static void printMarkDonePrompt(int taskIndex) {
        String doneTextTop = "______________________________________________________\n"
                + "[Objective Completed]:\n";
        String doneTextBottom = "______________________________________________________\n";

        System.out.println(doneTextTop);
        taskArray.get(taskIndex).printTaskType();
        taskArray.get(taskIndex).printCompletionStatus();
        taskArray.get(taskIndex).printTaskItem();
        System.out.println();
        System.out.println(doneTextBottom);
    }

    private static void printDeletedTaskCount() {
        String taskCountMessage = "There are now " + (taskArray.size() - 1)
                + " mission objectives, Commander.";
        System.out.println(taskCountMessage);
    }

    private static void printDeletePrompt(int taskIndex) {
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

    public static String convertToFileInput() {
        StringJoiner string = new StringJoiner("\n");

        /*
        for (int i = 0; i < taskIndex; i++) {
            string.add(getFileInput(i));
        }
         */

        return string.toString();
    }

    /*
    private static String getFileInput(int i) {
        String taskType = taskArray[i].getTaskType();
        String statusIcon = taskArray[i].getStatusIcon();
        String description = taskArray[i].description;
        return  taskType + " , " + statusIcon + " , " + description;

    }
     */
}
