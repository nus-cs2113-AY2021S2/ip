import java.util.Scanner;

public class Duke {
    public static void lineBreak(){
        String horizontalLine = "____________________________________________________________";
        System.out.println(horizontalLine);
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        //Level 0: Greet
        lineBreak();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        lineBreak();
        //Level 3: Mark as Done
        Scanner in = new Scanner(System.in);
        String userInput;
        Task[] taskList = new Task[100];
        int listCount = 0;
        while (true){
            userInput = in.nextLine();
            if (userInput.equals("list")){
                lineBreak();
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < listCount; i++){
                    Task currentTask = taskList[i];
                    System.out.println(i+1 + ".[" + currentTask.getStatusIcon() + "] " + currentTask.description);
                }
                lineBreak();
                continue;
            }

            if (userInput.startsWith("done")){
                String[] words = userInput.split(" ");
                Task currentTask = taskList[Integer.parseInt(words[1]) - 1];
                currentTask.markAsDone();
                lineBreak();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  [" + currentTask.getStatusIcon() + "] " + currentTask.description);
                lineBreak();
                continue;
            }

            if (userInput.equals("bye")){
                break;
            }
            lineBreak();
            taskList[listCount++] = new Task(userInput);
            System.out.println("added: " + userInput);
            lineBreak();
        }

        lineBreak();
        System.out.println("Bye. Hope to see you again soon!");
        lineBreak();
    }
}
