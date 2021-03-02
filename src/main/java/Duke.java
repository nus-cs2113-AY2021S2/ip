import duke.exception.DukeException;
import duke.exception.TaskType;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Scanner;

public class Duke {

    private static ArrayList<Task> tasks = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void listTasks() {
        if (Task.totalTasks == 0) {
            System.out.println("No tasks yet!");
        } else {
            for (int i = 0; i < Task.totalTasks; i++) {
                System.out.printf("%d.%s\n", i+1, tasks.get(i).toString());
            }
        }
    }

    public static ArrayList<Integer> cleanInput(String input, String keyword) {
        String[] inputArray = input.split(" ");
        ArrayList<Integer> indexes = new ArrayList<>();

        //completedIndex holds the index of valid integer(s) in inputArray (indicating index in tasklist)
        int completedIndex;
        for (String word: inputArray) {
            if (word.equals(keyword)) {
                continue;
            } else {
                completedIndex = Integer.parseInt(word);
                //ensure that the index given is valid
                if (completedIndex > 0 && completedIndex <= Task.totalTasks){
//                    tasks.get(completedIndex - 1).markAsDone();
                    indexes.add(completedIndex-1);
                } else {
                    System.out.printf("Task %d does not exist! Enter 'list' to view tasklist :)\n", completedIndex);
                }
            }
        }

        return indexes;
    }

    public static void markTasksAsDone(ArrayList<Integer> indexes) {
        for (Integer index : indexes) {
            tasks.get(index).markAsDone();
        }
    }

    public static void printNumTasks() {
        System.out.println("You now have " + Task.totalTasks + " tasks in your tasklist.");
    }

    public static void deleteTasks(ArrayList<Integer> indexes) {
        for (Integer index : indexes) {
            System.out.println("Okay, I've deleted this task:");
            System.out.println(tasks.get(index).toString());
            Task.totalTasks -= 1;
            tasks.remove(index.intValue());
            printNumTasks();
        }
    }

    public static String[] extractDetailsFromInput(String input, String keyword) {
        String[] inputArray = new String[2];
        String inputWithoutKeyword = input.substring(keyword.length());
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
        if (input.startsWith("deadline")) {
            try {
                inputArray = extractDetailsFromInput(input, "deadline");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException(TaskType.DEADLINE);
            }
            tasks.add(Task.totalTasks, new Deadline(inputArray[0], inputArray[1]));
        } else if (input.startsWith("event")) {
            try {
                inputArray = extractDetailsFromInput(input, "event");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException(TaskType.EVENT);
            }
            tasks.add(Task.totalTasks, new Event(inputArray[0], inputArray[1]));
        } else if (input.startsWith("todo")){
            try {
                inputArray = extractDetailsFromInput(input, "todo");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException(TaskType.TODO);
            }
            tasks.add(Task.totalTasks, new Todo(inputArray[0]));
        } else {
            throw new DukeException(TaskType.INVALID);
        }

        System.out.println("I have added this task:" );
        System.out.println(tasks.get(Task.totalTasks-1).toString());
        printNumTasks();
        writeTasklistToFile();
    }

    private static void loadTasklist() {
        try {
            String filePath = "data/duke.txt";
            // check if the directory and file exists
            if (Files.exists(Paths.get("data/duke.txt"))) {
                File f = new File(filePath);
                Scanner sc = new Scanner(f);
                while (sc.hasNext()) {
                    String taskString = sc.nextLine();
                    if (taskString.startsWith("[E]")) {
                        String description = taskString.substring(7, taskString.indexOf('(')-1);
                        String timing = taskString.substring(taskString.indexOf('(') + 5, taskString.indexOf(')'));
                        tasks.add(Task.totalTasks, new Event(description, timing));
                    } else if (taskString.startsWith("[D]")) {
                        String description = taskString.substring(7, taskString.indexOf('(')-1);
                        String timing = taskString.substring(taskString.indexOf('(') + 5, taskString.indexOf(')'));
                        tasks.add(Task.totalTasks, new Deadline(description, timing));
                    } else if (taskString.startsWith("[T]")) {
                        String description = taskString.substring(7);
                        tasks.add(Task.totalTasks, new Todo(description));
                    }
                    if (taskString.contains("[X]")) {
                        tasks.get(Task.totalTasks - 1).markAsDone();
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Tasklist file not found! :(");
        }
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
            for (Task task : tasks) {
                fw.write(task.toString() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Exception occurred when writing tasklist to file! :(");
        }
    }

    public static void main(String[] args) {
        loadTasklist();
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
            } else if (input.startsWith("done")) {
                //this keeps track of indexes that user calls for actions on
                ArrayList<Integer> indexes = new ArrayList<>();
                indexes = cleanInput(input, "done");
                markTasksAsDone(indexes);
                writeTasklistToFile();
            } else if (input.startsWith("delete")) {
                //this keeps track of indexes that user calls for actions on
                ArrayList<Integer> indexes = new ArrayList<>();
                indexes = cleanInput(input, "delete");
                //sort indexes in descending order so deletion will not affect index
                Collections.sort(indexes, Collections.reverseOrder());
                deleteTasks(indexes);
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
