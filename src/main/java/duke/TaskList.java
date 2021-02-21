package duke;


import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;
    private Ui ui = new Ui();

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }


    public void addTodo(String line) {
        try {
            int descriptionStart = 5;
            String description = line.substring(descriptionStart);
            tasks.add(new Todo(description));
            ui.printAddTaskMessage(tasks);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("OOPS!!! The description of a todo cannot be empty.");
        }
    }

    public void addDeadline(String line) {
        try {
            int descriptionStart = 9;
            int descriptionEnd = line.indexOf("/by") - 1;
            String description = line.substring(descriptionStart, descriptionEnd);
            int byStart = line.indexOf("/by") + 4;
            String by = line.substring(byStart);
            tasks.add(new Deadline(description, by));
            ui.printAddTaskMessage(tasks);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("OOPS!!! The description of a deadline cannot be empty.");
        }
    }

    public void addEvent(String line) {
        try {
            int descriptionStart = 6;
            int descriptionEnd = line.indexOf("/at") - 1;
            String description = line.substring(descriptionStart, descriptionEnd);
            int atStart = line.indexOf("/at") + 4;
            String at = line.substring(atStart);
            tasks.add(new Event(description, at));
            ui.printAddTaskMessage(tasks);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("OOPS!!! The description of an event cannot be empty.");
        }
    }

    public void markDone(String line) {
        try {
            int itemNum = Integer.parseInt(line.substring(5));
            tasks.get(itemNum - 1).setAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(tasks.get(itemNum - 1).toString());
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("OOPS!!! The index of the task to be marked as done cannot be empty.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("OOPS!!! The index of the task to be marked as done is out of range");
        } catch (NumberFormatException e) {
            System.out.println("OOPS!!! The index of the task to be marked as done is invalid");
        }
    }

    public void deleteTask(String line) {
        try {
            int itemNum = Integer.parseInt(line.substring(7));
            Task toBeDeleted = tasks.get(itemNum - 1);
            tasks.remove(itemNum - 1);
            System.out.println("Noted. I've removed this task:");
            System.out.println(toBeDeleted.toString());
            System.out.println("Now you have " + tasks.size() + " tasks in the list");
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("OOPS!!! The index of the task to be deleted cannot be empty.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("OOPS!!! The index of the task to be deleted is out of range");
        } catch (NumberFormatException e) {
            System.out.println("OOPS!!! The index of the task to be deleted is invalid");
        }

    }

}
