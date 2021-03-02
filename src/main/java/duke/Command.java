package duke;

import duke.tasksmanager.Deadlines;
import duke.tasksmanager.Events;
import duke.tasksmanager.Tasks;
import duke.tasksmanager.ToDos;

import static duke.Parser.*;
import static duke.Parser.commandWord;
import static duke.Storage.tasksCount;

public class Command {

    public static boolean isExit = false;

    public Command() {

    }

    public static boolean execute (TaskList tasks, Ui ui, Storage storage) {

        if (commandWord.equals("todo")) {
            addToDo(taskName, tasks);
        } else if (commandWord.equals("deadline")) {
            addDeadline(taskName, taskDate, tasks);
        } else if (commandWord.equals("event")) {
            addEvent(taskName, taskDate, tasks);
        }
        //OR: remove task from list of tasks
        else if (commandWord.equals("delete")) {
            deleteAndPrintTask(index, tasks);
        }
        //OR: mark current task as 'done' & outputs the taskType,taskStatus,taskName(and taskDate):
        else if (commandWord.equals("done")) {
            markAndPrintsTaskAsDone(index, tasks);
        }
        //OR: lists all the user's current tasks in the format of taskType,taskStatus,taskName(and taskDate):
        else if (commandWord.equals("list")) {
            printsList(tasks);
        }
        else if (commandWord.equals("bye")) {
            ui.saysByeToUser();
            isExit = true;
        }
        //OR: deal with invalid command
        else {
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        return isExit;
    }

    /**
     * Takes in User's input string
     * Adds todo type task to 'tasks' array
     * Prints added todo task
     *
     * @param taskName - user's input for Name
     */
    public static void addToDo(String taskName, TaskList tasks) {
        tasks.add(new ToDos(taskName)); //add task to list

        System.out.println("Got it. I've added this task:");
        printAddedTask(tasks);
    }

    /**
     * Takes in User's input string
     * Adds deadline type task to 'tasks' array
     * Prints added deadline task
     *
     * @param taskName,taskDate - user's input
     */
    private static void addDeadline(String taskName, String taskDate, TaskList tasks) {
        tasks.add(new Deadlines(taskName, taskDate)); //add task to list

        System.out.println("Got it. I've added this task:");
        printAddedTask(tasks);
    }

    /**
     * Takes in User's input string
     * Adds event type task to 'tasks' array
     * Prints added event task
     *
     * @param taskName,taskDate - user's input
     */
    private static void addEvent(String taskName, String taskDate, TaskList tasks) {
        tasks.add(new Events(taskName, taskDate)); //add task to list

        System.out.println("Got it. I've added this task:");
        printAddedTask(tasks);
    }

    /**
     * Takes in User's input string
     * Splits into 2 parts, the command 'delete' & TaskNumber
     * NumberFormatException() - when user does not input a TaskNumber
     * Obtains Index of Task to be deleted from it's taskNumber
     *
     * IndexOutOfBoundsException() - when user inputs an 'out of range' taskNumber
     * Removes Task from 'tasks' array
     *
     * Prints deleted task
     *
     * @param index - user's input - format: 'delete taskNumber'
     */
    private static void deleteAndPrintTask(int index, TaskList tasks) {
        try {
            Tasks deletedTask = tasks.get(index); //throws IndexOutOfBoundsException() if taskNumber out of bounds
            tasks.remove(index); //remove task

            System.out.println("Noted. I've removed this task: ");
            System.out.println(" " + deletedTask.convertToTaskOutputString());

        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please input a smaller or bigger valid task number.");
            System.out.println("You can list all tasks to check the total number of tasks you have. :))"); //invalid TaskNumber
        }
    }

    /**
     * Takes in User's input string
     * Splits into 2 parts, the command 'done' & TaskNumber
     * NumberFormatException() - when user does not input a TaskNumber
     * Obtains Index of Task to be deleted from it's taskNumber
     *
     * IndexOutOfBoundsException() - when user inputs an 'out of range' taskNumber
     * Marks Task as done (in 'tasks' array)
     *
     * Prints marked task
     *
     * @param index - user's input string - format: 'delete taskNumber'
     */
    public static void markAndPrintsTaskAsDone(int index, TaskList tasks) {
        try {
            Tasks taskDone = tasks.get(index); //throws IndexOutOfBoundsException() if taskNumber out of bounds
            taskDone.markAsDone(); //mark task given by current command as 'done'

            System.out.println("Nice! I've marked this task as done:");
            System.out.println(" " + taskDone.convertToTaskOutputString());

        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please input a smaller or bigger valid task number.");
            System.out.println("You can list all tasks to check the total number of tasks you have. :))"); //invalid TaskNumber
        }
    }

    /**
     * Prints out the taskType, status, taskName and taskDate of the task added by the user
     * and prints the current total number of tasks in the user's list
     * Add to total taskCount (since new task is added)
     */
    public static void printAddedTask(TaskList tasks) {
        System.out.println("  " + tasks.get(tasksCount-1).convertToTaskOutputString()); //prints task added
        //prints current total number of tasks (in the list of tasks):don
        System.out.print("Now you have " + tasksCount + " task");
        if (tasksCount > 1) {
            System.out.print("s");
        }
        System.out.println(" in the list.");
    }

    /**
     * Prints all tasks in current List 'tasks'
     * */
    private static void printsList(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasksCount; i++) {
            int taskNumber = i+1; //stores the current numbering of the bulleted tasks
            System.out.println(taskNumber + "." + tasks.get(i).convertToTaskOutputString());
        }
    }

}
