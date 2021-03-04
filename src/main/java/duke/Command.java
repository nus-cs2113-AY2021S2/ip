package duke;

import duke.tasksmanager.Deadlines;
import duke.tasksmanager.Events;
import duke.tasksmanager.Tasks;
import duke.tasksmanager.ToDos;

import java.util.ArrayList;

import static duke.Parser.commandWord;
import static duke.Parser.index;
import static duke.Parser.taskName;
import static duke.Parser.taskDate;

import static duke.Storage.tasksCount;

/**
 * Command Class: Contains methods for executing the User's command.
 */
public class Command {

    public static boolean isExit = false;

    public Command() {

    }

    /**
     * Executes User's command through specific methods included for each command.
     * Adds task if command is of todo, deadline, or event type.
     * Removes task if command is delete.
     * Marks task as done if command is done.
     * Lists all tasks in current list of tasks if command is list.
     * Returns isExit to signal for Duke to stop program. Boolean value isExit is set to true when command is 'bye'.
     *
     * @param tasks most current list storing user's tasks
     * @param ui ui object associated with methods that deals with interaction with User
     * @return isExit
    */
    public static boolean execute(TaskList tasks, Ui ui) {

        //adds task to list of tasks:
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
        //OR: searches for all tasks with the keyword
        else if (commandWord.equals("find")) {
            findTasksContainingKeyWord(keyword, tasks);
        }
        //OR: exits program
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
     * Takes in user's input of 'todo' task to
     * add task to current list of tasks.
     * Then, it shows the user the added task for success of addition.
     *
     * @param taskName Name of the task parsed from user's input
     * @param tasks Most current list storing all tasks
     */
    private static void addToDo(String taskName, TaskList tasks) {
        tasks.add(new ToDos(taskName)); //add task to list

        System.out.println("Got it. I've added this task:");
        printAddedTask(tasks);
    }

    /**
     * Takes in user's input of 'deadline' task to
     * add task to current list of tasks.
     * Then, it shows the user the added task for success of addition.
     *
     * @param taskName Name of the task parsed from user's input
     * @param taskDate Date of the task parsed from user's input
     * @param tasks Most current list storing all tasks
     */
    private static void addDeadline(String taskName, String taskDate, TaskList tasks) {
        tasks.add(new Deadlines(taskName, taskDate)); //add task to list

        System.out.println("Got it. I've added this task:");
        printAddedTask(tasks);
    }

    /**
     * Takes in user's input of 'event' task to
     * add task to current list of tasks.
     * Then, it shows the user the added task for success of addition.
     *
     * @param taskName Name of the task parsed from user's input.
     * @param taskDate Date of the task parsed from user's input.
     * @param tasks Most current list storing all tasks.
     */
    private static void addEvent(String taskName, String taskDate, TaskList tasks) {
        tasks.add(new Events(taskName, taskDate)); //add task to list

        System.out.println("Got it. I've added this task:");
        printAddedTask(tasks);
    }

    /**
     * Remove the task from the current list using the index.
     * Finds task in current list using index parsed from the 'delete' command
     * and show user the deleted task for success of deletion.
     * <p>
     * If user inputs an 'out of range' index, tells user to input index that is within current list.
     *
     * @param index derived from taskNumber, tells position of task in current list.
     * @param tasks Most current list storing all tasks.
     * @throws IndexOutOfBoundsException if user inputs an 'out of range' taskNumber.
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
     * Mark the task (as 'done') from the current list using the index.
     * Finds task in current list using index parsed from the 'done' command
     * and show user the marked task for success of marking task.
     * <p>
     * If user inputs an 'out of range' index, tells user to input index that is within current list.
     *
     * @param index derived from taskNumber, tells position of task in current list.
     * @param tasks Most current list storing all tasks.
     * @throws IndexOutOfBoundsException if user inputs an 'out of range' taskNumber.
     */
    private static void markAndPrintsTaskAsDone(int index, TaskList tasks) {
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
     * Prints out the taskType, status, taskName and taskDate of the task added by the user.
     * It also prints the current total number of tasks in the user's list.
     *
     * @param tasks Most current list storing all tasks.
     */
    private static void printAddedTask(TaskList tasks) {
        System.out.println("  " + tasks.get(tasksCount-1).convertToTaskOutputString()); //prints task added
        //prints current total number of tasks:
        System.out.print("Now you have " + tasksCount + " task");
        if (tasksCount > 1) {
            System.out.print("s");
        }
        System.out.println(" in the list.");
    }

    /**
     * Prints all tasks in the current list.
     *
     * @param tasks Most current list storing all tasks.
     */
    private static void printsList(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasksCount; i++) {
            int taskNumber = i+1; //stores the current numbering of the bulleted tasks
            System.out.println(taskNumber + "." + tasks.get(i).convertToTaskOutputString());
        }
    }

    /**
     * Searches through the latest List called tasks
     * to add all tasks with: taskName or taskDate containing the keyword
     * to a list called matchingTasks
     * Outputs 'no tasks found' or all matchingTasks
     */
    private static void findTasksContainingKeyWord(String keyword, TaskList tasks) {
        int matchingTasksCount = 0;
        //Adds all tasks containing 'keyword' to a list:
        ArrayList<String> matchingTasks = new ArrayList<>();
        for (int i = 0; i < tasksCount; i++) {
            int taskNumber = i+1; //stores the current numbering of the bulleted tasks
            if (tasks.get(i).description.contains(keyword) || tasks.get(i).date.contains(keyword)) {
                matchingTasks.add(taskNumber + "." + tasks.get(i).convertToTaskOutputString());
                matchingTasksCount++;
            }
        }

        //Output:
        if (matchingTasksCount == 0) {
            System.out.println("There are no matching tasks that contains the word.");
        }
        else {
            System.out.println("Here are the matching tasks in the list:");
            for (String currentTask: matchingTasks) {
                System.out.println(currentTask);
            }
        }
    }

}
