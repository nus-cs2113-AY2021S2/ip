package duke;

import duke.myTasks.Todo;

import java.util.ArrayList;

public class TaskList {
    public static ArrayList<Todo> list = new ArrayList<>();
    public static int index;

    TaskList() {
        index = 0;
    }

    public Todo get(int i) {
        return list.get(i);
    }

    public void addToList(Todo task) {
        list.add(task);
        index++;
    }

    public void printList() {
        for (int i = 0; i < index; i++) {
            System.out.println((i + 1) + ". " + list.get(i).toString());
        }
    }

    public void printAddMessage(Todo task) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + index + " tasks in the list.");
    }

    public void add(Todo task) {
        list.add(task);
    }
}
