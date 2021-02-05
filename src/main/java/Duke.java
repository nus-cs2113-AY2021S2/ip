import java.util.Scanner;

public class Duke {

    private static Task[] tasks = new Task[100];
    private static Scanner sc = new Scanner(System.in);

    public static void listTasks() {
        if (Task.totalTasks == 0) {
            System.out.println("No tasks yet!");
        } else {
            for (int i = 0; i < Task.totalTasks; i++) {
                System.out.printf("%d.%s\n", i+1, tasks[i].toString());
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

    public static String[] extractDetailsFromInput(String input, String keyword) {
        String[] inputArray = new String[2];
        String inputWithoutKeyword = input.split(keyword)[1];
        int numDetails = 0;
        switch(keyword) {
        case "deadline":
            inputArray = inputWithoutKeyword.split("/by");
            numDetails += 2;
            break;
        case "event":
            inputArray = inputWithoutKeyword.split("/at");
            numDetails += 2;
            break;
        case "todo":
            inputArray[0] = inputWithoutKeyword;
            numDetails++;
            break;
        default:
            break;
        }

        for (int i = 0; i < numDetails; i++) {
            inputArray[i] = inputArray[i].strip();
        }
        return inputArray;
    }

    public static void addTask(String input) {
        String[] inputArray;
        if (input.contains("deadline")) {
            inputArray = extractDetailsFromInput(input, "deadline");
            tasks[Task.totalTasks] = new Deadline(inputArray[0], inputArray[1]);
        } else if (input.contains("event")) {
            inputArray = extractDetailsFromInput(input, "event");
            tasks[Task.totalTasks] = new Event(inputArray[0], inputArray[1]);
        } else if (input.contains("todo")){
            inputArray = extractDetailsFromInput(input, "todo");
            tasks[Task.totalTasks] = new Todo(inputArray[0]);
        }

        System.out.println("I have added this task:" );
        System.out.println(tasks[Task.totalTasks-1].toString());
        System.out.println("You now have " + Task.totalTasks + " tasks in your tasklist.");
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

        System.out.println("Goodbye. See you again soon :)");
    }
}
