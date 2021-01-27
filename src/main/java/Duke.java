import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String lineLowerCase;
        String line;

        Scanner in = new Scanner(System.in);

        TaskManager taskManager = new TaskManager();

        System.out.println("Hello there! This is\n" + logo + "How may I help you ?\n");

        do {
            line = in.nextLine();
            lineLowerCase = line.toLowerCase();
            int donePosition = line.indexOf("done");

            if (lineLowerCase.equals("bye")) {
                taskManager.endTaskManager();
            } else if (lineLowerCase.equals("list")) {
                taskManager.listTasks();
            } else if (donePosition > -1) {
                String[] words = line.split(" ");
                int taskNumber = Integer.parseInt(words[1]);
                taskManager.markAsDone(taskNumber);
            } else {
                taskManager.addTask(line);
            }
        } while (!lineLowerCase.equals("bye"));
    }
}
