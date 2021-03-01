package duke;

import java.util.ArrayList;
import java.util.Arrays;

public class TaskList {
    protected ArrayList<Task> tasks = new ArrayList<>();

    protected Storage storage = new Storage();

    public TaskList(){

    }

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
        default:
            System.out.println("I have no such feature!");
            break;
        }
        return newItem;
    }

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

    public Task todoCommand(String[] command, ArrayList<Task> tasks) throws DukeException{
        if(command.length > 1) {
            Task newItem = new Todo(String.join(" ", Arrays.copyOfRange(command, 1, command.length)));
            tasks.add(newItem);
            return newItem;
        } else {
            throw new DukeException();
        }
    }

    public Task eventCommand(String[] command, ArrayList<Task> tasks) throws DukeException{
        if(Parser.checkForSubstring(command, "/at")){
            int separatorIndex = Parser.indexOfSubstring(command, "/at");
            String description = String.join(" ", Arrays.copyOfRange(command,
                    1, separatorIndex));
            String at = String.join(" ",Arrays.copyOfRange(command,
                    separatorIndex + 1, command.length));

            Task newItem = new Event(description, at);
            tasks.add(newItem);
            return newItem;
        } else{
            throw new DukeException();
        }
    }

    public Task deadlineCommand(String[] command, ArrayList<Task> tasks) throws DukeException{
        if(Parser.checkForSubstring(command, "/by")){
            int separatorIndex = Parser.indexOfSubstring(command, "/by");
            String description = String.join(" ", Arrays.copyOfRange(command,
                    1, separatorIndex));
            String by = String.join(" ",Arrays.copyOfRange(command,
                    separatorIndex + 1, command.length));

            Task newItem = new Deadline(description, by);
            tasks.add(newItem);
            return newItem;
        } else{
            throw new DukeException();
        }
    }

    public ArrayList<Task> getTasks(){
        return this.tasks;
    }
}
