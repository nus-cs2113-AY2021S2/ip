import java.util.Scanner;

public class Duke {

    private static Task[] tasks = new Task[100];
    private static Scanner sc = new Scanner(System.in);

    public static void listTasks() {
        if (Task.totalTasks == 0) {
            System.out.println("No tasks yet!");
        } else {
            for (int i = 0; i < Task.totalTasks; i++) {
                System.out.printf("%d.[%s] %s\n", i+1, tasks[i].getStatusIcon(), tasks[i].getDescription());
            }
        }
    }

    public static void markTasksAsDone(String input) {
        String[] inputArray = input.split(" ");

        //completedIndex holds the index of valid integer(s) in inputArray (indicating index in tasklist)
        int completedIndex;
        for (String word: inputArray) {
            if (word.equals("done")) {
                continue;
            } else {
                completedIndex = Integer.parseInt(word);
                //ensure that the index given is valid
                if (completedIndex > 0 && completedIndex <= Task.totalTasks){
                    tasks[completedIndex - 1].markAsDone();
                } else {
                    System.out.printf("Task %d does not exist! Enter 'list' to view tasklist :)\n", completedIndex);
                }
            }
        }
    }

    public static void addTask(String input) {
        tasks[Task.totalTasks] = new Task(input);
        System.out.println("added: "+ input);
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I am\n" + logo);

        System.out.println("What can I do for you today?");

        String input = sc.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                listTasks();
            } else if (input.contains("done")) {
                markTasksAsDone(input);
            } else {
                addTask(input);
            }
            input = sc.nextLine();
        }

        System.out.println("Goodbye！See you again soon :)");
    }
}
