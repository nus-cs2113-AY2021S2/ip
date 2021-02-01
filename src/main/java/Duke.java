import java.util.Scanner;

public class Duke {

    public static void printTask(Task[] taskList, int taskCount){
        System.out.println("**********************************************************");
        if(taskCount>0){
            System.out.println("Here are the tasks in your list: ");
            for(int i=0; i<taskCount; ++i){
                System.out.println(i+1 + ".[" + taskList[i].getStatusIcon() + "] " + taskList[i].description);
            }
        }else{
            System.out.println("You have not entered any tasks at the moment! :)");
        }
        System.out.println("**********************************************************");

    }

    public static void main(String[] args) {
        welcomeMessage();

        String commandInput;
        Task[] taskList = new Task[100];
        int taskCount = 0;
        Scanner in = new Scanner(System.in);
        commandInput = in.nextLine();

        while (!commandInput.equals("bye") ) {
            if(commandInput.equals("list")){
                printTask(taskList, taskCount);
            }else if(commandInput.startsWith("done")){
                int taskNumber = Integer.parseInt(commandInput.substring(5,6));
                taskList[taskNumber-1].doneTask();
            }else{
                taskList[taskCount] = new Task(commandInput);
                taskCount++;
            }
            commandInput = in.nextLine();
        }

        exitMessage();
    }

    private static void exitMessage() {
        System.out.println("#########################################################");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("#########################################################");
    }

    private static void welcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String startText = "____________________________________________________________\n"
                + "Hello! I'm Duke\n"
                + "What can I do for you?\n"
                + "____________________________________________________________\n";
        System.out.println(startText);
    }
}
