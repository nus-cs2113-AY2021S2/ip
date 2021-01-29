import java.util.Arrays;
import  java.util.Scanner;

public class Duke {

    static final int MAX_NO_OF_TASKS = 100;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        Task[] userTasks = new Task[MAX_NO_OF_TASKS];
        int taskCounter = 0;
        boolean breakLoopStatus = false;
        Scanner in = new Scanner(System.in);
        String userInput = null;
        String newUserTask = null;

        while(true){
            userInput = in.nextLine();
            String[] individualWords = userInput.split(" ", 2);
            switch(individualWords[0].toLowerCase()){
            case "list":
                System.out.println("Here are the tasks in your list:");
                for (int counter = 0; counter < taskCounter; counter++) {
                    System.out.println((counter+1) + "." + userTasks[counter].toString());
                }
                break;
            case "todo":
                newUserTask = individualWords[1];
                userTasks[taskCounter] = new Todo(newUserTask);
                taskCounter = showTaskCreationMessage(taskCounter, userTasks[taskCounter]);
                break;
            case "deadline":
                newUserTask = individualWords[1].split("/by")[0];
                String date = individualWords[1].split("/by")[1];
                userTasks[taskCounter] = new Deadline(newUserTask, date);
                taskCounter = showTaskCreationMessage(taskCounter, userTasks[taskCounter]);
                break;
            case "event":
                newUserTask = individualWords[1].split("/at")[0];
                String eventTime = individualWords[1].split("/at")[1];
                userTasks[taskCounter] = new Event(newUserTask,eventTime);
                taskCounter = showTaskCreationMessage(taskCounter, userTasks[taskCounter]);
                break;
            case "done":
                int activityNumber=-1;
                try {
                    activityNumber = Integer.parseInt(individualWords[1]);
                    userTasks[activityNumber-1].setTaskStatus(true);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(userTasks[activityNumber-1].toString());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid task number");
                }
                break;
            case "bye":
                System.out.println("Bye. Hope to see you again soon!");
                breakLoopStatus = true;
                break;
            default:
                System.out.println("Oops. Unknown command. Try again");
            }
            if(breakLoopStatus){
                break;
            }
        }


        /*
        while(!userInput.equals("bye")){
            String[] individualWords = userInput.trim().split("\\s+");
            if(userInput.equals("list")){
                System.out.println("Here are the tasks in your list:");
                for (int counter = 0; counter < taskCounter; counter++) {
                    System.out.println((counter+1) + ".["+ userTasks[counter].getStatusIcon()
                            +"] " + userTasks[counter].getDescription());
                }
            } else {
                String[] individualWords = userInput.trim().split("\\s+");
                int activityNumber=-1;
                if(individualWords.length==2 && individualWords[0].equals("done")){
                    System.out.println("yo1:");
                    try {
                        activityNumber = Integer.parseInt(individualWords[1]);
                        userTasks[activityNumber-1].setTaskStatus(true);
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println("   ["+ userTasks[activityNumber-1].getStatusIcon() +"] "
                                + userTasks[activityNumber-1].getDescription());
                    } catch (NumberFormatException e) {
                        System.out.println("added: " + userInput);
                        userTasks[taskCounter] = new Task(userInput);
                        taskCounter++;
                    }
                } else {
                    System.out.println("added: " + userInput);
                    userTasks[taskCounter] = new Task(userInput);
                    taskCounter++;
                }
            }
            userInput = in.nextLine();
        }
         */
    }

    private static int showTaskCreationMessage(int taskCounter, Task userTask) {
        System.out.println("Got it. I've added this task: ");
        System.out.println(userTask.toString());
        taskCounter++;
        System.out.println("Now you have " + taskCounter + " tasks in the list. ");
        return taskCounter;
    }
}
