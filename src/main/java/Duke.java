import  java.util.Scanner;

public class Duke {

    static final int MAX_NO_OF_TASKS = 100;

    public static void main(String[] args) {
        showWelcomeMessage();

        Task[] userTasks = new Task[MAX_NO_OF_TASKS];
        int taskCounter = 0;
        boolean exitLoopStatus = false;
        Scanner inputCommand = new Scanner(System.in);
        String userInput;

        while(true){
            userInput = inputCommand.nextLine();
            String[] individualWords = userInput.split(" ", 2);
            switch(individualWords[0].toLowerCase()){
            case "list":
                displayListOfActivities(userTasks, taskCounter);
                break;
            case "todo":
                taskCounter = createTodoTask(userTasks, taskCounter, individualWords);
                break;
            case "deadline":
                taskCounter = createDeadlineTask(userTasks, taskCounter, individualWords);
                break;
            case "event":
                taskCounter = createEventTask(userTasks, taskCounter, individualWords);
                break;
            case "done":
                markActivityAsDone(userTasks, individualWords);
                break;
            case "bye":
                exitLoopStatus = terminateProgram();
                break;
            default:
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            if (checkLoopStatus(exitLoopStatus)) {
                break;
            }
        }
    }

    private static boolean checkLoopStatus(boolean exitLoopStatus) {
        if(exitLoopStatus){
            return true;
        }
        return false;
    }

    private static boolean terminateProgram() {
        boolean exitLoopStatus;
        System.out.println("Bye. Hope to see you again soon!");
        exitLoopStatus = true;
        return exitLoopStatus;
    }

    private static void markActivityAsDone(Task[] userTasks, String[] individualWords) {
        int activityNumber;
        try {
            activityNumber = Integer.parseInt(individualWords[1]);
            userTasks[activityNumber-1].setTaskStatus(true);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(userTasks[activityNumber-1].toString());
        } catch (NumberFormatException e) {
            System.out.println("Invalid task number");
        }
    }

    private static int createEventTask(Task[] userTasks, int taskCounter, String[] individualWords) {
        String newUserTask;
        try {
            newUserTask = individualWords[1].split("/at")[0];
            String eventTime = individualWords[1].split("/at")[1];
            userTasks[taskCounter] = new Event(newUserTask, eventTime);
            taskCounter = showTaskCreationMessage(taskCounter, userTasks[taskCounter]);
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("☹ OOPS!!! The description or date of a event cannot be empty.");
        }
        return taskCounter;
    }

    private static int createDeadlineTask(Task[] userTasks, int taskCounter, String[] individualWords) {
        String newUserTask;
        try {
            newUserTask = individualWords[1].split("/by")[0];
            String date = individualWords[1].split("/by")[1];
            userTasks[taskCounter] = new Deadline(newUserTask, date);
            taskCounter = showTaskCreationMessage(taskCounter, userTasks[taskCounter]);
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("☹ OOPS!!! The description or date of a deadline cannot be empty.");
        }
        return taskCounter;
    }

    private static int createTodoTask(Task[] userTasks, int taskCounter, String[] individualWords) {
        String newUserTask;
        try {
            newUserTask = individualWords[1];
            userTasks[taskCounter] = new Todo(newUserTask);
            taskCounter = showTaskCreationMessage(taskCounter, userTasks[taskCounter]);
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        return taskCounter;
    }

    private static void displayListOfActivities(Task[] userTasks, int taskCounter) {
        System.out.println("Here are the tasks in your list:");
        for (int counter = 0; counter < taskCounter; counter++) {
            System.out.println((counter+1) + "." + userTasks[counter].toString());
        }
    }

    private static void showWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    private static int showTaskCreationMessage(int taskCounter, Task userTask) {
        System.out.println("Got it. I've added this task: ");
        System.out.println(userTask.toString());
        taskCounter++;
        System.out.println("Now you have " + taskCounter + " tasks in the list. ");
        return taskCounter;
    }
}
