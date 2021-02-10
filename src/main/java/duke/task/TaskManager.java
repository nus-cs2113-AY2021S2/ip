package duke.task;

import duke.command.CommandNotFoundException;
import duke.command.MainUI;

import java.util.Arrays;

public class TaskManager {
    private static final int MAX_TASK_SIZE = 100;

    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    public static final String DONE_COMMAND = "done";

    private static final String ADDED_TASK_MESSAGE = "Got it. I have added this task: ";
    private static final String MARKED_TASK_AS_DONE_MESSAGE = "Nice! I've marked this task as done: ";

    public TaskManager(){
        // TODO: 02-Feb-21
    }

    private static Task[] tasksObjectsArray = new Task[MAX_TASK_SIZE];

    public static void handleTask(String input) throws CommandNotFoundException {
//        String[] commandWords = input.split(" ");
//        String firstWord = commandWords[0];
        String firstWord = StringManipulator.getFirstWord(input);
        switch (firstWord){
        case DONE_COMMAND:
            int taskNumberDone = StringManipulator.getTaskNumberDone(input);
            markTaskAsDone(taskNumberDone);
            break;
        case TODO_COMMAND:
            try{
                String taskDescription = StringManipulator.getIndexOfStringAfterWhiteSpace(input);
                System.out.println(taskDescription);
                Todo t = new Todo(taskDescription);
                t.checkIfToDoDescriptionExists(taskDescription);
                System.out.println("added: " + taskDescription + "to array");
                tasksObjectsArray[Task.getTaskCount()-1] = t;
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
                tasksObjectsArray[Task.getTaskCount()-1] = d;
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
            tasksObjectsArray[Task.getTaskCount()-1] = e;
            printMessageAfterTaskIsAdded(e);
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
        tasksObjectsArray[taskNumber-1].markAsDone();
        System.out.println("[" + tasksObjectsArray[taskNumber-1].getStatusIcon() + "]"
                + tasksObjectsArray[taskNumber-1].getDescription());
    }

    public static Task[] removeNullFromList(){
        return Arrays.copyOf(tasksObjectsArray, Task.getTaskCount());
    }

    public static void printTaskList(){
        Task[] tasks = removeNullFromList();
        int TaskCount = 0;
        MainUI.printDivider();
        System.out.println("Here are the tasks in your list:");
        for (Task task: tasks){
            TaskCount +=1;
            System.out.println(TaskCount + "." + task);
        }
        MainUI.printDivider();
    }

}