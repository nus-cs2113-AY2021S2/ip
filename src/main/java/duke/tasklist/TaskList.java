package duke.tasklist;

import java.util.ArrayList;


import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;
import duke.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class TaskList {

    public static ArrayList<Task> tasks = new ArrayList<Task>();

    private Storage storage;

    public TaskList(Storage input) {
        tasks = new ArrayList<>();
        storage = input;
    }

    public static void validateInput(String input, String type) throws DukeException {
        try {
            if (type.equals("done") && (!(input.strip()).matches("[-+]?\\\\d*\\\\.?\\\\d+\n"))
                    || (type.equals("todo") && (input.strip().length() <= 0))
                    || (!input.contains("/") && (type.equals("deadline") || type.equals("event")))) {
                throw new DukeException();
            }
        } catch (IndexOutOfBoundsException e) {
            Ui.printInvalidMessage(type);
        }
    }

    public static void showList() {
        if (tasks.size() > 0) {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                Task currentTask = tasks.get(i);
                System.out.print(i + 1 + ". ");
                Ui.printTask(currentTask);
            }
        } else {
            System.out.println("Please input a task.");
        }
    }

    public static void markAsDone(String inputCommand) {
        try {
            String taskDone = inputCommand.split(" ", 2)[1];
            int taskIndex = Integer.parseInt(taskDone) - 1;
            if (taskIndex >= tasks.size() || taskIndex < 0) {
                System.out.println("You have not added task " + taskIndex + " yet! Please try again.");
            } else if (tasks.get(taskIndex).getIsDone() == true) {
                System.out.println("Task have already been set as done before. Please try to complete other tasks.");
            } else {
                tasks.get(taskIndex).setDone();
                System.out.println("Nice! I've marked this task as done:");
                Task currentTask = tasks.get(taskIndex);
                Ui.printTask(currentTask);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e + "invalid! Please try again");
        }
    }

    public static void addToDo(String inputCommand) {
        try {
            String taskToAdd = inputCommand.split(" ", 2)[1];
            validateInput(taskToAdd, "todo");
            Todo t = new Todo(taskToAdd);
            tasks.add(t);
            System.out.println("Got it. I've added this task:");
            Ui.printTask(t);
            Ui.printTaskSize();
        } catch (DukeException | IndexOutOfBoundsException e) {
            Ui.printInvalidMessage("todo");
        }
    }

    public static void addEvent(String inputCommand) {
        try {
            String taskToAdd = inputCommand.split(" ", 2)[1];
            validateInput(taskToAdd, "event");
            int splitIndex = taskToAdd.indexOf('/');
            String description = taskToAdd.substring(0, splitIndex);
            String dateTime = taskToAdd.substring(splitIndex + 3);
            Event e = new Event(description, dateTime);
            tasks.add(e);
            System.out.println("Got it. I've added this task:");
            Ui.printTask(e);
            Ui.printTaskSize();
        } catch (DukeException | IndexOutOfBoundsException e) {
            Ui.printInvalidMessage("event");
        }
    }

    public static void addDeadline(String inputCommand) {
        try {
            String taskToAdd = inputCommand.split(" ", 2)[1];
            validateInput(taskToAdd, "deadline");
            int splitIndex = taskToAdd.indexOf('/');
            String description = taskToAdd.substring(0, splitIndex);
            String dateTime = taskToAdd.substring(splitIndex + 3);
            Deadline d = new Deadline(description, dateTime);
            tasks.add(d);
            System.out.println("Got it. I've added this task:");
            Ui. printTask(d);
            Ui.printTaskSize();
        } catch (DukeException | IndexOutOfBoundsException e) {
            Ui.printInvalidMessage("deadline");
        }
    }

    public static void deleteTask(String inputCommand) {
        try {
            String taskToHandle = inputCommand.split(" ", 2)[1];
            int taskIndex = Integer.parseInt(taskToHandle) - 1;
            if (taskIndex < 0 || taskIndex >= tasks.size()) {
                System.out.println("Task does not exists!");
            } else {
                System.out.println("Noted. I've removed this task:");
                Task currentTask = tasks.get(taskIndex);
                Ui.printTask(currentTask);
                tasks.remove(taskIndex);
                Ui.printTaskSize();
            }
        } catch (IndexOutOfBoundsException e) {
            Ui.printInvalidMessage("delete");
        }
    }

    public static void findListOfCommands(String inputCommand) {
        try {
            String word = inputCommand.substring(5);
            ArrayList<Task> foundTasks = findInput(word);
            Ui.printFoundTasks(foundTasks);
        } catch (IndexOutOfBoundsException e) {
            Ui.printInvalidMessage("find");
        }
    }
    public static ArrayList<Task> findInput (String word) {
        return (ArrayList<Task>) tasks.stream()
                .filter(t -> t.getDescription().toLowerCase().contains(word))
                    .collect(Collectors.toList());
    }
}
