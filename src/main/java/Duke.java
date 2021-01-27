import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I am\n" + logo);

        System.out.println("What can I do for you today?");

        Scanner sc = new Scanner(System.in);

        Task[] tasks = new Task[100];

        String input = sc.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                if (Task.totalTasks == 0) {
                    System.out.println("No tasks yet!");
                } else {
                    for (int i = 0; i < Task.totalTasks; i++) {
                        System.out.printf("%d.[%s] %s\n", i+1, tasks[i].getStatusIcon(), tasks[i].getDescription());
                    }
                }

            } else if (input.contains("done")) {
                String[] inputArray = input.split(" ");
                for (String word: inputArray) {
                    if (word.equals("done")) {
                        continue;
                    } else {
                        int completedIndex = Integer.parseInt(word);
                        //ensure that the index given is valid
                        if (completedIndex > 0 && completedIndex <= Task.totalTasks){
                            tasks[completedIndex - 1].markAsDone();
                        } else {
                            System.out.printf("Task %d does not exist! Enter 'list' to view tasklist :)\n", completedIndex);
                        }

                    }
                }

            } else {
                tasks[Task.totalTasks] = new Task(input);
                System.out.println("added: "+ input);
            }
            input = sc.nextLine();
        }

        System.out.println("Goodbye！See you again soon :)");

    }
}
