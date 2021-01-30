import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String userCommand;
        String userCommandLowerCase;

        Scanner userInput = new Scanner(System.in);

        TaskManager taskManager = new TaskManager();

        System.out.println("Hello there! This is\n" + logo + "How may I help you ?\n");

        do {
            userCommand = userInput.nextLine();
            userCommandLowerCase = userCommand.toLowerCase();
            int donePosition = userCommand.indexOf("done");

            if (userCommandLowerCase.equals("bye")) {
                taskManager.endTaskManager();
            } else if (userCommandLowerCase.equals("list")) {
                taskManager.listTasks();
            } else if (donePosition > -1) {
                String[] words = userCommand.split(" ");
                if (words.length > 1) {
                    int taskNumber = Integer.parseInt(words[1]);
                    taskManager.markAsDone(taskNumber);
                } else {
                    System.out.print("\t____________________________________________________________\n");
                    System.out.print("\tInvalid command. Please enter another task number\n");
                    System.out.print("\t____________________________________________________________\n");
                }
            } else {
                taskManager.addTask(userCommand);
            }
        } while (!userCommandLowerCase.equals("bye"));
    }
}
