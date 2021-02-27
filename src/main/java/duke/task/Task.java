package duke.task;

import duke.exceptions.*;
import duke.util.Util;

import java.util.ArrayList;

/**
 * Represents a task in the real world.
 * A <code>Task</code> object contains the
 * task name and its completion status.
 */
public class Task {
    protected String description;
    private boolean isDone;
    private static ArrayList<Task> taskList = new ArrayList<>();
    private static int taskCount = 0;

    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    /**
     * Validates the @param of <code>addTask</code> method.
     *
     * Input error message will be shown if the parameters
     * provided are insufficient.
     *
     * Invalid date input error message will be shown if the
     * date is invalid.
     *
     * @param userInput Original user input.
     * @param t Task object to be added and validated.
     */
    public static void addTaskWithValidation(String userInput, Task t) {
        try {
            t.addTask();
        } catch (EmptyInputException | IncompleteInputException e) {
            t.printInputErrorMessage(userInput);
        } catch (InvalidDateInputException e) {
            t.printInvalidDateInputMessage(userInput);
        }
    }

    /**
     * Validates the @param of <code>markAsDone</code> method.
     *
     * Incomplete done input error message will be shown if
     * the parameters provided are insufficient.
     *
     * Invalid done input error message will be shown if the
     * input fields are invalid.
     *
     * @param i Number shown on the list of interested task.
     * @param userInput Original user input.
     */
    public static void markAsDoneWithValidation(String i, String userInput) {
        try {
            markAsDone(i, userInput);
        } catch (IncompleteInputException e) {
            printIncompleteDoneInputErrorMessage(userInput);
        } catch (InvalidInputException e) {
            printInvalidDoneInputErrorMessage(userInput);
        }
    }

    /**
     *
     *
     * @param i
     * @param userInput
     */
    public static void deleteTaskWithValidation(String i, String userInput){
        try {
            deleteTask(i, userInput);
        } catch (IncompleteInputException e) {
            printIncompleteDeleteInputErrorMessage(userInput);
        } catch (InvalidInputException e) {
            printInvalidDeleteInputErrorMessage(userInput);
        }
    }

    /**
     * Validates the @param of <code>findTask</code> method.
     *
     * Incomplete find input error message will be shown if
     * the parameters provided are insufficient.
     *
     * @param userInput Search keyword.
     */
    public static void findTaskWithValidation(String userInput) {
        try {
            findTask(userInput);
        } catch (IncompleteInputException e) {
            printIncompleteFindInputErrorMessage(userInput);
        }
    }

    private String getStatusIcon(){
        return(this.isDone ? "x" : " ");
    }

    public void setIsDone(){
        this.isDone = true;
    }

    /**
     * Adds corresponding task object to the array list.
     * Shows the task added upon successful addition to the list,
     * throws an exception otherwise.
     *
     * @throws EmptyInputException If task.description is empty.
     * @throws IncompleteInputException If some fields of a command is missing.
     * @throws InvalidDateInputException If date is not adhering to specified format.
     */
    public void addTask() throws
            EmptyInputException, IncompleteInputException, InvalidDateInputException {
        // Throw Exception if no valid task is detected
        if (isEmpty(this)){
            throw new EmptyInputException();
        }

        System.out.println("Got it, I've added this task:");
        System.out.print("  ");
        this.printTaskInformation();
        System.out.println("");
        addTaskToArrayList();
        printTaskCount();
    }

    /**
     * Sets the completion status of a task object to 1.
     * Shows the task marked as done upon successful execution,
     * throws an exception otherwise.
     *
     * @param i Number shown on the list of interested task.
     * @param userInput Original user input.
     * @throws IncompleteInputException If insufficient parameters were
     * given in the command.
     * @throws InvalidInputException If i <= 0 or i > taskCount.
     */
    public static void markAsDone(String i, String userInput) throws
            IncompleteInputException, InvalidInputException{

        if (isIncompleteIndexInput(userInput)){
            throw new IncompleteInputException();
        }
        if (isInvalidIndexInput(userInput)){
            throw new InvalidInputException();
        }

        int index = Integer.parseInt(i);
        Task t = taskList.get(index-1);
        t.isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.print("  ");
        t.printTaskInformation();
        System.out.println("");
    }

    /**
     * Removes a task object from the array list
     * corresponding to index = i-1.
     * Shows the task removed upon successful deletion,
     * throws an exception otherwise.
     *
     * @param i Number shown on the list of interested task.
     * @param userInput Original user input.
     * @throws IncompleteInputException If insufficient parameters were
     * given in the command.
     * @throws InvalidInputException If i <= 0 or i > taskCount.
     */
    public static void deleteTask(String i, String userInput) throws
            IncompleteInputException, InvalidInputException {

        if (isIncompleteIndexInput(userInput)){
            throw new IncompleteInputException();
        }
        if (isInvalidIndexInput(userInput)){
            throw new InvalidInputException();
        }

        int index = Integer.parseInt(i);
        Task t = taskList.get(index-1);
        System.out.println("Noted. I've removed this task:");
        System.out.print("  ");
        t.printTaskInformation();
        System.out.println("");
        taskList.remove(t);
        taskCount--;
        printTaskCount();
    }

    /**
     * Shows a list of tasks that matches the search keyword.
     * Shows no match is found when no tasks matches the
     * search keyword.
     *
     * @param userInput Search keyword
     * @throws IncompleteInputException If userInput is empty.
     */
    public static void findTask(String userInput) throws
            IncompleteInputException {
        if (userInput.equals("")){
            throw new IncompleteInputException();
        }
        int matchingTaskCount = 0;
        for(Task t : taskList){
            if (isMatchingTask(userInput, t)){
                matchingTaskCount++;
            }
        }
        if(matchingTaskCount == 0) {
            System.out.println("No match found :<");
        } else if(matchingTaskCount == 1) {
            System.out.println("Here is the matching task in your list:");
        } else if(matchingTaskCount > 1) {
            System.out.println("Here are the matching tasks in your list:");
        }
        for(Task t : taskList){
            if (isMatchingTask(userInput, t)){
                System.out.print((taskList.indexOf(t) + 1) + ". ");
                t.printTaskInformation();
                System.out.println("");
            }
        }
    }

    private static boolean isMatchingTask(String userInput, Task t) {
        String taskName = t.description.toLowerCase();
        return taskName.contains(userInput.toLowerCase());
    }

    /**
     * Adds task object to the array list.
     *
     * Increments the global variable taskCount.
     */
    public void addTaskToArrayList() {
        taskList.add(this);
        taskCount++;
    }

    private static void printSuccessfulTaskAddition() {
        if(taskCount == 1) {
            System.out.println("Now you have " + taskCount + " task in the list.");
        }
        else{
            System.out.println("Now you have " + taskCount + " tasks in the list.");
        }
    }

    /**
     * Shows the list of tasks added and not removed.
     * The index number (starting from 1), task type
     * symbol and task completion status symbol is shown.
     */
    public static void listTasks(){
        int index = 1;
        System.out.println("Here are the tasks in your list:");
        for(Task t : taskList){
            printIndex(index);
            t.printTaskInformation();
            System.out.println("");
            index++;
            if(!(taskCount > index - 1)){
                break;
            }
        }
    }

    private static void printIndex(int index) {
        System.out.print(Integer.toString(index) + ".");
    }

    public void printTaskInformation(){
        this.printTaskType();
        this.printStatusIcon();
        this.printTaskName();
    }

    public String getTaskType(){
        return "N/A";
    }

    public static int getTaskCount(){
        return taskList.size();
    }

    public String getDate(){
        return "N/A";
    }

    /**
     * Returns a task entry in a specific format to be saved
     * in a file.
     * Task entries are formatted according to its task type.
     *
     * @param i Index number of the array list.
     * @return Formatted entry to be saved in a file.
     */
    public static String getTask(int i){
        Task t = taskList.get(i);
        String taskType = t.getTaskType();
        String statusIcon = (t.getStatusIcon().equals("x") ? "1" : "0");
        if (t instanceof Todo){
            return taskType + " | " + statusIcon  + " | " + t.description
                    + System.lineSeparator();
        } else {
            return taskType + " | " + statusIcon + " | " + t.description
                    + " | " + t.getDate() + System.lineSeparator() ;
        }
    }

    private static void printTaskCount() {
        printSuccessfulTaskAddition();
    }

    private void printTaskType(){
        System.out.print("[" + this.getTaskType() + "]");
    }

    private void printStatusIcon(){
        System.out.print("[" + this.getStatusIcon() + "] ");
    }

    private void printTaskName(){
        System.out.print(this.description);
    }

    private boolean isEmpty(Task t){
        return t.description.equals("");
    }

    private static boolean isIncompleteIndexInput(String userInput){
        return Util.getTaskIndex(userInput).equals("-1");
    }

    private static boolean isInvalidIndexInput(String userInput){
        return Util.getTaskIndex(userInput).equals("0");
    }

    public void printInputErrorMessage(String userInput) {
        System.out.println("The command entered is INCOMPLETE: "
                + userInput + "\n");
        System.out.println("Please enter the command as follows:");
    }

    public void printInvalidDateInputMessage(String userInput) {
        System.out.println("The date entered is INVALID: "
                + userInput + "\n");
        System.out.println("Please enter the command as follows:");
    }

    private static void printIncompleteDoneInputErrorMessage(String userInput){
        System.out.println("The command entered is INCOMPLETE: " +
                userInput + "\n");
        System.out.println("Please enter the command as follows:");
        System.out.println("  done [task number]");
        System.out.println("    e.g. done 3");
    }

    private static void printInvalidDoneInputErrorMessage(String userInput) {
        System.out.println("The command entered is INVALID: " +
                userInput + "\n");
        System.out.println("Please enter the command as follows:");
        System.out.println("  done [task number]");
        System.out.println("    e.g. done 3" + "\n");
        System.out.println("[task number] field must be:");
        System.out.println("1. Within range (according to number of items in the list).");
        System.out.println("2. An integer greater than 0.");
    }

    private static void printIncompleteFindInputErrorMessage(String userInput) {
        System.out.println("The command entered has insufficient parameters."
                + "\n");
        System.out.println("Please enter the command as follows:");
        System.out.println("  find [search keyword]");
        System.out.println("    e.g. find project");
    }

    private static void printIncompleteDeleteInputErrorMessage(String userInput) {
        System.out.println("The command entered is INCOMPLETE: " +
                userInput + "\n");
        System.out.println("Please enter the command as follows:");
        System.out.println("  delete [task number]");
        System.out.println("    e.g. delete 3");
    }

    private static void printInvalidDeleteInputErrorMessage(String userInput) {
        System.out.println("The command entered is INVALID: " +
                userInput + "\n");
        System.out.println("Please enter the command as follows:");
        System.out.println("  delete [task number]");
        System.out.println("    e.g. delete 3" + "\n");
        System.out.println("[task number] field must be:");
        System.out.println("1. Within range (according to number of items in the list).");
        System.out.println("2. An integer greater than 0.");
    }
}
