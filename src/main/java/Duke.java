import duke.exception.DukeException;
import duke.exception.TaskType;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
                    System.out.printf("duke.task.Task %d does not exist! Enter 'list' to view tasklist :)\n", completedIndex);
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

    public static void addTask(String input) throws DukeException {
        String[] inputArray;
        if (input.contains("deadline")) {
            try {
                inputArray = extractDetailsFromInput(input, "deadline");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException(TaskType.DEADLINE);
            }
            tasks[Task.totalTasks] = new Deadline(inputArray[0], inputArray[1]);
        } else if (input.contains("event")) {
            try {
                inputArray = extractDetailsFromInput(input, "event");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException(TaskType.EVENT);
            }
            tasks[Task.totalTasks] = new Event(inputArray[0], inputArray[1]);
        } else if (input.contains("todo")){
            try {
                inputArray = extractDetailsFromInput(input, "todo");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException(TaskType.TODO);
            }
            tasks[Task.totalTasks] = new Todo(inputArray[0]);
        } else {
            throw new DukeException(TaskType.INVALID);
        }

        System.out.println("I have added this task:" );
        System.out.println(tasks[Task.totalTasks-1].toString());
        System.out.println("You now have " + Task.totalTasks + " tasks in your tasklist.");
        writeTasklistToFile();
    }

    private static void writeTasklistToFile() {
        try {
            String filePath = "data/duke.txt";
            // check if the directory and file exists
            if (Files.notExists(Paths.get("data/"))) {
                Files.createDirectory(Paths.get("data/"));
            } else if (Files.notExists(Paths.get("data/duke.txt"))) {
                Files.createFile(Paths.get("data/duke.txt"));
            }

            FileWriter fw = new FileWriter(filePath);
            //Task task : tasks for arraylist
            for (int i = 0; i < Task.totalTasks; i++) {
                fw.write(tasks[i].toString() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Exception occurred when writing tasklist to file! :(");
        }

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
                writeTasklistToFile();
            } else {
                try {
                    addTask(input);
                } catch (DukeException e) {
                    TaskType taskType = e.getTaskType();
                    switch (taskType) {
                    case DEADLINE:
                        System.out.println("Please input deadline tasks in the correct format. (e.g deadline <task description> /by <deadline>)");
                        break;
                    case EVENT:
                        System.out.println("Please input event tasks in the correct format. (e.g. event <task description> /at <timing>)");
                        break;
                    case TODO:
                        System.out.println("Please input todo tasks in the correct format. (e.g. todo <task description>)");
                        break;
                    case INVALID:
                        System.out.println("Please input a valid command! (e.g. deadline..., done..., list)");
                    }
                }
            }
            input = sc.nextLine();
        }

        System.out.println("Goodbye. See you again soon :)");
    }
}
