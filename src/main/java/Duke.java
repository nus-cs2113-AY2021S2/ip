import java.util.Scanner;

public class Duke {

    public static void printTask(Task[] taskList, int taskCount){
        if(taskCount>0){
            System.out.println("**********************************************************");
            System.out.println("Here are the tasks in your list: ");
            for(int i=0; i<taskCount; ++i){
                System.out.println(i+1 + ".[" + taskList[i].getStatusIcon() + "] " + taskList[i].description);
            }
            System.out.println("**********************************************************");
        }else{
            System.out.println("**********************************************************");
            System.out.println("You have not entered any tasks at the moment! :)");
            System.out.println("**********************************************************");
        }

    }

    public static void main(String[] args) {
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

        String input;
        Task[] taskList = new Task[100];
        int taskCount = 0;
        Scanner in = new Scanner(System.in);
        input = in.nextLine();

        while (!input.equals("bye") ) {
            if(input.equals("list")){
                printTask(taskList, taskCount);
            }else if(input.startsWith("done")){
                taskList[(Integer.parseInt(input.substring(5,6)))-1].doneTask();
            }else{
                taskList[taskCount] = new Task(input);
                taskCount++;
            }
            input = in.nextLine();
        }
        System.out.println("#########################################################");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("#########################################################");
    }
}
