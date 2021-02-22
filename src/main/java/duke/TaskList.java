package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;

public class TaskList {

    public static ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    public void printTasks() throws DukeException{
        if (tasks.size() < 1) {
            throw new DukeException();
        } else {
            System.out.print("There are " + tasks.size() + " tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.print((i+1) + "." + tasks.get(i) + '\n');
            }
        }
    }

    public void addTask(Task t) throws DukeException{
        if (t.getDescription().isEmpty()) {
            throw new DukeException();
        } else {
            tasks.add(t);
            System.out.print("Got it. I've added this task:\n" + t.toString() + '\n');
        }
    }

    public void addTodo(Todo todo) {
        try {
            addTask(todo);
        } catch (DukeException e) {
            System.out.print("The description of a todo cannot be empty.\n");
        }
    }

    public void addDeadline(Deadline deadline) {
        try {
            addTask(deadline);
        } catch (DukeException e) {
            System.out.print("The description of a deadline cannot be empty.\n");
        }
    }

    public void addEvent(Event event) {
        try {
            addTask(event);
        } catch (DukeException e) {
            System.out.print("The description of an event cannot be empty.\n");
        }
    }

    public static void deleteTask(int taskIndex) throws DukeException{
        try {
            System.out.print("Got it. I've deleted this task:\n" + tasks.get(taskIndex).toString() + '\n');
            tasks.remove(taskIndex);
        } catch (Exception e) {
            throw new DukeException();
        }
    }

    public static void markIndexDone(int taskIndex) throws DukeException{
        try {
            tasks.get(taskIndex).setDone();
        } catch (Exception e) {
            throw new DukeException();
        }
    }
}
