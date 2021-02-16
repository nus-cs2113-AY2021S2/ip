import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String SAVE_FILE_PATH = "saveFile.txt";
    private static final File SAVE_FILE = new File(SAVE_FILE_PATH);
    private static final String DIVIDER = "____________________________________________________________";
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final ArrayList<Task> STORED_TASKS = new ArrayList<Task>();

    public static void main(String[] args) {
        loadDataFromFile();
        welcomeMsg();

        String command;
        do {
            command = getCommandFromUser();
            handleCommand(command);
        } while (!command.equals("bye"));

        SCANNER.close();
    }

    public static void loadDataFromFile() {
        try {
            Scanner saveFileScanner = new Scanner(SAVE_FILE);
            while (saveFileScanner.hasNextLine()) {
                String[] taskInfo = saveFileScanner.nextLine().split(" \\| ");
                Task currentTask;
                if (taskInfo[0].equals("T")) {
                    currentTask = new Todo(taskInfo[2]);
                    if (taskInfo[1].equals("1")) {
                        currentTask.markAsDone();
                    }
                } else if (taskInfo[0].equals("D")) {
                    currentTask = new Deadline(taskInfo[2], taskInfo[3]);
                    if (taskInfo[1].equals("1")) {
                        currentTask.markAsDone();
                    }
                } else {
                    currentTask = new Event(taskInfo[2], taskInfo[3]);
                    if (taskInfo[1].equals("1")) {
                        currentTask.markAsDone();
                    }
                }
                STORED_TASKS.add(currentTask);
            }
            saveFileScanner.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(DIVIDER);
            System.out.println("oops, something went wrong...");
            System.out.println(DIVIDER);
        }
    }

    public static void saveDataToFile() {
        try {
            FileWriter saveFileWriter = new FileWriter(SAVE_FILE);
            for (int i = 0; i < STORED_TASKS.size(); i++) {
                Task currentTask = STORED_TASKS.get(i);
                System.out.println(currentTask.getSaveString());
                saveFileWriter.write(currentTask.getSaveString() + "\n");
            }
            saveFileWriter.flush();
            saveFileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(DIVIDER);
            System.out.println("oops, something went wrong...");
            System.out.println(DIVIDER);
        }
    }

    public static void welcomeMsg() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(DIVIDER);
        System.out.println(logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(DIVIDER);
    }

    public static String getCommandFromUser() {
        return SCANNER.nextLine().strip().toLowerCase();
    }

    public static void handleCommand(String command) {
        try {
            if (command.startsWith("todo")) {
                storeTodoTask(command);
            } else if (command.startsWith("deadline")) {
                storeDeadlineTask(command);
            } else if (command.startsWith("event")) {
                storeEventTask(command);
            } else if (command.equals("list")) {
                displayStoredTasks();
            } else if (command.startsWith("done")) {
                markTaskAsDone(command);
            } else if (command.startsWith("delete")) {
                deleteTask(command);
            } else if (command.equals("bye")) {
                exitMsg();
            } else {
                invalidCommand();
            }
        } catch (DukeException e) {
            errorMsg(e);
        }
    }

    public static void storeTodoTask(String command) throws DukeException {
        if (command.equals("todo")) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }

        if (!command.startsWith("todo ")) {
            throw new DukeException("☹ OOPS!!! The correct syntax for todo command is: 'todo task_description'");
        }

        String description = command.substring(5).strip();
        storeTask(new Todo(description));
    }

    public static void storeDeadlineTask(String command) throws DukeException {
        if (command.equals("deadline")) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }

        if (!command.startsWith("deadline ")) {
            throw new DukeException("☹ OOPS!!! The correct syntax for deadline command is: " +
                    "'deadline task_description /by due_date'");
        }

        String request = command.substring(9).strip();
        int indexOfBy = request.indexOf(" /by ");
        if (indexOfBy == -1) {
            throw new DukeException("☹ OOPS!!! The correct syntax for deadline command is: " +
                    "'deadline task_description /by due_date'");
        }

        String description = request.substring(0, indexOfBy).strip();
        String by = request.substring(indexOfBy + 4).strip();
        storeTask(new Deadline(description, by));
    }

    public static void storeEventTask(String command) throws DukeException {
        if (command.equals("event")) {
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
        }

        if (!command.startsWith("event ")) {
            throw new DukeException("☹ OOPS!!! The correct syntax for event command is: " +
                    "'event task_description /at timeframe'");
        }

        String request = command.substring(6).strip();
        int indexOfAt = request.indexOf(" /at ");
        if (indexOfAt == -1) {
            throw new DukeException("☹ OOPS!!! The correct syntax for event command is: " +
                    "'event task_description /at timeframe'");
        }

        String description = request.substring(0, indexOfAt).strip();
        String at = request.substring(indexOfAt + 4).strip();
        storeTask(new Event(description, at));
    }

    public static void storeTask(Task taskToStore) {
        STORED_TASKS.add(taskToStore);
        saveDataToFile();
        System.out.println(DIVIDER);
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + taskToStore);
        System.out.println(" Now you have " + STORED_TASKS.size() + " tasks in the list.");
        System.out.println(DIVIDER);
    }

    public static void displayStoredTasks() {
        if (STORED_TASKS.size() == 0) {
            System.out.println(DIVIDER);
            System.out.println(" You have no tasks in your list! :)");
            System.out.println(DIVIDER);
            return;
        }

        System.out.println(DIVIDER);
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < STORED_TASKS.size(); i++) {
            Task currentTask = STORED_TASKS.get(i);
            System.out.printf(" %d.%s\n", (i + 1), currentTask);
        }
        System.out.println(DIVIDER);
    }

    public static void markTaskAsDone(String command) throws DukeException {
        if (command.equals("done")) {
            throw new DukeException("☹ OOPS!!! The task number of a done command cannot be empty.");
        }

        if (!command.startsWith("done ")) {
            throw new DukeException("☹ OOPS!!! The correct syntax for done command is: 'done task_number'");
        }

        String taskNumber = command.substring(5).strip();
        int indexOfTaskToMark;
        try {
            indexOfTaskToMark = Integer.parseInt(taskNumber) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("☹ OOPS!!! The correct syntax for done command is: 'done task_number'");
        }

        if (indexOfTaskToMark >= STORED_TASKS.size() || indexOfTaskToMark < 0) {
            throw new DukeException("☹ OOPS!!! You entered an invalid task number!");
        }

        Task taskToMark = STORED_TASKS.get(indexOfTaskToMark);
        taskToMark.markAsDone();
        saveDataToFile();
        System.out.println(DIVIDER);
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + taskToMark);
        System.out.println(DIVIDER);
    }

    public static void deleteTask(String command) throws DukeException {
        if (command.equals("delete")) {
            throw new DukeException("☹ OOPS!!! The task number of a delete command cannot be empty.");
        }

        if (!command.startsWith("delete ")) {
            throw new DukeException("☹ OOPS!!! The correct syntax for delete command is: 'delete task_number'");
        }

        String taskNumber = command.substring(7).strip();
        int indexOfTaskToDelete;
        try {
            indexOfTaskToDelete = Integer.parseInt(taskNumber) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("☹ OOPS!!! The correct syntax for delete command is: 'delete task_number'");
        }

        if (indexOfTaskToDelete >= STORED_TASKS.size() || indexOfTaskToDelete < 0) {
            throw new DukeException("☹ OOPS!!! You entered an invalid task number!");
        }

        Task deletedTask = STORED_TASKS.remove(indexOfTaskToDelete);
        System.out.println(DIVIDER);
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + deletedTask);
        System.out.println((STORED_TASKS.size() == 0) ? " Now you have no more tasks left in the list! :)" :
                " Now you have " + STORED_TASKS.size() + " tasks in the list.");
        System.out.println(DIVIDER);
    }

    public static void exitMsg() {
        System.out.println(DIVIDER);
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(DIVIDER);
    }

    public static void invalidCommand() throws DukeException {
        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public static void errorMsg(DukeException e) {
        System.out.println(DIVIDER);
        System.out.println(" " + e.getMessage());
        System.out.println(DIVIDER);
    }
}
