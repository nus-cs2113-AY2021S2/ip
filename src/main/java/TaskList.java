import java.util.ArrayList;

import Exceptions.*;

public class TaskList {
    protected ArrayList<Task> tasksLists = new ArrayList<>();

    public void addTask(Task task) {
        task.toString();
        tasksLists.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        if (tasksLists.size() == 1) {
            System.out.println("Now you have " + tasksLists.size() + " task in the list.");
        } else {
            System.out.println("Now you have " + tasksLists.size() + " tasks in the list.");
        }
    }

    public void addEvent(String inputs) throws WrongEventFormatException {
        if (!inputs.matches("\".+/at.+\"")) throw new WrongEventFormatException();

        String command = inputs.substring(inputs.indexOf(" ") + 1);
        String title = command.substring(0, command.indexOf("/") - 1);
        String date = inputs.substring(inputs.indexOf("at") + 3);

        Event newEvent = new Event(title, date);
        addTask(newEvent);
    }

    public void addDeadline(String inputs) throws WrongDeadlineFormatException {
        if (!inputs.matches("\".+/by.+\"")) throw new WrongDeadlineFormatException();

        String command = inputs.substring(inputs.indexOf(" ") + 1);
        String title = command.substring(0, command.indexOf("/") - 1);
        String date = inputs.substring(inputs.indexOf("at") + 3);

        Deadline newDeadline = new Deadline(title, date);
        addTask(newDeadline);
    }

    public void addTodo(String inputs) throws WrongToDoFormatException {
        String title = inputs.substring(inputs.indexOf(" ") + 1);
        if (inputs.equals("todo")) throw new WrongToDoFormatException();

        Todo newTodo = new Todo(title);
        addTask(newTodo);
    }

    public void printTasksLists() throws EmptyTaskListsException {
        if (tasksLists.size() == 0) throw new EmptyTaskListsException();
        for (int i = 0; i < tasksLists.size(); i++) {
            System.out.println(i + 1 + "." + tasksLists.get(i).toString());
        }
    }

    public void markAsDone(int num) throws NoSuchTaskException {
        if (!isValidTaskNumber(num)) {
            throw new NoSuchTaskException();
        }
        System.out.println("Nice! I've marked this task as done:");
        tasksLists.get(num - 1).markAsDone();
        System.out.println(tasksLists.get(num - 1).toString());
    }

    public void deleteTask(int taskNumber) throws NoSuchTaskException {
        if (!isValidTaskNumber(taskNumber)) {
            throw new NoSuchTaskException();
        }
        Task toDelete = tasksLists.get(taskNumber - 1);
        tasksLists.remove(taskNumber - 1);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + toDelete);
        System.out.println("Now you have " + tasksLists.size() + " tasks in the list.");
    }

    private boolean isValidTaskNumber(int taskNumber) {
        return taskNumber >= 1 && taskNumber <= tasksLists.size();
    }
}
