import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Menu.greet();
        Task[] tasks = new Task[100];

        Scanner in = new Scanner(System.in);
        String userInput;
        userInput = in.nextLine();
        while (!userInput.equals("bye")) {
            if (userInput.startsWith("list")) {
                handleList(tasks);
            } else if (userInput.startsWith("done")) {
                handleDone(userInput, tasks);
            } else {
                handleAdd(userInput, tasks);
            }
            userInput = in.nextLine();
        }
        Menu.bye();
    }

    public static void handleList(Task[] tasks) {
        String outputText;
        outputText = "Here are the tasks in your list: ";
        for (int i = 0; i < Task.getNumberOfTasks(); i++) {
            String taskNumber = (i + 1) + ".";
            outputText += System.lineSeparator() +
                    " " +
                    taskNumber + " " +
                    tasks[i].toString();
        }
        Menu.echo(outputText);
    }

    public static void handleDone(String userInput, Task[] tasks) {
        String outputText;
        String[] words = userInput.split(" ");
        if (words.length != 2) {
            outputText = "You did not input valid task number to be marked as done!";
        } else {
            int taskNumber = Integer.parseInt(words[1]);
            if (taskNumber < 1 || taskNumber > Task.getNumberOfTasks()) {
                outputText = "Task number is invalid!";
            } else {
                Task task = tasks[taskNumber - 1];
                if (!task.isDone) {
                    task.setDone(true);
                    outputText = "Nice! I've marked this task as done:" +
                            System.lineSeparator() +
                            "\t" +
                            task.toString();
                } else {
                    outputText = "Task already marked as done!";
                }
            }
        }
        Menu.echo(outputText);
    }

    public static void handleAdd(String userInput, Task[] tasks) {
        String outputText = " added: ";
        Task t = new Task(userInput);
        tasks[Task.getNumberOfTasks() - 1] = t;
        outputText += userInput;
        Menu.echo(outputText);
    }
}
