package duke.task;

import duke.command.CommandNotFoundException;
import duke.command.MainUI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class TaskManager {
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    public static final String DONE_COMMAND = "done";
    public static final String DELETE_COMMAND = "delete";

    private static final String ADDED_TASK_MESSAGE = "Got it. I have added this task: ";
    private static final String MARKED_TASK_AS_DONE_MESSAGE = "Nice! I've marked this task as done: ";

    public static ArrayList<Task> taskArrayList = new ArrayList<>();

    public static void handleTask(String input) throws CommandNotFoundException {
        String firstWord = StringManipulator.getFirstWord(input);
        switch (firstWord){
        case DONE_COMMAND:
            int taskNumberDone = StringManipulator.getTaskNumberDone(input);
            markTaskAsDone(taskNumberDone);
            break;
        case TODO_COMMAND:
            try{
                String taskDescription = StringManipulator.getIndexOfStringAfterWhiteSpace(input);
                Todo t = new Todo(taskDescription);
                t.checkIfToDoDescriptionExists(taskDescription);
                taskArrayList.add(t);
                printMessageAfterTaskIsAdded(t);
            } catch (TaskDescriptionMissingException e) {
                System.out.println(e.getMessage());
                MainUI.printDivider();
            }
            break;
            
        case DEADLINE_COMMAND:
            try {
                String dueDate = StringManipulator.getStringAfterSlash(input);
                String taskDescription = StringManipulator.getStringAfterWhiteSpaceAndBeforeSlash(input);
                Deadline d = new Deadline(taskDescription,dueDate);
                d.checkIfDeadlineDescriptionExists(taskDescription);
                taskArrayList.add(d);
                printMessageAfterTaskIsAdded(d);
            } catch (TaskDescriptionMissingException e) {
                System.out.println(e.getMessage());
                MainUI.printDivider();
            }
            break;
        case EVENT_COMMAND:
            String eventPeriod = StringManipulator.getStringAfterSlash(input);
            String taskDescription = StringManipulator.getStringAfterWhiteSpaceAndBeforeSlash(input);
            Event e = new Event(taskDescription,eventPeriod);
            taskArrayList.add(e);
            printMessageAfterTaskIsAdded(e);
            break;
        case DELETE_COMMAND:
            int taskNumberDeleted = StringManipulator.getTaskNumberDeleted(input);
            System.out.println("Noted. I've removed this task:\n" + taskArrayList.get(taskNumberDeleted-1));
            taskArrayList.remove(taskNumberDeleted-1);
            Task.decreaseTaskCount();
            System.out.println("Now you have " + Task.getTaskCount() + " tasks in the list.");
            break;
        default:
            throw new CommandNotFoundException();
        }
    }

    private static void printMessageAfterTaskIsAdded(Task task) {
        MainUI.printDivider();
        System.out.println(ADDED_TASK_MESSAGE);
        System.out.println("\t"+task);
        printTaskCount();
        MainUI.printDivider();
    }

    private static void printTaskCount() {
        System.out.println("Now you have " + Task.getTaskCount() + " task(s) in the list.");
    }

    public static void markTaskAsDone(int taskNumber){
        System.out.println(MARKED_TASK_AS_DONE_MESSAGE);
        taskArrayList.get(taskNumber-1).markAsDone();
        System.out.println(taskArrayList.get(taskNumber-1));
        MainUI.printDivider();

    }

    public static void printAllTasks(ArrayList<Task> taskArrayList){
        int TaskCount = 0;
        MainUI.printDivider();
        System.out.println("Here are the tasks in your list:");
        for (Task task: taskArrayList){
            TaskCount +=1;
            System.out.println(TaskCount + "." + task);
        }
        MainUI.printDivider();
    }

}