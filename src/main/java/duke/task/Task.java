package duke.task;

import duke.exceptions.*;
import duke.util.Util;

import java.util.ArrayList;

public class Task {
    protected String description;
    private boolean isDone;
    private static ArrayList<Task> taskList = new ArrayList<>();
    private static int taskCount = 0;

    // Task Constructor
    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    public static void addTaskWithValidation(String userInput, Task t) {
        try {
            t.addTask();
        } catch (EmptyInputException | IncompleteInputException e) {
            t.printInputErrorMessage(userInput);
        } catch (InvalidDateInputException e) {
            t.printInvalidDateInputMessage(userInput);
        }
    }

    public static void markAsDoneWithValidation(String i, String userInput) {
        try {
            markAsDone(i, userInput);
        } catch (IncompleteDoneInputException e) {
            printIncompleteDoneInputErrorMessage(userInput);
        } catch (InvalidDoneInputException e) {
            printInvalidDoneInputErrorMessage(userInput);
        }
    }

    public String getStatusIcon(){
        return(this.isDone ? "x" : " ");
    }

    public void setIsDone(){
        this.isDone = true;
    }

    public static void markAsDone(String i, String userInput) throws
            IncompleteDoneInputException, InvalidDoneInputException{

        if (isIncompleteDoneInput(userInput)){
            throw new IncompleteDoneInputException();
        }
        if (isInvalidDoneInput(userInput)){
            throw new InvalidDoneInputException();
        }

        int index = Integer.parseInt(i);
        Task t = taskList.get(index-1);
        t.isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.print("  ");
        t.printTaskInformation();
        System.out.println("");
    }

    // Add Task to taskList and increment taskCount
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

    public static void deleteTask(String i){
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

    public static void findTask(String userInput){
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

    private static boolean isIncompleteDoneInput(String userInput){
        return Util.getTaskIndex(userInput).equals("-1");
    }

    private static boolean isInvalidDoneInput(String userInput){
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

    public static void printIncompleteDoneInputErrorMessage(String userInput){
        System.out.println("The command entered is INCOMPLETE: " +
                userInput + "\n");
        System.out.println("Please enter the command as follows:");
        System.out.println("  done [task number]");
        System.out.println("    e.g. done 3");
    }

    public static void printInvalidDoneInputErrorMessage(String userInput) {
        System.out.println("The command entered is INVALID: " +
                userInput + "\n");
        System.out.println("Please enter the command as follows:");
        System.out.println("  done [task number]");
        System.out.println("    e.g. done 3" + "\n");
        System.out.println("[task number] field must be:");
        System.out.println("1. Within range (according to number of items in the list).");
        System.out.println("2. An integer greater than 0.");
    }

}
