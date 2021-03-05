package duke.task;

import duke.ui.Ui;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;
    private Ui ui = new Ui();

    /**
     * Constructor of new TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor of loading a TaskList.
     *
     * @param tasks ArrayList of Tasks that was previously saved
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns all the tasks in the task list.
     *
     * @return ArrayList of tasks
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Prints a list of all tasks currently in the task list
     */
    public void printList() {
        ui.printList(tasks);
    }


    /**
     * Adds a Todo task to the task list.
     *
     * @param line user input containing description of task
     */
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

    /**
     * Adds a Deadline task to the task list.
     *
     * @param line user input containing description and due date
     */
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

    /**
     * Adds an Event task to the task list.
     *
     * @param line user input containing description and date of event
     */
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

    /**
     * Marks a task at specified index as done.
     *
     * @param line user input containing index of task to be marked as done
     */
    public void markDone(String line) {
        try {
            int taskIndex = Integer.parseInt(line.substring(5));
            tasks.get(taskIndex - 1).setAsDone();
            ui.printMarkDoneMessage(tasks, taskIndex);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("OOPS!!! The index of the task to be marked as done cannot be empty.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("OOPS!!! The index of the task to be marked as done is out of range");
        } catch (NumberFormatException e) {
            System.out.println("OOPS!!! The index of the task to be marked as done is invalid");
        }
    }

    /**
     * Deletes a task at specified index from the task list.
     *
     * @param line user input containing index of task to be deleted
     */
    public void deleteTask(String line) {
        try {
            int taskIndex = Integer.parseInt(line.substring(7));
            Task toBeDeleted = tasks.get(taskIndex - 1);
            tasks.remove(taskIndex - 1);
            ui.printDeleteTaskMessage(tasks, toBeDeleted);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("OOPS!!! The index of the task to be deleted cannot be empty.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("OOPS!!! The index of the task to be deleted is out of range");
        } catch (NumberFormatException e) {
            System.out.println("OOPS!!! The index of the task to be deleted is invalid");
        }

    }

    /**
     * Searches for task in task list containing keyword.
     *
     * @param line user input containing keyword
     */
    public void findTask(String line) {
        try {
            String keyword = line.substring(5);
            int count = 1;
            System.out.println("Here are the matching tasks in your list:");
            for (Task task : tasks) {
                if (task.getDescription().contains(keyword)) {
                    System.out.println(count + "." + task.toString());
                    count++;
                }
            }
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("OOPS!!! There was no keyword to search for");
        }
    }

}
