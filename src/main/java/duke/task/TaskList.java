package duke.task;

import duke.exception.DukeException;
import java.util.ArrayList;

/*
Class TaskList for creating multiple task objects as a collection
*/
public class TaskList {
    protected ArrayList<Task> userTasks;

    /*
    Constructor TaskList initializing multiple task objects as a collection
    */
    public TaskList() {
        userTasks = new ArrayList<>();
    }

    /*
    Constructor for TaskList Object
    Initialize userTasks variable from the argument passed in
    */
    public TaskList(ArrayList<Task> userTasks) {
        this.userTasks = userTasks;
    }

    /*
    Returns all user tasks created
    */
    public ArrayList<Task> getUserTasks(){
        return userTasks;
    }

    /*
    Function that delete a task from userTasks variable
    */
    public void deleteATask(String action) throws DukeException{
        int activityNumber;
        Task temporaryTask;
        try {
            activityNumber = Integer.parseInt(action);
            temporaryTask = userTasks.get(activityNumber-1);
            userTasks.remove(temporaryTask);
            System.out.println("Noted. I've removed this task: ");
            System.out.println(temporaryTask.toString());
            System.out.println("Now you have " + userTasks.size() + " tasks in the list. ");
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid task number");
        }
    }

    /*
    Function that mark a task done from userTasks variable
    */
    public void markActivityAsDone(String action) throws DukeException{
        int activityNumber;
        try {
            activityNumber = Integer.parseInt(action);
            userTasks.get(activityNumber-1).setTaskStatus(true);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(userTasks.get(activityNumber-1).toString());
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid task number");
        }
    }

    /*
    Function that create a event task into userTasks variable
    */
    public void createEventTask(String action) throws DukeException{
        String newUserTask;
        try {
            newUserTask = action.split("/at")[0];
            String eventTime = action.split("/at")[1];
            userTasks.add(new Event(newUserTask, eventTime));
            showTaskCreationMessage(userTasks.get(userTasks.size()-1), userTasks.size());
        } catch (ArrayIndexOutOfBoundsException e){
            throw new DukeException("☹ OOPS!!! The description or date of a event cannot be empty.");
        }
    }

    /*
    Function that create a deadline task into userTasks variable
    */
    public void createDeadlineTask(String action) throws DukeException{
        String newUserTask;
        try {
            newUserTask = action.split("/by")[0];
            String date = action.split("/by")[1];
            userTasks.add(new Deadline(newUserTask, date));
            showTaskCreationMessage(userTasks.get(userTasks.size()-1), userTasks.size());
        } catch (ArrayIndexOutOfBoundsException e){
            throw new DukeException("☹ OOPS!!! The description or date of a deadline cannot be empty.");
        }
    }

    /*
    Function that create a todo task into userTasks variable
    */
    public void createTodoTask(String action) throws DukeException{
        String newUserTask;
        try {
            newUserTask = action;
            userTasks.add(new Todo(newUserTask));
            showTaskCreationMessage(userTasks.get(userTasks.size()-1), userTasks.size());
        } catch (ArrayIndexOutOfBoundsException e){
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

    /*
    Function displays all tasks created so far
    */
    public void displayListOfActivities() {
        System.out.println("Here are the tasks in your list:");
        for (int counter = 0; counter < userTasks.size(); counter++) {
            System.out.println((counter+1) + "." + userTasks.get(counter).toString());
        }
    }

    private void showTaskCreationMessage(Task userTask, int noOfTasks) {
        System.out.println("Got it. I've added this task: ");
        System.out.println(userTask.toString());
        System.out.println("Now you have " + noOfTasks + " tasks in the list. ");
    }
}
