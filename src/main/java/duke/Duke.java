package duke;

import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;

public class Duke {

    public static ArrayList<Task> taskList = new ArrayList<>();

    public static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        greet();
        loadFile();
        while (true) {
            String userInput = SCANNER.nextLine();
            executeCommand(userInput);
        }
    }

    private static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        lineBreak();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        lineBreak();
    }

    public static void lineBreak() {
        final String HORIZONTAL_LINE = "____________________________________________________________";
        System.out.println(HORIZONTAL_LINE);
    }

    public static void loadFile() {
        File f = new File ("duke.txt");
        Scanner s;
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.println("Data file does not exist");
            lineBreak();
            return;
        }
        System.out.println("Loading saved data");
        lineBreak();
        while (s.hasNext()) {
            String line = s.nextLine();
            int dividerPosition;
            char taskType = line.charAt(1);
            switch (taskType) {
            case 'T':
                taskList.add(new Todo(line.substring(7)));
                break;
            case 'D':
                dividerPosition = line.indexOf("(by:");
                taskList.add(new Deadline(line.substring(7, dividerPosition - 1),
                        line.substring(dividerPosition + 5, line.length() - 1)));
                break;
            case 'E':
                dividerPosition = line.indexOf("(at:");
                taskList.add(new Event(line.substring(7, dividerPosition - 1),
                        line.substring(dividerPosition + 5, line.length() - 1)));
                break;
            default:
                System.out.println("Unknown task type found: " + taskType);
            }
            if (line.charAt(4) == '\u2713') {
                taskList.get(taskList.size() - 1).setDone();
            }
        }
    }

    public static void executeCommand(String userInput) {
        String[] words = userInput.split(" ", 2);
        DukeCommand commandWord;
        try {
            commandWord = DukeCommand.valueOf(words[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            lineBreak();
            System.out.println("I'm sorry, but I don't know what that means.");
            lineBreak();
            return;
        }
        lineBreak();
        switch (commandWord) {
        case LIST:
            listTasks();
            break;
        case DONE:
            try {
                updateTask(Integer.parseInt(words[1]) - 1);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Please enter the index of the task that is done.");
            } catch (NumberFormatException e) {
                System.out.println("You must enter an integer after done.");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Task " + words[1] + " does not exist.");
                System.out.println("There are " + taskList.size() + " tasks in the list.");
            }
            break;
        case TODO:
            //Fallthrough
        case DEADLINE:
            //Fallthrough
        case EVENT:
            try {
                String commandDescription = words[1];
                addTask(commandWord, commandDescription);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("The description of " + commandWord + " cannot be empty.");
            } catch (DukeException e) {
                System.out.println("The format of " + commandWord + " is incorrect.");
            }
            break;
        case DELETE:
            try {
                deleteTask(Integer.parseInt(words[1]) - 1);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Please enter the index of the task to be deleted.");
            } catch (NumberFormatException e) {
                System.out.println("You must enter an integer after delete.");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Task " + words[1] + " does not exist.");
                System.out.println("There are " + taskList.size() + " tasks in the list.");
            }
            break;
        case BYE:
            exitProgram();
            //Fallthrough
        }
        lineBreak();
    }

    public static void listTasks() {
        if (taskList.size() == 0) {
            System.out.println("There are no tasks in your list");
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            Task currentTask = taskList.get(i);
            System.out.println(i + 1 + "." + currentTask.toString());
        }
    }

    public static void updateTask(int taskIndex) {
        Task currentTask = taskList.get(taskIndex);
        if (currentTask.getDone()) {
            System.out.println("This task has already been completed:");
        } else {
            currentTask.setDone();
            System.out.println("Nice! I've marked this task as done:");
        }
        System.out.println("  " + currentTask.toString());
        try {
            saveFile();
        } catch (IOException e) {
            System.out.println("Oops something went wrong with saving the file");
            e.printStackTrace();
        }
    }

    public static void addTask(DukeCommand taskType, String description) throws DukeException {
        int dividerPosition;
        switch (taskType) {
        case TODO:
            taskList.add(new Todo(description));
            break;
        case DEADLINE:
            if (!description.contains("/by")) {
                throw new DukeException();
            }
            dividerPosition = description.indexOf("/by");
            String by = description.substring(dividerPosition + 4);
            taskList.add(new Deadline(description.substring(0, dividerPosition - 1), by));
            break;
        case EVENT:
            if (!description.contains("/at")) {
                throw new DukeException();
            }
            dividerPosition = description.indexOf("/at");
            String at = description.substring(dividerPosition + 4);
            taskList.add(new Event(description.substring(0, dividerPosition - 1), at));
            break;
        }
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + taskList.get(taskList.size() - 1).toString());
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        try {
            saveFile();
        } catch (IOException e) {
            System.out.println("Oops something went wrong with saving the file");
            e.printStackTrace();
        }
    }

    public static void deleteTask(int taskIndex) {
        Task currentTask = taskList.get(taskIndex);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + currentTask);
        taskList.remove(taskIndex);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        try {
            saveFile();
        } catch (IOException e) {
            System.out.println("Oops something went wrong with saving the file");
            e.printStackTrace();
        }
    }

    private static void saveFile() throws IOException {
        File f = new File("duke.txt");
        if (f.createNewFile()) {
            System.out.println("File created: " + f.getName());
        }
        FileWriter fw = new FileWriter("duke.txt");

        for (Task task : taskList) {
            if (task != null) {
                fw.write(task.toString() + "\n");
            }
        }
        fw.close();
    }

    public static void exitProgram() {
        System.out.println("Bye. Hope to see you again soon!");
        lineBreak();
        System.exit(0);
    }
}
