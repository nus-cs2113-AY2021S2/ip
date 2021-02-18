public class Command {
    public static void findCommand(String taskInput){
        String[] splitTaskInput = taskInput.toLowerCase().split(" ");
        String[] taskDescription;
        switch (splitTaskInput[0]){
            case "done":
                int taskNumberCompleted = Integer.parseInt(splitTaskInput[1]);
                taskNumberCompleted--;
                Task.completeTask(taskNumberCompleted);
                UI.taskCompleted();
                break;
            case "delete":
                int taskNumberDeleted = Integer.parseInt(splitTaskInput[1]);
                taskNumberDeleted--;
                Task.deleteTask(taskNumberDeleted);
                UI.taskDeleted();
                break;
            case "deadline":
                taskDescription = findTaskDescription(splitTaskInput);
                Task.addNewTask(new Deadline(taskDescription[0],taskDescription[1]));
                UI.taskAdded();
                break;
            case "event":
                taskDescription = findTaskDescription(splitTaskInput);
                Task.addNewTask(new Event(taskDescription[0],taskDescription[1]));
                UI.taskAdded();
                break;
            case "todo":
                taskDescription = findTaskDescription(splitTaskInput);
                if (taskDescription[0].length()==0) {
                    System.out.println("I think you missed out your task!");
                    System.out.println(taskDescription[0]);
                }
                else{
                    Task.addNewTask(new Todo(taskDescription[0]));
                    UI.taskAdded();
                }
                break;
            case "list":
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < Task.getTaskNumber(); i++){
                    int displayedTask = i + 1;
                    System.out.println(displayedTask + ": " + Task.getTaskList().get(i));
                }
                break;
            default:
                System.out.println("Huh? What do you mean? Please input a valid command word and task.");
                break;
        }
    }

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
