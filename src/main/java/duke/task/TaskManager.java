package duke.task;

import duke.command.CommandNotFoundException;
import duke.command.MainUI;


import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import java.io.IOException;

import static duke.command.MainUI.taskArrayList;

public class TaskManager {
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    public static final String DONE_COMMAND = "done";
    public static final String DELETE_COMMAND = "delete";

    private static final String ADDED_TASK_MESSAGE = "Got it. I have added this task: ";
    private static final String MARKED_TASK_AS_DONE_MESSAGE = "Nice! I've marked this task as done: ";


    public static void handleTask(String input) throws CommandNotFoundException {
        String firstWord = StringManipulator.getFirstWord(input);
        switch (firstWord){
        case DONE_COMMAND:
            try {
                int taskNumberDone = StringManipulator.getTaskNumberDone(input);
                markTaskAsDone(taskNumberDone);
                updateDataFile();
            } catch (IOException e) {
                System.out.println("IOError at Done command");
            }
            break;
        case TODO_COMMAND:
            try{
                String taskDescription = StringManipulator.getIndexOfStringAfterWhiteSpace(input);
                Todo t = new Todo(taskDescription);
                t.checkIfToDoDescriptionExists(taskDescription);
                taskArrayList.add(t);
                printMessageAfterTaskIsAdded(t);
                updateDataFile();
            } catch (TaskDescriptionMissingException e) {
                System.out.println(e.getMessage());
                MainUI.printDivider();
            } catch (IOException e) {
                System.out.println("IOError at TODO Command");
            }
            break;
            
        case DEADLINE_COMMAND:
            try {
                String dueDate = StringManipulator.getStringAfterSlash(input);
                String taskDescription = StringManipulator.getStringAfterWhiteSpaceAndBeforeSlash(input);
                System.out.println("deadline's dueDate: "+ dueDate);
                Deadline d = new Deadline(taskDescription,dueDate);
                d.checkIfDeadlineDescriptionExists(taskDescription);
                taskArrayList.add(d);
                printMessageAfterTaskIsAdded(d);
                updateDataFile();
            } catch (TaskDescriptionMissingException e) {
                System.out.println(e.getMessage());
                MainUI.printDivider();
            } catch (IOException e) {
                System.out.println("IOError at DEADLINE Command");
            }
            break;
        case EVENT_COMMAND:
            try {
                String eventPeriod = StringManipulator.getStringAfterSlash(input);
                String taskDescription = StringManipulator.getStringAfterWhiteSpaceAndBeforeSlash(input);
                System.out.println("event's dueDate: "+ eventPeriod);
                Event e = new Event(taskDescription,eventPeriod);
                taskArrayList.add(e);
                printMessageAfterTaskIsAdded(e);
                updateDataFile();
            } catch (IOException e) {
                System.out.println("IOError at EVENT Command");
            }
            break;
        case DELETE_COMMAND:
            try {
                int taskNumberDeleted = StringManipulator.getTaskNumberDeleted(input);
                System.out.println("Noted. I've removed this task:\n" + taskArrayList.get(taskNumberDeleted-1));
                taskArrayList.remove(taskNumberDeleted-1);
                Task.decreaseTaskCount();
                System.out.println("Now you have " + Task.getTaskCount() + " tasks in the list.");
                updateDataFile();
        } catch (IOException e) {
            System.out.println("IOError at DELETE Command");
        }
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

    public static void updateDataFile() throws IOException {
        PrintStream out = new PrintStream(new FileOutputStream(MainUI.FILE_PATH));
        PrintStream stdout = System.out;
        System.setOut(out);
        for (Task task: taskArrayList){
            System.out.println(task);
        }
        System.setOut(stdout);
    }
}