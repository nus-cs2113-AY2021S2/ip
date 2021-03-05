package duke;

import duke.myTasks.Todo;

import java.util.ArrayList;

/**
 * A customised ArrayList with additional methods for Duke
 */
public class TaskList {
    public static ArrayList<Todo> list = new ArrayList<>();
    public static int index;

    TaskList() {
        index = 0;
    }

    public Todo get(int i) {
        return list.get(i);
    }

    public void add(Todo task) {
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

    public void find(String content) {
        boolean exists = false;
        for (int i = 0; i < index; i++) {
            if (list.get(i).name.contains(content)) {
                exists = true;
                System.out.println((i+1) + ". " + list.get(i).toString());
            }
        }
        if (!exists) {
            System.out.println("There is no task matching the search term.");
        }
    }
}
