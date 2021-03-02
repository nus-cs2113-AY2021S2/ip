package duke;

import java.util.ArrayList;
import java.util.Arrays;

public class TaskList {
    protected ArrayList<Task> tasks = new ArrayList<>();
    protected Storage storage = new Storage();

    /**
     * A constructor for TaskList class, that will contain the arraylist of tasks entered by user.
     */
    public TaskList(){

    }

    /**
     * Function to check if the user input is an actual command or not, and acts accordingly
     *
     * @param command an array of strings, which was the user's input separated by spaces
     * @param tasks the arraylist containing all user entered tasks
     *
     * @returns null if no object was created by the users input, and returns an upcasted form if there was
     */
    public Task populateArrayList(String[] command, ArrayList<Task> tasks){
        Task newItem = null;
        switch(command[0]){
        case "list":
            try{
                listCommand(command, tasks);
            }
            catch (DukeException e){
                System.out.println("The list command can only contain 1 word.");
            }
            catch (Exception e){
                Messages.badUserInputMessage();
            }
            break;
        case "done":
            try{
                doneCommand(command, tasks);
                storage.wrapWriteToDisk(tasks);
            }
            catch (DukeException e){
                System.out.println("The done command consists of the word done, and an integer.");
            }
            catch (Exception e){
                Messages.badUserInputMessage();
            }
            break;
        case "todo":
            try{
                newItem = todoCommand(command, tasks);
            }
            catch (DukeException e){
                System.out.println("The description of a todo cannot be empty!");
            }
            catch (Exception e){
                Messages.badUserInputMessage();
            }
            break;
        case "event":
            try{
                newItem = eventCommand(command, tasks);
            }
            catch (DukeException e){
                System.out.println("Your input should contain /at separated by spaces, " +
                        "followed by the event time.");
            }
            catch(Exception e){
                Messages.badUserInputMessage();
            }
            break;
        case "deadline":
            try{
                newItem = deadlineCommand(command, tasks);
            }
            catch(DukeException e){
                System.out.println("Your input should contain /by separated by spaces, " +
                        "followed by the deadline.");
            }
            catch (Exception e){
                Messages.badUserInputMessage();
            }
            break;
        case "bye":
            if(command.length>1){
                System.out.println("I have no such feature!");
            }
            break;
        case "delete":
            try{
                deleteCommand(command, tasks);
                storage.wrapWriteToDisk(tasks);
            } catch (DukeException e) {
                System.out.println("The delete command consists of the word delete, and an integer.");
            } catch (Exception e) {
                Messages.badUserInputMessage();
            }
            break;
        case "find":
            findCommand(command, tasks);
            break;
        default:
            System.out.println("I have no such feature!");
            break;
        }
        return newItem;
    }

    /**
     * Searches through the task list for a search term given by user, and prints matching results. Prints an error
     * message if search fails
     *
     * @param command an array of strings, which was the user's input separated by spaces. Index 0 is "find"
     * @param tasks the arraylist containing all user entered tasks
     */
    public void findCommand(String[] command, ArrayList<Task> tasks){
        if(command.length == 1){
            System.out.println("Kindly enter a term to search for!");
        } else {
            String searchTerm = String.join(" ", Arrays.copyOfRange(command, 1, command.length));
            String searchString;
            int count = 0;
            int index = 1;
            for (Task task : tasks) {
                searchString = task.getDescription();
                searchString += " ";
                if (task instanceof Deadline) {
                    searchString += ((Deadline) task).getBy();
                } else if (task instanceof Event) {
                    searchString += ((Event) task).getAt();
                }
                if(searchString.contains(searchTerm)){
                    System.out.println(index + "." + task.toString());
                    count += 1;
                }
                index += 1;
            }
            if(count == 0){
                Messages.noFindResults(searchTerm);
            }
        }
    }

    /**
     * Lists all tasks (and their corresponding timings if any). Prints an empty list if the user has not entered any
     * tasks
     *
     * @param command an array of strings, which was the user's input separated by spaces. Index 0 is "list"
     * @param tasks the arraylist containing all user entered tasks
     */
    public void listCommand(String[] command, ArrayList<Task> tasks) throws DukeException{
        if(command.length == 1){
            int index = 1;
            System.out.println(Messages.LINE);
            for (Task task : tasks) {
                System.out.println(index + "." + task.toString());
                index += 1;
            }
            System.out.println(Messages.LINE);
        } else {
            throw new DukeException();
        }
    }

    /**
     * Marks a task as done, by checking the list of user entered tasks for the index specified by the user. Will print
     * an error message if the operation fails.
     *
     * @param command an array of strings, which was the user's input separated by spaces. Index 0 is "done"
     * @param tasks the arraylist containing all user entered tasks
     */
    public void doneCommand(String[] command, ArrayList<Task> tasks) throws DukeException{
        if(command.length == 2 && Parser.checkIfInteger(command[1])){
            int index = Integer.parseInt(command[1]) - 1;
            if (0 <= index && index < tasks.size()) {
                // If the given value to set as done is an existing index
                tasks.get(index).setAsDone();
                Messages.markTaskDoneMessage(tasks, index);
            } else {
                System.out.println("The input index that you have selected to indicate as done, "+
                        "is out of the range of existing indexes!");
            }
        } else {
            throw new DukeException();
        }
    }

    /**
     * Deletes a task, finds the item by checking the list of user entered tasks for the index specified by the user.
     * Will print an error message if the operation fails.
     *
     * @param command an array of strings, which was the user's input separated by spaces. Index 0 is "delete"
     * @param tasks the arraylist containing all user entered tasks
     */
    public void deleteCommand(String[] command, ArrayList<Task> tasks) throws DukeException{
        if(command.length == 2 && Parser.checkIfInteger(command[1])){
            int index = Integer.parseInt(command[1]) - 1;
            if (0 <= index && index < tasks.size()) {
                // If the given value to delete is an existing index, allow deletion
                Messages.deleteTaskMessage(tasks, index);
                tasks.remove(index);
            } else {
                System.out.println("The input index that you have selected to delete, "+
                        "is out of the range of existing indexes!");
            }
        } else {
            throw new DukeException();
        }
    }

    /**
     * Creates a new Task object and adds it to the arraylist, as well as return this newly created object.
     *
     * @param command an array of strings, which was the user's input separated by spaces. Index 0 is "todo"
     * @param tasks the arraylist containing all user entered tasks
     *
     * @return a Task object, created from information from command. Return null if there were issues with user input
     */
    public Task todoCommand(String[] command, ArrayList<Task> tasks) throws DukeException{
        if(command.length > 1) {
            Task newItem = new Todo(String.join(" ", Arrays.copyOfRange(command, 1, command.length)));
            tasks.add(newItem);
            return newItem;
        } else {
            throw new DukeException();
        }
    }

    /**
     * Creates a new Event object and adds it to the arraylist, as well as return this newly created object.
     *
     * @param command an array of strings, which was the user's input separated by spaces. Index 0 is "event"
     * @param tasks the arraylist containing all user entered tasks
     *
     * @return an Event object, created from information from command. Return null if there were issues with user input
     */
    public Task eventCommand(String[] command, ArrayList<Task> tasks) throws DukeException{
        if(Parser.checkForSubstring(command, "/at")){
            int separatorIndex = Parser.indexOfSubstring(command, "/at");
            String description = String.join(" ", Arrays.copyOfRange(command,
                    1, separatorIndex));
            String at = String.join(" ",Arrays.copyOfRange(command,
                    separatorIndex + 1, command.length));
            if(Parser.checkValidLocalDate(at)){
                Task newItem = new Event(description, at);
                tasks.add(newItem);
                return newItem;
            } else{
                System.out.println("Dates are to be entered in the following format: yyyy-mm-dd");
                return null;
            }
        } else{
            throw new DukeException();
        }
    }

    /**
     * Creates a new Deadline object and adds it to the arraylist, as well as return this newly created object.
     *
     * @param command an array of strings, which was the user's input separated by spaces. Index 0 is "deadline"
     * @param tasks the arraylist containing all user entered tasks
     *
     * @return a Deadline object, created from information from command. Return null if any issues with user input
     */
    public Task deadlineCommand(String[] command, ArrayList<Task> tasks) throws DukeException{
        if(Parser.checkForSubstring(command, "/by")){
            int separatorIndex = Parser.indexOfSubstring(command, "/by");
            String description = String.join(" ", Arrays.copyOfRange(command,
                    1, separatorIndex));
            String by = String.join(" ",Arrays.copyOfRange(command,
                    separatorIndex + 1, command.length));
            if(Parser.checkValidLocalDate(by)){
                Task newItem = new Deadline(description, by);
                tasks.add(newItem);
                return newItem;
            } else{
                System.out.println("Dates are to be entered in the following format: yyyy-mm-dd");
                return null;
            }
        } else{
            throw new DukeException();
        }
    }

    /**
     * Returns the arraylist of user's tasks, for use in certain operations
     *
     * @return arraylist of all user's
     */
    public ArrayList<Task> getTasks(){
        return this.tasks;
    }
}
