package task;

import printer.Printer;
import java.util.ArrayList;
import java.util.StringJoiner;

public class TaskHandler {
    private static ArrayList<Task> taskArrayList = new ArrayList<>();
    private static int taskCount = 0;
    private static final Printer printer = new Printer();

    public void addTask(Task task) {
        taskArrayList.add(task);
    }

    public void increaseTaskCount() {
        taskCount++;
    }

    public void deleteTask(int index) {
        taskArrayList.remove(index);
    }

    public void decreaseTaskCount() {
        taskCount--;
    }

    public int getTaskCount() {
        return taskCount;
    }

    public boolean checkStatus(int index) {
        return taskArrayList.get(index).isDone();
    }

    public void markDone(int taskNumber) {
        taskArrayList.get(taskNumber).setTaskAsDone();
    }

    public void findByWord(String keyword) {
        boolean foundKeyword = false;
        System.out.println("Processing keyword searches, please wait.");
        for (int i = 0; i < taskCount; i++) {
            if (taskArrayList.get(i).getTaskDescription().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.print((i+1) + ".");
                printer.printTaskDetails(i);
                foundKeyword = true;
            }
        }
        if (!foundKeyword) {
            System.out.println("Oh no mushroom head could not find any matches!");
        }
    }

    public String toFileFormat() {
        StringJoiner joiner = new StringJoiner("\n");
        for (Task currentTask : taskArrayList) {
            joiner.add(currentTask.getFileFormat());
        }
        return joiner.toString();
    }

    public String extractTaskTiming(int index) {
        return taskArrayList.get(index).getTaskTiming();
    }

    public String extractTaskSymbol(int index) {
        return taskArrayList.get(index).getTaskSymbol();
    }

    public String extractTaskStatus(int index) {
        return taskArrayList.get(index).getTaskStatus();
    }

    public String extractTaskDescription(int index) {
        return taskArrayList.get(index).getTaskDescription();
    }
}
