import java.io.IOException;
/**
 * This block is to execute the commands given
 */
public class Command {

    public static void findCommand(String taskInput){
        String[] splitTaskInput = taskInput.toLowerCase().split(" ");
        String[] taskDescription;
        try{
            switch (splitTaskInput[0]){
                case "done":
                    int taskNumberCompleted = Integer.parseInt(splitTaskInput[1]);
                    taskNumberCompleted--;
                    boolean isSuccess = Task.completeTask(taskNumberCompleted);
                    UI.taskCompleted(isSuccess);
                    Storage.saveToFile();
                    break;
                case "delete":
                    int taskNumberDeleted = Integer.parseInt(splitTaskInput[1]);
                    taskNumberDeleted--;
                    Task.deleteTask(taskNumberDeleted);
                    UI.taskDeleted();
                    Storage.saveToFile();
                    break;
                case "deadline":
                    taskDescription = findTaskDescription(splitTaskInput);
                    if (taskDescription[0].isEmpty()) {
                        System.out.println("I think you missed out the deadline!");
                    }
                    else {
                        if (taskDescription[1].isEmpty()){
                            System.out.println("Please add a date!");
                        }
                        else {
                            Task.addNewTask(new Deadline(taskDescription[0], taskDescription[1]));
                            UI.taskAdded();
                        }
                    }
                    Storage.saveToFile();
                    break;
                case "event":
                    taskDescription = findTaskDescription(splitTaskInput);
                    if (taskDescription[0].isEmpty()) {
                        System.out.println("I think you missed out the deadline!");
                    }
                    else {
                        if (taskDescription[1].isEmpty()) {
                            System.out.println("Please add a date!");
                        } else {
                            Task.addNewTask(new Event(taskDescription[0], taskDescription[1]));
                            UI.taskAdded();
                        }
                    }
                    Storage.saveToFile();
                    break;
                case "todo":
                    taskDescription = findTaskDescription(splitTaskInput);
                    if (taskDescription[0].length()==0) {
                        System.out.println("I think you missed out your task!");
                    }
                    else{
                        Task.addNewTask(new Todo(taskDescription[0]));
                        UI.taskAdded();
                    }
                    Storage.saveToFile();
                    break;
                case "list":
                    if (Task.getTaskNumber()>0) {
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < Task.getTaskNumber(); i++) {
                            int displayedTask = i + 1;
                            System.out.println(displayedTask + ": " + Task.getTaskList().get(i));
                        }
                    }
                    else{System.out.println("Your list is empty!");}
                    break;
                case "find":
                    taskDescription = findTaskDescription(splitTaskInput);
                    String query = taskDescription[0];
                    System.out.println("We found these tasks matching the query: " + query);
                    Task.find(query);
                    break;
                default:
                    System.out.println("Huh? What do you mean? Please input a valid command word and task.");
                    break;
            }
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid input, please try again :-)");
        }
        catch (IOException e){
            System.out.println("Something went wrong with the file!");
        }
    }

    /**
     * This splits up the input the user provides to the console
     * @param splitTaskInput is the string input
     * @return an array, that is split into the description and date if any
     */
    public static String[] findTaskDescription(String[] splitTaskInput){
        String[] taskDescription = new String[2];
        int slashIndex=1;
        StringBuilder description = new StringBuilder();
        StringBuilder deadline = new StringBuilder();
        String space = "";
        for (int i = 1; i<splitTaskInput.length;i++){
            if(splitTaskInput[i].equals("/")) {
                slashIndex++;
                break;
            }
            else {
                description.append(space).append(splitTaskInput[i]);
                space = " ";
                slashIndex++;
            }
        }
        taskDescription[0]=description.toString();

        space = "";
        for (int j = slashIndex; j<splitTaskInput.length;j++){
            deadline.append(space).append(splitTaskInput[j]);
            space = " ";
        }
        taskDescription[1]=deadline.toString();

        return taskDescription;
    }


}
