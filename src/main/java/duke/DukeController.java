package duke;

import exception.EmptyTodoException;
import exception.UnknownCommandException;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

import java.util.ArrayList;
import java.util.Scanner;

public class DukeController {

    public static final int EVENT_STRING_LENGTH = 5;
    public static final int DEADLINE_STRING_LENGTH = 8;
    public static final int TODO_STRING_LENGTH = 4;
    public static ArrayList<Task> tasks = new ArrayList<>();
    public static int taskCount = 0;

    public static void run(Scanner scanner) {
        tasks = DukeReader.getTaskListFromFile();
        taskCount = tasks.size();
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                return;
            } else if (input.equals("list")) {
                UI.printTaskList(tasks, taskCount);
            } else if (input.contains("done")) {
                markTaskDone(input);
            } else {
                addNewTask(input);
            }
        }
    }

    public static void markTaskDone(String input) {
        String[] parts = input.split(" ");
        int taskIndex = Integer.parseInt(parts[1]) - 1;
        Task taskToMarkDone = tasks.get(taskIndex);
        taskToMarkDone.markDone();
        UI.taskMarkedAsDoneMessage(taskToMarkDone);
    }

    public static void addNewTask(String input) {
        try {
            Task taskToAdd = processTaskToAdd(input);
            if (taskToAdd != null) {
                addTaskSuccessful(taskToAdd);
            }
        } catch (UnknownCommandException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void addTaskSuccessful(Task taskToAdd) {
        tasks.add(taskToAdd);
        taskCount++;
        UI.addTaskSuccessfulMessage(taskToAdd, taskCount);
    }

    public static Task processTaskToAdd(String input) throws UnknownCommandException{
        try {
            if (input.contains("todo")) {
                return processToDo(input);
            } else if (input.contains("deadline")) {
                return processDeadline(input);
            } else if (input.contains("event")) {
                return processEvent(input);
            } else {
                throw new UnknownCommandException();
            }
        } catch (EmptyTodoException e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    private static Event processEvent(String input) {
        String substr = input.substring(EVENT_STRING_LENGTH);
        String[] parts = substr.split("/at");
        String description = parts[0].trim();
        String at = parts[1].trim();
        return new Event(description, at);
    }

    private static Deadline processDeadline(String input) {
        String substr = input.substring(DEADLINE_STRING_LENGTH);
        String[] parts = substr.split("/by");
        String description = parts[0].trim();
        String by = parts[1].trim();
        return new Deadline(description, by);
    }

    private static ToDo processToDo(String input) throws EmptyTodoException {
        String substr = input.substring(TODO_STRING_LENGTH);
        if (substr.length() == 0) {
            throw new EmptyTodoException();
        }
        return new ToDo(substr.trim());
    }
}
