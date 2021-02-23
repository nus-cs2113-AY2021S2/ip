package duke;

import java.util.ArrayList;

public class TaskList {
    public static ArrayList<Task> tasks = new ArrayList<>();
    public static int maxTaskIndex = 0;

    static void enumerateTasks() {
        int currentTaskIndex;
        for (int i = 0; i < maxTaskIndex; i++) {
            currentTaskIndex = i + 1;
            System.out.println(currentTaskIndex + "." + tasks.get(i).toString());
        }
    }

    static void deleteTask(String input) {
        int taskNumberToDelete = (Integer.parseInt(input.substring(Parser.DELETE_START).strip()) - 1);
        Ui.deletedTaskMessage(taskNumberToDelete);
        tasks.remove(taskNumberToDelete);
    }

    static void markTaskAsDone(String input) {
        int completedTaskIndex = Parser.getCompletedTaskIndex(input);
        tasks.get(completedTaskIndex).markAsDone();
        Ui.completedTaskMessage(completedTaskIndex);
    }

    public static void addNewDeadline(String input) {
        tasks.add(new Deadline(input.substring(Parser.DEADLINE_START, Parser.getTimePosition(input)), Parser.getTime(input)));
    }

    public static void addNewEvent(String input) {
        tasks.add(new Event(input.substring(Parser.EVENT_START, Parser.getTimePosition(input)), Parser.getTime(input)));
    }

    public static void addNewTodo(String input) {
        tasks.add(new ToDo(input.substring(Parser.TODO_START)));
    }

    public static void incrementTasks() {
        maxTaskIndex++;
    }

    static void decrementTasks() {
        maxTaskIndex--;
    }
}
